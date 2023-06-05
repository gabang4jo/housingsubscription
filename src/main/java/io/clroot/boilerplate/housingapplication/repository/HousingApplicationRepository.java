package io.clroot.boilerplate.housingapplication.repository;

import io.clroot.boilerplate.housingapplication.model.HousingApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HousingApplicationRepository extends JpaRepository<HousingApplication,Long>,HousingApplicationRepositoryCustom {

}
