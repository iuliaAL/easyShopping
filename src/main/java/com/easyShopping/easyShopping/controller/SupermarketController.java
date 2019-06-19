package com.easyShopping.easyShopping.controller;

import com.easyShopping.easyShopping.dto.SupermarketDto;
import com.easyShopping.easyShopping.dto.SupermarketProductDto;
import com.easyShopping.easyShopping.service.SupermarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SupermarketController {
    @Autowired
    private SupermarketService supermarketService;

    @PostMapping("/supermarket")
    public ResponseEntity<SupermarketDto> addSupermarket(@RequestBody SupermarketDto supermarketDto)
    {

        SupermarketDto supermarketDtoResult = supermarketService.addSupermarket(supermarketDto);
        return ResponseEntity.ok(supermarketDtoResult);

    }

    @GetMapping("/supermarket")
    public List<SupermarketDto> getAllSupermarket()
    {

        return supermarketService.getAllSupermarkets();

    }

    @PutMapping("/supermarket/{id}/{name}")
    public ResponseEntity<SupermarketDto> updateSupermarket (@PathVariable Long id, @PathVariable String name)
    {

        SupermarketDto supermarketDtoResult = supermarketService.updateSupermarket(name, id);
        return ResponseEntity.ok(supermarketDtoResult);

    }

    @PostMapping("/supermarket/product/{supermarketId}")
    public ResponseEntity<SupermarketDto>addProductToSupermarket(@RequestBody SupermarketProductDto supermarketProductDto, @PathVariable final Long supermarketId)
    {

        SupermarketDto supermarketListDtoResult=supermarketService.addProductToSupermarket(supermarketId,supermarketProductDto);
        return ResponseEntity.ok(supermarketListDtoResult);

    }

    @PutMapping("/supermarket/product/{productId}")
    public ResponseEntity<SupermarketProductDto>updateProduct(@PathVariable Long productId, @RequestBody SupermarketProductDto productDto)
    {
        SupermarketProductDto prouct= supermarketService.updateProduct(productId, productDto);
        return ResponseEntity.ok(prouct);
    }

    @GetMapping("/supermarket/{id}")
    public List<SupermarketProductDto> getAllProductsForSupermarket(@PathVariable Long id)
    {

        return  supermarketService.showProductsFromASupermarket(id);

    }
    @DeleteMapping("/supermarket/product/{productId}")
    public void deleteSupermarketProduct(@PathVariable Long productId)
    {
        supermarketService.deleteSupermarketProduct(productId);
    }

    @DeleteMapping("/supermarket/{supermarketId}")
    public  void deleteSupermarket(@PathVariable Long supermarketId)
    {
        supermarketService.deleteSupermarket(supermarketId);
    }

}
