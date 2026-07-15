// Request represents each of the flight search instruction: origin city, destination city, sorted by T or C
public class Request {

    // C to sort by cheapest to most expensive route and T to sort by fastest to slowest route
    public enum SortType {
        COST,
        TIME
    }

    // cities pulled directly from the request line
    public String origin;
    public String destination;

    // sorting preference for this particular request
    public SortType sortType;

    // Constructs a Request using the origin, destination, and a single character (C or T)
    public Request(String origin, String destination, char sortChar) {
        this.origin = origin;
        this.destination = destination;

        // Convert it into an enum value
        char c = Character.toUpperCase(sortChar);
        if (c == 'C') {
            this.sortType = SortType.COST;
        } else {
            this.sortType = SortType.TIME;
        }
    }

    @Override
    public String toString() {
        return origin + " -> " + destination + " (" + sortType + ")";
    }
}