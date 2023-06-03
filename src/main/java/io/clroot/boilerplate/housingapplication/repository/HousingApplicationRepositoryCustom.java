package io.clroot.boilerplate.housingapplication.repository;

import io.clroot.boilerplate.housingapplication.dto.ApartmentApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.NonApartmentApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.OtherAptRandomApplicationDto;
import io.clroot.boilerplate.housingapplication.model.ApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.HousingApplication;
import io.clroot.boilerplate.housingapplication.model.NonApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomApplication;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HousingApplicationRepositoryCustom {

    List<HousingApplication> findAllByYearAndMonth(int year,int month);

    Page<ApartmentApplicationDto> findAllApartmentApplication(Pageable pageable);

    Page<NonApartmentApplicationDto> findAllNonApartmentApplication(Pageable pageable);

    Page<OtherAptRandomApplicationDto> findAllOtherAptRandomApplication(Pageable pageable);

}
