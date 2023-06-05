package io.clroot.boilerplate.housingapplication.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public enum HousingType{

    PRIVATE("민영"),
    NATIONAL("국민");


    private String value;

    HousingType(String value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("value",value)
            .toString();
    }

    @JsonCreator(mode=JsonCreator.Mode.DELEGATING)
    public static HousingType fromCode(String dbdata) {
        return Arrays.stream(values())
            .filter(type -> type.getValue().equals(dbdata))
            .findAny()
            .orElse(null);
    }
}
