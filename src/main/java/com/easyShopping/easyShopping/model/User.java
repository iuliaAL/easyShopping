package com.easyShopping.easyShopping.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String username;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Role role=Role.ROLE_USER;
    @ManyToMany
    @JoinTable(
            name = "user_shoppingList",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "shoppingList_id") }
    )
    List<ShoppingList> shoppingLists;

    public User(String username, String password, String email, Role role, ArrayList<ShoppingList> shoppingLists) {
        this.username=username;
        this.password=password;
        this.email=email;
        this.role=role;
        this.shoppingLists=shoppingLists;
    }

    public User(Long id, String username, String password, String email, Role role) {
        this.id=id;
        this.username=username;
        this.password=password;
        this.email=email;
        this.role=role;

    }
}
