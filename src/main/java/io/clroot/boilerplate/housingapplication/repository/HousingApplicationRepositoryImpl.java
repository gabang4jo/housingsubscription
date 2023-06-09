package io.clroot.boilerplate.housingapplication.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.*;
import io.clroot.boilerplate.housingapplication.dto.ApartmentApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.NonApartmentApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.OtherAptRandomApplicationDto;
import io.clroot.boilerplate.housingapplication.model.ApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.HousingApplication;
import io.clroot.boilerplate.housingapplication.model.NonApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomApplication;
import io.clroot.boilerplate.housingapplication.model.QApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.QHousingApplication;
import io.clroot.boilerplate.housingapplication.model.QNonApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.QOtherAptRandomApplication;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

@RequiredArgsConstructor
public class HousingApplicationRepositoryImpl implements HousingApplicationRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;
    private final QHousingApplication qHousingApplication = QHousingApplication.housingApplication;

    private final QApartmentApplication qApartmentApplication = QApartmentApplication.apartmentApplication;

    private final QNonApartmentApplication qNonApartmentApplication = QNonApartmentApplication.nonApartmentApplication;

    private final QOtherAptRandomApplication qOtherAptRandomApplication = QOtherAptRandomApplication.otherAptRandomApplication;


    @Override
    public List<HousingApplication> findAllByYearAndMonth(int year, int month) {
        LocalDateTime startOfMonth = LocalDateTime.of(year, month, 1, 0, 0, 0);
        LocalDateTime endOfMonth = startOfMonth.with(TemporalAdjusters.lastDayOfMonth())
            .withHour(23).withMinute(59).withSecond(59);

        return jpaQueryFactory
            .selectFrom(qHousingApplication)
            .where(qHousingApplication.schedule.startDate.lt(endOfMonth)
                .and(qHousingApplication.schedule.endDate.gt(startOfMonth)))
            .fetch();
    }


    @Override
    public Page<ApartmentApplicationDto> findAllApartmentApplication(Pageable pageable) {
        List<ApartmentApplication> list = jpaQueryFactory.selectFrom(qApartmentApplication)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        List<ApartmentApplicationDto> content = list.stream()
            .map(ApartmentApplicationDto::new).toList();

        Long totalCount = jpaQueryFactory.select(qApartmentApplication.count())
            .from(qApartmentApplication).fetchOne();

        return new PageImpl<>(content, pageable, totalCount);


    }


    @Override
    public Page<NonApartmentApplicationDto> findAllNonApartmentApplication(Pageable pageable) {
        List<NonApartmentApplication> list = jpaQueryFactory.selectFrom(qNonApartmentApplication)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        List<NonApartmentApplicationDto> content = list.stream()
            .map(NonApartmentApplicationDto::new).toList();

        Long totalCount= jpaQueryFactory.select(qNonApartmentApplication.count())
            .from(qNonApartmentApplication).fetchOne();

        return new PageImpl<>(content, pageable, totalCount);
    }

    @Override
    public Page<OtherAptRandomApplicationDto> findAllOtherAptRandomApplication(Pageable pageable) {
        List<OtherAptRandomApplication> list = jpaQueryFactory.selectFrom(qOtherAptRandomApplication)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        List<OtherAptRandomApplicationDto> content = list.stream()
            .map(OtherAptRandomApplicationDto::new).toList();

        Long totalCount= jpaQueryFactory.select(qOtherAptRandomApplication.count())
            .from(qOtherAptRandomApplication).fetchOne();

        return new PageImpl<>(content, pageable, totalCount);
    }
}
