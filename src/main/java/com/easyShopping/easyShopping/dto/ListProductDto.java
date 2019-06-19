package com.easyShopping.easyShopping.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class ListProductDto extends ProductDto {

    private Long shoppingList_id;


    public ListProductDto(Long id, String name, int quantity, Long shoppingList_id) {
        super(id,name,quantity);
        this.shoppingList_id=shoppingList_id;
    }

    public ListProductDto(Long id, String name, int quantity) {
        this.id=id;
        this.name=name;
        this.quantity=quantity;
    }
}
