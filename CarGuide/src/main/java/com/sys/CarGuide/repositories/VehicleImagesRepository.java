package com.sys.CarGuide.repositories;

import com.sys.CarGuide.models.entities.VehicleImages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleImagesRepository extends JpaRepository <VehicleImages, Long> {
}
