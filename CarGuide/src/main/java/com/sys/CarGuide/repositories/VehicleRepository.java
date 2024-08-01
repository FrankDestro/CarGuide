package com.sys.CarGuide.repositories;

import com.sys.CarGuide.models.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository <Vehicle, Long> {

    Page<Vehicle> findAllByOrderByValueAmountAsc(Pageable pageable);

}
