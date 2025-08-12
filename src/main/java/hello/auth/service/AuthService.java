package hello.auth.service;

import hello.auth.dto.AuthRequest;
import hello.auth.dto.AuthResponse;
import hello.schedule.entity.User;
import hello.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    @Transactional
    public void signUp(AuthRequest request) {
        User user = new User(request.getUsername(), request.getEmail(), request.getPassword());
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("해당 이메일의 유저가 없습니다."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return new AuthResponse(user.getId());
    }
}
