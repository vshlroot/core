package graphs;

/**
 * Created by vishalss on 3/12/2016.
 *//*
Class implements am edgenode of a graph
Data stored will be of type Object.

For an edge (x,y) it will store y only, then this edgenode containing y will be attached to the edgelist(adjacency list) of x.
There is no separate class for the Graph node, EdgeNode itself will hold the data.
 */
public class EdgeNode {

    EdgeNode next;  // Next node in the adjacency list of x
    int y;          // Pointer of this node. In adjacency list this will point to the index of the Vertex
    int weight;     // Weight of this edge

    EdgeNode(){
    }

    EdgeNode(int y){
        this(y,0,null);
    }

    EdgeNode(int y,int weight,EdgeNode next){
        this.y=y;
        this.weight=weight;
        this.next=next;
    }
}
