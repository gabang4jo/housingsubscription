package io.clroot.boilerplate.common.converter;

import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.housingapplication.model.RentOrSale;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;

@Convert
@Slf4j
public class RentOrSaleConverter implements AttributeConverter<RentOrSale, String> {

    @Override
    public String convertToDatabaseColumn(RentOrSale rentOrSale) {
        if (rentOrSale == null) {
            return null;
        }
        return rentOrSale.getValue();
    }

    @Override
    public RentOrSale convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        try {
            return RentOrSale.fromCode(dbData);
        } catch (IllegalArgumentException e) {
            log.error("failure to convert cause unexpected code [{}]", dbData, e);
            throw e;
        }
    }
}
