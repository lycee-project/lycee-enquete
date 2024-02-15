package net.lycee.web.enquete.api.repository.user;

import net.lycee.web.enquete.api.mapper.user.UserSearchResult;
import net.lycee.web.enquete.api.domain.UserId;

import java.util.Optional;

public interface UserRepository {
    UserId registerUser();

    Optional<UserSearchResult> search(UserId userId);

}
