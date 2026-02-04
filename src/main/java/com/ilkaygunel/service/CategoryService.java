package com.ilkaygunel.service;

import com.ilkaygunel.dto.CategoryOutputDto;
import com.ilkaygunel.entity.Category;
import com.ilkaygunel.mapper.CategoryMapper;
import com.ilkaygunel.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public Category findCategoryByCategoryCode(String categoryCode) {
        return categoryRepository.findByCode(categoryCode)
                .orElseThrow(() -> new RuntimeException("There is no category with category code: " + categoryCode));
    }

    public List<CategoryOutputDto> findAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::entityToDto).collect(Collectors.toList());
    }

}
