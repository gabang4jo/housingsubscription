package io.clroot.boilerplate.housingapplication.repository.search;

import static com.google.common.base.Preconditions.checkArgument;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.common.repository.SearchRepository;
import io.clroot.boilerplate.housingapplication.dto.NonApartmentApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.search.NonApartmentApplicationSearchDto;
import io.clroot.boilerplate.housingapplication.model.HousingType;
import io.clroot.boilerplate.housingapplication.model.NonApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.NonApartmentType;
import io.clroot.boilerplate.housingapplication.model.QNonApartmentApplication;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class NonApartmentApplicationSearchRepository extends SearchRepository<NonApartmentApplication, NonApartmentApplicationSearchDto> {


    private final JPAQueryFactory jpaQueryFactory;
    private final QNonApartmentApplication qNonApartmentApplication = QNonApartmentApplication.nonApartmentApplication;

    @Override
    protected JPAQuery<NonApartmentApplication> searchQuery(
        NonApartmentApplicationSearchDto search) {
        LocalDateTime startDateTime = getStartDateTime(search.startYear(), search.startMonth());
        LocalDateTime endDateTime = getEndDateTime(search.endYear(), search.endMonth());

        return jpaQueryFactory.selectFrom(qNonApartmentApplication)
            .where(containRegion(search.region()),
                containNonApartmentType(search.nonApartmentType()),
                containExecutorCompany(search.executorCompany()),
                qNonApartmentApplication.schedule.startDate.between(startDateTime,endDateTime)
                );
    }

    @Override
    protected JPAQuery<Long> totalCountQuery(NonApartmentApplicationSearchDto search) {
        var searchQuery = searchQuery(search);
        return searchQuery.select(qNonApartmentApplication.count());
    }

    private BooleanExpression containExecutorCompany(String executorCompany) {
        return executorCompany == null ? null : qNonApartmentApplication.executorCompany.contains(executorCompany);
    }

    private BooleanExpression containRegion(Region region) {
        return region == null ? null : qNonApartmentApplication.region.eq(region);
    }

    private BooleanExpression containNonApartmentType(NonApartmentType nonApartmentType) {
        return nonApartmentType == null ? null : qNonApartmentApplication.nonApartmentType.eq(nonApartmentType);
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
