package com.touneslina.property.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idProperty;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String equipmentType;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String invoiceReference;

    private String inventoryNumber;

    private Date acquisitionDate;

    @Enumerated(EnumType.STRING)
    private PropertyAcquisitionValue acquisitionValue;

    private Date deletionDate;

    private String deletionReference;

    private String deletionReason;

    @Enumerated(EnumType.STRING)
    private PropertyStatus status;

}
