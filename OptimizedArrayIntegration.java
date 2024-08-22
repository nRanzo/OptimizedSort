import java.util.Arrays;

/**
 * The OptimizedArrayIntegration class provides a method to efficiently 
 * integrate a small number of unsorted elements into a larger, pre-sorted array.
 * This approach uses binary search to find the correct insertion point for 
 * the new data and then sorts the relevant portion of the array. 
 * 
 * <p>This technique is particularly advantageous when dealing with incremental 
 * data updates where re-sorting the entire array would be inefficient.</p>
 */
public class OptimizedArrayIntegration {

    /**
     * Integrates the new unsorted elements into the pre-sorted portion of the 
     * array using a binary search and localized sorting strategy.
     * 
     * @param sortedArr the array containing the pre-sorted elements
     * @param newElements the new unsorted elements to be integrated
     */
    public static void integrateAndSort(int[] sortedArr, int[] newElements) {
        // Edge case: If there are no new elements, return as no sorting is needed.
        if (newElements.length == 0) {
            return;
        }

        // Identify the minimum element in the new elements.
        int minNewElement = Arrays.stream(newElements).min().getAsInt();

        // Find the insertion index for this minimum element using binary search.
        int insertionIndex = binarySearch(sortedArr, 0, sortedArr.length - 1, minNewElement);

        // Combine the old sorted elements and new unsorted elements into one array.
        int[] combinedArray = Arrays.copyOfRange(sortedArr, 0, sortedArr.length + newElements.length);
        System.arraycopy(newElements, 0, combinedArray, sortedArr.length, newElements.length);

        // Sort the relevant portion of the array.
        Arrays.sort(combinedArray, insertionIndex, combinedArray.length);

        // Display the integrated and sorted array.
        System.out.println(Arrays.toString(combinedArray));
    }

    /**
     * Performs a binary search to determine the index at which the target 
     * element would be inserted in the sorted array.
     *
     * @param arr the sorted array
     * @param low the lower bound of the search range
     * @param high the upper bound of the search range
     * @param target the element whose insertion point is to be determined
     * @return the index at which the target element should be inserted
     */
    private static int binarySearch(int[] arr, int low, int high, int target) {
        if (low >= high) {
            return (arr[low] < target) ? low + 1 : low;
        }

        int mid = low + (high - low) / 2;

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
     * Demonstrates the integration of new elements into a pre-sorted array.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        int[] sortedArr = {1, 3, 5, 7, 9, 11, 13, 15, 17, 19};
        int[] newElements = {8, 4, 14, 2};

        integrateAndSort(sortedArr, newElements);
    }
}
