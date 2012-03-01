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
public interface IVideoDescriptionMetadata extends IVideoMetadata {
    public void setKeywordCluster(List<String> cluster);
    public String getVideoName();
    public Boolean searchKeywordCluster(String keyword);
    public List<String> getKeywordCluster();

}
