package com.touneslina.property.controller;


import com.touneslina.property.client.FullPropertyResponse;
import com.touneslina.property.entity.CategoryEntity;
import com.touneslina.property.entity.PropertyEntity;
import com.touneslina.property.service.CategoryService;
import com.touneslina.property.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private final PropertyService propertyService;
    private final CategoryService categoryService;

    @PostMapping("/add")
    public ResponseEntity<PropertyEntity> addProperty(@RequestBody PropertyEntity property) {
        CategoryEntity category = categoryService.getCategoryById(property.getCategory().getId());
        if (category!=null){
            PropertyEntity createdProperty = propertyService.saveProperty(property);
            URI location = URI.create("/api/v1/properties/"+createdProperty.getIdProperty());
            return ResponseEntity.created(location).body(createdProperty);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropertyEntity>> findAllProperties() {
        List<PropertyEntity> properties = propertyService.findAllProperties();
        return ResponseEntity.ok(properties);
    }

    @PutMapping("/update/{idProperty}")
    public ResponseEntity<Void> updateProperty(@RequestBody PropertyEntity property, @PathVariable long idProperty) {
        PropertyEntity propertyEntity = propertyService.updateProperty(property,idProperty);
        if (propertyEntity != null) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/delete/{idProperty}")
    public ResponseEntity<Void> deleteProperty(@PathVariable long idProperty) {
        Boolean deleted = propertyService.deleteProperty(idProperty);
        if (deleted){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/find/{idProperty}")
    public ResponseEntity<PropertyEntity> findPropertyById(@PathVariable long idProperty) {
        PropertyEntity property = propertyService.findPropertyById(idProperty);
        if (property==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(property);
    }

    //This method is going to return all the loans of a specific property
    @GetMapping("/property/{idProperty}")
    public ResponseEntity<FullPropertyResponse> findAllLoansOfProperty(@PathVariable long idProperty) {
        return ResponseEntity.ok(propertyService.findAllLoansOfProperty(idProperty));
    }

}
