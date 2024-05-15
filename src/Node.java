public class Node<K, V> implements Comparable<Node<K, V>> {
    protected K key;
    protected V val;
    Node<K,V> left, right;

    public Node(K key, V val) {
        this.key = key;
        this.val = val;
    }

    public K getKey(){
        return key;
    }

    public V getValue(){
        return val;
    }

    // unused method
    // might be modified

    @Override
    public int compareTo(Node<K, V> o) {
        return 0;
    }
}