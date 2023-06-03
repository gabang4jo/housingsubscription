package io.clroot.boilerplate.housingapplication.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.querydsl.core.annotations.QueryProjection;
import io.clroot.boilerplate.common.model.HouseInfo;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.common.model.Schedule;
import io.clroot.boilerplate.housingapplication.model.HousingApplication;
import java.io.Serializable;
import lombok.Data;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A DTO for the {@link io.clroot.boilerplate.housingapplication.model.HousingApplication} entity
 */
@Getter
public class HousingApplicationDto implements Serializable {

    private  Long id;
    private  Region region;
    private  HouseInfo houseInfo;
    private  Schedule schedule;

    protected String applicationType;

    public HousingApplicationDto(HousingApplication source){
        this.id = source.getId();
        this.region = source.getRegion();
        this.houseInfo = source.getHouseInfo();
        this.schedule = source.getSchedule();
    }

    protected void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("region",region)
            .append("houseInfo",houseInfo)
            .append("schedule",schedule)
            .append("applicationType",applicationType)
            .toString();
    }
}