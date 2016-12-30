package project.service;

import project.tables.CustomUser;

import java.util.List;

public interface UserService {
    CustomUser getUserByLogin(String login);

    boolean existsByLogin(String login);

    void addUser(CustomUser customUser);

    List<CustomUser> customUserList();
}
