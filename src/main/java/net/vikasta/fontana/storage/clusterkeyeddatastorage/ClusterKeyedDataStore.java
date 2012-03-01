/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.clusterkeyeddatastorage;

import java.util.*;
import java.security.*;
/**
 *
 * @author ratulmukh
 */
public abstract class ClusterKeyedDataStore {

    private int keywordClusterStoreID;
    int indexIntervalUpdation;
    int daemonExecutionInterval;
    IIOCClusterKeyedDataLocalStoreDaemon localStoreDaemon;
    
    public abstract void storeKeywordCluster(ClusterKeyedData keywordCluster);
    public abstract List<ClusterKeyedData> search(List<String> keywordList);

    public int getKeywordClusterStoreID()
    {
        return keywordClusterStoreID;
    }

    public ClusterKeyedData retrieveKeywordClusterByName(String ID)
    {
        return null;
    }

    public ClusterKeyedDataStore(int indexIntervalUpdation, String policy, int daemonExecutionInterval, IIOCClusterKeyedDataLocalStoreDaemon localStoreDaemon)    {
        SecureRandom secureRandom = new SecureRandom();
        this.keywordClusterStoreID = secureRandom.nextInt();
        this.indexIntervalUpdation = indexIntervalUpdation;
        this.daemonExecutionInterval = daemonExecutionInterval;
        this.localStoreDaemon = localStoreDaemon;
    }

   // public ClusterKeyedDataStore()
    //{
      //
    //}



}
