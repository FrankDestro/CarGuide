package com.sys.CarGuide.services.mapper;

import com.sys.CarGuide.models.dto.VehicleDTO;
import com.sys.CarGuide.models.entities.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleMapper {

    VehicleDTO toVehicleDTO(Vehicle vehicle);

    Vehicle toVehicleEntity(VehicleDTO vehicleDTO);

    @Mapping(target = "id", ignore = true)
    void updateVehicleFromDTO(@MappingTarget Vehicle vehicle, VehicleDTO vehicleDTO);
}
