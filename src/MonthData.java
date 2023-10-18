public class MonthData {
    private String nameMonth;
    private short numberOfDay;
    private int countSteps;

    public MonthData(String nameMonth, short numberOfDay, int countSteps) {
        this.nameMonth = nameMonth;
        this.numberOfDay = numberOfDay;
        this.countSteps = countSteps;
    }

    public short getNumberOfDay() {
        return numberOfDay;
    }

    public void setNumberOfDay(short numberOfDay) {
        if (numberOfDay >= 1 && numberOfDay <= 30) {
            this.numberOfDay = numberOfDay;
        } else {
            System.out.println("Номер дня может быть от 1 до 30!");
        }
    }

    public int getCountSteps() {
        return countSteps;
    }

    public void setCountSteps(int countSteps) {
        if (countSteps >= 0) {
            this.countSteps = countSteps;
        } else {
            System.out.println("Количество шагов не может быть отрицательным числом!");
        }
    }

    public String getNameMonth() {
        return nameMonth;
    }

    public void setNameMonth(String nameMonth) {
        this.nameMonth = nameMonth;
    }
}
