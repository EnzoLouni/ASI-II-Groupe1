package com.cpe.irc5.asi2.grp1.user_manager.repository;

import com.cpe.irc5.asi2.grp1.user_manager.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findUserByLoginAndPassword(@Param("login") String login, @Param("password") String password);
}
