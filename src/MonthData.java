import java.util.Scanner;

public class MonthData {
    static Scanner scanner = new Scanner(System.in);

    private static String nameMonth;
    private static short numberOfDay;
    private static int countSteps;

    public MonthData(String nameMonth, short numberOfDay, int countSteps) {
        MonthData.nameMonth = nameMonth;
        MonthData.numberOfDay = numberOfDay;
        MonthData.countSteps = countSteps;
    }

    public short getNumberOfDay() {
        return numberOfDay;
    }

    public static short setNumberOfDay() {
        boolean flag = true;
        short temp = 0;
        while (flag) {
            System.out.println("Номер дня может быть от 1 до 30!");
            temp = scanner.nextShort();
            if (temp >= 1 && temp <= 30) flag = false;
        }
        return MonthData.numberOfDay = temp;
    }

    public int getCountSteps() {
        return countSteps;
    }

    public static int setCountSteps() {
        boolean flag = true;
        int temp = 0;
        while (flag) {
            System.out.println("Количество шагов не может быть отрицательным числом!");
            temp = scanner.nextInt();
            if (temp >= 0) flag = false;
        }
        return MonthData.countSteps = temp;
    }

    public String getNameMonth() {
        return nameMonth;
    }

    public static String setNameMonth(String nameMonth) {
        return MonthData.nameMonth = nameMonth;
    }
}
