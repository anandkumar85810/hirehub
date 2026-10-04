package com.hirehub.hirehub.service;

import com.hirehub.hirehub.dto.request.UserRequest;
import com.hirehub.hirehub.dto.response.UserResponse;
import com.hirehub.hirehub.entity.User;
import com.hirehub.hirehub.exception.ResourceNotFoundException;
import com.hirehub.hirehub.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(UserRequest userRequest) {
        //Request Dto -> Entity
        User user = new User();

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        user.setStatus(userRequest.getStatus());

        //save Entity into database
        User savedUser = userRepository.save(user);

        //Entity -> Response Dto
        UserResponse userResponse = new UserResponse();

        userResponse.setId(savedUser.getId());
        userResponse.setUsername(savedUser.getUsername());
        userResponse.setEmail(savedUser.getEmail());
        userResponse.setPhone(savedUser.getPhone());
        userResponse.setRole(savedUser.getRole());
        userResponse.setStatus(savedUser.getStatus());
        userResponse.setCandidateProfileId(savedUser.getCandidateProfile().getId());

        return userResponse;
    }

    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id " + id + " not found!"));

        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());
        userResponse.setPhone(user.getPhone());
        userResponse.setRole(user.getRole());
        userResponse.setStatus(user.getStatus());
        userResponse.setCandidateProfileId(user.getCandidateProfile().getId());

        return userResponse;
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> {
                    UserResponse userResponse = new UserResponse();

                    userResponse.setId(user.getId());
                    userResponse.setUsername(user.getUsername());
                    userResponse.setEmail(user.getEmail());
                    userResponse.setPhone(user.getPhone());
                    userResponse.setRole(user.getRole());
                    userResponse.setStatus(user.getStatus());
                    userResponse.setCandidateProfileId(user.getCandidateProfile().getId());

                    return  userResponse;
                })
                .collect(Collectors.toList());
    }

    public UserResponse updateUserById(Long id, UserRequest userRequest) {

        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id " + id + " not found!"));

        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setPassword(userRequest.getPassword());
        user.setRole(userRequest.getRole());
        user.setStatus(userRequest.getStatus());

        User updatedUser = userRepository.save(user);

        UserResponse userResponse = new UserResponse();

        userResponse.setId(updatedUser.getId());
        userResponse.setUsername(updatedUser.getUsername());
        userResponse.setEmail(updatedUser.getEmail());
        userResponse.setPhone(updatedUser.getPhone());
        userResponse.setRole(updatedUser.getRole());
        userResponse.setStatus(updatedUser.getStatus());
        userResponse.setCandidateProfileId(updatedUser.getCandidateProfile().getId());

        return userResponse;
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User with id " + id + " not found!"));

        userRepository.delete(user);
    }
}
