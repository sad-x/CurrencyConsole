import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CurrencyOnDateServiceTest {
    @Test
    public void getCurrencyTest() {
        UserInput testUserInput = new UserInput(LocalDate.parse("2010-10-10"), "USD");
        double result = 29.9086;
        Assertions.assertEquals(result, CurrencyOnDateService.getCurrency(testUserInput)[0]);
    }
}
