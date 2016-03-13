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

        // Will create graph of MAXV=6
        public static Graph createDummyGraph(){
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

        // Runs BFS on graph, uses Stack
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

        // Runs DFS on graph, uses Queue
        // Edit processVertexEarly, processVertexLate and processEdge to modify the processing order
        public void dfs(int root){
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
            Stack<Integer> s=new Stack<>();
            s.push(root);
            visited[root]=true;
            int currentIndex;
            EdgeNode edge;
            while(!s.isEmpty()){
                currentIndex=s.pop();
                processVertexEarly(currentIndex);
                processed[currentIndex]=true;
                edge=edges[currentIndex];
                while(edge!=null){
                    if(!visited[edge.y]) {
                        visited[edge.y]=true;
                        processEdge(currentIndex,edge.y);
                        s.push(edge.y);
                    }
                    edge = edge.next;
                }
                processVertexLate(currentIndex);
            }
        }

        public static void main(String[] args) {
            Graph g=Graph.createDummyGraph();

            g.printGraph();

            System.out.println("DFS:");
            g.dfs(4);

            System.out.println("BFS:");
            g.bfs(4);
        }
    }
