package com.example.vMachine.services;

import com.example.vMachine.common.APIResponse;
import com.example.vMachine.dto.APIResponseDTO;
import com.example.vMachine.dto.ProductDTO;
import com.example.vMachine.dto.UserDTO;
import com.example.vMachine.entities.Machine;
import com.example.vMachine.entities.Product;
import com.example.vMachine.entities.User;
import com.example.vMachine.exception.ProductNotFoundException;
import com.example.vMachine.exception.ResourceNotFoundException;
import com.example.vMachine.repos.MachineRepository;
import com.example.vMachine.repos.ProductRepository;
import com.example.vMachine.repos.UserRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VendingMachineService {


    private UserRepository userRepository;
    private ProductRepository productRepository;

    private APIResponse apiResponse;
    private  MachineRepository machineRepository;



    public VendingMachineService(UserRepository userRepository, ProductRepository productRepository, APIResponse apiResponse,
                                 MachineRepository machineRepository){
        this.userRepository =userRepository;
        this.productRepository=productRepository;
        this.apiResponse=apiResponse;
        this.machineRepository = machineRepository;
    }
    private ProductDTO entityToDTO(Product product) throws IOException {
/*
        byte[] fileContent = FileUtils.readFileToByteArray(new File(product.getImg()));

        String encodedString = Base64.getEncoder().encodeToString(fileContent); */
        String[] arrOfStr = product.getImg().split("/",7);
        String myStr="https://drive.google.com/uc?export=view&id="+arrOfStr[5];
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setImg(myStr);
        productDTO.setPrice(product.getPrice());
        productDTO.setCount(product.getCount());
        productDTO.setTemperature(product.getTemperature());

        return productDTO;
    }
    public List<ProductDTO> getAllProducts() {


        List<Product> products= productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Products", "ids", products);
        }

        List<ProductDTO> productsDTOs= new ArrayList<>();


        products.forEach( (product) -> {


            ProductDTO productDTO= null;
            try {
                productDTO = entityToDTO(product);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            productsDTOs.add(productDTO);
        });


        return productsDTOs;
    }

    public APIResponseDTO login(UserDTO newUser) {
        //TODO

        User user = userRepository.findById(1L)
                .orElseThrow( () -> new ResourceNotFoundException("user","id",1L));

        if((newUser.getUsername().equals(user.getUsername())) && (newUser.getPassword().equals(user.getPassword() ))){
            apiResponse.addInformationMessage("User is logged in.");
        }else{
            apiResponse.addErrorMessage("User login failed.");
            apiResponse.hasErrorMessage(true);
        }
        return apiResponse.createServiceResult();
    }

    public APIResponseDTO updateCountProduct(ProductDTO newProduct) {
        Product product = productRepository.findById(newProduct.getId())
                .orElseThrow( () -> new ResourceNotFoundException("product","id",newProduct.getId()));

        if(newProduct.getCount()<=30){
            product.setCount(newProduct.getCount());
            productRepository.save(product);
            apiResponse.addInformationMessage("Stock is updated");
        }else{
            apiResponse.addErrorMessage("Stock capacity is full.");
        }
        return apiResponse.createServiceResult();

    }

    public APIResponseDTO updatePriceProduct(ProductDTO newProduct) {
        Product product = productRepository.findById(newProduct.getId())
                .orElseThrow( () -> new ResourceNotFoundException("product","id",newProduct.getId()));
        product.setPrice(newProduct.getPrice());
        productRepository.save(product);
        apiResponse.addInformationMessage("Price is updated");
        return apiResponse.createServiceResult();

    }

    public APIResponseDTO buyOneProduct(ProductDTO productDTO) {

        Product product = productRepository.findById(productDTO.getId())
                .orElseThrow( () -> new ResourceNotFoundException("product","id",productDTO.getId()));
        Machine machine= machineRepository.findById(1L)
                .orElseThrow( () -> new ResourceNotFoundException("machine","id",1L ) );

        if (product.getCount() <= 0) {
            apiResponse.addErrorMessage("The product is not available.");
        }else {

            product.setCount(product.getCount() - 1);
            productRepository.save(product);

            machine.setTotalBalance(machine.getTotalBalance() + product.getPrice());
            machineRepository.save(machine);

            apiResponse.addInformationMessage(product.getName() + " is given.");
        }
        return apiResponse.createServiceResult();

    }

    public APIResponseDTO resetMachine() {

        List <Product> products= productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Products", "ids", products);
        }
        products.forEach( (product) -> {
            product.setCount(30);
            productRepository.save(product);
        } );

        apiResponse.addInformationMessage( " Machine is reset.");
        return apiResponse.createServiceResult();

    }


    public APIResponseDTO collectMoney() {
        Machine machine= machineRepository.findById(1L)
                .orElseThrow( () -> new ResourceNotFoundException("machine","id",1L ) );

        machine.setTotalBalance(0.0);
        machineRepository.save(machine);
        apiResponse.addInformationMessage( " Money is collected.");
        return apiResponse.createServiceResult();

    }

    public void performCoolingOperation() {
        List <Product> products= productRepository.findAll();
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Products", "ids", products);
        }
        products.forEach( (product) -> {
            if(product.getTemperature()>20){
                product.setTemperature(20);
                System.out.println("cooling operation running...");
            }
            productRepository.save(product);
        } );
    }

    @Scheduled(fixedRate = 10000) // Run every 1 minutes
    public void performCoolingTask() {
        performCoolingOperation();
    }

}
