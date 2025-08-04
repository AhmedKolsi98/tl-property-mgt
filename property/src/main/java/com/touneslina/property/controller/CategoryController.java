package com.touneslina.property.controller;

import com.touneslina.property.entity.CategoryEntity;
import com.touneslina.property.entity.PropertyEntity;
import com.touneslina.property.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<CategoryEntity> addCategory(@RequestBody CategoryEntity category) {
        CategoryEntity categoryEntity = categoryService.saveCategory(category);
        URI location = URI.create("/api/v1/categories/"+categoryEntity.getId());
        return ResponseEntity.created(location).body(categoryEntity);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryEntity>> findAllCategories() {
        List<CategoryEntity> categories = categoryService.findAllCategories();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/update/{idCategory}")
    public ResponseEntity<Void> updateCategory(@RequestBody CategoryEntity category, @PathVariable long idCategory) {
        CategoryEntity categoryEntity = categoryService.updateCategory(category,idCategory);
        if (categoryEntity != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/delete/{idCategory}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long idCategory) {
        Boolean deleted = categoryService.deleteCategory(idCategory);
        if (deleted){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find/{idCategory}")
    public ResponseEntity<CategoryEntity> findCategoryById(@PathVariable long idCategory) {
        CategoryEntity category = categoryService.getCategoryById(idCategory);
        if (category==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }


}
