
public class Problems {

    public static int leastSum(int[] A) {
        countingSort(A);
        String num1 = "";
        String num2 = "";
        for(int i=0; i< A.length; i++){
            if(i%2==0){
                num1 = num1 + A[i];
            }
            else {
                num2 = num2 + A[i];
            }
        }
        int num1_int;
        int num2_int;
        if(num1.equals(""))
            num1_int = 0;
        else
            num1_int = Integer.parseInt(num1);

        if(num2.equals(""))
            num2_int = 0;
        else
            num2_int = Integer.parseInt(num2);

        return num1_int + num2_int;
    }

    private static void countingSort(int[] A) {
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
        if(A.length==0)
            return 0;
        int max = A[0];
        for(int i=0; i<A.length; i++){
            if(A[i] > max){
                max = A[i];
            }
        }
        return max;
    }
}
