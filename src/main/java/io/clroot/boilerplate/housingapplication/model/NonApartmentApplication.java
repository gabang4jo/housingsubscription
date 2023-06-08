package io.clroot.boilerplate.housingapplication.model;


import static com.google.common.base.Preconditions.checkArgument;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.clroot.boilerplate.common.converter.NonApartmentTypeConverter;
import io.clroot.boilerplate.common.model.HouseInfo;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.common.model.Schedule;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DiscriminatorValue("NON_APARTMENT")
@JsonTypeName("NonApartmentApplication")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class NonApartmentApplication extends HousingApplication {

    @Column(name = "non_apartment_type",nullable = false)
    @Convert(converter = NonApartmentTypeConverter.class)
    private NonApartmentType nonApartmentType;

    @Column(name = "executor_company",nullable = false)
    private String executorCompany;

    public NonApartmentApplication(Region region,
        HouseInfo houseInfo,
        Schedule schedule, NonApartmentType nonApartmentType,
        String executorCompany) {
        super(region, houseInfo, schedule);

        checkArgument(nonApartmentType != null, "nonApartmentType must be provided");
        checkArgument(StringUtils.isNotEmpty(executorCompany) , "executorCompany must be provided");
        this.nonApartmentType = nonApartmentType;
        this.executorCompany = executorCompany;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("nonApartmentType", nonApartmentType)
            .append("executorCompany",executorCompany)
            .toString();
    }



}