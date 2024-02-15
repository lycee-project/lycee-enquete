package net.lycee.web.anquete.api.repository.user;

import net.lycee.web.anquete.api.mapper.user.UserSearchResult;
import net.lycee.web.anquete.api.domain.UserId;

import java.util.Optional;

public interface UserRepository {
    UserId registerUser();

    Optional<UserSearchResult> search(UserId userId);

}
