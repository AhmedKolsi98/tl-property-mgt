package com.touneslina.property.controller;


import com.touneslina.property.entity.PropertyEntity;
import com.touneslina.property.service.PropertyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/properties")
public class PropertyController {
    private final PropertyService propertyService;

    @PostMapping("/add")
    public ResponseEntity<Void> addProperty(@RequestBody PropertyEntity property) {
        PropertyEntity propertyEntity = propertyService.saveProperty(property);
        return ResponseEntity.ok().build();
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
        propertyService.deleteProperty(idProperty);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/find/{idProperty}")
    public ResponseEntity<PropertyEntity> findPropertyById(@PathVariable long idProperty) {
        PropertyEntity property = propertyService.findPropertyById(idProperty);
        return ResponseEntity.ok(property);
    }

}
