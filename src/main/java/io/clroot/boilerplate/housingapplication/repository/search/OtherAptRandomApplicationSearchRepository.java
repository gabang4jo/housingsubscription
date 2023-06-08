package io.clroot.boilerplate.housingapplication.repository.search;

import static com.google.common.base.Preconditions.checkArgument;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.common.repository.SearchRepository;
import io.clroot.boilerplate.housingapplication.dto.search.OtherAptRandomApplicationSearchDto;
import io.clroot.boilerplate.housingapplication.model.NonApartmentType;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomApplication;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomType;
import io.clroot.boilerplate.housingapplication.model.QOtherAptRandomApplication;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OtherAptRandomApplicationSearchRepository extends SearchRepository<OtherAptRandomApplication, OtherAptRandomApplicationSearchDto> {


    private final JPAQueryFactory jpaQueryFactory;

    private final QOtherAptRandomApplication qOtherAptRandomApplication = QOtherAptRandomApplication.otherAptRandomApplication;

    @Override
    protected JPAQuery<OtherAptRandomApplication> searchQuery(
        OtherAptRandomApplicationSearchDto search) {
        LocalDateTime startDateTime = getStartDateTime(search.startYear(), search.startMonth());
        LocalDateTime endDateTime = getEndDateTime(search.endYear(), search.endMonth());

        return jpaQueryFactory.selectFrom(qOtherAptRandomApplication)
            .where(containRegion(search.region()),
                containOtherAptRandomType(search.otherAptRandomType()),
                containExecutorCompany(search.executorCompany()),
                qOtherAptRandomApplication.schedule.startDate.between(startDateTime,endDateTime)
            );
    }

    @Override
    protected JPAQuery<Long> totalCountQuery(OtherAptRandomApplicationSearchDto search) {
        var searchQuery = searchQuery(search);
        return searchQuery.select(qOtherAptRandomApplication.count());
    }

    private BooleanExpression containExecutorCompany(String executorCompany) {
        return executorCompany == null ? null : qOtherAptRandomApplication.executorCompany.contains(executorCompany);
    }

    private BooleanExpression containRegion(Region region) {
        return region == null ? null : qOtherAptRandomApplication.region.eq(region);
    }

    private BooleanExpression containOtherAptRandomType(OtherAptRandomType otherAptRandomType) {
        return otherAptRandomType == null ? null : qOtherAptRandomApplication.otherAptRandomType.eq(otherAptRandomType);
    }

    public LocalDateTime getStartDateTime(Integer startYear, Integer startMonth) {
        if (startYear == null || startMonth == null) {
            return LocalDateTime.of(2023, 1, 1, 0, 0, 0); // far past
        }

        checkArgument(startYear >= 2022 && startYear <= 2023, "Invalid year: " + startYear);
        checkArgument(startMonth >= 1 && startMonth <= 12, "Invalid Month: " + startMonth);
        return LocalDateTime.of(startYear, startMonth, 1, 0, 0, 0);
    }

    public LocalDateTime getEndDateTime(Integer endYear, Integer endMonth) {
        if (endYear == null || endMonth == null) {
            return LocalDateTime.of(2023, 5, 31, 23, 59, 59); // far future
        }

        checkArgument(endYear >= 2022 && endYear <= 2023, "Invalid year: " + endYear);
        checkArgument(endMonth >= 1 && endMonth <= 12, "Invalid Month: " + endMonth);
        // 월의 마지막 날을 구하기 위해 Month 객체를 사용합니다.
        int lastDayOfMonth = Month.of(endMonth).length(Year.isLeap(endYear));
        return LocalDateTime.of(endYear, endMonth, lastDayOfMonth, 23, 59, 59);
    }
}
