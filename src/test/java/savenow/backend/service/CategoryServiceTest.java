package savenow.backend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import savenow.backend.domain.category.Category;
import savenow.backend.domain.category.CategoryRepository;
import savenow.backend.domain.user.User;
import savenow.backend.domain.user.UserRepository;
import savenow.backend.domain.userCategory.UserCategoryRepository;
import savenow.backend.dto.category.CategoryReqDto.CreateCategoryReqDto;
import savenow.backend.dummy.DummyObject;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static savenow.backend.dto.category.CategoryResDto.*;


@ExtendWith(MockitoExtension.class)
class CategoryServiceTest extends DummyObject {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private UserCategoryRepository userCategoryRepository;
    @Spy
    private ObjectMapper om;


    @Test
    public void 부모가없는카테고리() throws Exception{

        Long userId = 1L;

        //stub1
        User user = newMockUser(1L,"유저","1234@naver.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        //stub2
        Category parent = newMockCategory(1L, "부모", null);
        when(categoryRepository.save(parent)).thenReturn(parent);
        when(categoryRepository.findByName("부모")).thenReturn(Optional.of(parent));

        //stub3
        Category child1 = newMockCategory(2L, "아들", parent);
        Category child2 = newMockCategory(3L, "딸", parent);
        when(categoryRepository.save(child1)).thenReturn(child1);
        when(categoryRepository.save(child2)).thenReturn(child2);

        //given

        CreateCategoryReqDto createCategoryReqDto1 = new CreateCategoryReqDto();
        createCategoryReqDto1.setName("부모");


        CreateCategoryReqDto createCategoryReqDto2 = new CreateCategoryReqDto();
        createCategoryReqDto2.setName("아들");
        createCategoryReqDto2.setParent("부모");

        CreateCategoryReqDto createCategoryReqDto3 = new CreateCategoryReqDto();
        createCategoryReqDto3.setName("딸");
        createCategoryReqDto3.setParent("부모");


        //when
        CreateCategoryDto createCategoryDto = categoryService.createCategory(1L,createCategoryReqDto1);
        String responseBody = om.writeValueAsString(createCategoryDto);
        System.out.println("테스트 : " + responseBody);

        CreateCategoryDto createCategoryDto2 = categoryService.createCategory(1L,createCategoryReqDto2);
        String responseBody2 = om.writeValueAsString(createCategoryDto2);
        System.out.println("테스트 : " + responseBody2);


        CreateCategoryDto createCategoryDto3 = categoryService.createCategory(1L,createCategoryReqDto3);
        String responseBody3 = om.writeValueAsString(createCategoryDto3);
        System.out.println("테스트 : " + responseBody3);

        List<String> childNames = parent.getChildren()
                .stream()
                .map(c -> c.getName())
                .collect(Collectors.toList());



        //then
        assertThat(createCategoryDto.getName()).isEqualTo("부모");
        assertThat(childNames).isEqualTo(asList("아들", "딸"));

    }


}