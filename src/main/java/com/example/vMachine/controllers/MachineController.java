package com.example.vMachine.controllers;

import com.example.vMachine.dto.ProductDTO;
import com.example.vMachine.services.VendingMachineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value= "/vendingMachine")
public class MachineController {

    private VendingMachineService vendingMachineService;

    private MachineController(VendingMachineService vendingMachineService){
        this.vendingMachineService =vendingMachineService;
    }

    @GetMapping("/getProduct")
    public List<ProductDTO> getAllProduct(){
        return vendingMachineService.getAllProducts();
    }

}
