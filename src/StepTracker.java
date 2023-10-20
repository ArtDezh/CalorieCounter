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
                case 1 -> addStepsForDay();
                case 2 -> getStatisticForMonth();
                case 3 -> {
                    System.out.println("Введите количество шагов, сколько нужно пройти сегодня");
                    setGoalCountSteps(Integer.parseInt(scanner.nextLine()));
                }
                case 4 -> System.exit(0);
                default -> System.out.println("Вы ввели не правильную команду! Попробуйте еще раз");
            }
        }
    }

    public static void addStepsForDay() {
        System.out.println("Введите название месяца, номер дня и количество шагов");
        Scanner scanner = new Scanner(System.in);
        MonthData monthData = new MonthData(MonthData.setNameMonth(scanner.nextLine()), Short.parseShort(String.valueOf(MonthData.setNumberOfDay())), Integer.parseInt(String.valueOf(MonthData.setCountSteps())));

        if (allDate.containsKey(monthData.getNameMonth())) {
            HashMap<Short, Integer> daysAndSteps = allDate.get(monthData.getNameMonth());
            daysAndSteps.put(monthData.getNumberOfDay(), monthData.getCountSteps());
        } else {
            daysAndSteps.put(monthData.getNumberOfDay(), monthData.getCountSteps());
            allDate.put(monthData.getNameMonth(), daysAndSteps);
        }
        printMenu();
    }

    public static int getGoalCountSteps() {
        return goalCountSteps;
    }

    public static void setGoalCountSteps(int goalCountSteps) {
        Scanner scanner = new Scanner(System.in);
        int temp = goalCountSteps;
        while (true) {
            if (temp > 0) {
                StepTracker.goalCountSteps = goalCountSteps;
                break;
            } else {
                System.out.println("Нельзя ввести отрицательное количество шагов! Попробуйте снова.");
                temp = scanner.nextInt();
            }
        }
        printMenu();
    }

    public static void getStatisticForMonth() {
        System.out.println("Введите название месяца, за который хтите получить статисику.");
        Scanner scanner = new Scanner(System.in);
        String nameMonth = scanner.nextLine();
        int sumSteps = 0;
        int maxCountSteps = 0;
        int maxCountDaysInRow = 0;
        int count = 0; // для логик подсчета самой длинной серии

        for (Map.Entry<String, HashMap<Short, Integer>> entry : allDate.entrySet()) {
            String currentMonth = entry.getKey();
            if (currentMonth.equals(nameMonth)) {
                for (Map.Entry<Short, Integer> entry1 : daysAndSteps.entrySet()) {
                    short currentDay = entry1.getKey();
                    System.out.print(currentDay + " день: " + entry1.getValue() + ", ");
                    sumSteps += entry1.getValue();

                    if (maxCountSteps < entry1.getValue()) maxCountSteps = entry1.getValue();
                    if (entry1.getValue() >= getGoalCountSteps()) {
                        count++;
                        if (maxCountDaysInRow < count) maxCountDaysInRow = count;
                    } else count = 0;
                }
            }
        }
        System.out.println("\nОбщее количество шагов за месяц: " + sumSteps);
        System.out.println("Максимальное пройденное количество шагов в месяц: " + maxCountSteps);
        System.out.println("Среднее количество шагов: " + sumSteps / 30);
        System.out.println("Вы прошли за " + nameMonth + ": " + Converter.getDistance(sumSteps) + " км");
        System.out.println("Вы потеряли за " + nameMonth + ": " + Converter.calorieBurn(sumSteps) + "ккал");
        System.out.println("Количество дней подряд с количеством шагов больше " + getGoalCountSteps() + " получилось: " + maxCountDaysInRow + "\n");

        printMenu();
    }


}
