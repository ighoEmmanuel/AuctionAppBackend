package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.data.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);
}
