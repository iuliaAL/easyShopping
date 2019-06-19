package com.easyShopping.easyShopping.service;

import com.easyShopping.easyShopping.dto.ProductDto;
import com.easyShopping.easyShopping.dto.SupermarketDto;
import com.easyShopping.easyShopping.Mapper.Mapper;
import com.easyShopping.easyShopping.dto.SupermarketProductDto;
import com.easyShopping.easyShopping.model.Supermarket;
import com.easyShopping.easyShopping.model.SupermarketProduct;
import com.easyShopping.easyShopping.repository.ProductRepository;
import com.easyShopping.easyShopping.repository.SupermarketProductRepository;
import com.easyShopping.easyShopping.repository.SupermarketRepository;
import com.easyShopping.easyShopping.service.Exception.SupermarketProductServiceException;
import com.easyShopping.easyShopping.service.Exception.SupermarketProductServiceExceptionCode;
import com.easyShopping.easyShopping.service.Exception.SupermarketServiceException;
import com.easyShopping.easyShopping.service.Exception.SupermarketServiceExceptionCode;

import net.bytebuddy.implementation.bind.annotation.Super;
import org.eclipse.core.internal.runtime.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SupermarketService {
    private static final Logger log = LoggerFactory.getLogger(SupermarketService.class);

    private SupermarketRepository supermarketRepository;
    private SupermarketProductRepository supermarketProductRepository;
    private Mapper mapper;

    public SupermarketService(SupermarketRepository supermarketRepository, Mapper mapper,SupermarketProductRepository supermarketProductRepository) {
        this.supermarketRepository = supermarketRepository;
        this.supermarketProductRepository=supermarketProductRepository;
        this.mapper = mapper;
    }

    public List<SupermarketDto> getAllSupermarkets()
    {
        log.info("getAllSupermarkets");
        List<Supermarket> supermarketList = supermarketRepository.findAll();
        List<SupermarketDto> supermarketDtos= new ArrayList<SupermarketDto>();
        for(Supermarket supermarket:supermarketList)
        {
            supermarketDtos.add(mapper.supermarketToDto(supermarket));
        }
        log.info("supermarketList={}", supermarketDtos);
        return supermarketDtos;
    }

    public SupermarketDto addSupermarket(SupermarketDto supermarketDto)
    {
        log.info("add new Supermarket: supermarketDto = {}", supermarketDto);
        Supermarket newSupermarket=mapper.supermarketDtoToEntity(supermarketDto);
        Optional<Supermarket> supermarketOptional = supermarketRepository.findByName(newSupermarket.getName());
        if (!supermarketOptional.equals(Optional.empty())) throw  new SupermarketServiceException(SupermarketServiceExceptionCode.SUPERMARKET_EXISTS);
        Supermarket supermarket = supermarketRepository.save(newSupermarket);
        SupermarketDto supermarketDtoResp = mapper.supermarketToDto(supermarket);
        log.info("addedSupermarket = {}", supermarketDtoResp);
        return supermarketDtoResp;
    }

    public SupermarketDto updateSupermarket(String name, Long id) {
        log.info("update Supermarket: supermarketID = {}", id);
        Optional<Supermarket> supermarket= supermarketRepository.findById(id);
        Supermarket supermarketToUpdate = supermarket
                .orElseThrow(() -> new SupermarketServiceException(SupermarketServiceExceptionCode.SUPERMARKET_NOT_FOUND));
        supermarketToUpdate.setName(name);
        supermarketRepository.save(supermarketToUpdate);
        SupermarketDto supermarketDtoResp = mapper.supermarketToDto(supermarketToUpdate);
        log.info("addedSupermarket = {}", supermarketDtoResp);
        return supermarketDtoResp;
    }

    public SupermarketDto addProductToSupermarket(Long supermarketId, SupermarketProductDto supermarketProductDto)
    {
        log.info("Add product {} to the supermarketId {}",supermarketProductDto,supermarketId);
        SupermarketProduct supermarketProduct= mapper.supermarketProductDtoToEntity(supermarketProductDto);
        Optional<Supermarket> supermarketOptional= supermarketRepository.findById(supermarketId);
        Supermarket supermarket = supermarketOptional
                .orElseThrow(() -> new SupermarketServiceException(SupermarketServiceExceptionCode.SUPERMARKET_NOT_FOUND));

        List <SupermarketProduct> listProductInSupermarket =null;
        if (supermarket.getProductListForSupermarket()== null)
        {
            listProductInSupermarket= Arrays.asList(supermarketProduct);
        }
        else
        {
            listProductInSupermarket=supermarket.getProductListForSupermarket();
            listProductInSupermarket.add(supermarketProduct);
        }

        supermarket.setProductListForSupermarket(listProductInSupermarket);
        supermarketProduct.setSupermarket(supermarket);

        SupermarketProduct product=supermarketProductRepository.save(supermarketProduct);

        SupermarketDto supermarketDto = mapper.supermarketToDto(supermarket);

        List<SupermarketProductDto>supermarketProductsDto=null;
        SupermarketProductDto supermarketProdDto=mapper.supermarketProductToDto(product);

        supermarketProdDto.setSupermarket_id(supermarketId);
        if(supermarketProductsDto==null)  supermarketProductsDto= Arrays.asList(supermarketProdDto);
        supermarketDto.setProductListForSupermarketDto(supermarketProductsDto);
        log.info("updatedSupermarketNewProduct: updatedSupermrketDtoWithNewProduct={}", supermarketDto);
        return supermarketDto;
    }

    public List<SupermarketProductDto> showProductsFromASupermarket(Long supermarketId)
    {
        log.info("showProductsFromSupermarket: idSupermarket={}", supermarketId);

        Optional<Supermarket> supermarketOptional= supermarketRepository.findById(supermarketId);
        Supermarket supermarket = supermarketOptional
                .orElseThrow(() -> new SupermarketServiceException(SupermarketServiceExceptionCode.SUPERMARKET_NOT_FOUND));

        List<SupermarketProduct> listProducts= supermarket.getProductListForSupermarket();
        List<SupermarketProductDto> supermarketDtos= new ArrayList<>();
        for(SupermarketProduct supermarkeProduct : listProducts)
        {
            supermarketDtos.add(mapper.supermarketProductToDto(supermarkeProduct));
        }
        log.info("supermarketProductList={}", supermarketDtos);
        return supermarketDtos;
    }

    public SupermarketProductDto updateProduct(Long id, SupermarketProductDto productDto) {
        log.info("update supermarketProduct: productID = {}", id);
        Optional<SupermarketProduct> product= supermarketProductRepository.findById(id);
        SupermarketProduct productToUpdate = product
                .orElseThrow(() -> new SupermarketProductServiceException(SupermarketProductServiceExceptionCode.SUPERMARKET_PRODUCT_NOT_FOUND));
        productToUpdate.setName(productDto.getName());
        productToUpdate.setPrice(productDto.getPrice());
        productToUpdate.setQuantity(productDto.getQuantity());

        supermarketProductRepository.save(productToUpdate);

        SupermarketProductDto supermarketProductDtoResp = mapper.supermarketProductToDto(productToUpdate);
        log.info("updated supermarketProduct = {}", supermarketProductDtoResp);
        return supermarketProductDtoResp;

    }
    @Transactional
    public void deleteSupermarketProduct(Long productId) {
        Optional<SupermarketProduct> product= supermarketProductRepository.findById(productId);
        SupermarketProduct productToUpdate = product
                .orElseThrow(() -> new SupermarketProductServiceException(SupermarketProductServiceExceptionCode.SUPERMARKET_PRODUCT_NOT_FOUND));
        supermarketProductRepository.deleteById(productId);
    }
    @Transactional
    public void deleteSupermarket(Long supermarketId) {
        Optional<Supermarket> supermarketOptional= supermarketRepository.findById(supermarketId);
        Supermarket supermarket = supermarketOptional
                .orElseThrow(() -> new SupermarketServiceException(SupermarketServiceExceptionCode.SUPERMARKET_NOT_FOUND));
        List<SupermarketProduct> products=supermarketProductRepository.findAll();
        for (SupermarketProduct product:
             products) {
            if(product.getSupermarket().getId()==supermarketId)
                deleteSupermarketProduct(product.getId());
        }
        supermarketRepository.deleteById(supermarketId);
    }
}
