package io.clroot.boilerplate.housingapplication.dto;

import static io.clroot.boilerplate.common.controller.ApiResult.OK;
import static org.springframework.beans.BeanUtils.copyProperties;

import com.querydsl.core.annotations.QueryProjection;
import io.clroot.boilerplate.common.model.HouseInfo;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.common.model.Schedule;
import io.clroot.boilerplate.housingapplication.model.ApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.HousingApplication;
import io.clroot.boilerplate.housingapplication.model.HousingType;
import io.clroot.boilerplate.housingapplication.model.NonApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.NonApartmentType;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomApplication;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomType;
import io.clroot.boilerplate.housingapplication.model.RentOrSale;
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

    private String type;

    private HousingType housingType = null;

    private NonApartmentType nonApartmentType = null;

    private OtherAptRandomType otherAptRandomType = null;

    private RentOrSale rentOrSale = null;


    public HousingApplicationDto(HousingApplication source){
        this.id = source.getId();
        this.region = source.getRegion();
        this.houseInfo = source.getHouseInfo();
        this.schedule = source.getSchedule();
        checkType(source);
    }

    public void checkType(HousingApplication housingApplication){
        if (housingApplication instanceof ApartmentApplication) {
            this.type = "Apartment";
            this.housingType =  ((ApartmentApplication) housingApplication).getHousingType();
            this.rentOrSale =  ((ApartmentApplication) housingApplication).getRentOrSale();

        } else if (housingApplication instanceof NonApartmentApplication) {
            this.type = "NonApartment";
            this.nonApartmentType =  ((NonApartmentApplication) housingApplication).getNonApartmentType();
        } else if (housingApplication instanceof OtherAptRandomApplication) {
            this.type = "OtherAptRandom";
            this.otherAptRandomType =  ((OtherAptRandomApplication) housingApplication).getOtherAptRandomType();
        }else throw new RuntimeException("타입이 없습니다.");

    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", id)
            .append("region",region)
            .append("houseInfo",houseInfo)
            .append("schedule",schedule)
            .append("type",type)
            .toString();
    }
}