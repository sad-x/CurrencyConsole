import java.time.LocalDate;
import java.util.Scanner;

/**
 * Класс служит для сбора информации от пользователя
 */
public class ConsoleInput {
    /**
     * Метод служит для ввода пользователем чисел
     * @return введённое пользователем число
     */
    public static int numInput() {
        boolean isNumCorrect = false;
        while(!isNumCorrect){
            try {
                System.out.print("Enter the number: ");
                Scanner in = new Scanner(System.in);
                int num = in.nextInt();
                isNumCorrect = true;
                return num;
            } catch (Exception e){
                System.out.println("Wrong format!");
            }
        }
        return -1;
    }

    /**
     * Метод для ввода пользователем кода валюты
     * @param codeNumber - номер вводимой валюты
     * @return строковое представление кода валюты
     */
    public static String codeInput(int codeNumber) {
        System.out.print("Enter the currency code " + codeNumber + ":");
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    /**
     * Метод для ввода пользователем даты
     * @return дата, введенная пользователм, в формате LocalDate
     */
    public static LocalDate dateInput() {
        boolean isDateCorrect = false;
        while(!isDateCorrect){
            try {
                System.out.print("Enter data in format YYYY-MM-DD: ");
                Scanner in = new Scanner(System.in);
                String dateString = in.nextLine();
                LocalDate localDate = LocalDate.parse(dateString);
                // isDateCorrect = true;
                return localDate;
            } catch (Exception e){
                System.out.println("Wrong format!");
            }
        }
        return LocalDate.parse("2001-07-07");
    }
}
