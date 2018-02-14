
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        for(int i=0; i < array.length; i++){
            int p = i;
            while(p > 0 && array[p] < array[p-1]){
                switch_vals(array, p, p-1);
                p--;
            }
        }
        return array;
    }

    private void switch_vals(int[] array, int p, int p2){
        int temp = array[p];
        array[p] = array[p2];
        array[p2] = temp;
    }
}
