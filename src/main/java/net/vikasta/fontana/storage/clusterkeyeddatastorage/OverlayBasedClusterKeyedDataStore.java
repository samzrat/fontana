/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.clusterkeyeddatastorage;

import java.util.*;

import net.vikasta.fontana.network.p2p.overlay.*;
/**
 *
 * @author ratulmukh
 */
public class OverlayBasedClusterKeyedDataStore extends ClusterKeyedDataStore
{
    public OverlayBasedClusterKeyedDataStore(IExactKeyOverlay<ClusterKeyedData> overlay, int indexIntervalUpdation, String policy, int daemonExecutionInterval, IIOCClusterKeyedDataLocalStoreDaemon localStoreDaemon)
    {
        super(indexIntervalUpdation, policy, daemonExecutionInterval, localStoreDaemon);
        this.overlay = overlay;
    }

    IExactKeyOverlay<ClusterKeyedData> overlay;

    public List<ClusterKeyedData> search(List<String> keywordList)
    {
        //we can't use the overlay directly, since search is based on the cluster
        //need to use Bloom filter or some other mechanism to work on top of the overlay
        return null;
    }

    public void storeKeywordCluster(ClusterKeyedData keywordCluster)
    {
        
        overlay.store(keywordCluster.getName(), keywordCluster);
    }


}


