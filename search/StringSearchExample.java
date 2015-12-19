package search;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by vishalss on 12/19/2015.
 */
public class StringSearchExample {
    public static void main(String[] args) {
 /*       String text="this this a text thisis is ";
        String pattern="this";
*/
        String text="aaaaaa";
        String pattern="aaaa";
        ArrayList<Integer> result=StringSearch.rabinKarp(text, pattern, 156, 101);
        if(result!=null){
            System.out.println("Positions are: ");
            Iterator it=result.iterator();
            while(it.hasNext()){
                System.out.println(it.next());
            }
        }
        else{
            System.out.println("illegal params");
        }
    }
}
