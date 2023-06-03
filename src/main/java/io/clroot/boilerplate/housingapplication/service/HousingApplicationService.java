package io.clroot.boilerplate.housingapplication.service;

import static com.google.common.base.Preconditions.checkArgument;
import static org.apache.commons.lang3.math.NumberUtils.toInt;

import com.querydsl.core.Fetchable;
import io.clroot.boilerplate.housingapplication.dto.ApartmentApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.NonApartmentApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.OtherAptRandomApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.search.ApartmentApplicationSearchDto;
import io.clroot.boilerplate.housingapplication.dto.search.NonApartmentApplicationSearchDto;
import io.clroot.boilerplate.housingapplication.dto.search.OtherAptRandomApplicationSearchDto;
import io.clroot.boilerplate.housingapplication.model.ApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.HousingApplication;
import io.clroot.boilerplate.housingapplication.model.NonApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomApplication;
import io.clroot.boilerplate.housingapplication.repository.HousingApplicationRepository;
import io.clroot.boilerplate.housingapplication.repository.search.ApartmentApplicationSearchRepository;
import io.clroot.boilerplate.housingapplication.repository.search.NonApartmentApplicationSearchRepository;
import io.clroot.boilerplate.housingapplication.repository.search.OtherAptRandomApplicationSearchRepository;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HousingApplicationService {

    private final HousingApplicationRepository housingApplicationRepository;

    private final ApartmentApplicationSearchRepository apartmentApplicationSearchRepository;

    private final NonApartmentApplicationSearchRepository nonApartmentApplicationSearchRepository;

    private final OtherAptRandomApplicationSearchRepository otherAptRandomApplicationSearchRepository;

    private static final int DEFAULT_MONTH = 5;
    private static final int DEFAULT_YEAR = 2023;

    public List<HousingApplication> findApplicationsByYearAndMonth(String strYear,
        String strMonth) {
        int month = toInt(strMonth, DEFAULT_MONTH);
        if (month < 1 || month > 12) {
            month = DEFAULT_MONTH;
        }

        int year = toInt(strMonth, DEFAULT_YEAR);
        if (year < 2021 || year > 2023) {
            year = DEFAULT_MONTH;
        }

        return housingApplicationRepository.findAllByYearAndMonth(year, month);
    }


    public Optional<HousingApplication> findById(Long id) {
        return housingApplicationRepository.findById(id);
    }


    public Page<ApartmentApplicationDto> getApartmentApplications(int page, int size) {

        return housingApplicationRepository.findAllApartmentApplication(
            getPageable(page, size));
    }

    public Page<NonApartmentApplicationDto> getNonApartmentApplications(int page, int size) {

        return housingApplicationRepository.findAllNonApartmentApplication(getPageable(page, size));
    }

    public Page<OtherAptRandomApplicationDto> getOtherAptRandomApplications(int page, int size) {

        return housingApplicationRepository.findAllOtherAptRandomApplication(
            getPageable(page, size));
    }

    public Page<ApartmentApplication> searchApartmentApplication(
        ApartmentApplicationSearchDto aptSearchDto, int page, int size) {

        validateYearMonth(aptSearchDto.startYear(), aptSearchDto.startMonth(),
            aptSearchDto.endYear(), aptSearchDto.endMonth());

        return apartmentApplicationSearchRepository.search(aptSearchDto, getPageable(page, size));

    }

    public Page<NonApartmentApplication> searchNonApartmentApplication(
        NonApartmentApplicationSearchDto nonAptSearchDto, int page, int size) {
        validateYearMonth(nonAptSearchDto.startYear(), nonAptSearchDto.startMonth(),
            nonAptSearchDto.endYear(), nonAptSearchDto.endMonth());

        return nonApartmentApplicationSearchRepository.search(nonAptSearchDto,
            getPageable(page, size));

    }

    public Page<OtherAptRandomApplication> searchOtherRandomAptApplication(
        OtherAptRandomApplicationSearchDto otherAptRandomSearchDto, int page, int size) {
        validateYearMonth(otherAptRandomSearchDto.startYear(), otherAptRandomSearchDto.startMonth(),
            otherAptRandomSearchDto.endYear(), otherAptRandomSearchDto.endMonth());

        return otherAptRandomApplicationSearchRepository.search(otherAptRandomSearchDto,
            getPageable(page, size));

    }

    private int checkPage(int page) {
        return Math.max(page, 0);
    }

    private int checkSize(int size) {
        return (size < 1 || size > 10) ? 10 : size;
    }


    public void validateYearMonth(int startYear, int startMonth, int endYear, int endMonth) {
        checkArgument(startYear >= 2021 && startYear <= 2022, "Invalid start year: " + startYear);
        checkArgument(startMonth >= 1 && startMonth <= 12, "Invalid start month: " + startMonth);
        checkArgument(endYear >= 2021 && endYear <= 2022, "Invalid end year: " + endYear);
        checkArgument(endMonth >= 1 && endMonth <= 12, "Invalid end month: " + endMonth);

        LocalDateTime startDateTime = LocalDateTime.of(startYear, startMonth, 1, 0, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(endYear, endMonth, Month.of(endMonth).length(
            Year.isLeap(endYear)), 23, 59, 59);

        checkArgument(startDateTime.isBefore(endDateTime) || startDateTime.isEqual(endDateTime),
            "Start date must be before or equal to end date");
    }

    public Pageable getPageable(int page, int size) {
        page = checkPage(page);
        size = checkSize(size);
        return PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
    }

}
