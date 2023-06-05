package io.clroot.boilerplate.housingapplication.dto.search;

import io.clroot.boilerplate.common.dto.SearchDto;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.housingapplication.model.NonApartmentType;

public record NonApartmentApplicationSearchDto(NonApartmentType nonApartmentType,
                                               Region region,
                                               String executorCompany,
                                               Integer startYear,
                                               Integer startMonth,
                                               Integer endYear,
                                               Integer endMonth
                                               ) implements SearchDto {

}
