package com.easyShopping.easyShopping.dto;

import java.util.List;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@ToString(callSuper = true)
@Builder
public class ShoppingListDto {
    private Long id;
    private String name;
    private List<Long> usersDtoId;
    private List<ListProductDto> productListDto;

    public ShoppingListDto(Long id, String name) {
        this.id=id;
        this.name=name;
    }
}
