package com.easyShopping.easyShopping.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SupermarketProductDto extends ProductDto {
    private float price;
    private Long supermarket_id;

    public SupermarketProductDto(Long id, String name, int quantity, float price, Long supermarket_id) {
        super(id,name,quantity);
        this.price = price;
        this.supermarket_id=supermarket_id;
    }
}
