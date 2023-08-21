package com.example.vMachine;

import com.example.vMachine.common.APIResponse;
import com.example.vMachine.dto.ProductDTO;
import com.example.vMachine.entities.Machine;
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
public class TestCollectMoney {


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

    Optional<Machine> machine;

    @Before
    public void init() {
        vendingMachineService= new VendingMachineService( userRepository,  productRepository,  apiResponse,machineRepository);
        machine = Optional.of(new Machine());
        machine.get().setId(1L);
        machine.get().setTotalBalance(100.0);
        Mockito.when(machineRepository.findById(1L)).thenReturn(machine);
    }

    @Test
    public void perform() {
        vendingMachineService.collectMoney();
        double balance =machine.get().getTotalBalance();
        int totalBalance = (int)Math.round(balance);
        Assert.assertEquals(0,totalBalance);
    }
}
