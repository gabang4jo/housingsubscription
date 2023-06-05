package io.clroot.boilerplate.housingapplication.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public enum NonApartmentType  {

    OFFICETEL("오피스텔"),
    LIVING_ACCOMMODATION("생활숙박시설"),
    URBAN_HOUSING("도시형생활주택"),
    PRIVATE_RENTAL("민간임대");

    private  String value;

    NonApartmentType(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("value",value)
            .toString();
    }

    @JsonCreator(mode=JsonCreator.Mode.DELEGATING)
    public static NonApartmentType fromCode(String dbData) {
        return Arrays.stream(values())
            .filter(type -> type.getValue().equals(dbData))
            .findAny()
            .orElse(null);
    }
}
