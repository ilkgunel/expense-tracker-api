package com.ilkaygunel.service;

import com.ilkaygunel.entity.Category;
import com.ilkaygunel.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category findCategoryByCategoryCode(String categoryCode) {
        return categoryRepository.findByCode(categoryCode)
                .orElseThrow(() -> new RuntimeException("There is no category with category code: " + categoryCode));
    }

}
