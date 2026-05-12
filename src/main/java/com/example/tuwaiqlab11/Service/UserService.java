package com.example.tuwaiqlab11.Service;

import com.example.tuwaiqlab11.Api.ApiException;
import com.example.tuwaiqlab11.Model.User;
import com.example.tuwaiqlab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    //BASIC CRUD
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User oldUser = userRepository.findUserById(id);
        if(oldUser == null)
            throw new ApiException("User not found");

        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());

        userRepository.save(oldUser);
    }

    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if(user == null) throw new ApiException("User not found");

        userRepository.delete(user);
    }


    //EXTRA ENDPOINTS
    public User findUserWithMostPosts(){
        User user = userRepository.findUserWithMostPosts();

        if(user == null) throw new ApiException("User not found");

        return user;
    }

    public User findUserWithMostComments(){
        User user = userRepository.findUserWithMostComments();

        if(user == null) throw new ApiException("User not found");

        return user;
    }
}
