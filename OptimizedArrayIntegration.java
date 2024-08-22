import java.util.Arrays;

/**
 * The OptimizedArrayIntegration class implements a method to efficiently integrate 
 * a small number of unsorted elements into a larger, pre-sorted array. The approach 
 * uses binary search to determine the correct insertion point for the new data 
 * and then performs localized sorting, minimizing the need for re-sorting the 
 * entire array.
 * 
 * <p>This method is particularly useful in scenarios where new data arrives 
 * incrementally, and frequent updates to a large, sorted dataset are required.</p>
 */
public class OptimizedArrayIntegration {

    /**
     * Integrates a small, unsorted array of new elements into a larger, pre-sorted array.
     * The method identifies the appropriate insertion point for the new elements 
     * using binary search and then sorts the affected portion of the array.
     * 
     * @param sortedArr the pre-sorted array of elements
     * @param newElements the unsorted array of new elements to be integrated
     */
    public static void integrateAndSort(int[] sortedArr, int[] newElements) {
        // Edge case: If there are no new elements, return as no sorting is needed.
        if (newElements.length == 0) {
            return;
        }

        // Step 1: Identify the minimum element in the new elements array.
        int minNewElement = Arrays.stream(newElements).min().getAsInt();

        // Step 2: Perform a binary search on the sorted portion to find the insertion index.
        int insertionIndex = binarySearch(sortedArr, 0, sortedArr.length - 1, minNewElement);

        // Step 3: Combine the sorted array and new elements into one array.
        int[] combinedArray = Arrays.copyOf(sortedArr, sortedArr.length + newElements.length);
        System.arraycopy(newElements, 0, combinedArray, sortedArr.length, newElements.length);

        // Step 4: Sort only the portion of the array from the insertion index to the end.
        Arrays.sort(combinedArray, insertionIndex, combinedArray.length);

        // Display the integrated and sorted array.
        System.out.println("Integrated and Sorted Array: " + Arrays.toString(combinedArray));
    }

    /**
     * Performs a binary search on the sorted array to find the index where the target element
     * should be inserted. The search is limited to the range between low and high indices.
     * 
     * @param arr the sorted array
     * @param low the lower bound of the search range
     * @param high the upper bound of the search range
     * @param target the element whose insertion point is to be found
     * @return the index where the target element should be inserted
     */
    private static int binarySearch(int[] arr, int low, int high, int target) {
        // Base case: when the search range is reduced to one element
        if (low >= high) {
            return (arr[low] < target) ? low + 1 : low;
        }

        // Calculate the midpoint of the current search range.
        int mid = low + (high - low) / 2;

        // Recursive search to find the correct insertion index.
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearch(arr, mid + 1, high, target);
        } else {
            return binarySearch(arr, low, mid - 1, target);
        }
    }

    /**
     * Main method for testing the OptimizedArrayIntegration class.
     * Demonstrates the integration of new unsorted elements into a pre-sorted array.
     * 
     * <p>Examples:</p>
     * <ul>
     *   <li>Example 1: Integrating new elements into a small sorted array.</li>
     *   <li>Example 2: Integrating elements into a larger dataset.</li>
     * </ul>
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Example 1: Small sorted array with new elements
        int[] sortedArr1 = {1, 3, 5, 7, 9, 11, 13};
        int[] newElements1 = {4, 8, 2};

        System.out.println("Original Sorted Array 1: " + Arrays.toString(sortedArr1));
        System.out.println("New Elements 1: " + Arrays.toString(newElements1));
        integrateAndSort(sortedArr1, newElements1);

        // Example 2: Larger sorted array with additional elements
        int[] sortedArr2 = {10, 20, 30, 40, 50, 60, 70, 80};
        int[] newElements2 = {25, 35, 5};

        System.out.println("\nOriginal Sorted Array 2: " + Arrays.toString(sortedArr2));
        System.out.println("New Elements 2: " + Arrays.toString(newElements2));
        integrateAndSort(sortedArr2, newElements2);
    }
}
