import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        int currk = 0;
        Stack<Integer> myStack = new Stack<>();
        for (int i = 0; i < A.length; i++) {
            if (currk < k){
                while (!myStack.empty() && currk<k && myStack.peek() > A[i]){
                    myStack.pop();
                    currk++;
                }
            }
            if(myStack.size() < A.length-k) {
                myStack.add(A[i]);
            }
        }
        return myStack;
    }

    public static boolean isPalindrome(Node n) {
        int size = 0;
        int count = 0;
        Node currNode = n;
        Node newNode = null;
        Node prev = null;
        Node currNode2 = null;

        if(n==null){
            return true;
        }

        // determine the size of the linked list
        while (currNode.next != null){
            currNode = currNode.next;
            size++;
        }

        // reverse the first half of the linked list
        currNode = n;
        while (count < size/2){
            newNode = currNode.next;
            currNode.next = prev;
            prev = currNode;
            currNode = newNode;
            count++;
        }

        // deal with linked lists with odd numbers of elements
        if(size%2!=0){
            if(newNode != null) {
                currNode2 = newNode.next;
            }
        }
        else{
            currNode2 = newNode;
        }

        // determine if the elements from both halves of the linked list are the same
        boolean first = true;
        if(currNode != null && currNode2 !=null) {

            while (currNode.next != null) {
                if (currNode.val != currNode2.val) {
                    return false;
                }
                if(first) {
                    first = false;
                    currNode = prev;
                }
                else {
                    currNode = currNode.next;
                }
                currNode2 = currNode2.next;
            }
        }


        return true;
    }

    public static String infixToPostfix(String s) {
        Stack <String> operations = new Stack<>();
        String result = "";
        char temp = 0;
        boolean first = true;

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == '+' || s.charAt(i) == '-' || s.charAt(i)== '/' || s.charAt(i)=='*'){
                operations.push(s.charAt(i) + "");
            }
            else if(s.charAt(i) == ')'){
                while(!operations.isEmpty()) {
                    result = result + operations.pop() + " ";
                }
            }
            else if(s.charAt(i) != '(' && s.charAt(i) != ' '){
                result = result + s.charAt(i) + " ";
            }
        }

        return result.trim();
    }

}
