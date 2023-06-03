package io.clroot.boilerplate.housingapplication.controller;

import static io.clroot.boilerplate.common.controller.ApiResult.*;

import com.electronwill.nightconfig.core.conversion.Path;
import io.clroot.boilerplate.common.config.support.Pageable;
import io.clroot.boilerplate.common.config.support.SimplePageRequest;
import io.clroot.boilerplate.common.controller.ApiResult;
import io.clroot.boilerplate.housingapplication.dto.ApartmentApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.HousingApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.NonApartmentApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.OtherAptRandomApplicationDto;
import io.clroot.boilerplate.housingapplication.dto.search.ApartmentApplicationSearchDto;
import io.clroot.boilerplate.housingapplication.dto.search.NonApartmentApplicationSearchDto;
import io.clroot.boilerplate.housingapplication.dto.search.OtherAptRandomApplicationSearchDto;
import io.clroot.boilerplate.housingapplication.model.ApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.HousingApplication;
import io.clroot.boilerplate.housingapplication.model.NonApartmentApplication;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomApplication;
import io.clroot.boilerplate.housingapplication.service.HousingApplicationService;
import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/applications")
public class HousingApplicationController {

    private final HousingApplicationService housingApplicationService;


    @GetMapping("/")
    public ApiResult<List<HousingApplicationDto>> getApplicationByMonth(
        @RequestParam String year, @RequestParam String month) {
        return OK(
            housingApplicationService.findApplicationsByYearAndMonth(year, month).stream().map(
                HousingApplicationDto::new).toList());
    }

    @GetMapping("/{id}")
    public ApiResult<HousingApplicationDto> getApplicationById(
        @PathVariable Long id) {

        HousingApplication application = housingApplicationService.findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));

        if (application instanceof ApartmentApplication) {
            return OK(new ApartmentApplicationDto((ApartmentApplication) application));
        } else if (application instanceof NonApartmentApplication) {
            return OK(new NonApartmentApplicationDto((NonApartmentApplication) application));
        } else if (application instanceof OtherAptRandomApplication) {
            return OK(new OtherAptRandomApplicationDto((OtherAptRandomApplication) application));
        }

        throw new RuntimeException("Unexpected type of application");
    }


    @GetMapping("/apartment")
    public ApiResult<Page<ApartmentApplicationDto>> getApartmentApplications(Pageable pageable) {

        return OK(
            housingApplicationService.getApartmentApplications(pageable.page(), pageable.size()));
    }

    @GetMapping("/non-apartment")
    public ApiResult<Page<NonApartmentApplicationDto>> getNonApartmentApplications(
        SimplePageRequest pageable) {

        return OK(
            housingApplicationService.getNonApartmentApplications(pageable.page(),
                pageable.size()));
    }


    @GetMapping("/other-apt-random")
    public ApiResult<Page<OtherAptRandomApplicationDto>> getOtherAptRandomApplications(
        Pageable pageable) {

        return OK(
            housingApplicationService.getOtherAptRandomApplications(pageable.page(),
                pageable.size()));
    }

    @GetMapping("/apartment/search")
    public ApiResult<Page<ApartmentApplicationDto>> searchApartmentApplications(
        Pageable pageable, ApartmentApplicationSearchDto searchDto) {
        return OK(housingApplicationService.searchApartmentApplication(searchDto, pageable.page(),
            pageable.size()).map(ApartmentApplicationDto::new));
        // return results as needed
    }

    @GetMapping("/non-apartment/search")
    public ApiResult<Page<NonApartmentApplicationDto>> searchNonApartmentApplications(
        Pageable pageable, NonApartmentApplicationSearchDto searchDto) {
        return OK(
            housingApplicationService.searchNonApartmentApplication(searchDto, pageable.page(),
                pageable.size()).map(NonApartmentApplicationDto::new));
        // return results as needed
    }

    @GetMapping("/other-apt-random/search")
    public ApiResult<Page<OtherAptRandomApplicationDto>> searchOtherAptRandomApplications(
        Pageable pageable, OtherAptRandomApplicationSearchDto searchDto) {
        return OK(
            housingApplicationService.searchOtherRandomAptApplication(searchDto, pageable.page(),
                pageable.size()).map(OtherAptRandomApplicationDto::new));
        // return results as needed
    }


}
