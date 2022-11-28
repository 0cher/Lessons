package Lesson_1;

import java.util.List;

public interface UserDao {
    //CRUD //Create, Read, Update, Delete
    List<User> findAll();
    User findById (Long id);
    User findByEmail(String email);
    User create (User user);
    void update (Long id, User user);
    boolean delete (Long id);
}

