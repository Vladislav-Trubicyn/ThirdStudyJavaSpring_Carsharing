package ru.carsharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.carsharing.model.User;
import ru.carsharing.repository.UserRepository;

@Service
public class UserService implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return userRepository.findByUsername(username);
    }

    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    public Iterable<User> findAllUsers()
    {
        return userRepository.findAll();
    }

}
