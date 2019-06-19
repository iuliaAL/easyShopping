package com.easyShopping.easyShopping.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Entity
@Table
public class SupermarketProduct extends Product {
    @Column
    private float price;
    @ManyToOne
    @JoinColumn(name = "supermarket_id", nullable = false)
    private Supermarket supermarket;

    public SupermarketProduct(@NotNull String name, int quantity, float price, Supermarket supermarket) {
        this.name=name;
        this.quantity=quantity;
        this.price = price;
        this.supermarket = supermarket;
    }

    public SupermarketProduct(String name, int quantity, float price) {
        this.name=name;
        this.quantity=quantity;
        this.price = price;
    }
}
