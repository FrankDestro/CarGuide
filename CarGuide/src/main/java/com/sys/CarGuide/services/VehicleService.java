package com.sys.CarGuide.services;

import com.sys.CarGuide.models.dto.VehicleDTO;
import com.sys.CarGuide.models.entities.Vehicle;
import com.sys.CarGuide.repositories.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public Page<VehicleDTO> findAllVehicleOrderByValueAmount(Pageable pageable) {
        Page<Vehicle> result = vehicleRepository.findAllByOrderByValueAmountAsc(pageable);
        return result.map(vehicle -> new VehicleDTO(vehicle));
    }
}
