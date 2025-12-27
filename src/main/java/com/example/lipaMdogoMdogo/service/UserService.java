package com.example.lipaMdogoMdogo.service;

import com.example.lipaMdogoMdogo.models.Role;
import com.example.lipaMdogoMdogo.models.User;
import com.example.lipaMdogoMdogo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

        public User createUser(User userInput) {
            User newUser = new User();
            newUser.setFirstName(userInput.getFirstName());
            newUser.setSecondName(userInput.getSecondName());
            newUser.setIdNo(userInput.getIdNo());
            newUser.setMsisdn(userInput.getMsisdn());
            newUser.setRole(userInput.getRole() == null ? Role.USER : userInput.getRole());
            newUser.setCreatedAt(LocalDateTime.now());
            newUser.setUpdatedAt(LocalDateTime.now());

            return userRepository.save(newUser);
        }

        public List<User> getAllUsers(){
            return userRepository.findAll();
        }
        public Optional<User> findById(UUID id){
        return userRepository.findById(id);
        }
        public Boolean existsById(UUID id){
        return userRepository.existsById(id);
        }

}
