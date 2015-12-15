package examples;

/**
 * Created by vishalss on 12/15/2015.
 */
public class GenericsPairs<K,V> {

    // Here we will be using a key, value pair kind of generics.
    private K key;
    private V value;

    public GenericsPairs(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {

        this.value = value;
    }

    // Using Generics with pairs
    public static void main(String[] args) {
        // Following is known as diamond notation.
        GenericsPairs<Integer,String> obj1=new GenericsPairs<>(1,"A");
        // Following will give compile time error:
        // Generics<Integer,String> obj1=new Generics<,>();
        // Following also works fine
        // Generics<Integer,String> obj1=new Generics<Integer,String>();

        // Notice no type casting is required
        int i=obj1.getKey();
        String str=obj1.getValue();
        System.out.println("Key= "+i);
        System.out.println("Value= "+str);

        // We can use other Generic class objects also to make new object of GenericsPairs.

        Generics<String> genericString=new Generics<>("genericString");
        GenericsPairs<Integer,Generics<String>> obj2=new GenericsPairs<>(3,genericString);

        System.out.println("Key= "+obj2.getKey());
        System.out.println("Value= "+obj2.getValue());

    }
}
