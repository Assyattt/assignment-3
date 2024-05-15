import java.util.Iterator;
import MyList.*;

public class BST <K extends Comparable<K>, V> implements Iterable<Node<K, V>> {
    private Node<K, V> root;
    private int size;

    public Iterator<Node<K, V>> iterator() {
        return new MyIterator();
    }

    private class MyIterator implements Iterator<Node<K, V>> {
        Node<K, V> current = root;
        Stack<Node<K, V>> rights = new Stack<Node<K, V>>();

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Node<K, V> next() {
            Node<K, V> nextItem = current;
            if (current.right != null) rights.push(current.right);
            if(current.left == null && rights.empty()) {
                current = null;
                return nextItem;
            }
            if (current.left == null) current = rights.pop();
            else current = current.left;
            return nextItem;
        }
    }

        public BST() {
            root = null;
            size = 0;
        }

        public int size(){
            return size;
        }

        public void put(K key, V val) {
            root = put(root, key, val);
            size++;
        }

        private Node<K,V> put(Node<K, V> currentNode, K key, V val) {
            if (currentNode == null)
                return new Node<K, V>(key, val);
            else if (key.compareTo((K) currentNode.key) < 0)
                currentNode.left = put(currentNode.left, key, val);
            else if (key.compareTo((K) currentNode.key) >= 0)
                currentNode.right = put(currentNode.right, key, val);
            return currentNode;
        }

        public V get(K key) {
            return get(root, key);
        }

        private V get(Node currentNode, K key) {
            if (currentNode == null) return null;
            if (key.compareTo((K) currentNode.key) < 0)
                return (V) get(currentNode.left, key);
            else if (key.compareTo((K) currentNode.key) > 0)
                return (V) get(currentNode.right, key);
            else
                return (V) currentNode.val;
        }

        public void delete(K key) {
            root = delete(root, key);
            size--;
        }

        private Node<K, V> delete(Node<K, V> currentNode, K key) {
            if (currentNode == null) return currentNode;
            if (key.compareTo((K) currentNode.key) < 0)
                currentNode.left = delete(currentNode.left, key);
            else if (key.compareTo((K) currentNode.key) > 0)
                currentNode.right = delete(currentNode.right, key);
            else {
                if (currentNode.left == null && currentNode.right == null)
                    return null;
                else if (currentNode.left == null)
                    return  currentNode.right;
                else if (currentNode.right == null)
                    return currentNode.left;
                else {
                    currentNode.key = FindMaxNode(currentNode.left).key;
                    currentNode.val = FindMaxNode(currentNode.left).val;
                    currentNode.left = delete(currentNode.left, FindMaxNode(currentNode.left).key);
                }
            }
            return currentNode;
        }

        private Node<K,V> FindMaxNode(Node<K,V> currentNode) {
            if (currentNode.right == null) return currentNode;
            return FindMaxNode(currentNode.right);
        }

}
