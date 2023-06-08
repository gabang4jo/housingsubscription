package io.clroot.boilerplate.housingapplication.model;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.*;

import com.fasterxml.jackson.annotation.JsonTypeName;
import io.clroot.boilerplate.common.converter.OtherAptRandomTypeConverter;
import io.clroot.boilerplate.common.model.HouseInfo;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.common.model.Schedule;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
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
@DiscriminatorValue("OTHER_APT")
@JsonTypeName("OtherAptRandomApplication")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OtherAptRandomApplication extends HousingApplication {

    @Column(name = "executor_company",nullable = false)
    private String executorCompany;

    @Column(name = "other_apt_random_type",nullable = false)
    @Convert(converter = OtherAptRandomTypeConverter.class)
    private OtherAptRandomType otherAptRandomType;

    @Embedded
    private OtherAptRandomSchedule otherAptRandomSchedule;

    public OtherAptRandomApplication(Region region,
        HouseInfo houseInfo,
        Schedule schedule, String executorCompany,
        OtherAptRandomType otherAptRandomType, OtherAptRandomSchedule otherAptRandomSchedule) {
        super(region, houseInfo, schedule);

        checkArgument(otherAptRandomType != null, "otherAptRandomType must be provided");
        checkArgument(isNotEmpty(executorCompany) , "executorCompany must be provided");

        this.executorCompany = executorCompany;
        this.otherAptRandomType = otherAptRandomType;
        this.otherAptRandomSchedule = otherAptRandomSchedule;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("executorCompany",executorCompany)
            .toString();
    }

}