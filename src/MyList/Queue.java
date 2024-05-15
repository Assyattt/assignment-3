package MyList;
import java.util.NoSuchElementException;

public class Queue<T extends Comparable<T>> extends MyLinkedList<T> {
    public Queue(){
        super();
    }
    public boolean empty(){
        return iterator().hasNext();
    }
    public T peek(){
        if(!empty()) return getLast();
        return null;
    }
    public T element(){
        if(!empty()) return getLast();
        throw new NoSuchElementException("Queue is empty");
    }

    public T enqueue(T item){
        addFirst(item);
        return item;
    }

    public T dequeue(){
        T frontItem = element();
        removeLast();
        return frontItem;
    }
    public T remove(){
        T frontItem = peek();
        removeLast();
        return frontItem;
    }
}
