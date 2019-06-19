package com.easyShopping.easyShopping.dto;

import com.easyShopping.easyShopping.model.Role;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Getter
@Builder
public class UserDto {

    public Long id;
    public String username;
    public String password;
    public String email;
    private Role role;
    private List<ShoppingListDto> shoppingListDto;

    public UserDto(Long id, String username, String password, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
