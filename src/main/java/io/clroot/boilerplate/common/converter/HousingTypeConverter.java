package io.clroot.boilerplate.common.converter;

import io.clroot.boilerplate.housingapplication.model.HousingType;
import io.clroot.boilerplate.housingapplication.model.RentOrSale;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;

@Convert
@Slf4j
public class HousingTypeConverter implements AttributeConverter<HousingType, String> {

    @Override
    public String convertToDatabaseColumn(HousingType housingType) {
        if (housingType == null) {
            return null;
        }
        return housingType.getValue();
    }

    @Override
    public HousingType convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        try {
            return HousingType.fromCode(dbData);
        } catch (IllegalArgumentException e) {
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
            throw e;
        }
    }
}
