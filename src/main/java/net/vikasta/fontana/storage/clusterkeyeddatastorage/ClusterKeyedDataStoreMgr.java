/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.clusterkeyeddatastorage;

import java.util.*;
import java.net.*;
import net.vikasta.fontana.storage.*;
/**
 *
 * @author ratulmukh
 */
public class ClusterKeyedDataStoreMgr {
 

    public ClusterKeyedDataStoreMgr(String policy, IIOCLocalStoreDaemon localStoreDaemon)
    {

    }

    public ClusterKeyedDataStore createKeywordClusterStore(int indexIntervalUpdation, String policy, int daemonExecutionInterval, IIOCClusterKeyedDataLocalStoreDaemon localStoreDaemon)
    {
        return null;
        //return new ClusterKeyedDataStore(indexIntervalUpdation, policy, daemonExecutionInterval, localStoreDaemon);

    }

    public ClusterKeyedDataStore joinKeywordClusterStore(int keywordCluterStoreID, InetAddress bootstrapIP)
    {
        return null;
    }



}
