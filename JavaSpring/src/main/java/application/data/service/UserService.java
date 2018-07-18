package application.data.service;

import application.data.model.User;

public interface UserService {
    public User findUserByEmail(String email);
    public User findUserById(int id);
    public void saveUser(User user);
}