package MyList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T extends Comparable<T>> implements MyList<T>{
    private T[] arr;
    private int size;

    public MyArrayList() {
        arr = (T[]) new Comparable[5];
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{
        int cursor = 0;
        @Override
        public boolean hasNext() {
            return cursor < size && cursor >= 0;
        }
        @Override
        public T next() {
            T newItem = arr[cursor];
            cursor++;
            return newItem;
        }
    }

//    This method sets the value of an element of ArrayList under the given index to the parameter item.
//    First, it checks whether index is valid then change set arrays value with direct acccess to it through inner field T[] arr
//    Time complexity: O(1)
//    @param index The index of element of array which we want to change
//    @param item The new value of arr[index] that we want to set
//    @return Returns nothing

    @Override
    public void set(int index, T item) {
        checkIndex(index);
        arr[index] = (T) item;
    }

//    This method add new element to ArrayList in the end.
//    It check whether the size of arr is enough for adding new element if not it increase its capacity by method increaseBuffer
//    then it increase the size of by one and put parameter 'element' into index size-1.
//    Time complexity: if method ends up using increaseBuffer then time complexity becomes O(n) otherwise time complexity is O(1)
//    @parameter The parameter 'element' we put into index size-1
    public void add(T element) {
        if(size>=arr.length)
            increaseBuffer();
        arr[size++] = (T) element;
    }
//    This method assign the parameter 'element' to the given index of T[] arr and move all element to the right of index by 1 forward.
//    It checks whether the size of arr is enough for adding new element if not it increase its capacity by method increaseBuffer
//    also method checks if the index is valid by checkIndex.
//    Time complexity: The asymptotically time complexty of method is O(n) since it may iterates through all elemets from the end to the given index
//    resulting in the linear time complexity.
//    @param element The value we want to assign to the given index
//    @param index The given index
    public void add(int index, T element) {
        if(size>=arr.length)
            increaseBuffer();
        checkIndex(index);
        for(int i = size; i >= index+1; i--){
            arr[i] = arr[i-1];
        }
        arr[index] = element;
    }

//    This method assign the parameter 'element' to the first index 0 of T[] arr and move all element by 1 to the right in advance.
//    It checks whether the size of arr is enough for adding new element if not it increase its capacity by method increaseBuffer
//    also method checks if the index is valid by checkIndex.
//    Time complexit: time complexity is O(n) since it iterates through all elements of the array
//    resulting in linear time complexity.
    public void addFirst(T item){
        if(size>=arr.length)
            increaseBuffer();
        for(int i = size; i >= 1; i--){
            arr[i] = arr[i-1];
        }
        arr[0] = (T) item;
        size++;
    }

//    This method implements method add() and parameter 'item' to the end of array.
//    Time complexity: The asymptotically time complexty of method is O(n) since it may iterates through all elemets from the end to the given index
//    resulting in the linear time complexity.Time complexity: The asymptotically time complexty of method is O(n) since it may iterates through all elemets from the end to the given index
//    resulting in the linear time complexity.
    public void addLast(T item){
        add(item);
    }

//    This method increase capacity of array by creating new instance of array with length of array bigger and copy all elements into it.
//    The asymptotically time complexty of method is O(n) since it may iterates through all elemets from the end to the given index
//    resulting in the linear time complexity.
//    Time complexity: O(n)
    private void increaseBuffer() {
        T[] newArr = (T[]) new Comparable[arr.length*2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];  //copy each element from old to new
        }
        arr = newArr; //change reference of arr from old memory location to new
    }
//    Retrieves the element at the specified index in the array.
//    Throws an IndexOutOfBoundsException if the index is out of range.
//    Time complexity: O(1)
    public T get(int index){
        checkIndex(index);
        return arr[index];
    }

    public T getFirst(){
        return arr[0];
    }

    // Retrieves the first element of the array.
// Time complexity: O(1)
    public T getLast(){
        return arr[size-1];
    }

    // Checks if the given index is within the bounds of the array.
// Throws an IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size of the array.
    private void checkIndex(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index not correct");
    }

    // Returns the number of elements currently stored in the array.
// Time complexity: O(1)
    public int size(){
        return size;
    }

    // Prints all elements currently stored in the array.
// Time complexity: O(n)
    public void printArr(){
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // Removes the element at the specified index in the array.
// After removing move all elements on the left of removed elements to the left and subtract 1 from size
// Throws an IndexOutOfBoundsException if the index is out of range.
// Time complexity: O(n)
    public void remove(int index) {
        checkIndex(index);
        for (int i = index + 1; i < size; i++) {
            arr[i-1] = arr[i];
        }
        size--;
    }

    // Removes the first element of the array.
// Time complexity: O(n)
    public void removeFirst(){
        remove(0);
    }

    // Removes the last element of the array.
// Time complexity: O(1)
    public void removeLast(){
        remove(size-1);
    }

    // Sorts the elements of the array in ascending order using the compareTo method.
// It uses double loop, first loop fix the higher bounds of sorting i,
// second compare each element j below bound with the highest element i and swap them if j is greater than i.
// Time complexity: O(n^2)
    public void sort(){
        for (int i = size-1; i>=0; i--){
            for (int j=i; j>=0; j--){
                if(arr[j].compareTo(arr[i]) > 0){
                    T temp = arr[j];
                    arr[j]=arr[i];
                    arr[i]=temp;
                }
            }
        }
    }

    // Returns the index of the first occurrence of the specified element in the array, or -1 if the element is not found.
// Use loop going through all elements from th beginning of array.
// Time complexity: O(n)
    public int indexOf(Object item){
        for(int i=0; i < size; i++)
            if(arr[i].compareTo((T) item) == 0) return i;
        throw new NoSuchElementException("there is no such object");
    }

    // Returns the index of the last occurrence of the specified element in the array, or -1 if the element is not found.
    // Use loop going through all elements from the end of array.
    // Time complexity: O(n)
    public int lastIndexOf(Object item){
        for(int i=size-1; i >= 0; i--)
            if(arr[i].compareTo((T) item) == 0) return i;
        throw new NoSuchElementException("there is no such object");
    }

    // Returns true if the specified element exists in the array, false otherwise.
// Use loop going through all elements from th beginning of array.
// Time complexity: O(n)
    public boolean exists(Object item){
        for(int i=0; i < size; i++)
            if(arr[i].compareTo((T) item) == 0) return true;
        return false;
    }
    // Returns an array containing all of the elements in this array list in the same order as they appear.
// Time complexity: O(1)
    public T[] toArray(){
        return (T[]) arr;
    }

    // Removes all of the elements from this array list.
// Time complexity: O(1)
    public void clear() {
        arr =  (T[]) new Comparable[5];
        size = 0;
    }

    // Prints all elements currently stored in the array, separated by spaces.
    // Use loop going through all elements from th beginning of array.
    // Time complexity: O(n)
    public void print(){
        for(int i = 0; i < size; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.print("\n");
    }
}
