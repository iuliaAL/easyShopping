package com.easyShopping.easyShopping.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
public class ProductDto {
    public Long id;
    public String name;
    public int quantity;
}
