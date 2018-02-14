import java.util.HashMap;
import java.util.Map;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        int overallCount = 0;
        Map<Double, Integer> numbers = new HashMap();
        double dist;
        for(int i=0; i< points.length; i++){
            for(int j=0; j<points.length; j++){
                double temp = Math.pow(points[i][0]-points[j][0], 2)+Math.pow(points[i][1]-points[j][1], 2);
                dist = Math.sqrt(temp);
                if(numbers.containsKey(dist)){
                    numbers.put(dist, numbers.get(dist)+1);
                }
                else{
                    numbers.put(dist, 1);
                }
            }
            for(Integer k: numbers.values()){
                overallCount = overallCount + k*(k-1);
            }
            numbers.clear();
        }
        return overallCount;
    }
}

