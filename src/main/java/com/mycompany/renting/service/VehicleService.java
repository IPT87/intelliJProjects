package com.mycompany.renting.service;

import com.mycompany.renting.model.Vehicle;
import com.mycompany.renting.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository repository;

    public List<Vehicle> allVehicles() {
        return (List<Vehicle>) repository.findAll();
    }

    public void save(Vehicle vehicle) {
        repository.save(vehicle);
    }

    public Vehicle getVehicle(String licensePlate) {
        Optional<Vehicle> vehicle = repository.findById(licensePlate);
        return vehicle.get();
    }

    public void deleteVehicle(String licensePlate) {
        repository.deleteById(licensePlate);
    }
}
