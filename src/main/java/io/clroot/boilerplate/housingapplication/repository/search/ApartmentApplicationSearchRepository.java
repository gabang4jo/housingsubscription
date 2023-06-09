package io.clroot.boilerplate.housingapplication.repository.search;

import static com.google.common.base.Preconditions.checkArgument;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.common.repository.SearchRepository;
import io.clroot.boilerplate.housingapplication.dto.search.ApartmentApplicationSearchDto;
import io.clroot.boilerplate.housingapplication.model.ApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.HousingType;
import io.clroot.boilerplate.housingapplication.model.QApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.RentOrSale;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ApartmentApplicationSearchRepository extends
    SearchRepository<ApartmentApplication, ApartmentApplicationSearchDto> {


    private final JPAQueryFactory queryFactory;
    private final QApartmentApplication qApartmentApplication = QApartmentApplication.apartmentApplication;

    @Override
    protected JPAQuery<ApartmentApplication> searchQuery(ApartmentApplicationSearchDto search) {

        LocalDateTime startDateTime = getStartDateTime(search.startYear(), search.startMonth());
        LocalDateTime endDateTime = getEndDateTime(search.endYear(), search.endMonth());

        return queryFactory.selectFrom(qApartmentApplication).where(
            containRegion(search.region()),
            containHousingType(search.housingType()),
            containRentOrSale(search.rentOrSale()),
            qApartmentApplication.schedule.startDate.between(startDateTime, endDateTime));
    }



    @Override
    protected JPAQuery<Long> totalCountQuery(ApartmentApplicationSearchDto search) {
        var searchQuery = searchQuery(search);
        return searchQuery.select(qApartmentApplication.count());
    }

    private BooleanExpression containRegion(Region region) {
        return region == null ? null : qApartmentApplication.region.eq(region);
    }

    private BooleanExpression containHousingType(HousingType housingType) {
        return housingType == null ? null : qApartmentApplication.housingType.eq(housingType);
    }

    private BooleanExpression containRentOrSale(RentOrSale rentOrSale) {
        return rentOrSale == null ? null : qApartmentApplication.rentOrSale.eq(rentOrSale);
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
