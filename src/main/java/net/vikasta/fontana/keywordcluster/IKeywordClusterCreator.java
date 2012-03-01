/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.keywordcluster;
import java.util.*;
/**
 *
 * @author ratulmukh
 */
public interface IKeywordClusterCreator {
    public List<String> createKeywordCluster(String keyword);
    public List<String> createKeywordCluster(List<String> keywordList);
   

}
