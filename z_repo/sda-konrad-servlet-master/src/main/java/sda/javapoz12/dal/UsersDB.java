package sda.javapoz12.dal;

import sda.javapoz12.domain.User;

import java.util.*;

public enum UsersDB {
    USERS;

    private Map<Integer, User> storage = new HashMap<>();
    private static int id = 0;

    public void saveUser(User newUser){
        saveUser(newUser,id++);
    }

    public User saveUser(User newUser, int id){
        newUser.setId(id);
        return storage.put(id,newUser);
    }

    public User getUserByLastName(String lastName){
        User user = storage.values().stream().filter(u->u.getLastName().equals(lastName)).findFirst().orElseGet(null);
        System.out.println("Found user"+user);
        return user;
    }

    public User getUserByNo(int no){
        User user = storage.get(no);
        return user;
    }

    public Collection<User> getUsers(){
        return storage.values();
    }
}
