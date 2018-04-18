public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        return recursiveBadVersion(0, n, isBadVersion);
    }

    public static long recursiveBadVersion(long start, long end, IsFailingVersion isBadVersion){
        System.out.println();
        if(start+1>=end){

            if(isBadVersion.isFailingVersion(start)){
                return start;
            }
            else if(isBadVersion.isFailingVersion(end)){
                return end;
            }
            else{
                return -1;
            }
        }
        if(isBadVersion.isFailingVersion(start + (end-start)/2)){
            return recursiveBadVersion(start, start + (end-start)/2, isBadVersion);
        }
        else{
            return recursiveBadVersion(start+(end-start)/2, end, isBadVersion);
        }
    }
}
