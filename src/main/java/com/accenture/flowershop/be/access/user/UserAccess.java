package com.accenture.flowershop.be.access.user;

import com.accenture.flowershop.be.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserAccess {
    void saveUser(String login, String password, String name, String address,
                  String phoneNumber, double score, int sale, String role);
    Map<String, User> getAllUsers();
}
