package savenow.backend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import savenow.backend.dto.ResponseDto;
import savenow.backend.dto.category.CategoryReqDto.CreateCategoryReqDto;
import savenow.backend.dto.category.CategoryResDto.CreateCategoryDto;
import savenow.backend.service.CategoryService;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/{userId}/createCategory")
    public ResponseEntity<?> addCategory (
            @PathVariable Long userId,
            @RequestBody CreateCategoryReqDto createCategoryReqDto) {

        CreateCategoryDto createCategoryDto = categoryService.createCategory(userId,createCategoryReqDto);
        return new ResponseEntity<>(
                new ResponseDto<>(1, "카테고리 생성 완료", createCategoryDto), HttpStatus.CREATED);
    }

}
