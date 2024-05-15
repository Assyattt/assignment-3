package MyList;

public class MyMinHeap<T extends Comparable<T>> extends MyArrayList<T> {

    public MyMinHeap(){
        super();
    }
    public boolean empty(){
        return !iterator().hasNext();
    }
    public void insert(T item){
        if (empty()) add(item);
            add(item);
        traversUp(size()-1);
    }
    public T extractMin(){
        T result = get(1);
        set(1,getLast());
        removeLast();
        heapify(1);
        return result;
    }
    public T getMin(){
        return get(1);
    }
    public void heapify(int index){
        checkIndex(index);
        if(!existsIndex(index*2))
            return;
        if (existsIndex(rightChildOf(index)) && get(rightChildOf(index)).compareTo( (T) get(leftChildOf(index)) ) < 0 &&
                get(index).compareTo(get(rightChildOf(index))) > 0) {
            swap(index, rightChildOf(index));
            heapify(rightChildOf(index));
        } else if (get(index).compareTo(get(leftChildOf(index))) > 0){
            swap(index, leftChildOf(index));
            heapify(leftChildOf(index));
        }
    }
    private void traversUp(int index){
        while(index>1 && get(index).compareTo( (T)get(index/2) ) < 0) {
            swap(index, index/2);
            index = index / 2;
        }
    }
    private int leftChildOf(int index){
        checkIndex(index);
        if (index*2< size()) return index*2;
        else return -1;
    }
    private int rightChildOf(int index){
        checkIndex(index);
        if (index*2 + 1 < size()) return index*2 + 1;
        else return -1;
    }
    private int parentOf(int index){
        checkIndex(index);
        if(index/2 >= 1) return index/2;
        else throw new IndexOutOfBoundsException("Index " + index + " is root index");
    }
    private void swap(int index1, int index2){
        T temp = get(index1);
        set(index1, get(index2));
        set(index2, temp);
    }
    private void checkIndex(int index){
        if (index> size() || index<1 ) throw new IndexOutOfBoundsException("index not correct");
    }

    private boolean existsIndex(int index){
        return index < size() && index >= 1;
    }
}
