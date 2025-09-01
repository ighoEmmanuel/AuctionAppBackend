package semicolon.africa.data.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import semicolon.africa.data.models.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
//    List<Product> findByBidStartTimeBeforeAndBidEndTimeAfter(LocalDateTime now, LocalDateTime now1);
}
