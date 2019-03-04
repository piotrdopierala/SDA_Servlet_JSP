package sda.javapoz12.dal;

import sda.javapoz12.domain.User;

import java.util.Collection;

public interface Repo {
    void saveUser(User newUser);

    User getUserByLastName(String lastName);

    User getUserByNo(int no);

    Collection<User> getUsers();
}
