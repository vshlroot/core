package graphs;

/**
 * Created by vshlroot on 20-08-2016.
 */
public class EdgePair implements Comparable<EdgePair>{
    private int i;
    private int j;
    private int weight;

    public EdgePair(int i,int j, int weight){
        this.i=i;
        this.j=j;
        this.weight=weight;
    }

    public int compareTo(EdgePair e2){

        if(this.weight<e2.weight){
            return -1;
        }
        else if(this.weight>e2.weight){
            return 1;
        }
        else{
            return 0;
        }
    }
}
