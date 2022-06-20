package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vasiliy", "Popov", (byte) 32);
        userService.saveUser("Vitaliy", "Popov", (byte) 32);
        userService.saveUser("Kirill", "Popov", (byte) 32);
        userService.saveUser("Marina", "Popova", (byte) 32);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
