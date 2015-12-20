package programs;

/**
 * Created by vishalss on 12/20/2015.
 */
// Given a 2D array of 0s and 1s.
// Contiguous 1s form an organisms. left, right and top, bottom form one organism.
// Find out the no. of organisms in the array.
public class Organisms {

    public static int paint(int[][] input,int i, int j, int setAs){
        if(input[i][j]!=1){
            return 0;
        }
        if(input[i][j]==1){
            input[i][j]=setAs;
        }
        if(i>0 && input[i-1][j]==1){
            paint(input,i-1,j,setAs);
        }
        if(i<input.length-1 && input[i+1][j]==1){
            paint(input,i+1,j,setAs);
        }
        if(j>0 && input[i][j-1]==1){
            paint(input,i,j-1,setAs);
        }
        if(j<input[j].length-1 && input[i][j+1]==1){
            paint(input,i,j+1,setAs);
        }
        return 1;
    }

    public static int noOfOrganisms(int[][] input){
        int counter=2;
        System.out.println();
        for(int i=0;i<input.length;i++) {
            for (int j = 0; j < input[0].length; j++) {
                if(input[i][j]==1){
                    paint(input,i,j,counter);
                    counter++;
                }

            }
        }
        return counter-1;

    }

    public static void main(String[] args) {
        //int input[3][4]=new int[12];
        int input[][]={{0,1,1,1},{1,0,1,0},{0,0,1,0},{1,1,0,1}};
        for(int i=0;i<input.length;i++){
            for(int j=0;j<input[0].length;j++){
                System.out.print(input[i][j] + ",");
            }
            System.out.println("");
        }

        int result=noOfOrganisms(input);
        System.out.printf("result= "+(result-1));

    }
}
