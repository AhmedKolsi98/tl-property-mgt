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

    @Column(nullable = false)
    private long idCategory;

    @Column(nullable = false)
    private String source;

    @Column(nullable = false)
    private String invoiceReference;

    private String inventoryNumber;

    private Date acquisitionDate;

    @Enumerated(EnumType.STRING)
    private AcquisitionValue acquisitionValue;

    private Date deletionDate;

    private String deletionReference;

    private String deletionReason;

    @Enumerated(EnumType.STRING)
    private Status status;

}
