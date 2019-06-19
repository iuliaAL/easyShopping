package com.easyShopping.easyShopping.configuration;

import com.easyShopping.easyShopping.service.UserService;

import java.util.Random;

public class DatabaseInit {
    private static DatabaseInit instance;

    private Random random = new Random();

    public static DatabaseInit getInstance() {
        if (instance == null)
            instance = new DatabaseInit();

        return instance;
    }

    public void initializeUsers(UserService service) {
        // Add these users into the database
//        service.register(getAppUserDto("Cernat", "Mihai", "https://i.imgur.com/qhzEif4.png"));
//        service.register(getAppUserDto("Lascu", "Dan", "https://i.imgur.com/wUIL9CG.jpg"));
//        service.register(getAppUserDto("Popa", "Dorin", "https://i.imgur.com/mJT7GEB.jpg"));
//        service.register(getAppUserDto("Costin", "Ana", "https://i.imgur.com/AHR8kFu.jpg"));
    }
}
