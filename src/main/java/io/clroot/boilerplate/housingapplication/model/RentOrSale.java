package io.clroot.boilerplate.housingapplication.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public enum RentOrSale {

    SALE("분양"),
    RENT_TRANSFERABLE("분양전환가능임대"),
    RENT_NON_TRANSFERABLE("분양전환불가임대");


    private String value;

    RentOrSale(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("value",value)
            .toString();
    }

    @JsonCreator(mode=JsonCreator.Mode.DELEGATING)
    public static RentOrSale fromCode(String dbData) {
        return Arrays.stream(values())
            .filter(type -> type.getValue().equals(dbData))
            .findAny()
            .orElse(null);
    }
}
