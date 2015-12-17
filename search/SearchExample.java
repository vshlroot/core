package search;

import sort.Sort;

/**
 * Created by vishalss on 12/17/2015.
 */
public class SearchExample {
    public static void main(String[] args) {
        Integer a[]={-1, 0, 4, 5, 8, 9, 17, 26};
        Sort.printArray(a,1,"");
        int result=Search.binarySearch(a,12,0,a.length);
        System.out.printf("Position= "+result);
    }
}
