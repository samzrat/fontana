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
public class CharacterAppendKeywordClusterCreator implements  IKeywordClusterCreator {
    public List<String> createKeywordCluster(String keyword)
    {
        List<String> keywordCluster = new ArrayList<String>(4);
        keywordCluster.add(keyword);
        keywordCluster.add(keyword.concat("a"));
        keywordCluster.add(keyword.concat("b"));
        keywordCluster.add(keyword.concat("c"));

        return keywordCluster;
    }

    public List<String> createKeywordCluster(List<String> keywordList)
    {
        List<String> compoundCluster = new ArrayList<String>();
        Iterator<String> keywordListIterator = keywordList.iterator();
        while(keywordListIterator.hasNext())
        {
            List<String> cluster = createKeywordCluster(keywordListIterator.next());
            Iterator<String> clusterIterator = cluster.iterator();
            while(clusterIterator.hasNext())
                compoundCluster.add(clusterIterator.next());
        }
        return compoundCluster;
    }


}
