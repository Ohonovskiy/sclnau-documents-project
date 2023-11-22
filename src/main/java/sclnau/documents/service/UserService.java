package sclnau.documents.service;


import sclnau.documents.entity.User;
import sclnau.documents.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void remove(User user){
        userRepository.delete(user);
    }

    public void remove(Long id){
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public User getById(Long id){
        return userRepository.getReferenceById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Transactional(readOnly = true)
    public List<User> getAll(){
        return userRepository.findAll();
    }

    public void removeAll(){
        userRepository.deleteAll();
    }

}
