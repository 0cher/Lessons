package Lesson_1;

import java.util.Objects;
public class User {
    private Long id;
    private String email;
    private String password;
    private String name;
    private int age;
    public User() {
    }

    public User(String name, String email, int age, String password) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.age = age;
    }
    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User users)) return false;
        return age == users.age && Objects.equals(id, users.id) && Objects.equals(email, users.email) && Objects.equals(password, users.password) && Objects.equals(name, users.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, password, name, age);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

}
