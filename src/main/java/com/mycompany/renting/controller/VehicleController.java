package com.mycompany.renting.controller;

import com.mycompany.renting.model.Vehicle;
import com.mycompany.renting.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping(path = "/vehicles")
    public String showAllVehicles(Model model) {
        List<Vehicle> vehicleList = vehicleService.allVehicles();
        model.addAttribute("vehicleList", vehicleList);
        return "vehicles";
    }

    @GetMapping(path = "/vehicle/new")
    public String addNewVehicle(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("pageTitle", "Add new vehicle");
        model.addAttribute("btnTitle", "Add vehicle");

        return "add_new_vehicle";
    }

    @PostMapping(path = "/vehicles/save")
    public String saveNewVehicle(Vehicle vehicle) {
        vehicleService.save(vehicle);

        return "redirect:/vehicles";
    }

    @GetMapping(path = "/modify/{licensePlate}")
    public String modifyVehicle(@PathVariable("licensePlate") String licensePlate, Model model) {
        Vehicle vehicle = vehicleService.getVehicle(licensePlate);
        model.addAttribute("vehicle", vehicle);
        model.addAttribute("pageTitle", "Modify vehicle (License plate: " + licensePlate + ")");
        model.addAttribute("btnTitle", "Modify vehicle");

        return "modify_vehicle";
    }

    @GetMapping(path = "/delete/{licensePlate}")
    public String deleteVehicle(@PathVariable("licensePlate") String licensePlate, RedirectAttributes redirectAttributes) {
        vehicleService.deleteVehicle(licensePlate);
        redirectAttributes.addFlashAttribute("message", "Vehicle with license plate: " + licensePlate + " deleted successfully.");
        return "redirect:/vehicles";
    }

}
