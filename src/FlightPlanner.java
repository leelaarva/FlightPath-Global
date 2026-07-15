import java.util.*;

public class FlightPlanner {

    // core components used throughout the program
    private final FlightGraph flightGraph;
    private final RequestManager reqManager;
    private PathFinder pathFinder;
    private OutputWriter writer;

    private final String flightsFile;
    private final String requestsFile;
    private final String outputFile;

    public FlightPlanner(String flightsFile, String requestsFile, String outputFile) {
        this.flightGraph = new FlightGraph();
        this.reqManager = new RequestManager();
        this.flightsFile = flightsFile;
        this.requestsFile = requestsFile;
        this.outputFile = outputFile;
    }

    public void run() {

        flightGraph.loadFlights(flightsFile); // Load flight data and build the adjacency list
        reqManager.loadRequests(requestsFile); // Load requested flight operations

        pathFinder = new PathFinder(flightGraph); // Implements iterative DFS by using the graph to explore routes
        writer = new OutputWriter(outputFile); // Output writer
        int flightNumber = 1;

        for (Request req : reqManager.getRequests()) {

            System.out.println("\nProcessing Flight " + flightNumber + ": "
                    + req.origin + " -> " + req.destination
                    + " (" + req.sortType + ")");

            writer.writeFlightHeader(flightNumber, req);

            // use DFS to generate all possible paths
            List<Models.Path> allPaths =
                    pathFinder.findAllPaths(req.origin, req.destination);

            // Display message if no flight paths are found or available
            if (allPaths.isEmpty()) {
                System.out.println("No flight path available.");
                writer.writeNoPlan();
                writer.writeBlank();
                flightNumber++;
                continue;
            }

            // Use HeapSort to sort by cost and/or time
            HeapSorter.heapSort(allPaths, req.sortType);

            // output the top 3 most optimal routes
            int limit = Math.min(3, allPaths.size());

            System.out.println("Top Flight Plans:");
            for (int i = 0; i < limit; i++) {
                Models.Path p = allPaths.get(i);
                System.out.println("Path " + (i + 1) + ": " + p);
                writer.writePath(i + 1, p);
            }

            writer.writeBlank();
            flightNumber++;
        }

        writer.close();
        System.out.println("\nOutput file created: " + outputFile);
    }

    public static void main(String[] args) {

        if (args.length != 3) {
            System.out.println("Usage: java FlightPlanner <flightsFile> <requestsFile> <outputFile>");
            return;
        }

        FlightPlanner planner = new FlightPlanner(args[0], args[1], args[2]);
        planner.run();
    }
}
