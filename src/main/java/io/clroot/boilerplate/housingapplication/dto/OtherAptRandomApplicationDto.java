package io.clroot.boilerplate.housingapplication.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomApplication;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomSchedule;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class OtherAptRandomApplicationDto extends HousingApplicationDto{

    private String executorCompany;

    private OtherAptRandomSchedule otherAptRandomSchedule;

    public OtherAptRandomApplicationDto(OtherAptRandomApplication source) {
        super(source);
        this.executorCompany = source.getExecutorCompany();
        this.otherAptRandomSchedule = source.getOtherAptRandomSchedule();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("executorCompany", executorCompany)
            .append("otherAptRandomSchedule",otherAptRandomSchedule)
            .toString();
    }



}
