package io.clroot.boilerplate.housingapplication.dto.search;

import io.clroot.boilerplate.common.dto.SearchDto;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.housingapplication.model.OtherAptRandomType;

public record OtherAptRandomApplicationSearchDto(Region region,
                                                 String executorCompany,
                                                 OtherAptRandomType otherAptRandomType,
                                                 Integer startYear,
                                                 Integer startMonth,
                                                 Integer endYear,
                                                 Integer endMonth


                                                 ) implements SearchDto {

}
