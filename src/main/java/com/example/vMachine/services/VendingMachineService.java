package com.example.vMachine.services;

import com.example.vMachine.common.APIResponse;
import com.example.vMachine.dto.ProductDTO;
import com.example.vMachine.entities.Product;
import com.example.vMachine.exception.ProductNotFoundException;
import com.example.vMachine.repos.ProductRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class VendingMachineService {


    private ProductRepository productRepository;

    private APIResponse apiResponse;

    private VendingMachineService(ProductRepository productRepository, APIResponse apiResponse){

        this.productRepository=productRepository;
        this.apiResponse=apiResponse;
    }
    private ProductDTO entityToDTO(Product product) throws IOException {

        byte[] fileContent = FileUtils.readFileToByteArray(new File(product.getImg()));

        String encodedString = Base64.getEncoder().encodeToString(fileContent);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setImg(encodedString);
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


        int n= products.size();
        for(int i=0; i==n; i++) {
            ProductDTO productDTO= null;
            try {
                productDTO = entityToDTO(products.get(i));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            productsDTOs.add(productDTO);
        };

        return productsDTOs;
    }

}
