package project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.repository.CustomUserRepository;
import project.tables.CustomUser;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private CustomUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public CustomUser getUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByLogin(String login) {
        return userRepository.existsByLogin(login);
    }

    @Override
    @Transactional
    public void addUser(CustomUser customUser) {
        userRepository.save(customUser);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomUser> customUserList() {
        return userRepository.findAll();
    }
}
