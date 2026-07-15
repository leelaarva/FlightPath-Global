public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Main <flightsFile> <requestsFile> <outputFile>");
            return;
        }

        FlightPlanner planner = new FlightPlanner(args[0], args[1], args[2]);
        planner.run();
    }
}
