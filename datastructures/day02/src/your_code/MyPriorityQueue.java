package your_code;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    private MyStack myStack = new MyStack();
    private LinkedList<Integer> maxlist;
    private LinkedList<Integer> maxes;
    private int size;

    public MyPriorityQueue(){
        maxlist = new LinkedList<>();
        maxes = new LinkedList<>();
        size = 0;
    }


    /**
     * Adds an item to the priority queue
     *
     * @param item number to be added to the queue
     */
    public void enqueue(int item) {
        this.push(item);

    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        return this.maxElement();
    }

    private void push(Integer e) {
        if (size==0){
            maxlist.addFirst(e);
        }
        else if (e >= maxlist.peek()){
            maxlist.addFirst(e);
        }
        else{
            while (!maxlist.isEmpty() && e < maxlist.peek()){
                maxes.addFirst(maxlist.pop());
            }
            maxlist.addFirst(e);
            while (!maxes.isEmpty()){
                maxlist.addFirst(maxes.pop());
            }
        }
        size++;
    }


    private Integer maxElement() {
        return maxlist.pop();
    }


}