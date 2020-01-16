import com.fasterxml.jackson.jr.ob.JSON;
import model.Order;
import model.TopOfTheBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class JSONParseTest {
    @Test
    void jsonParseTest() {
        String json="{ \"symbol\": \"ABC\", \"bidQuantity\": \"50\", \"bidPrice\" : \"100.0\", \"askQuantity\": \"50\", \"askPrice\" : \"99.0\" }";
        String json1 = "{ \"id\": \"2fb2270e\", \"symbol\": \"ABC\", \"quantity\": \"50\", \"side\":\"BUY\", \"price\": \"101.0\" }";
        try {
            TopOfTheBook top = JSON.std.beanFrom(TopOfTheBook.class, json);
            Order order = JSON.std.beanFrom(Order.class, json1);
        } catch (IOException ex) { ex.printStackTrace(); }
    }
}
