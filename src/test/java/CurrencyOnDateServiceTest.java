import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CurrencyOnDateServiceTest {

    UserInput testUserInput;
    @BeforeAll
    public void setUp() {
        UserInput testUserInput = new UserInput(LocalDate.parse("2010-10-10"), "USD");
    }
    @Test
    public void getCurrencyTest() {
        double result = 29.9086;
        Assertions.assertEquals(result, CurrencyOnDateService.getCurrency(testUserInput)[0]);
    }
}
