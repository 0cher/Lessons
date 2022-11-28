package Lesson_1;

import java.util.List;

public class Main {

    public static void main(String[] args){
        UserDao userDao = new UserDaoImp();
        List<User> users = userDao.findAll();
        users.forEach(System.out::println);

        User usersById = userDao.findById(2L);
        System.out.println(usersById);
        System.out.println();

        User user = new User("Aleks", "aleks11@mail.com", 35,"1212");

        User userCreate = userDao.create(user);
        System.out.println("create "+userCreate);
        System.out.println("mail "+userDao.findByEmail("aleks141@mail.com"));


//        userDao.update(41L,user);
//        System.out.println(userDao.findById(41L));
//        boolean deleteById = userDao.delete(40L);
//        System.out.println(deleteById);

      }
}

