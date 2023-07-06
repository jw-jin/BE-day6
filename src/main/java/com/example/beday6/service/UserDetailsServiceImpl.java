package com.example.beday6.service;

import com.example.beday6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        System.out.println("email = " + email);
//        System.out.println(userRepository.findByEmail(email).get().getPassword());

        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("not found email"));
    }
}
