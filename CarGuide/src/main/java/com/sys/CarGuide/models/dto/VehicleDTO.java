package com.sys.CarGuide.models.dto;

import com.sys.CarGuide.models.entities.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class VehicleDTO {

    private Long id;
    private String name;
    private String model;
    private String brad;
    private Double valueAmount;

    private List<VehicleImagesDTO> vehicleImages = new ArrayList<>();

    public VehicleDTO(Vehicle vehicleEntity) {
        id = vehicleEntity.getId();
        name = vehicleEntity.getName();
        model = vehicleEntity.getModel();
        brad = vehicleEntity.getBrand();
        valueAmount = vehicleEntity.getValueAmount();
        this.vehicleImages = vehicleEntity.getVehicleImages().stream()
                .map(VehicleImagesDTO::new)
                .collect(Collectors.toList());
    }
}
