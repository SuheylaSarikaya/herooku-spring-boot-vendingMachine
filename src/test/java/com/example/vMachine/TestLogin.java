package com.example.vMachine;

import com.example.vMachine.common.APIResponse;
import com.example.vMachine.dto.APIResponseDTO;
import com.example.vMachine.dto.ProductDTO;
import com.example.vMachine.dto.UserDTO;
import com.example.vMachine.entities.Product;
import com.example.vMachine.entities.User;
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
public class TestLogin {


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
    Optional<User> user;

    private UserDTO userDTO;
    @Before
    public void init() {
        vendingMachineService= new VendingMachineService( userRepository,  productRepository,  apiResponse,machineRepository);

        userDTO = new UserDTO();
        user = Optional.of(new User());
        user.get().setId(1L);
        user.get().setPassword("pass");
        user.get().setUsername("user");

        userDTO.setPassword("pass");
        userDTO.setUsername("user");

        Mockito.when(userRepository.findById(1L)).thenReturn(user);
    }

    @Test
    public void perform() {
        boolean errorMsg=true;
        vendingMachineService.login(userDTO);
        Assert.assertEquals(false,apiResponse.hasErrorMessage(errorMsg));

    }
}
