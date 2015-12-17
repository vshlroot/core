package sort;

/**
 * Created by vishalss on 12/16/2015.
 */
public class OrderStatistics {
    // Implements order statistics.

    // returns first element in case of illegal case.
    // Returns kth smallest element in the array.
    public static <T extends Comparable<T>> T orderStatistics(T array[], int k, int start, int end){
        //Sort.printArray(array,1,"   ");
        //System.out.println("k- "+k +"start= "+start+"end= "+end);
        if(array.length<=1){
            return array[0];
        }
        if(k>end || k<start){
            return array[0];
        }
        if(start>=end){
            return array[0];
        }

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
        //System.out.println("    partitioning Left= "+left);
        if(k-1==left){
            return array[left];
        }
        else if(k-1>left){
            return orderStatistics(array,k, left + 1, end);
        }
        else{
            //return orderStatistics(array,k-(end-left), start,left);
            return orderStatistics(array,k, start,left);
        }
    }
}
