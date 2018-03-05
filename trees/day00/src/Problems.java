import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
        Collections.sort(values); // this will be O(nlogn) but I don't think we can do better anyway
        return minimalHeightRecursive(new BinarySearchTree<>(), values, 0, values.size()-1);
    }

    private static BinarySearchTree<Integer> minimalHeightRecursive(BinarySearchTree<Integer> bst,
                                                                    List<Integer> values, int min, int max){

        if(min>max){
            return bst;
        }


        int midindex = ((max-min)/2)+min;
        int mid = values.get(midindex);

        bst.add(mid);

        minimalHeightRecursive(bst, values, min, midindex-1);
        minimalHeightRecursive(bst, values, midindex+1, max);

        return bst;
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
