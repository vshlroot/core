package utils;

/**
 * Created by vishalss on 12/28/2015.
 */
public class Utils {


//===============================================================================
// Function to print the values of the array.
// In case of class objects, the class itself should override the toString method of Object class.
// orientation: To print data in horizontally or vertically. 0- Vertical 1- Horizontal
// append: Add a Sting in front of each line while printing.\
//===============================================================================
    public static <T extends Comparable<T>>  void printArray(T[] array, int orientation, String append){
        if(orientation==0) {
            for (int i = 0; i < array.length; i++) {
                System.out.println(append+array[i]);
            }
        }
        else{
            System.out.print(append);;
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]+", ");
            }
            System.out.println("");
        }
    }

//===============================================================================
// Function to print a range of the array.
// ===============================================================================
    public static <T extends Comparable<T>>  void printArray(T[] array, int orientation, String append, int start, int end){
        if(orientation==0) {
            for (int i = start; i < end; i++) {
                System.out.println(append+array[i]);
            }
        }
        else{
            System.out.print(append);;
            for (int i = start; i < end; i++) {
                System.out.print(array[i]+", ");
            }
            System.out.println("");
        }
    }

    public static void printIntArray(int[] array, int orientation, String append){
        if(orientation==0) {
            for (int i = 0; i < array.length; i++) {
                System.out.println(append+array[i]);
            }
        }
        else{
            System.out.print(append);;
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]+", ");
            }
            System.out.println("");
        }
    }

    public static void printLongArray(long[] array, int orientation, String append){
        if(orientation==0) {
            for (int i = 0; i < array.length; i++) {
                System.out.println(append+array[i]);
            }
        }
        else{
            System.out.print(append);;
            for (int i = 0; i < array.length; i++) {
                System.out.print(array[i]+", ");
            }
            System.out.println("");
        }
    }
}
