public class MyArrayList {
    private Cow[] elems;
    private int size;

    // Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // Runtime: O(1)*
    public void add(Cow c) {
        if(size==elems.length){
            grow();
        }
        elems[size] = c;
        size++;
    }

    // Runtime: O(1)
    public int size() {
        return size;
    }

    // Runtime: O(1)
    public Cow get(int index) {
        if(elems[index] == null){
            throw new IndexOutOfBoundsException("Sorry, there is no cow at that index");
        }
        return elems[index];
    }

    // Runtime: O(N)
    public Cow remove(int index) {
        if(index >= elems.length || index < 0){
            throw new IndexOutOfBoundsException("Sorry, that is not a valid index");
        }
        else if(elems[index] == null){
            throw new IndexOutOfBoundsException("Sorry, there is no cow at that index");
        }
        Cow removed = elems[index];
        elems[index] = null;
        if(index+1 < size){
            for(int i=index+1; i<size;i++){
                elems[i-1] = elems[i];
            }
        }
        size = size-1;
        if(size < .25*elems.length && size >= 4){
            shrink();
        }
        return removed;
    }

    // Runtime: O(N)
    public void shrink(){
        Cow[] B = new Cow[(int)(.5*elems.length)];
        System.arraycopy(elems,0, B, 0, size);
        elems = B;
    }

    // Runtime: O(N)
    public void add(int index, Cow c) {
        if(size == elems.length){
            grow();
        }
        else if(index > size || index < 0){
            throw new IndexOutOfBoundsException("Sorry, invalid index for addition of cow");
        }
        if(index+1 < size){
            Cow temp = elems[index];
            for(int i=index+1; i<size; i++){
                if(i-1 == index){
                    elems[i-1] = c;
                }
                else {
                    elems[i-1] = temp;
                    temp = elems[i];
                }
            }
            size++;
        }
        else{
            elems[index+1] = elems[index];
            elems[index] = c;
            size++;
        }
    }

    // Runtime: O(N)
    public void grow(){
        Cow[] B = new Cow[size*2];
        System.arraycopy(elems,0, B, 0, size);
        elems = B;
    }
}
