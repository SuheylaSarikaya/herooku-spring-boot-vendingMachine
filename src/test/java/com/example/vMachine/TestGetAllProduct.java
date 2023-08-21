package com.example.vMachine;

import com.example.vMachine.common.APIResponse;
import com.example.vMachine.dto.ProductDTO;
import com.example.vMachine.entities.Product;
import com.example.vMachine.repos.MachineRepository;
import com.example.vMachine.repos.ProductRepository;
import com.example.vMachine.repos.UserRepository;
import com.example.vMachine.services.VendingMachineService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {VMachineApplication.class})
public class TestGetAllProduct {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private VendingMachineService vendingMachineService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private  MachineRepository machineRepository;

    @Mock
    private APIResponse apiResponse;

    Optional<Product> product;

    private ProductDTO productDTO;

    @Before
    public void init() {
        vendingMachineService= new VendingMachineService( userRepository,  productRepository,  apiResponse,machineRepository);
        product = Optional.of(new Product());
        productDTO = new ProductDTO();
        product.get().setId(1L);
        product.get().setName("test");
        product.get().setPrice(12);
        product.get().setTemperature(20);
        product.get().setImg("https://drive.google.com/file/d/1iYzePjyKO9qG2M_Ea4E-gy-Af8eU3FWj/view?usp=drive_link");

        List<Product> productList= new ArrayList<>();
        productList.add(product.get());

        Mockito.when(productRepository.findAll()).thenReturn(productList);
    }

    @Test
    public void perform() {

        List<ProductDTO> productDTOs= vendingMachineService.getAllProducts();
        Assert.assertEquals(false,productDTOs.isEmpty());
    }

}
