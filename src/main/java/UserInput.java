import java.time.LocalDate;

public class UserInput {
    private LocalDate Date;
    private String Code1;
    private String Code2;
    private int Num;


    public UserInput(LocalDate date, String code) {
        this.Date = date;
        this.Code1 = code;
        Code2 = "";
    }

    public UserInput(LocalDate date, int num, String code1, String code2) {
        Date = date;
        Num = num;
        Code1 = code1;
        Code2 = code2;
    }

    public LocalDate getDate() {
        return Date;
    }

    public String getCode1() {
        return Code1;
    }

    public String getCode2() {
        return Code2;
    }

    public int getNum() {
        return Num;
    }

    public void setDate(LocalDate date) {
        this.Date = date;
    }

    public void setCode(String code) {
        this.Code1 = code;
    }

}
