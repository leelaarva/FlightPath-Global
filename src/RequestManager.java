import java.io.*;
import java.util.*;

// RequestManager loads and stores all requested flight plans from the user’s input file. Each request is converted into a Request object and saved
public class RequestManager {
    private List<Request> requests = new ArrayList<>();

    public void loadRequests(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            int count = Integer.parseInt(sc.nextLine().trim()); // count of total request entries
            int processed = 0;

            while (sc.hasNextLine() && processed < count) {
                String line = sc.nextLine().trim();

                if (line.isEmpty()) { // skip empty/whitespace lines
                    continue;
                }
                String[] fields = line.split("\\|");

                if (fields.length != 3) {
                    System.out.println("Skipping invalid input: " + line);
                    continue;
                }
                String origin = fields[0].trim();
                String destination = fields[1].trim();
                char typeChar = fields[2].trim().charAt(0);
                requests.add(new Request(origin, destination, typeChar));
                processed++;
            }

        } catch (Exception e) {
            System.out.println("Error loading requests: " + e.getMessage());
        }
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void printRequests() {
        for (Request r : requests) {
            System.out.println(r);
        }
    }
}
