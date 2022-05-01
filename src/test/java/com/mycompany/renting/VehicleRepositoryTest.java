package com.mycompany.renting;

import com.mycompany.renting.model.Vehicle;
import com.mycompany.renting.repository.VehicleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Collection;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class VehicleRepositoryTest {
    @Autowired
    VehicleRepository repository;

    @Test
    public void testAddNewVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("2981LKN");
        vehicle.setBrand("Audi");
        vehicle.setModel("A5");
        vehicle.setColor("Black");
        vehicle.setPower(170);
        vehicle.setPriceDay(75);

        Vehicle savedVehicle = repository.save(vehicle);

        Assertions.assertEquals(vehicle.getLicensePlate(), savedVehicle.getLicensePlate());
        Assertions.assertNotNull(savedVehicle.getBrand());

    }

    @Test
    public void testVehicleCollectionSize() {
        Iterable<Vehicle> savedVehiclesCollection = repository.findAll();

        Assertions.assertNotNull(savedVehiclesCollection.iterator().next());
    }

    @Test
    public void testUpdateVehicle() {
        Optional<Vehicle> optionalVehicle = repository.findById("2981LKN");
        Vehicle vehicle = optionalVehicle.get();
        vehicle.setPower(175);

        Assertions.assertEquals(175, repository.findById("2981LKN").get().getPower());
    }

}
