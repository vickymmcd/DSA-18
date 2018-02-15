import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        PriorityQueue<Integer> minPQ = maxPQ();
        PriorityQueue<Integer> maxPQ = minPQ();


        if(inputStream.length == 0){
            return runningMedian;
        }
        for(int i=0; i<inputStream.length; i++){
            if(minPQ.size() == 0) {
                minPQ.offer(inputStream[i]);
            }
            else if(minPQ.peek() >= inputStream[i] && maxPQ.size() < minPQ.size()){
                maxPQ.offer(minPQ.poll());
                minPQ.offer(inputStream[i]);
            }
            else if(minPQ.peek() >= inputStream[i]){
                minPQ.offer(inputStream[i]);
            }
            else if(maxPQ.size() == 0){
                maxPQ.offer(inputStream[i]);
            }
            else if(maxPQ.peek() <= inputStream[i] && minPQ.size() < maxPQ.size()){
                minPQ.offer(maxPQ.poll());
                maxPQ.offer(inputStream[i]);
            }
            else if(maxPQ.peek() <= inputStream[i]){
                maxPQ.offer(inputStream[i]);
            }
            else if(minPQ.size() < maxPQ.size()){
                minPQ.offer(inputStream[i]);
            }
            else{
                maxPQ.offer(inputStream[i]);
            }


            if((minPQ.size() + maxPQ.size()) % 2 == 0){
                runningMedian[i] = (double)(minPQ.peek()+maxPQ.peek())/2;
            }
            else{
                if(minPQ.size() > maxPQ.size()){
                    runningMedian[i] = minPQ.peek();
                }
                else{
                    runningMedian[i] = maxPQ.peek();
                }
            }
        }
        return runningMedian;
    }

}
