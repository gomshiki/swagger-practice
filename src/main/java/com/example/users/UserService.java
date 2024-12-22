package com.example.users;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private static final AtomicLong autoIncrement = new AtomicLong();
    private static final Map<Long, Users> usersInMemory = new HashMap<>();

    public Long register(RegisterRequest registerRequest) {
        Users user = RegisterRequest.of(registerRequest);
        long newId = autoIncrement.incrementAndGet();
        usersInMemory.put(newId, user);
        return newId;
    }

    public Boolean login(LoginRequest loginRequest) {
        Users loginUser = LoginRequest.of(loginRequest);
        for (Users savedUser : usersInMemory.values()) {
            if (savedUser.equals(loginUser)) {
                return true;
            }
        }
        return false;
    }
}
