package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;
    private Node my_node;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        if (head==null){
            my_node = new Node(c, null, null);
            head = my_node;
            tail = my_node;
            size++;
        }
        else{
            my_node = new Node(c, tail, null);
            tail.next = my_node;
            tail = my_node;
            size++;
        }
    }

    public void addFirst(Chicken c) {
        if (head==null){
            my_node = new Node(c, null, null);
            head = my_node;
            tail = my_node;
            size++;
        }
        else{
            my_node = new Node(c, null, head);
            head.prev = my_node;
            head = my_node;
            size++;
        }
    }

    public Chicken get(int index) {
        int count = 0;
        Node curr_node = head;
        while(count < size){
            if(count == index){
                return curr_node.val;
            }
            count++;
            curr_node = curr_node.next;
        }
        return null;
    }

    public Chicken remove(int index) {
        // TODO
        return null;
    }

    public Chicken removeFirst() {
        if (head == null){
            return null;
        }
        else if (head.next == null){
            Chicken chick = head.val;
            head = null;
            tail = null;
            size = 0;
            return chick;
        }
        else{
            Chicken chick = head.val;
            head = head.next;
            head.prev = null;
            size--;
            return chick;
        }
    }

    public Chicken removeLast() {
        if (head == null){
            return null;
        }
        else if (head.next == null){
            Chicken chick = head.val;
            head = null;
            tail = null;
            size = 0;
            return chick;
        }
        else{
            Chicken chick = tail.val;
            tail = tail.prev;
            tail.next = null;
            size--;
            return chick;
        }
    }
}