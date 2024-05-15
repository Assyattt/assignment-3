package MyList;
import java.util.NoSuchElementException;

public class Stack<T extends Comparable<T>> extends MyLinkedList<T> {
    public Stack(){
        super();
    }
    public boolean empty(){
        return size() == 0;
    }
    public T peek(){
        if(!empty()) return getLast();
        throw new NoSuchElementException("Stack is empty");
    }
    public T push(T item){
        add(item);
        return item;
    }
    public T pop(){
        T frontItem = peek();
        removeLast();
        return frontItem;
    }
}
