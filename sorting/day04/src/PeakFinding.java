public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;
        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }

    private static int windowMaxXIndex(int[][] nums, int xlow, int xhigh, int ylow, int yhigh){
        int numRows = yhigh-ylow;
        int numColumns = xhigh-xlow;
        int greatest = 0;
        int xindex = 0;
        int max1 = maxXIndex(ylow, xlow, xhigh, nums);
        int max2 = maxXIndex(ylow+numRows/2, xlow, xhigh, nums);
        int max3 = maxXIndex(ylow+numRows-1, xlow, xhigh, nums);
        int max4 = maxYIndex(xlow, ylow, yhigh, nums);
        int max5 = maxYIndex(xlow+numColumns/2, ylow, yhigh, nums);
        int max6 = maxYIndex(xlow+numColumns-1, ylow, yhigh, nums);
        if(nums[ylow][max1] > greatest){
            greatest = nums[ylow][max1];
            xindex = max1;
        }
        if(nums[ylow+numRows/2][max2] > greatest){
            greatest = nums[ylow+numRows/2][max2];
            xindex = max2;
        }
        if(nums[ylow+numRows-1][max3] > greatest){
            greatest = nums[ylow+numRows-1][max2];
            xindex = max2;
        }
        if(nums[max4][xlow] > greatest){
            greatest = nums[max4][xlow];
            xindex = xlow;
        }
        if(nums[max5][xlow+numColumns/2] > greatest){
            greatest = nums[max5][xlow+numColumns/2];
            xindex = xlow+numColumns/2;
        }
        if(nums[max6][xlow+numColumns-1] > greatest){
            xindex = xlow+numColumns-1;
        }
        return xindex;

    }

    private static int windowMaxYIndex(int[][] nums, int xlow, int xhigh, int ylow, int yhigh){
        int numRows = yhigh-ylow;
        int numColumns = xhigh-xlow;
        int greatest = 0;
        int yindex = 0;
        int max1 = maxXIndex(ylow, xlow, xhigh, nums);
        int max2 = maxXIndex(ylow+numRows/2, xlow, xhigh, nums);
        int max3 = maxXIndex(ylow+numRows-1, xlow, xhigh, nums);
        int max4 = maxYIndex(xlow, ylow, yhigh, nums);
        int max5 = maxYIndex(xlow+numColumns/2, ylow, yhigh, nums);
        int max6 = maxYIndex(xlow+numColumns-1, ylow, yhigh, nums);
        if(nums[ylow][max1] > greatest){
            greatest = nums[ylow][max1];
            yindex = ylow;
        }
        if(nums[ylow+numRows/2][max2] > greatest){
            greatest = nums[ylow+numRows/2][max2];
            yindex = ylow+numRows/2;
        }
        if(nums[ylow+numRows-1][max3] > greatest){
            greatest = nums[ylow+numRows-1][max2];
            yindex = ylow+numRows-1;
        }
        if(nums[max4][xlow] > greatest){
            greatest = nums[max4][xlow];
            yindex = max4;
        }
        if(nums[max5][xlow+numColumns/2] > greatest){
            greatest = nums[max5][xlow+numColumns/2];
            yindex = max5;
        }
        if(nums[max6][xlow+numColumns-1] > greatest){
            yindex = max6;
        }
        return yindex;

    }


    private static int recursiveOneDPeak(int low, int high, int[] nums){
        int i = low + (high-low)/2;
        if(i>=nums.length || i<0){
            return -1;
        }
        if(peakOneD(i, nums) == 0){
            return i;
        }
        else if(peakOneD(i, nums) == 1){
            return recursiveOneDPeak(i+1, high, nums);
        }
        else{
            return recursiveOneDPeak(low, high-1, nums);
        }
    }


    public static int findOneDPeak(int[] nums) {
        return recursiveOneDPeak(0, nums.length, nums);
    }

    private static int[] recursiveFindTwoDPeak(int[][] nums, int xlow, int xhigh, int ylow, int yhigh){
        int answer[] = new int[2];

        int x = windowMaxXIndex(nums, xlow, xhigh, ylow, yhigh);
        int y = windowMaxYIndex(nums, xlow, xhigh, ylow, yhigh);

        if(peakX(x, y, nums)==0 && peakY(x, y, nums)==0){
            answer[0] = y;
            answer[1] = x;
            return answer;
        }
        if(peakX(x, y, nums)==1){
            return recursiveFindTwoDPeak(nums, x+1, xhigh, ylow, yhigh);
        }
        else if(peakX(x, y, nums) == -1){
            return recursiveFindTwoDPeak(nums, xlow, x, ylow, yhigh);
        }
        else if(peakY(x, y, nums) == 1){
            return recursiveFindTwoDPeak(nums, xlow, xhigh, y+1, yhigh);
        }
        else{
            return recursiveFindTwoDPeak(nums, xlow, xhigh, ylow, y);
        }
    }

    public static int[] findTwoDPeak(int[][] nums) {
        return recursiveFindTwoDPeak(nums,0, nums[0].length, 0, nums.length);
    }

}
