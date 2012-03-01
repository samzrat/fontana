/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.keywordcluster;
import net.vikasta.fontana.keywordcluster.IKeywordClusterCreator;
import java.util.*;

/**
 *
 * @author ratulmukh
 */
public class KeywordClusterMgr {

    //VideoMetadataStore videoMetadataStore = new VideoMetadataStore();
    IKeywordClusterCreator keywordClusterCreator = new CharacterAppendKeywordClusterCreator();

    public void KeywordClusterMgr()
    {

    }

    public List<String> createKeywordCluster(String keyword)
    {
        return keywordClusterCreator.createKeywordCluster(keyword);
    }

    public List<String> createKeywordCluster(List<String> keywordList)
    {
        return keywordClusterCreator.createKeywordCluster(keywordList);
    }

}
