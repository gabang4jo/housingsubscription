package io.clroot.boilerplate.common.converter;

import io.clroot.boilerplate.common.model.Region;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import lombok.extern.slf4j.Slf4j;

@Convert
@Slf4j
public class RegionConverter implements AttributeConverter<Region,String> {

    @Override
    public String convertToDatabaseColumn(Region region) {
        if (region == null) return null;
        return region.getValue();
    }

    @Override
    public Region convertToEntityAttribute(String dbData) {
        if (dbData == null){
            return null;
        }


        try {
            return Region.fromCode(dbData);
        }catch (IllegalArgumentException e){
            log.error("failure to convert cause unexpected code [{}]",dbData,e);
            throw e;
        }
    }
}
