package io.clroot.boilerplate.housingapplication.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public enum OtherAptRandomType {
    BEFOREHAND("사전"),
    AFTER("사후"),
    CANCELANDRESUPPLY("취소후재공급");

    private String value;

    OtherAptRandomType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("code",value)
            .toString();
    }

    @JsonCreator(mode=JsonCreator.Mode.DELEGATING)
    public static OtherAptRandomType fromCode(String dbData) {
        return Arrays.stream(values())
            .filter(type -> type.getValue().equals(dbData))
            .findAny()
            .orElse(null);
    }
}
