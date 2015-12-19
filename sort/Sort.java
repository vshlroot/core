package sort;

import java.lang.reflect.Array;
import java.util.Arrays;

import java.util.Random;

/**
 * Created by vishalss on 12/15/2015.
 */
public class Sort {

//===============================================================================
// Bubble sort in place
//===============================================================================
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

//===============================================================================
// Quick sort in place
//===============================================================================
    public static <T extends Comparable<T>> void quickSort(T[] array, int start, int end){

        //System.out.println("Params received: "+"Start= "+start+"End= "+ end);
        //printArray(array,1,"");

        if(start>=end){
            return;
        }
        //int pivot=start;
        T temp;
        int pointer=start+1;
        int left=start; // Location of elements <= pivot element inclusive.
        int right=start; // right+1 can point to next > element.
        while(pointer<end){
           // System.out.println("    Comparing= "+array[start] +"and "+array[pointer]);
            if(array[start].compareTo(array[pointer])>0){
                left++;
                //System.out.println("    Swaping = "+array[left] +"and "+array[pointer]);
                //swap array[left] with the pointer element
                temp= array[pointer];
                array[pointer]=array[left];
                array[left]=temp;
                right++;
            }
            else if(array[start].compareTo(array[pointer])<=0){
                right++;
            }
            //printArray(array,1,"    ");
            pointer++;
        }
        // Swap the pivot with the a[left] ie last in the <= number
        temp= array[start];
        array[start]=array[left];
        array[left]=temp;
        quickSort(array,start,left);
        quickSort(array,left+1,end);
    }

//===============================================================================
// Randomized Quick Sort in place
//===============================================================================

    // Uses Rand class to generate a random number between start and end.
    // Rand is used in order to pick a pivot element, one a pivot is picked rest everything is same as normal quickSort.
    public static <T extends Comparable<T>> void randomizedQuickSort(T array[], int start, int end){
        if(start>=end){
            return;
        }
        //System.out.println("Params received: "+"Start= "+start+"End= "+ end);
        // Picking a number at random between start and end
        int randomNumber=(new Random()).nextInt(end-start)+start;
        // Swap the random number with the start element.
        //System.out.println("Random number generated= "+randomNumber);
        T temp;
        temp= array[randomNumber];
        array[randomNumber]=array[start];
        array[start]=temp;

        // Normal quickSort continues
        int pointer=start+1;
        int left=start; // Location of elements <= pivot element inclusive.
        int right=start; // right+1 can point to next > element.
        while(pointer<end){
            // System.out.println("    Comparing= "+array[start] +"and "+array[pointer]);
            if(array[start].compareTo(array[pointer])>0){
                left++;
                //System.out.println("    Swaping = "+array[left] +"and "+array[pointer]);
                //swap array[left] with the pointer element
                temp= array[pointer];
                array[pointer]=array[left];
                array[left]=temp;
                right++;
            }
            else if(array[start].compareTo(array[pointer])<=0){
                right++;
            }
            //printArray(array,1,"    ");
            pointer++;
        }
        // Swap the pivot with the a[left] ie last in the <= number
        temp= array[start];
        array[start]=array[left];
        array[left]=temp;
        randomizedQuickSort(array, start, left);
        randomizedQuickSort(array, left+1,end);
    }

//===============================================================================
// Merge sort
//===============================================================================

    public static <T extends Comparable<T>> void mergeSort(T[] array, int start, int end){
        if(start>=end)
            return;
        if(end-start==1){
            return;
        }
        int mid=(start+end)/2;
        mid--;
        mergeSort(array, start, mid+1);
        mergeSort(array, mid+1 , end);
        merge(array,start,mid,end);
    }

//===============================================================================
// Implements merge part of the mergesort
// Merges two continuous parts of the same array.
// Returns the sorted version of the part to merge.
//===============================================================================
    public static <T extends Comparable<T>> void merge(T[] array, int start, int mid, int end){
        // start is included; end is excluded
        // mid is included in the first part
        // That means mid+1 is included in the second part.
        if(start>=end || mid<start || mid>end){
            System.out.println("ERROR: Out of Bounds: returning");
            return;
        }
        // Take temp array that will store result and at the end will copy it back to the original array.
        T[] result=(T[]) Array.newInstance(array[0].getClass(),end-start);
        int i=start;
        int j=mid+1;
        int k=0;
        while(i<mid+1 && j<end){
            if(array[i].compareTo(array[j])<=0){
                result[k]=array[i];
                i++;
            }
            else if(array[i].compareTo(array[j])>0){
                result[k]=array[j];
                j++;
            }
            k++;
        }
        // while broke due to i. Copy all contents of j into result.
        if(i>=mid+1 && j<end){
            while(j<end){
                result[k]=array[j];
                j++;
                k++;
            }
        }
        else if(j==end && i<mid+1){    // while broke due to j. Copy all contents of i into result.
            while(i<mid+1){
                result[k]=array[i];
                i++;
                k++;
            }
        }
        // Copy result to the original array
        for(i=start,k=0;i<end;i++,k++){
            array[i]=result[k];
        }
    }

//===============================================================================
// Merges two different arrays and returns a single sorted array.
// First one will be max and second one will be min and so on.
//===============================================================================
    public static <T extends Comparable<T>> T[] merge(T[] array1, T[] array2){
        T[] array3=(T[])Array.newInstance(array1[0].getClass(),array1.length+array2.length);
        int i=0;
        for(i=0;i<array1.length;i++){
            array3[i]=array1[i];
        }
        for(int j=0;j<array2.length;j++,i++){
            array3[i]=array2[j];
        }
        Sort.printArray(array3,1,"");
        merge(array3,0,array1.length-1,array3.length);
        return array3;
    }

//===============================================================================
// Wiggle Sort, in place
//===============================================================================

    public static <T extends Comparable<T>> void wiggleSort(T[] array){
        T temp;
        for(int i=0;i<array.length-1;i++){
            if(i%2==0){ //even ones will be max
                if(array[i].compareTo(array[i+1])<0){
                    temp=array[i];
                    array[i]=array[i+1];
                    array[i+1]=temp;
                }
            }
            else{
                if(array[i].compareTo(array[i+1])>0){
                    temp=array[i];
                    array[i]=array[i+1];
                    array[i+1]=temp;
                }
            }
        }
    }

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
}
