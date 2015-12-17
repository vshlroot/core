package search;

/**
 * Created by vishalss on 12/16/2015.
 */
public class Search {

    // returns +ve value if found, returns -ve value of position to add in if not found.

    /*
     Integer a[]={-1, 0, 4, 5, 8, 9, 17, 26};
     Search.binarySearch(a,12,0,a.length);

     will return -6

     */
    public static <T extends Comparable<T>> int binarySearch(T[] array, T element,int start, int end){
        if(start>=end){
            return start;
        }
        int mid=(start+end)/2;
        System.out.println("    Start= "+start+"End= "+end+"mid= "+mid);
        if(array[mid].compareTo(element)==0){
            return mid;
        }
        else if(array[mid].compareTo(element)<0){
            if(mid+1<array.length && array[mid+1].compareTo(element)>0){
                return -1*(mid+1);
            }
            return binarySearch(array,element,mid+1,end);
        }
        else{
            if(mid-1>=0 && array[mid-1].compareTo(element)<0){
                return -1*mid;
            }
            return binarySearch(array,element,start,mid-1);

        }
    }
}
