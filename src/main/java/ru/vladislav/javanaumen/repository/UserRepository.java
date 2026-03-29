package ru.vladislav.javanaumen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.vladislav.javanaumen.entity.User;

@RepositoryRestResource(path = "users")
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
