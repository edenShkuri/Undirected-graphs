package ex0;

import java.util.Collection;
import java.util.HashMap;

public class NodeData implements node_data {
    private int key;
    private static int counter=0;
    private  String metaData;
    private int tag;
    private HashMap<Integer, node_data> neighbor;

    public NodeData()
    {
        key=counter;
        counter++;
        tag=0;
        metaData=null;
        neighbor = new HashMap<Integer, node_data>();
    }
    public NodeData (int key)
    {
        this.key=key;
        tag=0;
        metaData=null;
        neighbor = new HashMap<Integer, node_data>();
    }

    @Override
    public int getKey()
    {
        return key;
    }

    @Override
    public Collection<node_data> getNi()
    {
        return neighbor.values();
    }

    @Override
    public boolean hasNi(int key)
    {
        return neighbor.containsKey(key);
    }

    @Override
    public void addNi(node_data t)
    {
        neighbor.put(t.getKey(),t);
    }

    @Override
    public void removeNode(node_data node)
    {
        neighbor.remove(node.getKey());
    }

    @Override
    public String getInfo() {
        return metaData;
    }

    @Override
    public void setInfo(String s) {
      this.metaData=s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
    this.tag=t;
    }

    public String toString ()
    {
        return "[key: "+key+",info: "+metaData+",tag: "+tag+"]";
    }
}
