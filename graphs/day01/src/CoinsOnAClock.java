import java.util.ArrayList;
import java.util.List;

public class CoinsOnAClock {

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        List<char[]> result = new ArrayList<>();
        char[] soln = new char[hoursInDay];
        return coinsOnAClockRecursive(result, soln, pennies, nickels, dimes, hoursInDay, 0);
    }

    private static List<char[]> addSolution(List<char[]> curr, char[] soln){
        char[] B = new char[soln.length];
        System.arraycopy(soln, 0, B, 0, soln.length);
        curr.add(B);
        return curr;
    }

    public static List<char[]> coinsOnAClockRecursive(List<char[]> curr, char[] soln, int pennies, int nickels, int dimes, int hoursInDay, int loc){
        if(pennies+nickels+dimes == 0)
            return addSolution(curr, soln);
        for(int i=0; i<3; i++){
            int newloc;
            if(soln[loc] != 'p' && soln[loc] != 'n' && soln[loc] != 'd') {
                if (i == 0 && pennies > 0) {
                    soln[loc] = 'p';
                    pennies--;
                    newloc = (loc + 1) % soln.length;
                    coinsOnAClockRecursive(curr, soln, pennies, nickels, dimes, hoursInDay, newloc);
                    pennies++;
                }
                if (i == 1 && nickels > 0) {
                    soln[loc] = 'n';
                    nickels--;
                    newloc = (loc + 5) % soln.length;
                    coinsOnAClockRecursive(curr, soln, pennies, nickels, dimes, hoursInDay, newloc);
                    nickels++;
                }
                if (i == 2 && dimes > 0) {
                    soln[loc] = 'd';
                    dimes--;
                    newloc = (loc + 10) % soln.length;
                    coinsOnAClockRecursive(curr, soln, pennies, nickels, dimes, hoursInDay, newloc);
                    dimes++;
                }
            }
            else{
                return curr;
            }

        }
        return curr;
    }
}
