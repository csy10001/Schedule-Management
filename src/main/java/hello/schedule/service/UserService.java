package hello.schedule.service;

import hello.schedule.dto.UserRequest;
import hello.schedule.dto.UserResponse;
import hello.schedule.entity.User;
import hello.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse save(UserRequest request) {
        User user = new User(request.getUsername(), request.getEmail());
        User savedUser = userRepository.save(user);
        return new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail());
    }

    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail()
                )).toList();
    }

    @Transactional(readOnly = true)
    public UserResponse findById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("그런 id의 유저는 없어요")
        );
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }

    @Transactional
    public UserResponse updateUser(Long userId, UserRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("그런 id의 유저는 없어요")
        );
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());

        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }

    @Transactional
    public UserResponse deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("그런 id의 유저는 없어요")
        );
        userRepository.delete(user);
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
