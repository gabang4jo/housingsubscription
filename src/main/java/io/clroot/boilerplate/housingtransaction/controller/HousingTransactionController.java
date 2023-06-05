package io.clroot.boilerplate.housingtransaction.controller;


import io.clroot.boilerplate.common.controller.ApiResult;
import io.clroot.boilerplate.common.model.Coordinate;
import io.clroot.boilerplate.housingtransaction.dto.HousingTransactionDto;
import io.clroot.boilerplate.housingtransaction.model.HousingTransaction;
import io.clroot.boilerplate.housingtransaction.service.HousingTransactionService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class HousingTransactionController {


    private final HousingTransactionService housingTransactionService;

    @GetMapping("/getHousingTransaction")

    public ApiResult<List<HousingTransactionDto>> getNearHousingTranscation(Double latitude,
        Double longitude) {

        Coordinate coordinate = new Coordinate(latitude, longitude);
        return ApiResult.OK(housingTransactionService.getNearHousingTransaction(coordinate).stream()
            .map(HousingTransactionDto::new).collect(Collectors.toList()));
    }
}
