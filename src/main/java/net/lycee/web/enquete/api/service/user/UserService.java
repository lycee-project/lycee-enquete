package net.lycee.web.enquete.api.service.user;

import net.lycee.web.enquete.api.controller.user.UserInfo;
import net.lycee.web.enquete.api.mapper.user.UserSearchResult;
import net.lycee.web.enquete.api.repository.user.UserRepository;
import net.lycee.web.enquete.api.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserId registerUser() {
        return userRepository.registerUser();
    }

    public boolean existsUserId(UserId userId) {
        return userRepository.search(userId).isPresent();
    }

    public UserInfo readUserInfo(UserId userId) {
        Optional<UserSearchResult> userInfo = userRepository.search(userId);
        if (userInfo.isEmpty()) {
            throw new RuntimeException();
        }
        return new UserInfo(
                new UserId(userInfo.get().userId())
        );
    }
}
