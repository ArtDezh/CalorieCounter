public class Converter {
    public static double getDistance(int countSteps) {
        return (countSteps * 75) / 100_000.0;
    }

    public static double calorieBurn(int countSteps) {
        return (countSteps * 50) / 1000.0;
    }
}
