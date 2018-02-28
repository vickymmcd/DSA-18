
public class MergeSort{

    private static final int INSERTION_THRESHOLD = 10;
    private static int swapCounter = 0;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * Best-case runtime: O(nlogn) already in order
     * Worst-case runtime: O(nlogn)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(n)
     */
    public static int[] sort(int[] array) {
        return recursiveSort(array);
    }

    private static int[] recursiveSort(int[] array){
        if(array.length <= 1){
            return array;
        }

        int[] left;
        int[] right = new int[array.length/2];
        if(array.length % 2 != 0){
            left = new int[(array.length/2)+1];
            System.arraycopy(array, 0, left, 0, (int)Math.floor(array.length/2)+1);
            System.arraycopy(array, (array.length/2)+1, right, 0, (int)Math.floor(array.length/2));

        }
        else{
            left = new int[(array.length/2)];
            System.arraycopy(array, 0, left, 0, array.length/2);
            System.arraycopy(array, array.length/2, right, 0, array.length/2);
        }

        left = recursiveSort(left);
        right = recursiveSort(right);
        return merge(left, right);

    }

    public static int getSwaps(int[] array){
        swapCounter = 0;
        array = sort(array);
        return swapCounter;
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    private static int[] merge(int[] a, int[] b) {
        int[] result = new int[a.length+b.length];
        int pointerA = 0;
        int pointerB = 0;
        int count = 0;

        while(pointerA < a.length && pointerB < b.length){
            if(a[pointerA] <= b[pointerB]){
                result[count] = a[pointerA];
                pointerA++;
            }
            else{
                result[count] = b[pointerB];
                pointerB++;
                swapCounter++;
            }
            count++;
        }
        while(pointerA < a.length){
            result[count] = a[pointerA];
            pointerA++;
            count++;
        }
        while(pointerB < b.length){
            result[count] = b[pointerB];
            pointerB++;
            count++;
        }
        return result;
    }

}
