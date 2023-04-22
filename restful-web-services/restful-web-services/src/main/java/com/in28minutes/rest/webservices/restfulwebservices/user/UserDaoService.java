package com.in28minutes.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    //use jpa/hibernate to talk to the database using a Static List
    private static List<User> users = new ArrayList<>();
    private static Integer usersCount = 0;

    //inicialize this list with a set o users
    static{
        users.add(new User(++usersCount,"Toni", LocalDate.now().minusYears(36)));
        users.add(new User(++usersCount,"Dário", LocalDate.now().minusYears(25)));
        users.add(new User(++usersCount,"Canoas", LocalDate.now().minusYears(18)));
        users.add(new User(++usersCount,"Vascos", LocalDate.now().minusYears(33)));
        users.add(new User(++usersCount,"Gustavo", LocalDate.now().minusYears(20)));
    }

    //method to retrieve all users
    public List<User> findAll(){
        return users;
    }

    //method to save data of a specific user
    public User save(User user) {
        user.setId(++usersCount);
        users.add(user);
        return user;
    }

    //to be able to find user by id
    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    //delete when id is given
    public void deleteById(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate); //remove if predicate matches
    }
}
