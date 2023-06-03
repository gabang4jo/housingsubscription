package io.clroot.boilerplate.common.converter;

import io.clroot.boilerplate.housingapplication.model.HousingType;
import io.clroot.boilerplate.housingapplication.model.NonApartmentType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;

@Convert
@Slf4j
public class NonApartmentTypeConverter implements AttributeConverter<NonApartmentType, String> {

    @Override
    public String convertToDatabaseColumn(NonApartmentType nonApartmentType) {
        if (nonApartmentType == null) {
            return null;
        }
        return nonApartmentType.getValue();
    }

    @Override
    public NonApartmentType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        try {
            return NonApartmentType.fromCode(dbData);
        } catch (IllegalArgumentException e) {
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
            throw e;
        }
    }
}
