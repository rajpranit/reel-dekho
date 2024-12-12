package com.itrifid.reeldekho.service;

import com.itrifid.reeldekho.dto.ChangePasswordRequest;
import com.itrifid.reeldekho.dto.UserResponseDto;
import com.itrifid.reeldekho.entity.User;
import com.itrifid.reeldekho.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }

    public UserResponseDto getUser(@PathVariable String email){
        var user = repository.findByEmail(email)
                .orElseThrow();

        return UserResponseDto.builder()
                .role(user.getRole())
                .email(email)
                .firstname(user.getFirstname())
                .lastname(user.getLastname()).
                build();
    }
}
