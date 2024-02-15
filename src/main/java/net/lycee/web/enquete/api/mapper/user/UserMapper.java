package net.lycee.web.enquete.api.mapper.user;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    void insert(String userId);

    Optional<UserSearchResult> search(String userId);

}
