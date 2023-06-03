package io.clroot.boilerplate.housingapplication.model;


import com.fasterxml.jackson.annotation.JsonTypeName;
import io.clroot.boilerplate.common.converter.NonApartmentTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("NON_APARTMENT")
@JsonTypeName("NonApartmentApplication")
public class NonApartmentApplication extends HousingApplication {

    @Column(name = "non_apartment_type",nullable = false)
    @Convert(converter = NonApartmentTypeConverter.class)
    private NonApartmentType nonApartmentType;

    @Column(name = "executor_company",nullable = false)
    private String executorCompany;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("nonApartmentType", nonApartmentType)
            .append("executorCompany",executorCompany)
            .toString();
    }



}