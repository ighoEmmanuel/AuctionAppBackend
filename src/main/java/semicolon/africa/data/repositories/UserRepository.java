package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.data.models.User;
@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
