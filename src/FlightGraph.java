import java.util.*;
import java.io.*;

public class FlightGraph {

    // Uses an adjacency list to map each city to a linked list of outgoing edges
    private Map<String, LinkedList<Models.Edge>> flightMap = new HashMap<>();
    public Map<String, LinkedList<Models.Edge>> getFlightMap() {
        return flightMap;
    }

    public void loadFlights(String filePath) {
        try (Scanner fileScanner = new Scanner(new File(filePath))) {

            int recCount = Integer.parseInt(fileScanner.nextLine().trim()); // read the total flight records in the file
            int processed = 0;

            // continue reading until all records are read or the file ends
            while (fileScanner.hasNextLine() && processed < recCount) {
                String line = fileScanner.nextLine().trim();

                // ignores blank lines or whitespaces
                if (line.isEmpty()) {
                    continue;
                }

                String[] fields = line.split("\\|");
                if (fields.length != 4) {
                    System.out.println("Skipping invalid input: " + line);
                    continue;
                }

                // record each flight's info
                String originCity = fields[0].trim();
                String destCity = fields[1].trim();
                int cost = Integer.parseInt(fields[2].trim());
                int time = Integer.parseInt(fields[3].trim());

                /* Add the forward connection from origin to destination. If originCity doesn't have an adjacency list,
                create a new list and attach it to the map */
                LinkedList<Models.Edge> originList = flightMap.get(originCity);
                if (originList == null) {
                    originList = new LinkedList<>();
                    flightMap.put(originCity, originList);
                }
                originList.add(new Models().new Edge(destCity, cost, time));

                // Add a reverse edge from destCity to originCity since flights work in both directions
                LinkedList<Models.Edge> destList = flightMap.get(destCity);
                if (destList == null) {
                    destList = new LinkedList<>();
                    flightMap.put(destCity, destList);
                }
                destList.add(new Models().new Edge(originCity, cost, time));
                processed++; // add to all the processed valid flight lines
            }

        } catch (Exception ex) {
            System.out.println("Error reading flight file: " + ex.getMessage());
        }
    }
    public void printGraph() {
        for (String city : flightMap.keySet()) {
            System.out.print(city + " -> ");

            LinkedList<Models.Edge> edges = flightMap.get(city);
            if (edges != null) {
                for (Models.Edge e : edges) {
                    System.out.print(e.destCity + "(" + e.price + "," + e.minutes + ")  ");
                }
            }
            System.out.println("");
        }
    }
}
