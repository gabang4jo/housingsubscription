package io.clroot.boilerplate.housingapplication.dto.search;

import io.clroot.boilerplate.common.dto.SearchDto;
import io.clroot.boilerplate.common.model.Region;
import io.clroot.boilerplate.housingapplication.model.HousingType;
import io.clroot.boilerplate.housingapplication.model.RentOrSale;
import java.time.LocalDateTime;

public record ApartmentApplicationSearchDto (HousingType housingType,
                                             Region region,
                                             RentOrSale rentOrSale,
                                             int  startYear,
                                             int startMonth,
                                             int endYear,
                                             int endMonth

                                             )implements SearchDto{

}
