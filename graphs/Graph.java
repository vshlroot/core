package graphs;

/**
 * Created by vishalss on 3/12/2016.
 */
public class Graph {

    private final int MAXV;         // Max number of vertices allowed
    private int numberOfVertices;   // Current number of vertices
    private int numberOfEdges;      // Current number of edges
    private Object vertices[];      // Holds the node data
    private EdgeNode edges[];       // Holds the pointers to the edges of vertex i
    private int degree[];           // degree of vertex i;

    Graph(){
        this(0);
    }

    Graph(int MAXV){
        this.MAXV=MAXV;
        vertices=new Object[MAXV];
        edges=new EdgeNode[MAXV];
        degree=new int[MAXV];
        initializeGraph();
    }

    public void initializeGraph(){
        numberOfEdges=0;
        numberOfVertices=0;
        for (int i = 0; i < MAXV; i++) {
            degree[i]=0;
            edges[i]=null;
            vertices[i]=null;
        }
    }

    public void insertEdge(int x,int y, int weight, boolean directed){
        EdgeNode newEdge=new EdgeNode(y,weight,edges[x]);
        edges[x]=newEdge;
        degree[x]++;
        System.out.println("NEW DEGREE= "+degree[x]);
        if(!directed){
            insertEdge(y,x,weight,true);        // passing true to break infinite loop.
        }
        else{
            numberOfEdges++;
        }
    }

    public void insertVertex(Object data){
        if(data==null){
            System.out.println("Invalid input");
            return;
        }
        if(numberOfVertices>=MAXV){
            System.out.println("MAXV reached");
            return;
        }
        initializeVertex(data);
        numberOfVertices++;
        System.out.println("numberOfVertices"+numberOfVertices);
    }

    public void initializeVertex(Object data){
        vertices[numberOfVertices]=data;
        edges[numberOfVertices]=null;
        degree[numberOfVertices]=0;
    }

    public void printGraph(){
        EdgeNode nextEdge;
        for (int i = 0; i < numberOfVertices; i++) {
            System.out.print(degree[i]+"    "+vertices[i]+"-->");
            nextEdge=edges[i];
            while(nextEdge!=null){
                System.out.print(vertices[nextEdge.y]+"-->");
                nextEdge=nextEdge.next;
            }
            System.out.print("X");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Graph g=new Graph(6);
        g.insertVertex(0);
        g.insertVertex(1);
        g.insertVertex(2);
        g.insertVertex(3);
        g.insertVertex(4);
        g.insertVertex(5);


        System.out.println("Inserting edges");

        g.insertEdge(0,1,1,false);
        g.insertEdge(0,2,1,false);
        g.insertEdge(1,2,1,false);
        g.insertEdge(1,3,1,false);
        g.insertEdge(2,4,1,false);
        g.insertEdge(2,5,1,false);
        g.insertEdge(3,5,1,false);

        g.printGraph();


    }
}
