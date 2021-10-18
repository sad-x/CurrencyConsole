import java.time.LocalDate;

/**
 * Класс для работы приложения в режиме конвертации валют
 */
public class ApplicationConvert {
    public static void main(String[] args) {
        UserInput userInput = cli();
        double[] curses = CurrencyOnDateService.getCurrency(userInput);
        if (curses[0] == 0 || curses[1] == 0) {
            System.out.println("No rates for these codes!");
        } else {
            double res = userInput.getNum() * curses[0] / curses[1];
            System.out.println("The result is: " + res);
        }
    }

    /**
     * Метод для взаимодействия с пользователем
     * @return данные, введенные пользователем в формате UserInput
     */
    private static UserInput cli() {
        LocalDate date = ConsoleInput.dateInput();
        int num = ConsoleInput.numInput();
        String code1 = ConsoleInput.codeInput(1);
        String code2 = ConsoleInput.codeInput(2);
        return new UserInput(date, num, code1, code2);
    }
}
