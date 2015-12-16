package sort;

import java.util.Random;

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

    // quickSort in place
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

    // randomizedQuickSort
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


    // Function to print the values of the array.
    // In case of class objects, the class itself should override the toString method of Object class.
    // orientation: To print data in horizontally or vertically. 0- Vertical 1- Horizontal
    // append: Add a Sting in front of each line while printing.
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
}
