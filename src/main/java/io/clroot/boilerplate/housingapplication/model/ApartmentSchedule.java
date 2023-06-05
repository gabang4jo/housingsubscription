package io.clroot.boilerplate.housingapplication.model;

import static com.google.common.base.Preconditions.checkArgument;

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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApartmentSchedule {

    @Column(name = "special_supply_date")
    private LocalDateTime specialSupplyDate;

    @Column(name = "first_priority_application_date", nullable = false)
    private LocalDateTime firstPriorityApplicationDate;

    @Column(name = "second_priority_application_date", nullable = false)
    private LocalDateTime secondPriorityApplicationDate;

    public ApartmentSchedule(LocalDateTime specialSupplyDate,LocalDateTime firstPriorityApplicationDate,LocalDateTime secondPriorityApplicationDate){
        checkArgument(firstPriorityApplicationDate != null, "firstPriorityApplicationDate must be provided");
        checkArgument(secondPriorityApplicationDate != null, "secondPriorityApplicationDate must be provided");
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("specialSupplyDate", specialSupplyDate)
            .append("firstPriorityApplicationDate",firstPriorityApplicationDate)
            .append("secondPriorityApplicationDate",secondPriorityApplicationDate)
            .toString();
    }
}