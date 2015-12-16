package sort;

/**
 * Created by vishalss on 12/16/2015.
 */
public class SortingExample {
    public static void main(String[] args) {
        // Bubble sort.
        System.out.println("Bubble Sort Example Start");
        Integer a[]={3,6,2,78,22,1,0,1};

        System.out.println("Array before sorting");
        Sort.printArray(a);
        a=Sort.bubbleSort(a);
        System.out.println("Array after sorting");
        Sort.printArray(a);
        System.out.println("Bubble Sort Example End");
    }
}
