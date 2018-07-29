package com.kodgemisi.course.ecommerce.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsername(@Param("username") String username);

     boolean existsByUsername(@Param("username") String username);
}
