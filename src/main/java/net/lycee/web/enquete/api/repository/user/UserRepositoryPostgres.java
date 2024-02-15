package net.lycee.web.enquete.api.repository.user;

import net.lycee.web.enquete.api.mapper.user.UserSearchResult;
import net.lycee.web.enquete.api.mapper.user.UserMapper;
import net.lycee.web.enquete.utils.IdUtils;
import net.lycee.web.enquete.api.domain.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryPostgres implements UserRepository {

    @Autowired
    private IdUtils userUtils;

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserId registerUser() {

        UserId userId = userUtils.publishUserId();
        userMapper.insert(userId.value());

        return userId;
    }

    @Override
    public Optional<UserSearchResult> search(UserId userId) {
        return userMapper.search(userId.value());
    }

}
