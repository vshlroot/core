package graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by vishalss on 3/13/2016.
 */
/*
Implements basic algorithms on graphs
Shortest Path between two nodes
  */
public class GraphAlgo {

    static private int time;
    GraphAlgo(){

    }

    // Finds shortest path between vertex x and y (x and y are indexes)
    // Uses BFS
    public ArrayList<Integer> shortestPath(Graph g,int x, int y){
        if(g==null || g.getNumberOfVertices()<=0){
            System.out.println("Invalid input: Empty Graph");
            return null;
        }

        // Running BFS
        Queue<Integer> q=new LinkedList<>();
        q.add(x);
        boolean visited[]=new boolean[g.getNumberOfVertices()];
        int parent[]=new int[g.getNumberOfVertices()];
        for (int i = 0; i < g.getNumberOfVertices(); i++) {
            visited[i]=false;
            parent[i]=-1;
        }
        int currentVertex;
        EdgeNode edge;
        visited[x]=true;
        while (!q.isEmpty()){
            currentVertex=q.remove();
            if(currentVertex==y){
                break;
            }
            edge=g.getEdgeList(currentVertex);
            while (edge!=null){
                if(!visited[edge.y]){
                    visited[edge.y]=true;
                    parent[edge.y]=currentVertex;
                    q.add(edge.y);
                }
                if (edge.y==y){
                    break;
                }
                edge=edge.next;
            }
        }
        //fetching the path
        return printPath(parent,y);
    }

    // Provided a parent array prints the path from source node to a node.
    public ArrayList<Integer> printPath(int[] parent, int vertex){
        ArrayList<Integer> path =new ArrayList<>();
        Stack<Integer> s= new Stack<>();
        s.push(vertex);
        while (parent[vertex]!=-1){
            vertex=parent[vertex];
            s.add(vertex);
        }

        while (!s.empty()){
            path.add(s.pop());
        }
        return path;
    }


    public int numberOfConnectedComponents(Graph g){
        ArrayList connectedComponents=getConnectedComponents(g);
        return connectedComponents ==null?0: connectedComponents.size();
    }

    public ArrayList getConnectedComponents(Graph g){
        if(g==null || g.getNumberOfVertices()==0){
            return null;
        }
        ArrayList<ArrayList<Integer>> result=new ArrayList<>();

        boolean visited[]=new boolean[g.getNumberOfVertices()];
        for (int i = 0; i <visited.length ; i++) {
            visited[i]=false;
        }

        ArrayList currentComponent;
        for (int i = 0; i < visited.length; i++) {
            if(visited[i]==false){
                currentComponent=findConnectedComponentFrom(g,i,visited);
                result.add(currentComponent);
            }
        }
        return result;
    }

    // running BFS
    public ArrayList<Integer> findConnectedComponentFrom(Graph g,int index,boolean[] visited ){
        System.out.println("visited.length"+visited.length);
        ArrayList<Integer> components=new ArrayList<>();
        Queue<Integer> q=new LinkedList<>();
        q.add(index);
        visited[index]=true;

        EdgeNode edge;
        int currentVertex;
        while (!q.isEmpty()) {
            currentVertex=q.remove();
            components.add(currentVertex);
            edge=g.getEdgeList(currentVertex);
            while (edge != null) {
                if(!visited[edge.y]){
                    visited[edge.y]=true;
                    q.add(edge.y);
                }
                edge = edge.next;
            }
        }
        return components;
    }

    // Will use BFS. Our exploration will radiate out rather than pierce.
    // Will start from the root vertex.
    // Will consider graph with single Connected component
    // Future: Will work for more than one connected components
    public boolean isGraphTwoColorable(Graph g){
        if(g==null){
            return false;
        }
        if(g.getNumberOfVertices()<2)
            return true;

        int color[]=new int[g.getNumberOfVertices()];
        for (int i = 0; i < g.getNumberOfVertices(); i++) {

            color[i]=-1;
        }
        Queue<Integer> q=new LinkedList<>();

        EdgeNode edge;
        int currentVertex;
        q.add(0);
        color[0]=0;
        while(!q.isEmpty()){
            currentVertex=q.remove();
            edge=g.getEdgeList(currentVertex);
            while(edge!=null){
                if(color[edge.y]==-1) {
                    color[edge.y] = 1-color[currentVertex]; // Neet trick, isn't it...
                    q.add(edge.y);
                }
                else if(color[edge.y]==color[currentVertex]){
                    System.out.println(currentVertex);
                    System.out.println(edge.y);
                    return false;
                }
                edge=edge.next;
            }
        }
        return true;
    }

    // Checks for a cycle using DFS
    // Starts from root 0
    public boolean cycleExists(Graph g){
        if(g==null || g.getNumberOfVertices()<=1){
            return false;
        }
        boolean discovered[]=new boolean[g.getNumberOfVertices()];
        int parent[]=new int[g.getNumberOfVertices()];

        for (int i = 0; i < discovered.length; i++) {
            discovered[i]=false;
            parent[i]=-1;
        }
        Stack<Integer> s=new Stack<>();
        s.push(0);
        discovered[0]=true;
        int currentVertex;
        EdgeNode edge;
        while (!s.isEmpty()){
            currentVertex=s.pop();
            discovered[currentVertex]=true;
            edge=g.getEdgeList(currentVertex);
            while (edge!=null){
                if(discovered[edge.y] && edge.y!=parent[currentVertex]){
                    System.out.println(currentVertex);
                    System.out.println(edge.y);
                    System.out.println(parent[currentVertex]);
                    return true;
                }
                if(!discovered[edge.y]) {
                    s.push(edge.y);
                    discovered[edge.y] = true;
                    parent[edge.y] = currentVertex;
                }
                edge = edge.next;
            }

        }
        return false;
    }

