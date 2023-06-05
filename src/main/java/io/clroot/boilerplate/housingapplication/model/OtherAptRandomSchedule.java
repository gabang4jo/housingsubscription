package io.clroot.boilerplate.housingapplication.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OtherAptRandomSchedule {

    @Column(name = "special_supply_date")
    private LocalDateTime specialSupplyDate;

    @Column(name = "general_supply_date")
    private LocalDateTime generalSupplyDate;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("specialSupplyDate", specialSupplyDate)
            .append("specialSupplyDate",specialSupplyDate)
            .toString();
    }

}