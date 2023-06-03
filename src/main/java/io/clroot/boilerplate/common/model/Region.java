package io.clroot.boilerplate.common.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Arrays;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public enum Region  {

    KANGWON("강원"),
    KYEONGGI("경기"),
    GYEONGNAM("경남"),
    GYUNGBUK("경북"),
    GWANGJU("광주"),
    DAEGU("대구"),
    DAEGEON("대전"),
    BUSAN("부산"),
    SEOUL("서울"),
    SEJONG("세종"),
    ULSAN("울산"),
    INCHEON("인천"),
    JEONNAM("전남"),
    JEONBUK("전북"),
    JEJU("제주"),
    CHUNGNAM("충남"),
    CHUNGBUK("충북");

    private  String value;

    Region(String value){
        this.value = value;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("code",value)
            .toString();
    }

    @JsonCreator(mode=JsonCreator.Mode.DELEGATING)
    public static Region fromCode(String dbData) {
        return Arrays.stream(values())
            .filter(type -> type.getValue().equals(dbData))
            .findAny()
            .orElse(null);
    }
}
