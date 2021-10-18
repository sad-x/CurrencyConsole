
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Класс для работы приложения в режиме показа курса валюты
 */
public class Application {
    public static void main(String[] args) {
        UserInput userInput = cli();
        double[] curses = CurrencyOnDateService.getCurrency(userInput);
        if (curses[0] == -1) {
            System.out.println();
            System.out.println("No rates for code " + userInput.getCode1() + "!");
        } else {
            System.out.println(userInput.getCode1() + ": " + Double.toString(curses[0]));
        }
    }


    /**
     * Функция получения данных от пользователя
     * @return UserInput - данные пользователя
     */
    private static UserInput cli() {
        LocalDate date = ConsoleInput.dateInput();
        System.out.print("Enter currency code: ");
        Scanner in = new Scanner(System.in);
        String code = in.nextLine();
        return new UserInput(date, code);
    }
}
