import java.util.Arrays;

public class LocksAndKeys {

    private static void swap(char[] A, int i, int j) {
        char t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    static char[][] locksAndKeys(char[] locks, char[] keys) {
        // Returns a 2d char array, where result[0] is the sorted locks, and result[1] is the sorted keys
        char[][] result = new char[2][];
        quickSort(keys, locks, 0, locks.length - 1);
        //Arrays.sort(locks);
        //Arrays.sort(keys);
        result[0] = locks;
        result[1] = keys;

        System.out.println();
        return result;
    }

//    // Method which works just like quick sort
//    private static void quickSort(char[] nuts, char[] bolts, int low,
//                                  int high) {
//        if (low < high) {
//            // Choose last character of bolts array for nuts partition.
//            int pivot = partition(nuts, low, high, bolts[high]);
//
//            // Now using the partition of nuts choose that for bolts
//            // partition.
//            partition(bolts, low, high, nuts[pivot]);
//
//            // Recur for [low...pivot-1] & [pivot+1...high] for nuts and
//            // bolts array.
//            quickSort(nuts, bolts, low, pivot - 1);
//            quickSort(nuts, bolts, pivot + 1, high);
//        }
//    }
//
//    // Similar to standard partition method. Here we pass the pivot element
//    // too instead of choosing it inside the method.
//    private static int partition(char[] arr, int low, int high, char pivot) {
//        int i = low;
//        char temp1, temp2;
//        for (int j = low; j < high; j++) {
//            if (arr[j] < pivot) {
//                temp1 = arr[i];
//                arr[i] = arr[j];
//                arr[j] = temp1;
//                i++;
//            } else if (arr[j] == pivot) {
//                temp1 = arr[j];
//                arr[j] = arr[high];
//                arr[high] = temp1;
//                j--;
//            }
//        }
//        temp2 = arr[i];
//        arr[i] = arr[high];
//        arr[high] = temp2;
//
//        // Return the partition index of an array based on the pivot
//        // element of other array.
//        return i;
//    }

    public static void quickSort(char[] a, char[] b, int left, int right){
        if(left < right){

            for(int i=0; i< a.length; i++){
                System.out.print(a[i]);
            }
            System.out.println();
            for(int i=0; i< a.length; i++){
                System.out.print(b[i]);
            }
            System.out.println();
            System.out.println(left);
            System.out.println(right);

            int index = partition(a, left, right, b[right]);
            partition(b, left, right, a[index]);
            System.out.println(index);

            quickSort(a, b, left, index-1);
            quickSort(a, b, index+1, right);
        }
    }

    public static int partition(char[] array, int left1, int right1, char pivot){
        int i = left1;
        for(int j=left1; j<right1; j++){
            if(array[j] < pivot){
                swap(array, i, j);
                i++;
            }
            else if(array[j] == pivot){
                swap(array, j, right1);
                j--;
            }
        }
        swap(array, i, right1);
        return i;
//        while(left1 <= right1){
//            while(left1<array.length && array[left1] < pivot){
//                left1++;
//            }
//
//            while(right1>0 && array[right1] > pivot){
//                right1--;
//            }
//
//            if(left1 <= right1){
//                swap(array, left1, right1);
//                left1++;
//                right1--;
//            }
//        }
//        return left1;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
//    static void quickSort(char[] keys, char[] locks, int lo, int hi) {
//        if (lo < hi) {
//            int p = partition(keys, lo, hi, locks[hi]);
//            partition(locks, lo, hi, keys[p]);
//
//            quickSort(keys, locks, lo, p-1);
//            quickSort(keys, locks, p+1, hi);
//            quickSort(locks, keys, lo, p-1);
//            quickSort(locks, keys, p+1, hi);
//        }
//    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
//    static int partition(char[] array, int lo, int hi, char val) {
//        char num = val;
//        boolean found = false;
//        char temp = array[lo];
//        for(int i=lo; i<hi; i++){
//            if(array[i] == num){
//                array[lo] = num;
//                array[i] = temp;
//                found = true;
//            }
//        }
//        for(int k=lo; k<hi+1; k++){
//            if(array[k] < num){
//                array[lo] = array[k];
//                //array[lo+1] = num;
//                for(int i=k; i>lo+1; i--){
//                    System.out.println("hello");
//
//                    array[i] = array[i-1];
//                }
//
//                if(found && lo+1 < array.length) {
//                    System.out.println("howdy");
//
//                    array[lo + 1] = num;
//                }
//                else if(lo+1 < array.length){
//                    array[lo + 1] = temp;
//                }
//                lo++;
//            }
//
//        }
//
//
//
//        return lo;
//    }
}




