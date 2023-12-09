package ru.vsu.rogachev.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.entities.User;
import ru.vsu.rogachev.blog.repositories.UserRepository;
import ru.vsu.rogachev.blog.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findById(s)
                .map(PersonDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
