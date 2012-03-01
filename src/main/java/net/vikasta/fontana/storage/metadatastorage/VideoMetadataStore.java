/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.metadatastorage;

import java.util.*;

import net.vikasta.fontana.network.p2p.overlay.*;
/**
 *
 * @author ratulmukh
 */
public abstract class VideoMetadataStore implements IVideoMetadataStore {


    public List<IVideoMetadata> searchVideoMetadataStore(List<String> keywordCluster)
    {
        List<IVideoMetadata> keywordClusterMetadataList = new ArrayList<IVideoMetadata>();
        Iterator<String> keywordClusterIterator = keywordCluster.iterator();


        String keyword;
        while(keywordClusterIterator.hasNext()){
            keyword = keywordClusterIterator.next();
            List<IVideoMetadata> keywordVideoMetadataList = searchVideoMetadataStore(keyword);
            Iterator<IVideoMetadata> keywordVideoMetadataListIterator = keywordVideoMetadataList.iterator();

            IVideoMetadata videoMetadata;
            while(keywordVideoMetadataListIterator.hasNext()){
                videoMetadata = keywordVideoMetadataListIterator.next();
                keywordClusterMetadataList.add(videoMetadata);
            }
        }

        return keywordClusterMetadataList;

    }

//    public List<VideoMetadata> getVideoMetadataList(String keyword)
//    {
//        return searchMetadataStore(keyword);
//    }

    public abstract List<IVideoMetadata> searchVideoMetadataStore(String keyword);

}
