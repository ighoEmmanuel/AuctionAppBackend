package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.data.models.Bidder;

import java.util.Optional;

@Repository
public interface BidderRepository extends MongoRepository<Bidder, String> {

    boolean existsByEmail(String email);

    Optional<Bidder> findByEmail(String email);

    boolean existsByUserName(String userName);
}
