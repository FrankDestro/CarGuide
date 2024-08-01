package com.sys.CarGuide.models.dto;


import com.sys.CarGuide.models.entities.VehicleImages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class VehicleImagesDTO {

    private Long id;
    private String imageUrl;

    public VehicleImagesDTO(VehicleImages vehicleImagesEntity) {
        this.id = vehicleImagesEntity.getId();
        this.imageUrl = vehicleImagesEntity.getImageUrl();
    }
}
