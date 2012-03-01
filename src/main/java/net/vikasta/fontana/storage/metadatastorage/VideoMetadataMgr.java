/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.metadatastorage;
import java.util.List;

import net.vikasta.fontana.keywordcluster.KeywordClusterMgr;
import net.vikasta.fontana.storage.IIOCLocalStoreDaemon;
/**
 *
 * @author ratulmukh
 */
public class VideoMetadataMgr {

    private KeywordClusterMgr keywordClusterMgr = new KeywordClusterMgr();
    private  VideoMetadataStore videoMetadataStore = new InMemoryVideoMetadataStore();

    public VideoMetadataMgr(IIOCLocalStoreDaemon localStoreDaemon, int daemonExecutionInterval)
    {
        
    }

    public List<IVideoMetadata> searchVideoMetadataStore(String keyword)
    {
        List<String> keywordCluster = keywordClusterMgr.createKeywordCluster(keyword);
        return videoMetadataStore.searchVideoMetadataStore(keywordCluster);
    }

    public IVideoMetadata createAndStoreVideoMetadata(IVideoMetadata iVideoMetadata, List<String> tags)
    {
        List<String> keywordCluster = keywordClusterMgr.createKeywordCluster(tags);
        IVideoDescriptionMetadata videoDescriptonMetadata = (IVideoDescriptionMetadata)iVideoMetadata;
        videoDescriptonMetadata.setKeywordCluster(keywordCluster);
   
        videoMetadataStore.storeVideoMetadata(iVideoMetadata);
        return iVideoMetadata;
    }
     
 
}
