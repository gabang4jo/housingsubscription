package io.clroot.boilerplate.housingapplication.model;
import static com.google.common.base.Preconditions.checkArgument;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.clroot.boilerplate.common.converter.RegionConverter;
import io.clroot.boilerplate.common.model.BaseEntity;
import io.clroot.boilerplate.common.model.HouseInfo;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.common.model.Schedule;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "HTYPE")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = ApartmentApplication.class, name = "ApartmentApplication"),
    @JsonSubTypes.Type(value = NonApartmentApplication.class, name = "NonApartmentApplication"),
    @JsonSubTypes.Type(value = OtherAptRandomApplication.class, name = "OtherAptRandomApplication"),
    // 여기에 다른 하위 클래스들을 추가합니다.
})
public class HousingApplication extends BaseEntity {

    @Column(name = "region",nullable = false)
    @Convert(converter = RegionConverter.class)
    private Region region;

    @Embedded
    private HouseInfo houseInfo;

    @Embedded
    private Schedule schedule;

    public HousingApplication(Region region, HouseInfo houseInfo, Schedule schedule) {
        checkArgument(region != null, "region must be provided");
        checkArgument(houseInfo != null, "houseInfo must be provided");
        checkArgument(schedule != null, "schedule must be provided");
        this.region = region;
        this.houseInfo = houseInfo;
        this.schedule = schedule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        HousingApplication that = (HousingApplication) o;
        return getId().equals(that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("id", getId())
            .append("region",region)
            .append("houseInfo",houseInfo)
            .append("schedule",schedule)
            .toString();
    }



}