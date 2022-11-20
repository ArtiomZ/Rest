package ru.netology.restproject;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
@Repository
public class UserRepository {

    public UserRepository() {
    }

    private final ConcurrentHashMap<String, List<Authorities>> permission = new ConcurrentHashMap<>();

    public List<Authorities> getUserAuthorities(String user, String password) {
        if (!permission.containsKey(user + password)) {
            return new ArrayList<>();
        }
        return permission.get(user + password);
    }
}