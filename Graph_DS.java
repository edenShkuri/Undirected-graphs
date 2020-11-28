package ex0;

import java.security.PrivateKey;
import java.util.Collection;
import java.util.HashMap;


public class Graph_DS implements graph{
    private HashMap<Integer, node_data> graf;
    private int MC;
    private int Edge;
    /**
     *  build a new graph
     */
    public Graph_DS()
    {
        MC=0;
        Edge=0;
        graf = new HashMap<>();
    }
//
    @Override
    /**
     * return the node_data by the node_id,
     * @param key - the node_id
     * @return the node_data by the node_id, null if none.
     */
    public node_data getNode(int key) {
        return graf.get(key);
    }

    @Override
    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * @param node1, node2
     */
    public boolean hasEdge(int node1, int node2) {
        if(!graf.containsKey(node1) || !graf.containsKey(node2)) //if one of the nodes not exist in the graph return false
            return false;
           return getNode(node1).hasNi(node2);
    }

    @Override
    /**
     * add a new node to the graph with the given node_data.
     * @param n
     */
    public void addNode(node_data n) {
        MC++;
        graf.put(n.getKey(),n);
    }

    @Override
    /**
     * Connect an edge between node1 and node2.
     * if the edge node1-node2 already exists - the method does nothing.
     * @param node1, node2
     */
    public void connect(int node1, int node2) {
//            if (graf.get(node1) == null || graf.get(node2) == null) // Checking if node1 & node2 exist
//                return;
//            if (node1 == node2) // No need to connect if it's the same key
//                return;
//            if (graf.get(node1).hasNi(node2) || graf.get(node2).hasNi(node1)) // No need to connect if it is already connected
//                return;
//
//        graf.get(node1).addNi(graf.get(node2)); // Adding node2 as a neighbor to node1
//        graf.get(node2).addNi(graf.get(node1)); // Adding node1 as a neighbor to node2
//            MC++;
//            Edge++;

        if(!graf.containsKey(node1) ||!graf.containsKey(node2)|| hasEdge(node1, node2) || node1==node2 )
            return;
        MC++;
        Edge++;
        getNode(node1).addNi(getNode(node2));
        getNode(node2).addNi(getNode(node1));
    }

    @Override
    /**
     * This method return a pointer (shallow copy) for the
     * collection representing all the nodes in the graph.
     * @return Collection<node_data>
     */
    public Collection<node_data> getV()
    {
        HashMap<Integer, node_data> grafpointer= new HashMap<>();
        grafpointer=graf;
        return grafpointer.values();
    }

    @Override
    /**
     * This method return a collection of  the
     * collection representing all the nodes connected to node_id
     * @return Collection<node_data>
     */
    public Collection<node_data> getV(int node_id)
    {
        return getNode(node_id).getNi();
    }
    @Override
    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * @return the data of the removed node (null if none).
     * @param key
     */
    public node_data removeNode(int key)
    {
        if(graf.containsKey(key)==false) //if the node not exists return null
            return null;
        MC++;
        node_data tmp=getNode(key);  //save the node in a temporary variable before i remove it
        for (node_data n: getNode(key).getNi()) { //go over all the neighbor of the node and remove the connection beetween them
            n.getNi().remove(getNode(key));
            Edge--;
            MC++;
        }
        graf.remove(key);
        return tmp;
    }

    @Override
    /**
     * Delete the edge from the graph,
     * @param node1, node2
     */
    public void removeEdge(int node1, int node2) {
        if (hasEdge(node1, node2)) {
            MC++;
            Edge--;
            getNode(node1).getNi().remove(graf.get(node2));
            getNode(node2).getNi().remove(graf.get(node1));
    }
    }

    @Override
    /** return the number of vertices (nodes) in the graph.
     */
    public int nodeSize() {
        return graf.size();
    }
    /**
     * return the number of edges (undirectional graph).
     */
    @Override
    public int edgeSize() {
        return Edge;
    }

    @Override
    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     */
    public int getMC() {
        return MC;
    }

    /**
     * return a string with all the nodes in
     * the graph and their neighbor
     */
    public String toString()
    {
        String s = "";
        for(node_data n: getV()) {
            s += n.toString() + ", neighbors: {" + n.getNi()+ "}\n";
        }
        return s;

    }
}