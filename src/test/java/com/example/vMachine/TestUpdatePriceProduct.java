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

import java.util.Optional;

@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {VMachineApplication.class})
public class TestUpdatePriceProduct {


    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private VendingMachineService vendingMachineService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MachineRepository machineRepository;

    @Mock
    private APIResponse apiResponse;
    Optional<Product> product;

    private ProductDTO productDTO;
    @Before
    public void init() {
        vendingMachineService= new VendingMachineService( userRepository,  productRepository,  apiResponse,machineRepository);
        productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setPrice(25);
        product = Optional.of(new Product());

        product.get().setId(1L);
        product.get().setName("test");
        product.get().setPrice(12);
        product.get().setCount(2);
        product.get().setTemperature(20);
        product.get().setCapacity(30);

    }

    @Test
    public void perform() {
        Mockito.when(productRepository.findById(1L)).thenReturn(product);
        vendingMachineService.updatePriceProduct(productDTO);
        Assert.assertEquals(25,product.get().getPrice());
    }
}