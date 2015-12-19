package search;

import java.util.ArrayList;

/**
 * Created by vishalss on 12/19/2015.
 */
public class StringSearch {

//===============================================================================
// Rabin-Karp algorithm for string matching.
// Returns an array of integers containing all positions of the indices.
// base is the base of number system in which characters are given. eg 256 for characters usually.
// mod is a prime number to take the modulo with.
// returns null in case of invalid params
// ===============================================================================
    public static ArrayList<Integer> rabinKarp(String text,String pattern,int base, int mod){
        if(pattern.length()==0 || text.length()==0){
            return null;
        }
        int patternHash=0;
        int textHash=0;
        int rightValue=1;
        int i=0;
        ArrayList<Integer> result=new ArrayList<>();

        // finding the hash of the pattern and the first block of the text.
        for(i=0;i<pattern.length();i++){
            patternHash=(patternHash*base+pattern.charAt(i))%mod;
            textHash=(textHash*base+text.charAt(i))%mod;
        }

        //finding out static rightValue that will be used in finding rolling hash while removing left most character.
        for(i=0;i<pattern.length()-1;i++){
            rightValue=(rightValue*base)%mod;
        }

        // matching the text with the pattern
        int j=0;
        for(i=0;i<text.length()-pattern.length()+1;i++){
            //System.out.println("patternHash= "+patternHash);
            //System.out.println("textHash= "+textHash);
            if(patternHash==textHash){
                // match character by character
                for(j=0;j<pattern.length();j++){
                    if(pattern.charAt(j)!=text.charAt(i+j)){
                        break;
                    }
                }
                if(j==pattern.length()){
                    // adding result to the arraylist
                    result.add(i);
                }
            }
            // finding new hash by adding next character from right and removing the leftmost character.
            if(i+pattern.length()<text.length()){
                //System.out.println("    Removing= "+text.charAt(i)+" Adding "+text.charAt(i+pattern.length()));
                textHash=(((textHash-text.charAt(i)*rightValue)*base)%mod +text.charAt(i+pattern.length()))%mod;
                if(textHash<0){
                    textHash+=mod;
                }
            }

        }
        return result;
    }
}
