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
    }
}
