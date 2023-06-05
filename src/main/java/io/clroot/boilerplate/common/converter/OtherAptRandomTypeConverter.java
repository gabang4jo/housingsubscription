package io.clroot.boilerplate.common.converter;

import io.clroot.boilerplate.housingapplication.model.NonApartmentType;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;

@Convert
@Slf4j
public class OtherAptRandomTypeConverter implements AttributeConverter<OtherAptRandomType, String> {

    @Override
    public String convertToDatabaseColumn(OtherAptRandomType otherAptRandomType) {
        if (otherAptRandomType == null) {
            return null;
        }
        return otherAptRandomType.getValue();
    }

    @Override
    public OtherAptRandomType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        try {
            return OtherAptRandomType.fromCode(dbData);
        } catch (IllegalArgumentException e) {
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
            throw e;
        }
    }
}
