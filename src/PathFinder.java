import java.util.*;

/* PathFinder searches all the possible flight paths between any 2 cities by using an iterative backtracking approach or
stack-based DFS. Reads the adjacency list, explores all the possible paths without a cycle, and returns complete path as an object */
public class PathFinder {

    private final FlightGraph flightGraph;

    public PathFinder(FlightGraph graph) {
        this.flightGraph = graph;
    }

    // Stores each step of the path during DFS by using a stack frame that tracks current position and next node to explore
    private static class Frame {
        String currCity;  //  where this partial path currently ends
        List<String> pathSoFar; // ordered list of cities visited in this path
        int totCost;
        int totTime;
        Set<String> visitedCities;    // Prevents duplicates or looping back to previously visited cities

        Frame(String city, List<String> pathSoFar, int cost, int time, Set<String> visited) {
            this.currCity = city;
            this.pathSoFar = pathSoFar;
            this.totCost = cost;
            this.totTime = time;
            this.visitedCities = visited;
        }
    }

    // Determine all possible flight paths from origin to destination by using iterative stack-based DFS
    public List<Models.Path> findAllPaths(String origin, String destination) {

        List<Models.Path> allPaths = new ArrayList<>();
        Map<String, LinkedList<Models.Edge>> adj = flightGraph.getFlightMap();

        // if origin has no outgoing edges and isn't the destination itself
        if (!adj.containsKey(origin) && !origin.equals(destination)) {
            return allPaths;
        }

        Deque<Frame> stack = new ArrayDeque<>();

        // initial frame for DFS
        List<String> initialPath = new ArrayList<>();
        initialPath.add(origin);

        Set<String> initialVisited = new HashSet<>();
        initialVisited.add(origin);

        stack.push(new Frame(origin, initialPath, 0, 0, initialVisited));

        while (!stack.isEmpty()) {

            Frame frame = stack.pop();

            // Record this full path if destination has been reached
            if (frame.currCity.equals(destination)) {
                Models.Path found = new Models().new Path();
                found.cities.addAll(frame.pathSoFar);
                found.totalCost = frame.totCost;
                found.totalTime = frame.totTime;
                allPaths.add(found);
                continue;
            }

            // Get outgoing edges from this city
            LinkedList<Models.Edge> neighbors = adj.get(frame.currCity);
            if (neighbors == null) {
                continue;
            }

            // Explore all unvisited nodes
            for (Models.Edge edge : neighbors) {

                String nextCity = edge.destCity;

                // skip previously visited cities to avoid cycles
                if (!frame.visitedCities.contains(nextCity)) {

                    List<String> extendedPath = new ArrayList<>(frame.pathSoFar);
                    extendedPath.add(nextCity);

                    Set<String> extendedVisited = new HashSet<>(frame.visitedCities);
                    extendedVisited.add(nextCity);

                    Frame nextFrame = new Frame(nextCity, extendedPath, frame.totCost + edge.price, frame.totTime + edge.minutes, extendedVisited);

                    stack.push(nextFrame);
                }
            }
        }
        return allPaths;
    }
}
