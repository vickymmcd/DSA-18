import java.util.*;

public class Problems {

    public static BinarySearchTree<Integer> minimalHeight(List<Integer> values) {
        // TODO
        Collections.sort(values); // this will be O(nlogn) but I don't think we can do better anyway
        return minimalHeightRecursive(null, null, values, 0, values.size()-1);
    }

    private static BinarySearchTree<Integer> minimalHeightRecursive(BinarySearchTree<Integer> bst, TreeNode<Integer> currRoot,
                                                                    List<Integer> values, int min, int max){

        if(values.size()==1){
            bst = new BinarySearchTree<>();
            bst.root = new TreeNode<>(values.get(0));
            return bst;
        }
        if(min>max){
            return bst;
        }


        int mid = (values.get(max)-values.get(min))/2;

        if(bst== null) {
            bst = new BinarySearchTree<>();
            bst.root = new TreeNode<>(values.get(mid));
            currRoot = bst.root;
        }
        else{
            currRoot.key = values.get(mid);
        }

        currRoot.leftChild = new TreeNode<>(0);
        currRoot.rightChild = new TreeNode<>(0);

        currRoot.leftChild = minimalHeightRecursive(bst, currRoot.leftChild, values, min, mid).root;
        currRoot.rightChild = minimalHeightRecursive(bst, currRoot.rightChild, values, mid+1, max).root;

        return bst;
    }

    public static boolean isIsomorphic(TreeNode n1, TreeNode n2) {
        // TODO
        return false;
    }
}
