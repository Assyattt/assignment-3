public class Main {
    public static void main(String[] args) {
        MyHashTableTest();
//        BSTTest();
    }



    private static void MyHashTableTest(){
        // Tests for put()
        MyHashTable<MyTestingClass, String> rota =  new MyHashTable<>();
        rota.put(new MyTestingClass(18, "Abay"), "Abay");
        rota.put(new MyTestingClass(18, "Assyat"), "Assyat");
        rota.put(new MyTestingClass(18, "Arsen"), "Arsen");
        rota.put(new MyTestingClass(17, "Maksat"), "Maksat");
        rota.put(new MyTestingClass(17, "Erlan"), "Erlan");
        rota.put(new MyTestingClass(123, "Renat"), "Renat");
        rota.put(new MyTestingClass(9, "Terminator"), "Terminator");
        rota.put(new MyTestingClass(8, "Superman"), "Superman");
        rota.put(new MyTestingClass(11, "Freddy Fazbear"), "Freddy Fazbear");

        // Tests for get()
        MyTestingClass TestKey = new MyTestingClass(17, "Erlan");
        System.out.println(rota.get(TestKey));

        TestKey = new MyTestingClass(9, "Terminator");
        System.out.println(rota.get(TestKey));

        TestKey = new MyTestingClass(123, "Renat");
        System.out.println(rota.get(TestKey));

        // Tests for contains()
        System.out.println(rota.contains("Renat"));
        System.out.println(rota.contains("Assyat"));
        System.out.println(rota.contains("Kostya Tzyu"));

        // Test for getKey()
        System.out.println(rota.getKey("Maksat"));
        System.out.println(rota.getKey("Abay"));
//        System.out.println(rota.getKey("Kostya Tzyu"));

        // Tests for remove()

        rota.remove(TestKey);
        System.out.println(rota.get(TestKey));
    }



    public static void BSTTest(){
        BST<Integer, Character> tree = new BST<>();
        // Tests for put()
        tree.put(4,'a');
        tree.put(1,'b');
        tree.put(3,'c');
        tree.put(2,'d');
        tree.put(6,'f');
        tree.put(10,'g');
        tree.put(0,'e');

        for(var elem: tree){
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }
        System.out.println();

        // Tests for get()
        System.out.println("The value under the key 10 is " + tree.get(10));
        System.out.println("The value under the key 6 is " + tree.get(6));
        System.out.println("The value under the key 12 is " + tree.get(12));
        System.out.println();

        // Tests for delete()
        tree.delete(4);
        for(var elem: tree){
            System.out.println("key is " + elem.getKey() + " and value is " + elem.getValue());
        }

    }
}