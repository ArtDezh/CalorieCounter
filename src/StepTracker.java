import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StepTracker {

    private static int goalCountSteps = 10_000;
    static HashMap<String, HashMap<Short, Integer>> allDate = new HashMap<>();
    static HashMap<Short, Integer> daysAndSteps = new HashMap<>();

    public static void printMenu() {
        String[] menu = new String[4];
        menu[0] = "1. Ввести количество шагов за определенный день.";
        menu[1] = "2. Напечатать статистику за определенный месяц.";
        menu[2] = "3. Изменить цель по количеству шагов в день.";
        menu[3] = "4. Выйти из приложения.";

        for (String s : menu) {
            System.out.println(s);
        }
    }

    public static void startApp() {
        printMenu();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            int inputDigit = Integer.parseInt(scanner.nextLine());
            switch (inputDigit) {
                case 1:
                    addStepsForDay();
                    break;
                case 2:
                    getStatisticForMonth();
                    break;
                case 3:
                    goalCountSteps = scanner.nextInt();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Вы ввели не правильную команду! Попробуйте еще раз");
            }
        }
    }

    public static void addStepsForDay() {
        System.out.println("Введите название месяца, номер дня и количество шагов");
        Scanner scanner = new Scanner(System.in);
        MonthData monthData = new MonthData(scanner.nextLine(), Short.parseShort(scanner.nextLine()), Integer.parseInt(scanner.nextLine()));

        if (allDate.containsKey(monthData.getNameMonth())) {
            HashMap<Short, Integer> daysAndSteps = allDate.get(monthData.getNameMonth());
            daysAndSteps.put(monthData.getNumberOfDay(), monthData.getCountSteps());
        } else {
            daysAndSteps.put(monthData.getNumberOfDay(), monthData.getCountSteps());
            allDate.put(monthData.getNameMonth(), daysAndSteps);
        }
        printMenu();
    }

    public int getGoalCountSteps() {
        return goalCountSteps;
    }

    public void setGoalCountSteps(int goalCountSteps) {
        StepTracker.goalCountSteps = goalCountSteps;
    }

    public static void getStatisticForMonth() {
        Scanner scanner = new Scanner(System.in);
        String nameMonth = scanner.nextLine();
        int sumSteps = 0;
        for (Map.Entry<String, HashMap<Short, Integer>> entry : allDate.entrySet()) {
            String currentMonth = entry.getKey();
            if (currentMonth.equals(nameMonth)) {
                for (Map.Entry<Short, Integer> entry1 : daysAndSteps.entrySet()) {
                    short currentDay = entry1.getKey();
                    System.out.print(currentDay + " день: " + entry1.getValue() + ", ");
                    sumSteps += entry1.getValue();
                }
            }
        }
        System.out.println("\nОбщее количество шагов за месяц: " + sumSteps);
        System.out.println("Среднее количество шагов: " + sumSteps / 30);

        printMenu();
    }


}
