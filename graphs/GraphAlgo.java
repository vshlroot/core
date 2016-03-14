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
        ArrayList<Integer> path =new ArrayList<>();
        Stack<Integer> s= new Stack<>();

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
        int vertex=y;
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

    }
}
