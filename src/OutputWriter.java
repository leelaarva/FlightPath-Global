import java.io.*;
import java.util.*;

/* OutputWriter handles all output file writing for the program. It writes flight results to a separate output file
and prints the top 3 most efficient paths */
public class OutputWriter {

    private PrintWriter out;

    // Creates a writer for the specified file
    public OutputWriter(String filename) {
        try {
            out = new PrintWriter(new FileWriter(filename));
        } catch (Exception e) {
            System.out.println("Error opening output file: " + e.getMessage());
        }
    }

    public void writeFlightHeader(int flightNum, Request r) {
        String typeLabel;
        if (r.sortType == Request.SortType.COST) {
            typeLabel = "Cost";
        } else {
            typeLabel = "Time";
        }
        out.println("Flight " + flightNum + ": " + r.origin + ", " + r.destination + " (" + typeLabel + ")");
    }

    public void writePath(int index, Models.Path p) {
        StringBuilder route = new StringBuilder(); // Build the city sequence string
        for (int i = 0; i < p.cities.size(); i++) {
            route.append(p.cities.get(i));
            if (i < p.cities.size() - 1) {
                route.append(" -> ");
            }
        }

        // Write to OUTPUT FILE, not console
        out.println("Path " + index + ": " + route + ". Time: " + p.totalTime + " Cost: " + String.format("%.2f", (double) p.totalCost));
    }

    // If no flight path found
    public void writeNoPlan() {
        out.println("No flight plan can be created.");
    }

    public void writeBlank() {
        out.println();
    }

    public void close() {
        out.close();
    }
}
