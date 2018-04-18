import java.util.Arrays;
import java.util.PriorityQueue;

class Gap implements Comparable<Gap>{
    int start;
    int end;

    public Gap(int x1, int x2){
        start = x1;
        end = x2;
    }

    @Override
    public int compareTo(Gap o) {
        return gapSize() - o.gapSize();
    }

    public int gapSize(){
        return end-start-1;
    }
}

public class BarnRepair {

    public static int solve(int M, int[] occupied) {
        PriorityQueue<Gap> gaps = new PriorityQueue<>();

        int stallsBlocked = occupied.length;

        Arrays.sort(occupied);

        for(int i=1; i<occupied.length; i++){
            if(occupied[i]-occupied[i-1] > 1){
                gaps.add(new Gap(occupied[i-1], occupied[i]));
            }
        }

        int boardsUsed = gaps.size()+1;

        while(boardsUsed > M){
            Gap gap = gaps.poll();

            stallsBlocked = stallsBlocked + gap.gapSize();

            boardsUsed--;
        }

        return stallsBlocked;
    }
}