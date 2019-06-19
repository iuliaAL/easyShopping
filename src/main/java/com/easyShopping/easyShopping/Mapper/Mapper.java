package com.easyShopping.easyShopping.Mapper;

import com.easyShopping.easyShopping.service.Exception.*;
import com.easyShopping.easyShopping.dto.*;
import com.easyShopping.easyShopping.model.*;
import com.easyShopping.easyShopping.repository.ListProductRepository;
import com.easyShopping.easyShopping.repository.ShoppingListRepository;
import com.easyShopping.easyShopping.repository.SupermarketRepository;
import com.easyShopping.easyShopping.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    private SupermarketRepository supermarketRepository;
    private ShoppingListRepository shoppingListRepository;
    private ListProductRepository listProductRepository;
    private UserRepository userRepository;

    public ListProductDto listProductToDto(final ListProduct entity)
    {
        return new ListProductDto(entity.getId(),entity.getName(),entity.getQuantity());
    }

    public ListProduct listProductDtoToEntity(final ListProductDto dto) {
        return new ListProduct(dto.getId(),dto.getName(), dto.getQuantity());
    }

    public SupermarketProductDto supermarketProductToDto(final SupermarketProduct entity)
    {

        return new SupermarketProductDto(entity.getId(),entity.getName(),entity.getQuantity(),entity.getPrice(),entity.getSupermarket().getId());
    }

    public SupermarketProduct supermarketProductDtoToEntity(final SupermarketProductDto dto)
    {
        return new SupermarketProduct(dto.getName(),dto.getQuantity(),dto.getPrice());
        //return new SupermarketProduct (dto.getName(),dto.getQuantity(),dto.getPrice(),supermarketRepository.findById(dto.getSupermarket_id()).orElseThrow(() ->
          //      new SupermarketServiceException(SupermarketServiceExceptionCode.SUPERMARKET_NOT_FOUND)));
    }

    public SupermarketDto supermarketToDto(final Supermarket entity)
    {
        /*ArrayList<SupermarketProductDto> supermarketProductDtos=new ArrayList<SupermarketProductDto>();
        for (SupermarketProduct supermarketProduct:entity.getProductListForSupermarket()) {
            supermarketProductDtos.add(supermarketProductToDto(supermarketProduct));

        }*/
        return new SupermarketDto(entity.getId(),entity.getName());
    }

    public Supermarket supermarketDtoToEntity(final SupermarketDto dto)
    {

        /*List<SupermarketProduct>supermarketProducts=null;
        if (dto.getProductListForSupermarketDto()==null) return new Supermarket(dto.getId(),dto.getName(),null);
        for(SupermarketProductDto supermarketProductDto:dto.getProductListForSupermarketDto())
        {
            supermarketProducts.add(supermarketProductDtoToEntity(supermarketProductDto));
        }*/
        return new Supermarket(dto.getId(),dto.getName());
    }


    public ShoppingListDto shoppingListToDto(final ShoppingList entity)
    {
        /*List<Long>listProductsId=null;
        for(ListProduct listProducts:entity.getListProducts())
        {
            listProductsId.add(listProducts.getId());
        }
        List<Long>usersId=null;
        for(User user:entity.getUsers())
        {
            usersId.add(user.getId());
        }
*/
        return new ShoppingListDto(entity.getId(),entity.getName());
    }

    public ShoppingList shoppingListDtoToEntity(final ShoppingListDto dto)
    {
        /*List<ListProduct>listProducts=null;
        for(Long id :dto.getProductsDtoId())
        {
            listProducts.add(listProductRepository.findById(id).orElseThrow(() ->
                    new ProductListServiceException(ProductListServiceExceptionCode.LIST_PRODUCT_NOT_FOUND)));
        }*/
        /*List<User>users=null;
        for(Long id:dto.getUsersDtoId())
        {
            users.add(userRepository.findById(id).orElseThrow(() ->
                    new UserServiceException(UserServiceExceptionCode.USER_NOT_FOUND)));
        }*/
        return new ShoppingList(dto.getId(),dto.getName());
    }


    public UserDto userToDto(final User entity)
    {
        /*ArrayList<ShoppingListDto> shoppingListDto=new ArrayList<ShoppingListDto>();
        for (ShoppingList shoppingLists:entity.getShoppingLists()) {
            shoppingListDto.add(shoppingListToDto(shoppingLists));
        }*/
        return new UserDto(entity.getId(),entity.getUsername(),entity.getPassword(),entity.getEmail(),entity.getRole());
    }

    public User userDtoToEntity(final UserDto dto)
    {
        /*ArrayList<ShoppingList> shoppingLists=new ArrayList<ShoppingList>();
        if(dto.getShoppingListDto()==null)
        {
            shoppingLists=null;
        }
        else {
            for (ShoppingListDto shoppingListsDto : dto.getShoppingListDto()) {
                shoppingLists.add(shoppingListDtoToEntity(shoppingListsDto));
            }
        }*/

        return new User(dto.getId(),dto.getUsername(),dto.getPassword(),dto.getEmail(),dto.getRole());
    }


}