public class MyTestingClass {
    private int age;
    private String name;
    public MyTestingClass(int age, String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public int hashCode(){
        int result = 1;
        for (int i = 0; i < name.length(); i++) result = age + result*(name.charAt(i)%age);
        return result;
    }
}
