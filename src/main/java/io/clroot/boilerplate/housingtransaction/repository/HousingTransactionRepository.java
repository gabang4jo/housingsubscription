package io.clroot.boilerplate.housingtransaction.repository;

import io.clroot.boilerplate.housingtransaction.model.HousingTransaction;
import java.util.List;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HousingTransactionRepository extends JpaRepository<HousingTransaction,Long> {


}
