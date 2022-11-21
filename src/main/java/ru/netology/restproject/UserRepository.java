package ru.netology.restproject;

import org.springframework.stereotype.Repository;
import ru.netology.restproject.Authorities;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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