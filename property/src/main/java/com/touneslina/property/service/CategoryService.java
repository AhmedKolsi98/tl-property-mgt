package com.touneslina.property.service;

import com.touneslina.property.entity.CategoryEntity;
import com.touneslina.property.entity.CategoryStatus;
import com.touneslina.property.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import static com.touneslina.property.entity.CategoryStatus.DELETED;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryEntity saveCategory(CategoryEntity category){
        return categoryRepository.saveAndFlush(category);
    }

    public List<CategoryEntity> findAllCategories (){
        return categoryRepository.findAll();
    }

    public CategoryEntity getCategoryById(long idCategory){
        Optional<CategoryEntity> category = categoryRepository.findById(idCategory);
        return category.orElse(null);
    }

    public CategoryEntity updateCategory(CategoryEntity categoryEntity, long idCategory){
        CategoryEntity category = getCategoryById(idCategory);
        if (category!=null){
            category.setDescription(categoryEntity.getDescription());
            category.setName(categoryEntity.getName());
            return saveCategory(category);
        }
        return null;
    }

    public void deleteCategory(long idCategory){
        CategoryEntity category = getCategoryById(idCategory);
        if(category!=null) {
            category.setStatus(DELETED);
            saveCategory(category);
        }
    }



}
