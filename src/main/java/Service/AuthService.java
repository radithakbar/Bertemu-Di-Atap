/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Author.User;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Asus
 */
public class AuthService {
    private ArrayList<User> daftarUser = new ArrayList<>();

    private HashMap<String, User> mapUser = new HashMap<>();

    public void register(User user) {
        daftarUser.add(user);
        mapUser.put(user.getUsername(), user);
        System.out.println(" User '" + user.getUsername() + "' berhasil didaftarkan.");
    }

    public User login(String username, String password) {
        User user = mapUser.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public ArrayList<User> getDaftarUser() {
        return daftarUser;
    }
}
