/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.clusterkeyeddatastorage;

import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class MemoryBasedClusterKeyedDataStore extends ClusterKeyedDataStore {

    public MemoryBasedClusterKeyedDataStore(int indexIntervalUpdation, String policy, int daemonExecutionInterval, IIOCClusterKeyedDataLocalStoreDaemon localStoreDaemon)
    {
        super(indexIntervalUpdation, policy, daemonExecutionInterval, localStoreDaemon);
    }
    
    List<ClusterKeyedData> clusterKeyedDataList = new ArrayList<ClusterKeyedData>();

    public List<ClusterKeyedData> search(List<String> keywordList)
    {
        List<ClusterKeyedData> foundClusterKeyedDataList = new ArrayList<ClusterKeyedData>();

        Iterator<String> keywordListIterator = keywordList.iterator();
        while(keywordListIterator.hasNext())
        {
            String keyword = keywordListIterator.next();
            Iterator<ClusterKeyedData> clusterKeyedDataListIterator = clusterKeyedDataList.iterator();
            while(clusterKeyedDataListIterator.hasNext())
            {
                ClusterKeyedData clusterKeyedData = clusterKeyedDataListIterator.next();
                if (clusterKeyedData.isKeywordPresent(keyword))
                   foundClusterKeyedDataList.add(clusterKeyedData);
            }

        }
        return foundClusterKeyedDataList;

    }

    public void storeKeywordCluster(ClusterKeyedData keywordCluster)
    {
        clusterKeyedDataList.add(keywordCluster);
    }


}

