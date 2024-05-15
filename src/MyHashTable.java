import java.util.NoSuchElementException;

public class MyHashTable <K,V> {
    private class HashNode<K, V>{
        private K key;
        private V value;
        private HashNode<K, V> next;
        public HashNode(K key, V value){
            this.key = key;
            this.value = value;
        }
        @Override
        public String toString() {
            return "{" + key + " " + value + "}";
        }
    }

    private HashNode<K, V>[] chainArray;
    private int M = 11;
    private int size;

    public MyHashTable(){
        chainArray = new HashNode[M];
    }
    public MyHashTable(int M){
        this.M = M;
        chainArray = new HashNode[M];
    }

    private int hash(K key){
        return key.hashCode() % M;
    }

    public void put (K key, V value){
        HashNode<K, V> newHashNode = new HashNode<>(key, value);
        if (chainArray[hash(key)] == null) chainArray[hash(key)] = newHashNode;
        else {
            HashNode<K, V> currenthashNode = chainArray[hash(key)];
            if (currenthashNode == null) {
                currenthashNode = newHashNode;
                return;
            }
            while (currenthashNode.next != null) {
                currenthashNode = currenthashNode.next;
            }
            currenthashNode.next = newHashNode;
        }
    }

    public V get (K key){
        HashNode<K, V> currenthashNode = chainArray[hash(key)];
        while(currenthashNode != null){
            if (currenthashNode.key.hashCode() == key.hashCode()) return currenthashNode.value;
            currenthashNode = currenthashNode.next;
        }
        throw new NoSuchElementException(key + " is not found");
    }

    public V remove(K key){
        HashNode<K, V> currenthashNode = chainArray[hash(key)];
        if (chainArray[hash(key)].key == key) return chainArray[hash(key)].value;
        while(currenthashNode.next != null){
            if (currenthashNode.next.key == key){
                V removedItem = currenthashNode.next.value;
                currenthashNode.next = currenthashNode.next.next;
                return removedItem;
            }
            currenthashNode = currenthashNode.next;
        }
        throw new NoSuchElementException(key + " is not found");
    }

    public boolean contains (V value){
        for (int i = 0; i < M; i++){
            HashNode<K, V> currenthashNode = chainArray[i];
            while(currenthashNode != null){
                if (currenthashNode.value == value) return true;
                currenthashNode = currenthashNode.next;
            }
        }
        return false;
    }

    public K getKey (V value) {
        for (int i = 0; i < M; i++){
            HashNode<K, V> currenthashNode = chainArray[i];
            while(currenthashNode != null){
                if (currenthashNode.value == value) return currenthashNode.key;
                currenthashNode = currenthashNode.next;
            }
        }
        throw new NoSuchElementException(value + " is not found");
    }

}
