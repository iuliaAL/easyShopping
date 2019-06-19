package com.easyShopping.easyShopping.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table
public class ShoppingList{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "shoppingLists")
    private List<User> users;

    /*@ManyToMany
    @JoinTable(
            name = "ShoppingList_ListProduct",
            joinColumns = { @JoinColumn(name = "shoppingList_id") },
            inverseJoinColumns = { @JoinColumn(name = "listProduct_id") }
    )
    private List<ListProduct>listProducts;*/

    @OneToMany(mappedBy = "shoppingList", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ListProduct> listProducts;

    public ShoppingList(String name, List<User> users, List<ListProduct> listProducts) {
        this.name=name;
        this.listProducts=listProducts;
    }

    public ShoppingList(Long id, String name, List<User> users) {
        this.id=id;
        this.name=name;
        this.users=users;

    }

    public ShoppingList(Long id, String name) {
        this.id=id;
        this.name=name;
    }
}
