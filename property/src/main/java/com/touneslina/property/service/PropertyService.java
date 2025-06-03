package com.touneslina.property.service;

import com.touneslina.property.entity.PropertyEntity;
import com.touneslina.property.entity.Status;
import com.touneslina.property.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.touneslina.property.entity.Status.DELETED;

@Service
@AllArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyEntity saveProperty(PropertyEntity propertyEntity) {
        return propertyRepository.save(propertyEntity);
    }

    public List<PropertyEntity> findAllProperties() {
        return (List<PropertyEntity>) propertyRepository.findAll();
    }

    public PropertyEntity updateProperty(PropertyEntity updatedProperty, long idProperty) {
        PropertyEntity property = propertyRepository.findById(idProperty).orElse(null);
        if (property != null) {
            property.setReference(updatedProperty.getReference());
            property.setName(updatedProperty.getName());
            property.setEquipmentType(updatedProperty.getEquipmentType());
            property.setIdCategory(updatedProperty.getIdCategory());
            property.setSource(updatedProperty.getSource());
            property.setInvoiceReference(updatedProperty.getInvoiceReference());
            property.setInventoryNumber(updatedProperty.getInventoryNumber());
            property.setAcquisitionDate(updatedProperty.getAcquisitionDate());
            property.setAcquisitionValue(updatedProperty.getAcquisitionValue());
            property.setDeletionDate(updatedProperty.getDeletionDate());
            property.setDeletionReference(updatedProperty.getDeletionReference());
            property.setDeletionReason(updatedProperty.getDeletionReason());
            property.setStatus(updatedProperty.getStatus());

            return propertyRepository.save(property);
        }
        return null;
    }


    public void deleteProperty(long idProperty) {
        PropertyEntity property = propertyRepository.findById(idProperty).orElse(null);
        if (property != null) {
            property.setStatus(DELETED);
            propertyRepository.save(property);
        }
    }

    public PropertyEntity findPropertyById(long idProperty) {
        Optional<PropertyEntity> property = propertyRepository.findById(idProperty);
        return property.orElse(null);
    }

}
