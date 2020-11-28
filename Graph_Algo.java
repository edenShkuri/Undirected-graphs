package ex0;

import java.util.*;

public class Graph_Algo implements graph_algorithms {
    private graph ga;
    HashMap<Integer, node_data> path;
    private int start; //the start and tha path are used in the is connected and shortestPath methods
    /** build a new graph
     */
    public Graph_Algo() {
        ga = new Graph_DS();
        path = new HashMap<Integer, node_data>();
        start = 0;
    }
    @Override
    /**
     * Init the graph on which this set of algorithms operates on.
     * @param g
     */
    public void init(graph g) {
        ga = g;
    }

    @Override
    /**
     * Compute a deep copy of this graph.
     */
    public graph copy() {
        graph gNew = new Graph_DS();
        for (node_data nodeData : ga.getV()) { //create new nodes (same like in the graph we have) and add them to the new graph
            gNew.addNode(new NodeData(nodeData.getKey()));
        }
        for (node_data nodeData : ga.getV()) { //go over all the "old" graph's nodes
            for (node_data nodeData1 : nodeData.getNi()) //go over all the neighbors of each node
                gNew.connect(nodeData1.getKey(), nodeData.getKey()); //make a connection between the node and his neighbor in the new gragh
        }
        return gNew;
    }

    @Override
    /**
     * Returns true if and only if (iff) there is a valid path from EVREY node to each
     * other node.
     */
    public boolean isConnected() {
        if(ga.nodeSize()==0) //if there is no nodes in the graph return true
            return true;
        if (ga.getNode(start)==null) //if there is no such node with the key start then get the first node in the graph
            start =ga.getV().stream().findFirst().orElse(new NodeData()).getKey();
        HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
        Queue<node_data> q = new ArrayDeque<node_data>();
        for (node_data n: ga.getV()) { //Initializing the visited to false to all nodes and the path to null
            visited.put(n.getKey(), false);
            path.put(n.getKey(),null);
        }
            q.add(ga.getNode(start)); //adding the start node to the queue
            visited.replace(start, true); //change the start node to true (sign that we visited him)
        while(q.isEmpty()==false) {
            node_data n = q.poll(); //save the last node we poll from the queue
            for (node_data ni : n.getNi()) { //get over all the node's neighbors
                if (visited.get(ni.getKey()) == false) { //if the node is'nt marked as true:
                    q.add(ni);
                    visited.replace(ni.getKey(), true); //mark as visited
                    path.replace(ni.getKey(), n);  //mark the ni node with the node "n"- that's mean that we came to ni from n, and n is ni's father
                }
            }
        }
        if (visited.containsValue(false)) //if we have false in the visited hashmap - we have node that's have'nt been visited.
            return false;
        return true;
    }

    @Override
    /**
     * returns the length of the shortest path between ex1.ex1.src to dest
     * Note: if no such path --> returns -1
     * @param ex1.ex1.src - start node, end (target) node
     */
    public int shortestPathDist(int src, int dest) {
        if (shortestPath(src, dest)==null)
            return -1;
        return shortestPath(src, dest).size()-1;
    }

    @Override
    /**
     * returns the the shortest path between ex1.ex1.src to dest - as an ordered List of nodes:
     * ex1.ex1.src--> n1-->n2-->...dest
     * Note if no such path --> returns null;
     * @param ex1.ex1.src - start node, dest - end (target) node
     */
    public List<node_data> shortestPath(int src, int dest) {
        if(ga.getNode(src)==null || ga.getNode(dest)==null) //if there is no nodes with ex1.ex1.src or dest key return null
            return null;
        List<node_data> revers = new ArrayList<node_data>();
        List<node_data> revers2 = new LinkedList<node_data>();
        if(src==dest)
        {
            revers2.add(ga.getNode(src));
                    return revers2;
        }
        start=src; //Initialize start to ex1.ex1.src before we call to isConnected
        isConnected();
        revers.add(ga.getNode(dest));
        for (node_data n = path.get(dest); n != null; n = path.get(n.getKey()))
            revers.add(n);
        for (int i=revers.size()-1;i>=0; i--)
            revers2.add(revers.get(i));
        if (revers2.get(0).getKey() == src)
            return revers2;
        return null;
    }

    public String toString() {
           return "[" + ga.getV() + "]";
        }

}