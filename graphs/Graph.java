    package graphs;

    import java.util.LinkedList;
    import java.util.Queue;
    import java.util.Stack;

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

        public int getNumberOfVertices() {
            return numberOfVertices;
        }

        public int getNumberOfEdges() {
            return numberOfEdges;
        }

        public int getDegree(int index){
            if(index>numberOfVertices){
                System.out.println("Invalid index");
                return -1;
            }
            return degree[index];
        }

        public EdgeNode getEdgeList(int index){
            if(index>numberOfVertices){
                System.out.println("Invalid index");
                return null;
            }
            return edges[index];
        }

        public Object getVertex(int index){
            if(index>numberOfVertices){
                System.out.println("Invalid index");
                return -1;
            }
            return vertices[index];
        }

        // Inserts a new edge in the graph
        public void insertEdge(int x,int y, int weight, boolean directed){
            EdgeNode newEdge=new EdgeNode(y,weight,edges[x]);
            edges[x]=newEdge;
            degree[x]++;
            //System.out.println("NEW DEGREE= "+degree[x]);
            if(!directed){
                insertEdge(y,x,weight,true);        // passing true to break infinite loop.
            }
            else{
                numberOfEdges++;
            }
        }

        // Inserts a new vertex in the graph
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
            //System.out.println("numberOfVertices"+numberOfVertices);
        }

        public void initializeVertex(Object data){
            vertices[numberOfVertices]=data;
            edges[numberOfVertices]=null;
            degree[numberOfVertices]=0;
        }

        // Will create graph of MAXV=6 with one connected component.
        public static Graph createDummyGraph(){
            Graph g=new Graph(6);
            g.insertVertex(0);
            g.insertVertex(1);
            g.insertVertex(2);
            g.insertVertex(3);
            g.insertVertex(4);
            g.insertVertex(5);


            System.out.println("Inserting edges");
/*
            g.insertEdge(0,1,1,false);
            g.insertEdge(0,2,1,false);
            //g.insertEdge(1,2,1,false);
            g.insertEdge(1,3,1,false);
            g.insertEdge(2,4,1,false);
            g.insertEdge(2,5,1,false);
            g.insertEdge(3,5,1,false);

            */
            g.insertEdge(0,1,1,false);
            g.insertEdge(1,2,1,false);
            g.insertEdge(2,3,1,false);
            g.insertEdge(3,4,1,false);
            g.insertEdge(4,2,1,false);
            g.insertEdge(5,0,1,false);

            return g;
        }


        // Will create graph of MAXV=6 with 3 connected component.
        public static Graph createDummyGraphUnconnected(){
            Graph g=new Graph(11);
            g.insertVertex(0);
            g.insertVertex(1);
            g.insertVertex(2);
            g.insertVertex(3);
            g.insertVertex(4);
            g.insertVertex(5);
            g.insertVertex(6);
            g.insertVertex(7);
            g.insertVertex(8);
            g.insertVertex(9);
            g.insertVertex(10);


            System.out.println("Inserting edges");

            g.insertEdge(0,1,1,false);
            g.insertEdge(0,2,1,false);
            g.insertEdge(1,2,1,false);
            g.insertEdge(1,3,1,false);
            g.insertEdge(2,4,1,false);
            g.insertEdge(2,5,1,false);
            g.insertEdge(6,7,1,false);
            g.insertEdge(8,9,1,false);
            g.insertEdge(9,10,1,false);

            return g;
        }

        // Will create directed acyclic graph of MAXV=6 with one connected component.
        public static Graph createDummyDAG(){
            Graph g=new Graph(6);
            g.insertVertex(0);
            g.insertVertex(1);
            g.insertVertex(2);
            g.insertVertex(3);
            g.insertVertex(4);
            g.insertVertex(5);


            System.out.println("Inserting edges");
/*
            g.insertEdge(0,1,1,false);
            g.insertEdge(0,2,1,false);
            //g.insertEdge(1,2,1,false);
            g.insertEdge(1,3,1,false);
            g.insertEdge(2,4,1,false);
            g.insertEdge(2,5,1,false);
            g.insertEdge(3,5,1,false);


            g.insertEdge(0,1,1,true);
            g.insertEdge(0,5,1,true);
            g.insertEdge(5,2,1,true);
            g.insertEdge(2,3,1,true);
            g.insertEdge(5,4,1,true);
            */
            //g.insertEdge(4,0,1,true);

            g.insertEdge(1,0,1,true);
            g.insertEdge(0,2,1,true);
            g.insertEdge(2,1,1,true);
            g.insertEdge(0,3,1,true);
            g.insertEdge(5,3,1,true);
            g.insertEdge(3,5,1,true);
            g.insertEdge(3,4,1,true);
            return g;
        }

        public static Graph createDummyWightedGraph(){
            Graph g=new Graph(7);
            g.insertVertex(0);
            g.insertVertex(1);
            g.insertVertex(2);
            g.insertVertex(3);
            g.insertVertex(4);
            g.insertVertex(5);
            g.insertVertex(6);


            System.out.println("Inserting edges");
/*
            g.insertEdge(0,1,1,false);
            g.insertEdge(0,2,1,false);
            //g.insertEdge(1,2,1,false);
            g.insertEdge(1,3,1,false);
            g.insertEdge(2,4,1,false);
            g.insertEdge(2,5,1,false);
            g.insertEdge(3,5,1,false);


            g.insertEdge(0,1,1,true);
            g.insertEdge(0,5,1,true);
            g.insertEdge(5,2,1,true);
            g.insertEdge(2,3,1,true);
            g.insertEdge(5,4,1,true);
            */
            //g.insertEdge(4,0,1,true);

            g.insertEdge(0,1,1,true);
            g.insertEdge(0,2,3,true);
            g.insertEdge(0,4,100,true);
            g.insertEdge(2,4,10,true);
            g.insertEdge(3,5,2,true);
            g.insertEdge(4,5,4,true);
            g.insertEdge(5,6,5,true);
            return g;
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

        // Runs BFS on graph, uses Queue
        // Edit processVertexEarly, processVertexLate and processEdge to modify the processing order
        public void bfs(int root){
            if(root<0){
                System.out.println("Invalid root");
                return;
            }
            if(numberOfVertices<=0){
                return;
            }
            boolean visited[]=new boolean[numberOfVertices];
            boolean processed[]=new boolean[numberOfVertices];

            for (int i = 0; i < visited.length; i++) {
                visited[i]=false;
                processed[i]=false;
            }
            Queue<Integer> q=new LinkedList<>();
            q.add(root);
            visited[root]=true;
            int currentIndex;
            EdgeNode edge;
            while(!q.isEmpty()){
                currentIndex=q.remove();
                processVertexEarly(currentIndex);
                processed[currentIndex]=true;
                edge=edges[currentIndex];
                while(edge!=null){
                    if(!visited[edge.y]) {
                        visited[edge.y]=true;
                        processEdge(currentIndex,edge.y);
                        q.add(edge.y);
                    }
                    edge = edge.next;
                }
                processVertexLate(currentIndex);
            }
        }

        public void processVertexEarly(int index){
            System.out.println(vertices[index]);
        }

        public void processVertexLate(int index){
            //System.out.println(vertices[index]);
        }

        public void processEdge(int x,int y){
            //System.out.println(vertices[index]);
        }

        // Runs DFS on graph, uses recursion
        // Edit processVertexEarly, processVertexLate and processEdge to modify the processing order
        // For now I don't think iterative way of running DFS is there...We'll see...
        public void dfs(int root,boolean[] visited){
            if(root<0){
                System.out.println("Invalid root");
                return;
            }
            if(numberOfVertices<=0){
                return;
            }
            visited[root]=true;
            EdgeNode edge;
            processVertexEarly(root);
            edge=edges[root];
            while(edge!=null){
                if(!visited[edge.y]) {
                    visited[edge.y]=true;
                    processEdge(root,edge.y);
                    dfs(edge.y,visited);
                }
                edge = edge.next;
            }
            processVertexLate(root);
        }

        // Reverses the edges of the graph
        // returns a new graph, keeping original graph intact.
        public static Graph reverseEdges(Graph g){
            if(g==null || g.getNumberOfVertices()<2)
                return g;
            Graph g1=new Graph(g.getNumberOfVertices());

            // Adding Vertices
            for (int i = 0; i < g.getNumberOfVertices(); i++) {
                g1.insertVertex(g.getVertex(i));
            }

            // Adding edges
            EdgeNode edge;
            for (int i = 0; i < g.getNumberOfVertices(); i++) {
                edge=g.getEdgeList(i);
                // As we are reversing edges that means the graph is directed
                while (edge!=null){
                    g1.insertEdge(edge.y,i,edge.weight,true);
                    edge=edge.next;
                }
            }
            return g1;
        }

        public static void main(String[] args) {
            Graph g=Graph.createDummyGraph();

            g.printGraph();

            System.out.println("DFS:");
            boolean visited[]=new boolean[g.getNumberOfVertices()];
            //g.dfs(0,visited);

//            System.out.println("BFS:");
  //          g.bfs(4);

            System.out.println("Original DAG");
            Graph g1=Graph.createDummyDAG();
            g1.printGraph();
            System.out.println("Reversed DAG");
            g1=g1.reverseEdges(g1);
                    g1.printGraph();
        }
    }
