package semicolon.africa.service.imp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import semicolon.africa.data.repositories.BidderRepository;




@SpringBootTest
class BidderServiceImplTest {

    @Autowired
    private BidderRepository bidderRepository;

    @AfterEach
    void tearDown() {
        bidderRepository.deleteAll();
    }

    @Test
    public void whenAUserLogin_HeOrSheCanBid(){

    }

}