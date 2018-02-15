import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * Best-case runtime: O(nlogn)
     * Worst-case runtime: O(n^2) but we are hopefully avoiding this with the random shuffling
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(logn)
     */
    @Override
    public int[] sort(int[] array) {
        Random random = new Random();
        for(int i=0; i<array.length; i++){
            int index = random.nextInt(array.length);
            swap(array, i, index);
        }
        quickSort(array, 0, array.length-1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p-1);
            quickSort(a, p+1, hi);
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {
        int num = array[lo];
        for(int k=lo; k<hi+1; k++){
            if(array[k] < num){
                array[lo] = array[k];
                //array[lo+1] = num;
                for(int i=k; i>lo+1; i--){
                    array[i] = array[i-1];
                }
                array[lo+1] = num;
                lo++;
            }

        }

        return lo;
    }

}
