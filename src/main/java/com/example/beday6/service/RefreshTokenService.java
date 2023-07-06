package com.example.beday6.service;


import com.example.beday6.domain.token.RefreshToken;
import com.example.beday6.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken findByRefreshToken(String refreshToken) {
        return refreshTokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new IllegalArgumentException("unexpected refreshtoken"));
    }

    public RefreshToken saveToken(Long id, String refreshToken){
        return refreshTokenRepository.save(new RefreshToken(id,refreshToken));
    }

    public String findByUserId(Long userId) {
        RefreshToken refreshToken =  refreshTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("unexpected user id"));
        return refreshToken.getEmail();
    }

    public void deleteToken(String email) {
        refreshTokenRepository.deleteById(email);
    }
}
