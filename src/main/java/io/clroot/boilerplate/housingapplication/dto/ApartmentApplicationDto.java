package io.clroot.boilerplate.housingapplication.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.clroot.boilerplate.housingapplication.model.ApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.ApartmentSchedule;
import io.clroot.boilerplate.housingapplication.model.HousingType;
import io.clroot.boilerplate.housingapplication.model.RentOrSale;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class ApartmentApplicationDto extends HousingApplicationDto{
    private HousingType housingType;
    private RentOrSale rentOrSale;
    private String constructorCompany;
    private ApartmentSchedule apartmentSchedule;

    public ApartmentApplicationDto(ApartmentApplication source) {
        super(source);
        setApplicationType("Apartment");
        this.housingType = source.getHousingType();
        this.rentOrSale = source.getRentOrSale();
        this.constructorCompany = source.getConstructorCompany();
        this.apartmentSchedule = source.getApartmentSchedule();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("housingType", housingType)
            .append("rentOrSale",rentOrSale)
            .append("constructorCompany",constructorCompany)
            .append("apartmentSchedule",apartmentSchedule)
            .toString();
    }


}
