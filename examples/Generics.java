package examples;

/**
 * Created by vishalss on 12/14/2015.
 */
public class Generics<T> {
    /*General way to use generics is

    class name<T1, T2, ..., Tn> { ... }
    where Tn are the types passed to the generic class.
    Here in our case T is being passed as the first type to the class.
*/

    // We can use T to declare variables and return types of methods.
    // It can be used just like any other data type like int or Integer or any class variable.

    private T var;
    public Generics() {

    }
    public Generics(T var) {
        this.var = var;
    }


    //Notice how T is being used as a return type also, just like any other variable.
    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    public String toString(){
        // Usually returns
        //getClass().getName() + '@' + Integer.toHexString(hashCode())

        // We are returning the toString() method for var.
        // If var is some other class object then now it is it's responsibility to return correct value.
        //
        // var can't be some primitive type hence no need to worry about the primitive types.
        return var.toString();
    }


    // Using Generics
    public static void main(String[] args) {
        Generics<Integer> obj1=new Generics<Integer>();
        obj1.setVar(1); // autoboxing in case you pass primitive types.
        // Notice no type casting is required
        int i=obj1.getVar();
        System.out.println(i);
        System.out.println(obj1.getVar());

        Generics<String> obj2=new Generics<String>();
        obj2.setVar("This is a String");
        System.out.println(obj2.getVar());

        // Following gives error.
        // Generics<int> obj3=new Generics<Strinint>();
    }
}
