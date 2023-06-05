package io.clroot.boilerplate.housingtransaction.service;


import io.clroot.boilerplate.common.model.Coordinate;
import io.clroot.boilerplate.housingtransaction.model.HousingTransaction;
import io.clroot.boilerplate.housingtransaction.repository.HousingTransactionRepository;
import io.clroot.boilerplate.housingtransaction.repository.HousingTransactionSearchRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HousingTransactionService {


    private final HousingTransactionRepository housingTransactionRepository;

    private final HousingTransactionSearchRepository housingTransactionSearchRepository;




    public List<HousingTransaction> getNearHousingTransaction(Coordinate coordinate){
        Pair<Coordinate, Coordinate> vertex = coordinate.getVertex(1.0);
        return housingTransactionSearchRepository.findAllHousingTransaction(vertex);
    }


}
