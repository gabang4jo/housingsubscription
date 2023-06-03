package io.clroot.boilerplate.common.model;


import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.StringUtils.*;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {


    @Column(name = "announcement_date",nullable = false)
    private LocalDateTime announcementDate;
    @Column(name = "start_date",nullable = false)
    private LocalDateTime startDate;
    @Column(name = "end_date",nullable = false)
    private LocalDateTime endDate;

    @Column(name = "winner_announcement_date",nullable = false)
    private LocalDateTime winnerAnnouncementDate;

    @Column(name = "contact", nullable = false)
    private String contact;


    public Schedule(LocalDateTime announcementDate, LocalDateTime startDate, LocalDateTime endDate,
        LocalDateTime winnerAnnouncementDate,String contact) {

        checkArgument(announcementDate != null, "announcementDate must be provided");
        checkArgument(startDate != null, "startDate must be provided");
        checkArgument(endDate != null, "endDate must be provided");
        checkArgument(winnerAnnouncementDate != null, "winnerAnnouncementDate must be provided");
        checkArgument(contact != null && isNotEmpty(contact), "contact must be provided");


        this.announcementDate = announcementDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.winnerAnnouncementDate = winnerAnnouncementDate;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("announcementDate",announcementDate)
            .append("startDate",startDate)
            .append("endDate",endDate)
            .append("winnerAnnouncementDate",winnerAnnouncementDate)
            .append("contact",contact)
            .toString();
    }

}
