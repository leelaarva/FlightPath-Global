import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Models {
    /* Edge.java represents each single flight leg in the adjacency list by storing the destCity (string),
    price (int), and minutes (int). Allows to explore all the possible flight paths */
    public class Edge {
        public String destCity; // city flight leg arrives at
        public int price;
        public int minutes;

        // Creates a directed flight leg with a cost & time
        public Edge(String destination, int cost, int time) {
            destCity = destination;
            price = cost;
            minutes = time;
        }

        @Override
        public String toString() {
            return "(" + destCity + ", cost =" + price + ", time =" + minutes + ")";
        }
    }

    /* Path represents a complete flight route by storing an ordered list of cities visited, the total cost, and the total travel time.
    By building these objects, it can use HeapSort to order all those paths by cost or time depending on what the user requires */
    public class Path {

        public List<String> cities; // cities in the order they are visited
        public int totalCost;
        public int totalTime;

        // Default constructor
        public Path() {
            this.cities = new ArrayList<>();
            this.totalCost = 0;
            this.totalTime = 0;
        }

        @Override
        public String toString() {
            return String.join(" -> ", cities) + " | Cost = " + totalCost + " | Time = " + totalTime;
        }
    }
}
