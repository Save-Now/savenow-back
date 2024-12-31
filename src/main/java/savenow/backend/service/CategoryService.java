package savenow.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import savenow.backend.domain.category.Category;
import savenow.backend.domain.category.CategoryRepository;
import savenow.backend.domain.user.User;
import savenow.backend.domain.user.UserRepository;
import savenow.backend.domain.userCategory.UserCategory;
import savenow.backend.domain.userCategory.UserCategoryRepository;
import savenow.backend.handler.exception.CustomApiException;

import java.util.ArrayList;
import java.util.List;

import static savenow.backend.domain.category.Category.*;
import static savenow.backend.dto.category.CategoryReqDto.*;
import static savenow.backend.dto.category.CategoryResDto.*;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final UserCategoryRepository userCategoryRepository;

    @Transactional
    public CreateCategoryDto createCategory(Long userId, CreateCategoryReqDto request) {
        // 부모 카테고리 기본 설정
        Category parent = rootParent();

        // 유저 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("유저 정보가 존재하지 않습니다."));

        // 부모의 존재 여부 판별
        if (request.hasParent()) {
            parent = findParent(request.getParent());
        }

        // 카테고리 생성
        Category newCategory = categoryRepository.save(request.toEntity(parent));

        UserCategory userCategory = new UserCategory();
        user.addUserCategory(userCategory);
        newCategory.addUserCategory(userCategory);

        userCategoryRepository.save(userCategory);

        // 부모가 있으면 하는 설정
        if (parent.isNotRoot()) {
            parent.addChildren(newCategory);
            categoryRepository.save(parent);
        }

        // response
        return new CreateCategoryDto(newCategory);


    }

    // 부모 카테고리 찾기
    public Category findParent(String parent) {
        return categoryRepository.findByName(parent)
                .orElseThrow(() -> new CustomApiException("해당이름의 부모 카테고리가 존재하지 않습니다."));
    }

    // 유저의 카테고리 찾기
    public List<Category> findUserCategory(Long userId) {
        // 유저 찾기
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomApiException("유저 정보가 존재하지 않습니다."));

        List<UserCategory> userCategories = userCategoryRepository.findByUser(user);
        List<Category> categories = new ArrayList<>();

        for (UserCategory userCategory : userCategories) {
            Category category = categoryRepository.findByUserCategory(userCategory).orElseThrow(
                    () -> new CustomApiException("유저 카테고리로 해당 카테고리를 찾을 수 없습니다.")
            );
            categories.add(category);
        }

        return categories;
    }


    //
}
