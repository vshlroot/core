package sort;

/**
 * Created by vishalss on 12/16/2015.
 */
public class SortingExample {
    public static void main(String[] args) {

//        Integer a[]={3,6,2,78,22,1,0,1};

/*
        Integer a[]={1, 12, 13, 24, 11,15 , 27, 28};
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


Integer a[]={1, 12, 13, 24, 11,15 , 27, 28};
            // Randomized Quick sort.
            System.out.println("Randomized Quick Sort Example Start");
            System.out.println("Array before sorting");
            Sort.printArray(a, 1, "");
            Sort.randomizedQuickSort(a,0,a.length);
            System.out.println("Array after sorting");
            Sort.printArray(a,1,"");
            System.out.println("Randomized Quick Sort Example End");

        // Merge Sort
        Integer a[]={0,9,29,11,11,1,9,-1};
        //Integer a[]={11,4};
        System.out.println("MergeSort Example Start");
        System.out.println("Array before sorting");
        Sort.printArray(a, 1, "");
        //Sort.merge(a, 0, 0, a.length);
        Sort.mergeSort(a, 0, a.length);
        System.out.println("Array after sorting");
        Sort.printArray(a,1,"");
        System.out.println("MergeSort Example End");

*/
        // Merge Two sorted arrays.
        Integer a[]={4,20,7777};
        Integer b[]={11};
        Sort.printArray(a,1,"");
        Sort.printArray(b,1,"");
        Integer result[]=Sort.merge(a, b);
        Sort.printArray(result,1,"");

    }
}
