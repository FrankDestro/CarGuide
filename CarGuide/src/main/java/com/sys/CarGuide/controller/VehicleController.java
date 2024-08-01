package com.sys.CarGuide.controller;

import com.sys.CarGuide.models.dto.VehicleDTO;
import com.sys.CarGuide.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<Page<VehicleDTO>> findAllVehicleOrderByValueAmount(Pageable pageable){
        Page<VehicleDTO> list = vehicleService.findAllVehicleOrderByValueAmount(pageable);
        return ResponseEntity.ok().body(list);
    }
}
