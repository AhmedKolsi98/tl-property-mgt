package com.touneslina.property.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    @Column(nullable=false)
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private CategoryStatus status;
    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<PropertyEntity> properties;
}
