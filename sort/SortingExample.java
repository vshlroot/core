package sort;

/**
 * Created by vishalss on 12/16/2015.
 */
public class SortingExample {
    public static void main(String[] args) {

//        Integer a[]={3,6,2,78,22,1,0,1};
        Integer a[]={1, 2, 3, 5, 8, 4, 7, 6};
/*
        // Bubble sort.
        System.out.println("Bubble Sort Example Start");
        System.out.println("Array before sorting");
        Sort.printArray(a,1,"");
        a=Sort.bubbleSort(a);
        System.out.println("Array after sorting");
        Sort.printArray(a,1,"");
        System.out.println("Bubble Sort Example End");


        // Quick sort.
        System.out.println("Quick Sort Example Start");
        System.out.println("Array before sorting");
        Sort.printArray(a,1,"");
        Sort.quickSort(a,0,a.length);
        System.out.println("Array after sorting");
        Sort.printArray(a,1,"");
        System.out.println("Quick Sort Example End");
*/
            // Randomized Quick sort.
            System.out.println("Randomized Quick Sort Example Start");
            System.out.println("Array before sorting");
            Sort.printArray(a, 1, "");
            Sort.randomizedQuickSort(a,0,a.length);
            System.out.println("Array after sorting");
            Sort.printArray(a,1,"");
            System.out.println("Randomized Quick Sort Example End");


    }
}
