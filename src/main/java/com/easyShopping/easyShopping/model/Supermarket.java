package com.easyShopping.easyShopping.model;

import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table
public class Supermarket{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column
    private String name;

    @OneToMany(mappedBy = "supermarket", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<SupermarketProduct> productListForSupermarket;

    public Supermarket(String name, List<SupermarketProduct> supermarketProducts) {
        this.name=name;
        this.productListForSupermarket=supermarketProducts;
    }

    public Supermarket(Long id, String name) {
        this.id=id;
        this.name=name;
    }
}
