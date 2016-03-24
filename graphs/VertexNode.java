package graphs;

/**
 * Created by vishalss on 3/23/2016.
 */
public class VertexNode implements Comparable<VertexNode>{
    private int vertex;
    private int distance;
    private Object data;

    VertexNode(){}

    public VertexNode(int vertex, int distance){
        this(vertex, distance, null);
    }

    public VertexNode(int vertex, int distance, Object data){
        this.vertex=vertex;
        this.distance=distance;
        this.data=data;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int compareTo(VertexNode v2){
        if(this.distance<v2.getDistance()) {
            return -1;
        }
        else if(this.distance>v2.getDistance()){
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

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public String toString(){
        return data.toString();
    }
}
