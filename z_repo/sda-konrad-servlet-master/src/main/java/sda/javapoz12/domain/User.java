package sda.javapoz12.domain;

public class User {
    private String name;
    private String lastName;
    private String email;
    private int age;
    private int id;

    public User(String name, String lastName, String email,int age) {
        this.name = name;
        this.lastName = lastName;
        this.email=email;
        this.age = age;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User id:"+id+" firstName:"+name+" lastName:"+lastName;
    }
}
