package io.clroot.boilerplate.housingapplication.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.querydsl.core.annotations.QueryProjection;
import io.clroot.boilerplate.housingapplication.model.NonApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.NonApartmentType;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A DTO for the {@link io.clroot.boilerplate.housingapplication.model.NonApartmentApplication}
 * entity
 */
@Getter
public class NonApartmentApplicationDto extends HousingApplicationDto {

    private  NonApartmentType nonApartmentType;
    private  String executorCompany;

    public NonApartmentApplicationDto(NonApartmentApplication source){
        super(source);
        this.nonApartmentType = source.getNonApartmentType();
        this.executorCompany = source.getExecutorCompany();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("nonApartmentType", nonApartmentType)
            .append("executorCompany",executorCompany)
            .toString();
    }

}