//ClusterKeyedData consists of data that is indexed by a set of keywords called
//a keyword cluster

package net.vikasta.fontana.storage.clusterkeyeddatastorage;

import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class ClusterKeyedData<Data> {
    //private String ID;
    private List<String> key = new LinkedList<String>();
    private Data data;
    private String name;

    public String getName()
    {
        return name;
    }
    public Object getData()
    {
        return data;
    }

    public ClusterKeyedData(Data data, String name)
    {
        this.data = data;
        this.name = name;
//        this.ID = ID;
//        data = null;
    }

    public void addKeywordToCluster(String keyword)
    {
        key.add(keyword);
    }

    public void removeKeywordFromCluster(String keyword)
    {
        int foundIndex;
        if((foundIndex=key.indexOf(keyword))!=-1)
            key.remove(foundIndex);
    }

    public boolean isKeywordPresent(String keyword)
    {
        if(key.contains(keyword))
            return true;
        else
            return false;
    }
//
//    public void addAssociatedData(Object data)
//    {
//        this.data = data;
//    }


}
