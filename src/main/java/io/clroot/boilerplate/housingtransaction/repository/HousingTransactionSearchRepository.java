package io.clroot.boilerplate.housingtransaction.repository;

import static io.clroot.boilerplate.housingtransaction.model.QHousingTransaction.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.clroot.boilerplate.common.model.Coordinate;
import io.clroot.boilerplate.housingtransaction.model.HousingTransaction;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class HousingTransactionSearchRepository {


    private final JPAQueryFactory jpaQueryFactory;


    public List<HousingTransaction> findAllHousingTransaction(Pair<Coordinate, Coordinate> vertex) {

        Coordinate leftTop = vertex.getLeft();
        Coordinate rightBottom = vertex.getRight();

      return  jpaQueryFactory.selectFrom(housingTransaction)
            .where(
                housingTransaction.houseInfo.coordinate.latitude.between(leftTop.getLatitude(),
                    rightBottom.getLatitude()),housingTransaction.houseInfo.coordinate.longitude.between(leftTop.getLongitude(),
                    rightBottom.getLongitude())
            ).fetch();
    }
}
