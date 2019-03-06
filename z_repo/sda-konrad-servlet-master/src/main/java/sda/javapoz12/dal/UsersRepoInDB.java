package sda.javapoz12.dal;

import sda.javapoz12.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;

public enum UsersRepoInDB implements UsersRepo {
    USERS;

    static final EntityManagerFactory entityManagerFactory;

    static{
        entityManagerFactory = Persistence.createEntityManagerFactory("sda.javapoz12.users_db");
    }

    @Override
    public void saveUser(User newUser) {

    }

    @Override
    public User getUserByLastName(String lastName) {
        return null;
    }

    @Override
    public User getUserByNo(int no) {
        return null;
    }

    @Override
    public Collection<User> getUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query selectAllQuery = em.createQuery("FROM User",User.class);
        List resultList = selectAllQuery.getResultList();
        return resultList;
    }
}
