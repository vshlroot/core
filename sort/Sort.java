package sort;

/**
 * Created by vishalss on 12/15/2015.
 */
public class Sort {

    // Bubble sort
    public static <T extends Comparable<T>> T[] bubbleSort(T[] array){
        int length=array.length;
        T temp;
        for(int i=0;i<length;i++){
            for(int j=0;j<length-i-1;j++){
                if(array[j].compareTo(array[j+1])>0){
                    temp=array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
        }
        return array;
    }

    public static <T extends Comparable<T>> void printArray(T[] array){
        for(int i=0;i<array.length;i++){
            System.out.println(array[i]);
        }
    }
}
