package com.easyShopping.easyShopping.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class SupermarketDto {
    private Long id;
    private String name;
    private List<SupermarketProductDto> productListForSupermarketDto;

    public SupermarketDto(Long id, String name) {
        this.id=id;
        this.name=name;
    }
}
