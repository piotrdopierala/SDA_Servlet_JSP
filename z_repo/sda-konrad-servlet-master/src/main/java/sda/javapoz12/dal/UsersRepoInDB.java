package sda.javapoz12.dal;

import sda.javapoz12.domain.User;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

enum UsersRepoInDB implements UsersRepo {
    USERS;

    static final EntityManagerFactory entityManagerFactory;

    static {
        entityManagerFactory = Persistence.createEntityManagerFactory("sda.javapoz12.users_db");
    }

    @Override
    public void saveUser(User newUser) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        em.persist(newUser);
        tran.commit();

    }

    @Override
    public User getUserByLastName(String lastName) {
        EntityManager em = entityManagerFactory.createEntityManager();
        TypedQuery<User> selectAllByName = em.createQuery("FROM User WHERE lastName=:lastNamePar", User.class);
        selectAllByName.setParameter("lastNamePar", lastName);
        List<User> resultList = selectAllByName.getResultList();
        User firstUser = resultList.get(0);
        return firstUser;
    }

    @Override
    public User getUserByNo(int no) {
        EntityManager em = entityManagerFactory.createEntityManager();
        User user = em.find(User.class, no);
        return user;
    }

    @Override
    public Collection<User> getUsers() {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query selectAllQuery = em.createQuery("FROM User", User.class);
        List resultList = selectAllQuery.getResultList();
        return resultList;
    }

    @Override
    public User deleteUserByNo(int no) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction tran = em.getTransaction();
        tran.begin();
        User userToDelete = em.find(User.class, no);
        em.remove(userToDelete);
        tran.commit();
        return null;
    }
}
