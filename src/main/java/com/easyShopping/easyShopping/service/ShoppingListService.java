package com.easyShopping.easyShopping.service;


import com.easyShopping.easyShopping.Mapper.Mapper;
import com.easyShopping.easyShopping.dto.ListProductDto;
import com.easyShopping.easyShopping.dto.ProductDto;
import com.easyShopping.easyShopping.dto.ShoppingListDto;
import com.easyShopping.easyShopping.dto.UserDto;
import com.easyShopping.easyShopping.model.*;
import com.easyShopping.easyShopping.repository.ListProductRepository;
import com.easyShopping.easyShopping.repository.ShoppingListRepository;
import com.easyShopping.easyShopping.repository.UserRepository;
import com.easyShopping.easyShopping.service.Exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {
    private static final Logger log = LoggerFactory.getLogger(ShoppingListService.class);

    private ShoppingListRepository shoppingListRepository;
    private UserRepository userRepository;
    private ListProductRepository productRepository;
    private Mapper mapper;

    @Autowired
    public ShoppingListService(ShoppingListRepository shoppingListRepository, UserRepository userRepository, ListProductRepository productRepository, Mapper mapper) {
        this.shoppingListRepository = shoppingListRepository;
        this.userRepository = userRepository;
        this.productRepository=productRepository;
        this.mapper = mapper;
    }


    /*public List<ShoppingListDto> findByUserId(Long userId) {
        log.info("findByUserId: id={}", userId);
        Optional<User> users = userRepository.findById(userId);
        User userResult = users
                .orElseThrow(() -> new UserServiceException(UserServiceExceptionCode.USER_NOT_FOUND));
        List<ShoppingList> shoppingLists = shoppingListRepository.findByUser(userResult).orElse(new ArrayList<>());
        List<ShoppingListDto> shoppingLisDtos = new ArrayList<>();
        for(ShoppingList shoppingList :shoppingLists)
        {
            shoppingLisDtos.add(mapper.shoppingListToDto(shoppingList));
        }
        log.info("findByUserId: shoppingLists={}", shoppingLisDtos);
        return shoppingLisDtos;
    }*/

    public ShoppingListDto createShoppingList(Long userId, ShoppingListDto shoppingListDto) {
        log.info("createShoppingList: shoppingListDto={}", shoppingListDto);
        //ShoppingList shoppingList = shoppingListRepository.save(mapper.shoppingListDtoToEntity(shoppingListDto));

        ShoppingList shoppingList = mapper.shoppingListDtoToEntity(shoppingListDto);

        Optional<User> users = userRepository.findById(userId);
        User userResult = users
                .orElseThrow(() -> new UserServiceException(UserServiceExceptionCode.USER_NOT_FOUND));

        List <User> usersInShoppingList =null;
        if (shoppingList.getUsers() == null)
        {
            usersInShoppingList= Arrays.asList(userResult);
            shoppingList.setUsers(usersInShoppingList);
        }
        else
        {
            usersInShoppingList=shoppingList.getUsers();
            usersInShoppingList.add(userResult);
            shoppingList.setUsers(usersInShoppingList);
        }
        List<ShoppingList> shopping=null;
        if(userResult.getShoppingLists()==null)
        {
            shopping  = Arrays.asList(shoppingList);
            userResult.setShoppingLists(shopping);
        }
        else
        {
            shopping=userResult.getShoppingLists();
            shopping.add(shoppingList);
            userResult.setShoppingLists(shopping);
        }
        ShoppingList resultList=shoppingListRepository.save(shoppingList);
        User resultUser=userRepository.save(userResult);
        ShoppingListDto createdShoppinglistDto = mapper.shoppingListToDto(shoppingList);

        List<Long>usersDtoId=null;
        if (createdShoppinglistDto.getUsersDtoId()==null)
        {
            usersDtoId = Arrays.asList(userId);
        }
        else {
            usersDtoId=createdShoppinglistDto.getUsersDtoId();
            usersDtoId.add(userId);
        }
        createdShoppinglistDto.setUsersDtoId(usersDtoId);

        log.info("createShoppingList: createdShoppingListDto={}", createdShoppinglistDto);
        return createdShoppinglistDto;
    }

    public ShoppingListDto addNewUserToShoppingList (Long userId, Long shoppingListId)
    {
        log.info("addUserToShoppingList: userId={}, shoppingList={}", userId,shoppingListId);
        Optional<ShoppingList> shoppingList=shoppingListRepository.findById(shoppingListId);
        ShoppingList resultShoppingList=shoppingList
                .orElseThrow(() -> new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_NOT_FOUND));
        //ShoppingList shoppingList = shoppingListRepository.save(mapper.shoppingListDtoToEntity(shoppingListDto));
        Optional<User> users = userRepository.findById(userId);
        User userResult = users
                .orElseThrow(() -> new UserServiceException(UserServiceExceptionCode.USER_NOT_FOUND));

        for(User user : resultShoppingList.getUsers())
        {
            if (user.getId()==userResult.getId())  throw new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_HAS_USER);
        }
        List <User> usersInShoppingList =null;
        if (resultShoppingList.getUsers() == null)
        {
            usersInShoppingList= Arrays.asList(userResult);
            resultShoppingList.setUsers(usersInShoppingList);
            usersInShoppingList=resultShoppingList.getUsers();
            usersInShoppingList.add(userResult);
            resultShoppingList.setUsers(usersInShoppingList);
        }
        else
        {
            usersInShoppingList=resultShoppingList.getUsers();
            usersInShoppingList.add(userResult);
            resultShoppingList.setUsers(usersInShoppingList);
        }
        List<ShoppingList> shopping=null;
        if(userResult.getShoppingLists()==null)
        {
            shopping  = Arrays.asList(resultShoppingList);
            userResult.setShoppingLists(shopping);

        }
        else
        {
            shopping=userResult.getShoppingLists();
            shopping.add(resultShoppingList);
            userResult.setShoppingLists(shopping);
        }
        ShoppingList resultList=shoppingListRepository.save(resultShoppingList);
        User resultUser=userRepository.save(userResult);

        ShoppingListDto createdShoppinglistDto = mapper.shoppingListToDto(resultList);

        List<Long>usersDtoId = new ArrayList<Long>();
        for(User user : usersInShoppingList)
        {
            usersDtoId.add(user.getId());
        }
        createdShoppinglistDto.setUsersDtoId(usersDtoId);

        log.info("createShoppingList: createdShoppingListDto={}", createdShoppinglistDto);
        return createdShoppinglistDto;
    }

    public List<ShoppingListDto> findByUserId(Long userId)
    {
        log.info("findByUserId: id={}", userId);
        Optional<User> users = userRepository.findById(userId);
        User userResult = users
                .orElseThrow(() -> new UserServiceException(UserServiceExceptionCode.USER_NOT_FOUND));

        List<ShoppingList> shoppingLists = userResult.getShoppingLists();
        List<String> shoppingName = new ArrayList<>();
        List<ShoppingListDto> shoppingLisDtos = new ArrayList<>();
        for(ShoppingList shoppingList :shoppingLists)
        {
            shoppingLisDtos.add(mapper.shoppingListToDto(shoppingList));
        }
        log.info("findByUserId: shoppingLists={}", shoppingLisDtos);
        return shoppingLisDtos;
    }

    public ShoppingListDto addProductToList(Long userId, Long shoppingListId, ListProductDto listProductDto)
    {
        log.info("User id={} add product {} to the list {}",userId,listProductDto,shoppingListId);
        Optional<User> users = userRepository.findById(userId);
        User userResult = users
                .orElseThrow(() -> new UserServiceException(UserServiceExceptionCode.USER_NOT_FOUND));

        Optional<ShoppingList> shoppingList=shoppingListRepository.findById(shoppingListId);
        ShoppingList resultShoppingList=shoppingList
                .orElseThrow(() -> new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_NOT_FOUND));
        int shpoopingListHasUser=0;
        for(User user : resultShoppingList.getUsers())
        {
            if (user.getId()==userResult.getId())  shpoopingListHasUser=1;
        }
        if (shpoopingListHasUser==0) throw new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_HAS_NOT_USER);

        ListProduct listProduct= mapper.listProductDtoToEntity(listProductDto);

        List <ListProduct> listProductInShoppingList =null;
        if (resultShoppingList.getListProducts()== null)
        {
            listProductInShoppingList= Arrays.asList(listProduct);
        }
        else
        {
            listProductInShoppingList=resultShoppingList.getListProducts();
            listProductInShoppingList.add(listProduct);
        }

        resultShoppingList.setListProducts(listProductInShoppingList);
        listProduct.setShoppingList(resultShoppingList);

        //ShoppingList resultList=shoppingListRepository.save(resultShoppingList);
        ListProduct product=productRepository.save(listProduct);

        ShoppingListDto createdShoppinglistDto = mapper.shoppingListToDto(resultShoppingList);
        List<Long>usersDtoId = new ArrayList<Long>();
        for(User user : resultShoppingList.getUsers())
        {
            usersDtoId.add(user.getId());
        }
        createdShoppinglistDto.setUsersDtoId(usersDtoId);

        List<ListProductDto>listProductsDto=null;
        ListProductDto listProdDto=mapper.listProductToDto(product);
        listProdDto.setShoppingList_id(shoppingListId);
        if(listProductsDto==null)  listProductsDto= Arrays.asList(listProdDto);
        createdShoppinglistDto.setProductListDto(listProductsDto);
        log.info("updatedShoppingListWithNewProduct: updatedShoppingListDtoWithNewProduct={}", createdShoppinglistDto);
        return createdShoppinglistDto;
    }

    public List<ProductDto> showProductsFromAList(Long shoppingListId, Long userId)
    {
        log.info("showProductsFromAList: idShoppingList={}", shoppingListId);
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        ShoppingList resultShoppingList=shoppingList
                .orElseThrow(() -> new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_NOT_FOUND));

        int shpoopingListHasUser=0;
        for(User user : resultShoppingList.getUsers())
        {
            if (user.getId()==userId)  shpoopingListHasUser=1;
        }
        if (shpoopingListHasUser==0) throw new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_HAS_NOT_USER);
        List<ListProduct> listProducts= resultShoppingList.getListProducts();
        List<ProductDto> productDtos= new ArrayList<>();
        for(ListProduct product : listProducts)
        {
            productDtos.add(mapper.listProductToDto(product));
        }
        log.info("supermarketProductList={}", productDtos);
        return productDtos;


        /*log.info("showProductsFromAList: idShoppingList={}", shoppingListId);
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        ShoppingList resultShoppingList=shoppingList
                .orElseThrow(() -> new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_NOT_FOUND));

        int shpoopingListHasUser=0;
        for(User user : resultShoppingList.getUsers())
        {
            if (user.getId()==userId)  shpoopingListHasUser=1;
        }
        if (shpoopingListHasUser==0) throw new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_HAS_NOT_USER);
        List<ListProduct> listProducts= resultShoppingList.getListProducts();
        List<String> productName = new ArrayList<>();
        ListProductDto listProductDto;
        for(ListProduct listProduct :listProducts)
        {
            listProductDto= mapper.listProductToDto(listProduct);
            productName.add(listProductDto.getName()+": "+listProductDto.getQuantity());
        }
        log.info("findByUserId: shoppingLists={}", productName);
        return productName;*/
    }

    public ShoppingListDto updateShoppingList(String name, Long id) {
        log.info("update ShoppingList: shoppingListID = {}", id);
        Optional<ShoppingList> shoppingList= shoppingListRepository.findById(id);
        ShoppingList resultShoppingList=shoppingList
                .orElseThrow(() -> new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_NOT_FOUND));
        resultShoppingList.setName(name);
        shoppingListRepository.save(resultShoppingList);
        ShoppingListDto shoppingListDtoResp = mapper.shoppingListToDto(resultShoppingList);
        log.info("updatedShoppingList = {}", shoppingListDtoResp);
        return shoppingListDtoResp;
    }

    @Transactional
    public void deleteShoppingListProduct(Long productId) {
        Optional<ListProduct> listProduct= productRepository.findById(productId);
        ListProduct resultProductList=listProduct
                .orElseThrow(() -> new ProductListServiceException(ProductListServiceExceptionCode.LIST_PRODUCT_NOT_FOUND));
        productRepository.deleteById(productId);
    }

    @Transactional
    public void deleteShoppingList(Long id) {
        Optional<ShoppingList> shoppingList= shoppingListRepository.findById(id);
        ShoppingList resultShoppingList=shoppingList
                .orElseThrow(() -> new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_NOT_FOUND));
        List<ListProduct> products=productRepository.findAll();
        for (ListProduct product:
                products) {
            if(product.getShoppingList().getId()==id)
                deleteShoppingListProduct(product.getId());
        }
        List<User>userList=resultShoppingList.getUsers();
        for(User user:
                userList){
            user.getShoppingLists().remove(resultShoppingList);
        }
        shoppingListRepository.deleteById(id);
    }

    public List<String> showMembersFromAList(Long shoppingListId, Long userId) {
        log.info("showMembersFromAList: idShoppingList={}", shoppingListId);
        Optional<ShoppingList> shoppingList = shoppingListRepository.findById(shoppingListId);
        ShoppingList resultShoppingList=shoppingList
                .orElseThrow(() -> new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_NOT_FOUND));

        int shpoopingListHasUser=0;
        for(User user : resultShoppingList.getUsers())
        {
            if (user.getId()==userId)  shpoopingListHasUser=1;
        }
        if (shpoopingListHasUser==0) throw new ShoppingListServiceException(ShoppingListServiceExceptionCode.SHOPPING_LIST_HAS_NOT_USER);
        List<User> listUsers= resultShoppingList.getUsers();
        List<String> userName = new ArrayList<>();
        UserDto listMemberDto;
        //List<UserDto> listUserDto = null;
        for(User listUser :listUsers)
        {
            listMemberDto= mapper.userToDto(listUser);
            //listUserDto.add(listMemberDto);
            userName.add(listMemberDto.getEmail());
        }

        log.info("findByShoppingListId: Members={}", userName);
        return userName;
    }
}
