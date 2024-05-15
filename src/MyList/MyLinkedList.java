package MyList;

import java.util.Iterator;

public class MyLinkedList<T extends Comparable<T>> implements MyList<T>{

    private class MyNode<T> {
        T data;
        MyNode<T> next;
        MyNode<T> prev;

        public MyNode(T data) {
            this.data = data;
            next = null;
            prev = null;
        }
    }
    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    public MyLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public Iterator<T> iterator(){
        return new MyIterator();
    }

    private class MyIterator implements Iterator<T>{
        MyNode<T> cursor = head;
        @Override
        public boolean hasNext(){
            return cursor.next != null;
        }
        @Override
        public T next(){
            T nextItem = cursor.data;
            cursor = cursor.next;
            return nextItem;
        }
    }

    // Replaces the element at the specified index in the array with the specified element.
// Checks if the given index is zero if so assign the given parameter to Node reference head
// otherwise iterates through linked list by loop to index and replace the element.s
// Throws an IndexOutOfBoundsException if the index is out of range.
// Time complexity: O(n)
    @Override
    public void set(int index, T item) {
        checkIndex(index);
        if (index == 0) {
            head.data = (T) item;
        }
        else {
            MyNode<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.next;
            }
            currentNode.data = (T) item;
        }
    }

//     Adds the specified element to the end of the array list.
//      If the array list is empty, the new element becomes both the head and the tail.
//      Otherwise, the new element is appended to the end of the list, and the tail is updated.
//      Increase the size of the array list
//      Time complexity: O(1)
    public void add(T item){
        MyNode<T> newNode = new MyNode<>(item);
        if(head == null){
            head = newNode;
            tail = newNode;
        }
        else{
            tail.next = newNode;
            newNode.prev=tail;
            tail= newNode;

        }
        size++;
    }

//     Checks if the given index is within the bounds of the array list.
//      Throws an IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size of the array list.
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index not correct");
    }

//      Inserts the specified element at the specified position in the array list.
//      If the index is 0, insert the new element at the beginning of the list
//      If the index is not 0, iterate through the list to find the position to insert the new element.
//      Insert the new node between currentNode and currentNode.next
//      Throws an IndexOutOfBoundsException if the index is out of range.
//      Time complexity: O(n)
    @Override
    public void add(int index, T item){
        MyNode<T> newNode= new MyNode<>(item);
        checkIndex(index);
        if (index == 0){
            addFirst(item);
        } else {
            MyNode<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                if (i==index-1) {
                    MyNode<T> temp = currentNode.next;
                    currentNode.next = newNode;
                    newNode.prev = currentNode;
                    newNode.next = temp;
                    temp.prev = newNode;
                    size++;
                    return;
                }
                currentNode = currentNode.next;
            }
        }
    }

//     Inserts the specified element at the beginning of the array list.
//     If the array list is empty, set both the head and tail to the new node
//     If the current head is not null, set its previous pointer to the new node
//     Update the head to be the new node
//     Increase the size of the array list.
//     Time complexity: O(1)
    @Override
    public void addFirst(T item){
        MyNode<T> newNode = new MyNode<>(item);
        if(head == null){
            head = newNode;
            tail = newNode;
        }
        newNode.next=head;
        head.prev = newNode;
        head=newNode;
        size++;
    }

// Inserts the specified element at the end of the array list.
// Time complexity: O(n)
    @Override
    public void addLast(T item){
        add(item);
    }

    // Returns the element at the specified index in the array list.
