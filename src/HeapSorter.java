import java.util.*;

/* HeapSorter organizes a list of Path objects by total cost or total time using the HeapSort algorithm. It uses a max-heap and
repeatedly extracts the maximum element to fully sort the list */
public class HeapSorter {

    // Perform heap sort on the list of Path objects based on cost or time
    public static void heapSort(List<Models.Path> paths, Request.SortType type) {
    int size = paths.size();

        // Build the initial max heap
        for (int index = size / 2 - 1; index >= 0; index--) {
            heapify(paths, size, index, type);
        }

        // Extract elements from the heap one at a time
        for (int end = size - 1; end >= 0; end--) {
            Collections.swap(paths, 0, end);  // move largest to the end
            heapify(paths, end, 0, type);  // heapify
        }
    }

    // Make sure that the subtree rooted at rootIndex satisfies max-heap structure by comparing the root with its children
    private static void heapify(List<Models.Path> paths, int heapSize, int rootIndex, Request.SortType type) {

        int largest = rootIndex;
        int leftChild  = 2 * rootIndex + 1;
        int rightChild = 2 * rootIndex + 2;

        // Check left child
        if (leftChild < heapSize && compare(paths.get(leftChild), paths.get(largest), type) > 0) {
            largest = leftChild;
        }

        // Check right child
        if (rightChild < heapSize && compare(paths.get(rightChild), paths.get(largest), type) > 0) {
            largest = rightChild;
        }

        // If largest isn't the root, swap and keep heapifying
        if (largest != rootIndex) {
            Collections.swap(paths, rootIndex, largest);
            heapify(paths, heapSize, largest, type);
        }
    }

    // Compare two Path objects depending on T or C sortType
    private static int compare(Models.Path a, Models.Path b, Request.SortType type) {

        if (type == Request.SortType.COST) {
            return Integer.compare(a.totalCost, b.totalCost);
        }
        return Integer.compare(a.totalTime, b.totalTime);
    }
}