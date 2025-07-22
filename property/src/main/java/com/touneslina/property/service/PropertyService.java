package com.touneslina.property.service;

import com.touneslina.property.entity.PropertyEntity;
import com.touneslina.property.repository.PropertyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.touneslina.property.entity.PropertyStatus.DELETED;

@Service
@AllArgsConstructor
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyEntity saveProperty(PropertyEntity propertyEntity) {
        return propertyRepository.saveAndFlush(propertyEntity);
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
            property.setCategory(updatedProperty.getCategory());
            property.setSource(updatedProperty.getSource());
            property.setInvoiceReference(updatedProperty.getInvoiceReference());
            property.setInventoryNumber(updatedProperty.getInventoryNumber());
            property.setAcquisitionDate(updatedProperty.getAcquisitionDate());
            property.setAcquisitionValue(updatedProperty.getAcquisitionValue());
            property.setDeletionDate(updatedProperty.getDeletionDate());
            property.setDeletionReference(updatedProperty.getDeletionReference());
            property.setDeletionReason(updatedProperty.getDeletionReason());
            property.setStatus(updatedProperty.getStatus());

            return saveProperty(property);
        }
        return null;
    }


    public Boolean deleteProperty(long idProperty) {
        PropertyEntity property = propertyRepository.findById(idProperty).orElse(null);
        if (property != null) {
            property.setStatus(DELETED);
            saveProperty(property);
            return true;
        }
        return false;
    }

    public PropertyEntity findPropertyById(long idProperty) {
        return propertyRepository.findById(idProperty).orElse(null);
    }

}
