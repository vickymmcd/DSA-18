public class CountingSort {

    /**
     * Use counting sort to sort non-negative integer array A.
     * Runtime: O(n+k)
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int k = get_max(A);
        int[] buckets = new int[k+1];
        for(int i=0; i<A.length; i++){
            buckets[A[i]]++;
        }
        int j=0;
        for(int i=0; i<buckets.length; i++){
            while(buckets[i] > 0){
                buckets[i]--;
                A[j] = i;
                j++;
            }
        }
    }

    static private int get_max(int[] A){
        int max = A[0];
        for(int i=0; i<A.length; i++){
            if(A[i] > max){
                max = A[i];
            }
        }
        return max;
    }

}
