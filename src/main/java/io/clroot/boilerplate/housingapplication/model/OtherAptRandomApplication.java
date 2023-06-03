package io.clroot.boilerplate.housingapplication.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.clroot.boilerplate.common.converter.OtherAptRandomTypeConverter;
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
@DiscriminatorValue("OTHER_APT")
@JsonTypeName("OtherAptRandomApplication")
public class OtherAptRandomApplication extends HousingApplication {

    @Column(name = "executor_company",nullable = false)
    private String executorCompany;

    @Column(name = "other_apt_random_type",nullable = false)
    @Convert(converter = OtherAptRandomTypeConverter.class)
    private OtherAptRandomType otherAptRandomType;

    @Embedded
    private OtherAptRandomSchedule otherAptRandomSchedule;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("executorCompany",executorCompany)
            .toString();
    }

}