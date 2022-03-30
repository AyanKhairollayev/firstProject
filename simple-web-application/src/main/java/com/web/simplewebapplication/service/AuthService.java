package com.web.simplewebapplication.service;

import com.web.simplewebapplication.dto.request.LoginDtoRequest;
import com.web.simplewebapplication.dto.request.RegistrationDtoRequest;
import com.web.simplewebapplication.dto.request.UserUpdateDtoRequest;
import com.web.simplewebapplication.dto.response.UserDtoResponse;
import com.web.simplewebapplication.models.User;
import com.web.simplewebapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class AuthService {
    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> showAll() {
        return userRepository.findAll();
    }

    public Optional<User> showByName(String name) {
        return userRepository.getByName(name);
    }

    public Optional<User> showById(Long id) {
        return userRepository.findById(id);
    }

    public String updateUser(Long id, UserUpdateDtoRequest dtoRequest) {
        Optional<User> oUser = userRepository.findById(id);

        if(oUser.isEmpty()) {
            return "User not found";
        }

        User user = oUser.get();
        if(!dtoRequest.getEmail().isEmpty()) {
            if(isValidEmail(dtoRequest.getEmail())) {
                user.setEmail(dtoRequest.getEmail());
            }
        }

        if(!dtoRequest.getPassword().isEmpty()) {
            user.setPassword(dtoRequest.getPassword());
        }

        if(!dtoRequest.getName().isEmpty()) {
            user.setName(dtoRequest.getName());
        }

        if(dtoRequest.getAge() != null) {
            user.setAge(dtoRequest.getAge());
        }

        userRepository.save(user);

        return "Updated";
    }

    public String login(LoginDtoRequest dtoRequest) {
        if(userRepository.existsByEmail(dtoRequest.getEmail())) {
            User user = userRepository.getByEmail(dtoRequest.getEmail());
            if (dtoRequest.getPassword().equalsIgnoreCase(user.getPassword())) {
                return "Success";
            } else {
                return "Password is not correct!";
            }
        }

        return "No such user";
    }

    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "User deleted";
    }

    public String registration(RegistrationDtoRequest dtoRequest) {
        if (!dtoRequest.getPassword().equals(dtoRequest.getRePassword())) {
            return "Password and re-password should be similar";
        }

        if (!isValidEmail(dtoRequest.getEmail())) {
            return "Email is not valid";
        }

        if (userRepository.existsByEmail(dtoRequest.getEmail())) {
            return "User with this email exists";
        }

        User user = new User(dtoRequest.getEmail(), dtoRequest.getPassword(),
                dtoRequest.getName(), dtoRequest.getAge());

        userRepository.save(user);

        return "User " + user.getName() + " successfully created";
    }

    private boolean isValidEmail(String email) {

        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);

        if (email == null) {
            return false;
        }

        return pat.matcher(email).matches();
    }


}