// Throws an IndexOutOfBoundsException if the index is out of range.
// Time complexity: O(n)
    @Override
    public T get(int index){
        checkIndex(index);
        MyNode<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    // Returns the first element of the array list.
// Throws an IndexOutOfBoundsException if the array list is empty.
// Check if the array list is empty
// Return the data of the head node
// Time complexity: O(1)
    @Override
    public T getFirst(){
        checkIndex(0);
        return head.data;
    }

    // Returns the last element of the array list.
// Throws an IndexOutOfBoundsException if the array list is empty.
// Check if the array list is empty
// Return the data of the tail node
// Time complexity: O(1)
    @Override
    public T getLast(){
        checkIndex(0);
        return tail.data;
    }

    // Removes the element at the specified index in the array list.
    // Check if the index is valid
    // If the index is 0, remove the first element
    // Find the node before the node to be removed
    // Remove the node at the specified index by updating pointers
    // Decrease the size of the array list
// Throws an IndexOutOfBoundsException if the index is out of range.
// Time complexity: O(n)
    @Override
    public void remove(int index){
        checkIndex(index);
        if(index == 0){
            removeFirst();
            return;
        } else if (index == size - 1){
            removeLast();
            return;
        }
        MyNode<T> currentNode = head;
        for (int i = 0; i < index-1; i++) {
            currentNode = currentNode.next;
        }
        currentNode.next = currentNode.next.next;
        currentNode.next.prev =currentNode;
        size--;
    }

    public void removeFirst(){
        checkIndex(0);
        if (head == tail){
            head = null;
            tail = null;
            size = 0;
        }
        else {
            head = head.next;
            head.prev = null;
            size--;
        }
    }

    // Removes the first element of the array list.
    // Check if the array list is empty
    // Update the head to point to the next node
    // If the new head is not null, set its previous pointer to null
    // Decrease the size of the array list
// Throws an IndexOutOfBoundsException if the array list is empty.
// Time complexity: O(1)
    public void removeLast(){
        checkIndex(0);
        if (head == tail){
            head = null;
            tail = null;
            size = 0;
        }
        else {
            tail=tail.prev;
            tail.next = null;
            size--;
        }
    }

    // Sorts the elements of the array list in ascending order using the compareTo method.
    // Iterate through the list from the tail to the head
    // Start from the current node and compare it with all previous nodes
    // Swap the data of the current node and the current node1 if the data of current node1 is greater
// Time complexity: O(n^2)
    public void sort(){
        MyNode<T> currentNode = tail;
        while(currentNode != null){
            MyNode<T> currentNode1 = currentNode;
            while(currentNode1 != null){
                if(currentNode1.data.compareTo(currentNode.data) > 0 ) {
                    T temp = currentNode.data;
                    currentNode.data = currentNode1.data;
                    currentNode1.data = temp;
                }
                currentNode1=currentNode1.prev;
            }
            currentNode = currentNode.prev;
        }
    }

    // Returns the index of the first occurrence of the specified element in the array list, or -1 if the element is not found.
    // Iterate through the list
    // Compare the data of the current node with the specified object
    // If the data matches, return the current index
    // If the element is not found, return -1
// Time complexity: O(n)
    @Override
    public int indexOf(Object object) {
        MyNode<T> currentNode = head;
        int index = 0;
        while(currentNode != null){
            if(currentNode.data.compareTo( (T) object) == 0) {
                return index;
            }
            index++;
            currentNode = currentNode.next;
        }
        return -1;
    }

    // Returns the index of the last occurrence of the specified element in the array list, or -1 if the element is not found.
    // Iterate through the list from the tail to the head
    // Compare the data of the current node with the specified object
    // If the data matches, return the current index
    // If the element is not found, return -1
    // Time complexity: O(n)
    @Override
    public int lastIndexOf(Object object) {
        MyNode<T> currentNode = tail;
        int index = size -1;
        while(currentNode !=null){
            if(currentNode.data.compareTo( (T) object) == 0) {
                return index;
            }
            index--;
            currentNode = currentNode.prev;
        }
        return -1;
    }

    // Returns true if the specified element exists in the array list, false otherwise.
    // Check if the last index of the element is not -1, indicating whether it absents in the list
    // Time complexity: O(n)
    @Override
    public boolean exists(Object object) {
        return lastIndexOf(object) != -1;
    }

    // Returns an array containing all of the elements in the array list in the same order as they appear.
    // Create a new array with the same size as the linked list
    // Iterate through the list and copy elements to the new array
    // Return the new array
    // Time complexity: O(n)
    @Override
    public T[] toArray() {
        T[] newArr = (T[]) new Comparable[size];
        MyNode<T> currentNode = head;
        int index = 0;
        while(currentNode!=null){
            newArr[index]= currentNode.data;
            index++;
            currentNode = currentNode.next;
        }
        return newArr;
    }

    // Removes all elements from the array list.
    // Set both the head and tail to null, and reset the size to 0
    // Time complexity: O(1)
    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    // Returns the number of elements in the array list.
    // Simply return the size variable
    // Time complexity: O(1)
    @Override
    public int size() {
        return size;
    }

    // Prints all elements currently stored in the array list, separated by spaces, and ends with a newline character.
    // Iterate through the list and print each element followed by a space
    // Print the last element followed by a newline character
    // Time complexity: O(n)
    @Override
    public void print(){
        MyNode<T> currentNode = head;
        while(currentNode.next != null){
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.print(currentNode.data + "\n");
    }
}
