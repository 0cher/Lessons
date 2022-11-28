package Lesson_1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {
    private static final String URL = "jdbc:postgresql://localhost/HomeWork";
    private static final String useName = "postgres";
    private static final String PASSWORD = "root";
    private static final String SELECT_ALL = "SELECT * FROM users";
    private static final String FIND_ById = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_ByEmail = "SELECT * FROM users WHERE email = ?";
    private static final String CREATE_USER = "INSERT INTO users (name, email, age, password) VALUES(?, ?, ?, ?);";
    private static final String UPDATE_USER = "UPDATE users SET name = ?, email = ?, age = ?, password=? WHERE id = ?";
    private static final String DELETE_ById = "DELETE FROM users WHERE id = ?";
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL,useName, PASSWORD);) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT_ALL); //"SELECT * FROM users where id = 2"

            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));   // Long id = resultSet.getLong("id");
                user.setEmail(resultSet.getString("email"));    //String email = resultSet.getString("email");
                user.setName(resultSet.getString("name"));    //String name = resultSet.getString("name");
                user.setAge(resultSet.getInt("age")); //int age = resultSet.getInt("age");
                user.setPassword(resultSet.getString("password")); ////String password = resultSet.getString("password");

                users.add(user);
                //System.out.println(user);
                //System.out.printf("Users: id: %d, email: %s, name: %s, age: %d ,password: %s%n", id,email,name, age,password);
            }
            connection.close();
        }catch (SQLException e){
            // e.printStackTrace();
            System.out.println(e);
        }
        return users;
    }
    @Override
    public User findById(Long id) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(URL,useName, PASSWORD);) {
           PreparedStatement statement = connection.prepareStatement(FIND_ById);
           statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));   // Long id = resultSet.getLong("id");
                user.setEmail(resultSet.getString("email"));    //String email = resultSet.getString("email");
                user.setName(resultSet.getString("name"));    //String name = resultSet.getString("name");
                user.setAge(resultSet.getInt("age")); //int age = resultSet.getInt("age");
                user.setPassword(resultSet.getString("password")); ////String password = resultSet.getString("password");
            }

        }catch (SQLException e){
             e.printStackTrace();
            System.out.println(e);
        }
        return user;
    }
    @Override
    public User findByEmail(String email) {
        User user = null;
        try (Connection connection = DriverManager.getConnection(URL,useName, PASSWORD);) {
            PreparedStatement statement = connection.prepareStatement(FIND_ByEmail);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()){
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setEmail(resultSet.getString("email"));
                user.setName(resultSet.getString("name"));
                user.setAge(resultSet.getInt("age"));
            }
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
        return user;
    }
    @Override
    public User create(User user) {
        boolean check = false;
      if (findByEmail(user.getEmail())==null){
        try (Connection connection = DriverManager.getConnection(URL,useName, PASSWORD);) {
            PreparedStatement statement = connection.prepareStatement(CREATE_USER);
            statement.setString(1,user.getName());
            statement.setString(2,user.getEmail());
            statement.setLong(3,user.getAge());
            statement.setString(4,user.getPassword());
            statement.executeUpdate();

            user.setId(findByEmail(user.getEmail()).getId());
            check = true;
//            Statement statementByEmail = connection.createStatement();
//            ResultSet resultSet = statementByEmail.executeQuery("SELECT * FROM users WHERE email = "+user.getEmail());
//            user.setId(resultSet.getLong("id"));

        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
      } if (!check){
            System.err.println("User is already exists");
          return findByEmail(user.getEmail());
      }
        return user;
    }

    @Override
    public void update(Long userId, User user) {
     if (findById(userId)!=null){
        try (Connection connection = DriverManager.getConnection(URL,useName, PASSWORD);) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1,user.getName());
            statement.setString(2,user.getEmail());
            statement.setLong(3,user.getAge());
            statement.setString(4,user.getPassword());
            statement.setLong(5,userId);
            statement.executeUpdate();


        }catch (SQLException e){
            e.printStackTrace();
            System.out.println(e);
        }
     }
    }

    @Override
    public boolean delete(Long id) {
    boolean remove = false;
    if (findById(id)!=null){
        try (Connection connection = DriverManager.getConnection(URL,useName, PASSWORD);) {
            PreparedStatement statement = connection.prepareStatement(DELETE_ById);
            statement.setLong(1,id);
            remove = !statement.execute();
            System.out.println("Запись удалена");
        }catch (SQLException e){
            e.printStackTrace();
           // System.out.println(e);
        }
    }
        return remove;
    }
}
