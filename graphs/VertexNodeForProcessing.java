package graphs;

/**
 * Created by vishalss on 3/23/2016.
 */
public class VertexNodeForProcessing implements Comparable<VertexNodeForProcessing>{
    private int vertex;
    private int weight;

    VertexNodeForProcessing(){}

    public VertexNodeForProcessing(int vertex,int weight){
        this.vertex=vertex;
        this.weight=weight;
    }

    public int compareTo(VertexNodeForProcessing v2){
        if(weight<v2.getWeight()){
            return -1;
        }
        else if(weight>v2.getWeight()){
            return 1;
        }
        else{
            return 0;
        }
    }

    public int getVertex() {
        return vertex;
    }

    public void setVertex(int vertex) {
        this.vertex = vertex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
