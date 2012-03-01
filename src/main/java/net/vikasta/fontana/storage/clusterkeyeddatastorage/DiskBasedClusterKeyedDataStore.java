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
public class DiskBasedClusterKeyedDataStore extends ClusterKeyedDataStore {

    public DiskBasedClusterKeyedDataStore(int indexIntervalUpdation, String policy, int daemonExecutionInterval, IIOCClusterKeyedDataLocalStoreDaemon localStoreDaemon)
    {
        super(indexIntervalUpdation, policy, daemonExecutionInterval, localStoreDaemon);
    }

    public void storeKeywordCluster(ClusterKeyedData keywordCluster)
    {

    }
    public List<ClusterKeyedData> search(List<String> keywordList)
    {
        return null;
    }


}