    // Using DFS.
    // Focusing on Back edge processing
    public ArrayList<Integer> findArticulationVertices(Graph g) {
        boolean[] visited=new boolean[g.getNumberOfVertices()];
        boolean[] articulationVertex=new boolean[g.getNumberOfVertices()];

        time=-1;
        int discoveryTime[]=new int[g.getNumberOfVertices()];
        int lowReachable[]=new int[g.getNumberOfVertices()];
        int parent[]=new int[g.getNumberOfVertices()];
        for (int i = 0; i < parent.length; i++) {
            parent[i]=-1;
        }
        runDfsForArticulation(g,0,visited,articulationVertex,discoveryTime,lowReachable,parent);
        ArrayList<Integer> result=new ArrayList<>();
        for (int i = 0; i < g.getNumberOfVertices(); i++) {
            if(articulationVertex[i]){
                result.add(i);
            }
        }
        return result;
    }

    public void runDfsForArticulation(Graph g,int root, boolean[] visited,boolean[] articulationVertex,int[] discoveryTime,int[] lowReachable ,int[] parent){
        if(g==null ||g.getNumberOfVertices()<1 ||root>g.getNumberOfVertices()){
            return;
        }
        int children=0;
        time++;
        discoveryTime[root]=lowReachable[root]=time;
        visited[root]=true;
        EdgeNode edge=g.getEdgeList(root);
        // discoveryTime should be when pushing in the stack and not when fetching from it.

            while (edge!=null){
                if(!visited[edge.y]){
                    children++;
                    parent[edge.y]=root;
                    runDfsForArticulation(g,edge.y,visited,articulationVertex,discoveryTime,lowReachable,parent);
                    lowReachable[root]=Math.min(lowReachable[edge.y],lowReachable[root]);
                    // Root is an articulation vertex iff it has atleast 2 children(children means, parent of both is root)
                    // In case of a cycle which starts from root through one neighbor and ends at root through another neighbor,
                    // root won't have two children as DFS will mark latter as a back edge rather than child.
                    if(parent[root]==-1 && children>1){
                        articulationVertex[root]=true;
                    }
                    // <= because it will be articulation vertex even if some back edge from descendants returns to it.
                    if(parent[root]!=-1 && lowReachable[root]>=discoveryTime[root]){
                        System.out.println(lowReachable[root]);
                        System.out.println(discoveryTime[root]);
                        System.out.println("Adding "+ root);
                        articulationVertex[root]=true;
                    }
                }
                else if(parent[edge.y]!=root){
                    System.out.println("Found visited edge from "+root+" ->"+ edge.y);
                    lowReachable[root]=Math.min(discoveryTime[edge.y],lowReachable[root]);
                    //lowReachable[root]=Math.min(lowReachable[edge.y],discoveryTime[root]);
                }
                edge=edge.next;
            }
    }

    // Assumption: Graph should be a DAG

    public ArrayList<Integer> topologicalSort(Graph g, int root){
        if(g==null || g.getNumberOfVertices()<1)
            return null;
        if(root>=g.getNumberOfVertices()){
            System.out.println("INVALID INPUT");
            return null;
        }
        boolean[] visited=new boolean[g.getNumberOfVertices()];
        int parent[]=new int[g.getNumberOfVertices()];
        for (int i = 0; i < parent.length; i++) {
            parent[i]=-1;
        }
        Stack<Integer> s=new Stack<>();
        runDfsForTopologicalSort(g, root, visited, parent, s);
        System.out.println("Checking for others");
        for (int i = 0; i < g.getNumberOfVertices(); i++) {
            if(!visited[i]){
                runDfsForTopologicalSort(g, i, visited, parent, s);
            }
        }
        ArrayList<Integer> result=new ArrayList<>();
        while ((!s.isEmpty())){
            result.add(s.pop());
        }
        return result;
    }

    public boolean runDfsForTopologicalSort(Graph g,int root, boolean[] visited,int[] parent, Stack<Integer> s){
        visited[root]=true;
        EdgeNode edge=g.getEdgeList(root);
        while (edge!=null){
            if(visited[edge.y]){
                edge=edge.next;
                continue;
            }
            parent[edge.y]=root;
            if(runDfsForTopologicalSort(g,edge.y,visited,parent,s))
                return true;
            edge=edge.next;
        }
        s.push(root);
        return false;
    }

    public static void main(String[] args) {
        Graph g=Graph.createDummyGraph();
        GraphAlgo algo=new GraphAlgo();
        ArrayList<Integer> path=algo.shortestPath(g, 0, 5);
        System.out.println("Shortest Path is:");
        System.out.println(path);

        Graph g1=Graph.createDummyGraphUnconnected();
        System.out.println(g1.getNumberOfVertices());
        System.out.println(algo.numberOfConnectedComponents(g1));
        System.out.println(algo.getConnectedComponents(g1));

        System.out.println("Is colorable:");
        System.out.println(algo.isGraphTwoColorable(g));

        System.out.println("Cycle Exists");
        System.out.println(algo.cycleExists(g));

        System.out.println("Articulation Points");
        System.out.println(algo.findArticulationVertices(g));


        Graph dag=Graph.createDummyDAG();
        System.out.println("===========================");
        System.out.println("Topological Sort");
        System.out.println(algo.topologicalSort(dag,5));
    }
}
