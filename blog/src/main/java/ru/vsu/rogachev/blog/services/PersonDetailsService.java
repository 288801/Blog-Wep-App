package ru.vsu.rogachev.blog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vsu.rogachev.blog.models.User;
import ru.vsu.rogachev.blog.repositories.PeopleRepository;
import ru.vsu.rogachev.blog.security.PersonDetails;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = peopleRepository.findByUsername(username);

        if(user.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }

        return new PersonDetails(user.get());
    }
}
