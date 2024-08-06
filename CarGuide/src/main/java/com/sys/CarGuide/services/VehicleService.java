package com.sys.CarGuide.services;

import com.sys.CarGuide.models.dto.VehicleDTO;
import com.sys.CarGuide.models.entities.Vehicle;
import com.sys.CarGuide.repositories.VehicleRepository;
import com.sys.CarGuide.services.exceptions.DatabaseException;
import com.sys.CarGuide.services.exceptions.ResourceNotFoundException;
import com.sys.CarGuide.services.mapper.VehicleMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Transactional(readOnly = true)
    public Page<VehicleDTO> findAllVehicleOrderByValueAmount(Pageable pageable) {
        Page<Vehicle> result = vehicleRepository.findAllByOrderByValueAmountAsc(pageable);
        return result.map(vehicle -> vehicleMapper.toVehicleDTO(vehicle));
    }

    @Transactional(readOnly = true)
    public VehicleDTO findVehicleById(Long id) {
        Optional<Vehicle> obj = vehicleRepository.findById(id);
        Vehicle entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return vehicleMapper.toVehicleDTO(entity);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public VehicleDTO insertNewVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleMapper.toVehicleEntity(vehicleDTO);
        vehicle = vehicleRepository.save(vehicle);
        return vehicleMapper.toVehicleDTO(vehicle);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional
    public VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO) {
        try {
            Vehicle vehicle = vehicleRepository.getReferenceById(id);
            vehicleMapper.updateVehicleFromDTO(vehicle, vehicleDTO);
            vehicle = vehicleRepository.save(vehicle);
            return vehicleMapper.toVehicleDTO(vehicle);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Transactional(propagation = Propagation.SUPPORTS)
    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new ResourceNotFoundException("resource not found");
        }
        try {
            vehicleRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Referential integrity failure");
        }
    }
}
