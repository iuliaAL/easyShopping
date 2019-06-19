package com.easyShopping.easyShopping.model;



import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table
public class ListProduct extends Product {

    @ManyToOne
    @JoinColumn(name = "shoppingList_id", nullable = false)
    private ShoppingList shoppingList;
    //@ManyToMany(mappedBy = "listProducts")
    //private List<ShoppingList> shoppingList;

    public ListProduct(Long id, String name, int quantity, ShoppingList shoppingList) {
        super(id,name,quantity);
        this.shoppingList=shoppingList;
    }

    public ListProduct(String name, int quantity, ShoppingList shoppingList) {
        this.name=name;
        this.quantity=quantity;
        this.shoppingList=shoppingList;
    }


    public ListProduct(Long id, String name, int quantity) {
        super(id, name, quantity);
    }
}
