package graphs;

/**
 * Created by vishalss on 3/25/2016.
 */
/*
Implements the Union-Find algorithm using tree data structure.
 */
public class UnionFind {

    private int numberOfElements;
    private int[] parent;                   // Will have the back edges.
    private int[] sizeOfSubtree;            // Size of the subtree rooted at i

    UnionFind(){

    }

    UnionFind(int numberOfElements){
        this.numberOfElements=numberOfElements;
        parent=new int[numberOfElements];
        sizeOfSubtree=new int[numberOfElements];
        initialize();
    }

    public void initialize(){
        for (int i = 0; i < numberOfElements; i++) {
            parent[i]=i;
            sizeOfSubtree[i]=1;
        }
    }

    // Returns the root of the vertex i;
    public int find(int i){
        return parent[i]==i?i:find(parent[i]);
    }

    // Merges two subtrees with different roots
    // Does nothing if same root.
    public void unionSets(int s1, int s2){
        int root1=find(s1);
        int root2=find(s1);
        if(root1==root2)
            return;

        // root1 is a bigger tree.
        if(sizeOfSubtree[root1]>=sizeOfSubtree[root2]){
            parent[root2]=root1;
            sizeOfSubtree[root1]+=sizeOfSubtree[root2];
        }
        else {
            parent[root1]=root2;
            sizeOfSubtree[root2]+=sizeOfSubtree[root1];
        }
    }

    public boolean sameComponent(int s1, int s2){
        return find(s1)==find(s2);
    }

}
