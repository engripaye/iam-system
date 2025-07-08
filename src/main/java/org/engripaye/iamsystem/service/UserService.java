package org.engripaye.iamsystem.service;


import org.engripaye.iamsystem.model.Role;
import org.engripaye.iamsystem.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {

        private final Map<String, User> users = new HashMap<>();

        public void register(String username, String password, Role role) {
            users.put(username, new User(username, password, role));
        }

        public User getByUsername(String username) {
            return  users.get(username);
        }

        public boolean validate(String username, String password){
            User user = users.get(username);
            return user != null && user.getPassword().equals(password);
        }

}
