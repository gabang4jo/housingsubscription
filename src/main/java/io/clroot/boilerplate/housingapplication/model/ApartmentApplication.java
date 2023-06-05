package io.clroot.boilerplate.housingapplication.model;


import com.fasterxml.jackson.annotation.JsonTypeName;
import io.clroot.boilerplate.common.converter.HousingTypeConverter;
import io.clroot.boilerplate.common.converter.RentOrSaleConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("APARTMENT")
@JsonTypeName("ApartmentApplication")
public class ApartmentApplication extends HousingApplication {

    @Column(name = "house_type",nullable = false)
    @Convert(converter = HousingTypeConverter.class)
    private HousingType housingType;

    @Column(name = "rent_or_sale",nullable = false)
    @Convert(converter = RentOrSaleConverter.class)
    private RentOrSale rentOrSale;

    @Column(name = "constructor_company",nullable = false)
    private String constructorCompany;

    @Embedded
    private ApartmentSchedule apartmentSchedule;

    public void setApartmentSchedule(ApartmentSchedule apartmentSchedule) {
        this.apartmentSchedule = apartmentSchedule;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("housingType", housingType)
            .append("rentOrSale",rentOrSale)
            .append("constructorCompany",constructorCompany)
            .toString();
    }


}