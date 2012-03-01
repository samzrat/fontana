//ClusterKeyedData consists of data that is indexed by a set of keywords called
//a keyword cluster

package net.vikasta.fontana.datamodel;

import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class SingleKeyedAndClusterKeyedData {
    //private String ID;
    private String singleKey;
    private List<String> clusterKey = new LinkedList<String>();
    private Object data;

    public SingleKeyedAndClusterKeyedData(String singleKey, Object data)
    {
        this.singleKey = singleKey;
        this.data = data;
//        this.ID = ID;
//        data = null;
    }

    public void addKeywordToCluster(String keyword)
    {
        clusterKey.add(keyword);
    }

    public void removeKeywordFromCluster(String keyword)
    {
        int foundIndex;
        if((foundIndex=clusterKey.indexOf(keyword))!=-1)
            clusterKey.remove(foundIndex);
    }

    public boolean isKeywordPresentInCluster(String keyword)
    {
        if(clusterKey.contains(keyword))
            return true;
        else
            return false;
    }

    public String getSinglekey()
    {
        return singleKey;
    }
    
//
//
//    public void addAssociatedData(Object data)
//    {
//        this.data = data;
//    }


}
