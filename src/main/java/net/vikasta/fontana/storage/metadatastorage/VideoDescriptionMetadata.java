/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.metadatastorage;
import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class VideoDescriptionMetadata implements IVideoDescriptionMetadata {
    private String videoName;
    private List<String> keywordCluster;

    public void setKeywordCluster(List<String> cluster)
    {
        keywordCluster = cluster;
    }

    public String getVideoName()
    {
        return new String(videoName);
    }

    public Boolean searchKeywordCluster(String keyword)
    {

        Iterator<String> keywordClusterIterator = keywordCluster.iterator();
        while(keywordClusterIterator.hasNext())
           if(keywordClusterIterator.next().equals(keyword))
               return true;
        return false;
    }
    
    public List<String> getKeywordCluster()
    {
        List<String> clonedKeywordCluster = new ArrayList<String>(keywordCluster.size());

        Iterator<String> keywordClusterIterator = keywordCluster.iterator();
        while(keywordClusterIterator.hasNext())
           clonedKeywordCluster.add(keywordClusterIterator.next());
        return clonedKeywordCluster;


    }

}
