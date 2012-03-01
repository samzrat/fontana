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
public class InMemoryVideoMetadataStore extends VideoMetadataStore {

    List<IVideoMetadata> videoMetadataList = new ArrayList<IVideoMetadata>();

    public List<IVideoMetadata> searchVideoMetadataStore(String keyword)
    {
        List<IVideoMetadata> foundVideoMetadataList = new ArrayList<IVideoMetadata>();
        Iterator<IVideoMetadata> videoMetadataListIterator = videoMetadataList.iterator();
        while(videoMetadataListIterator.hasNext())
        {
            IVideoDescriptionMetadata videoDescriptionMetadata = (IVideoDescriptionMetadata)(videoMetadataListIterator.next());
            if (videoDescriptionMetadata.searchKeywordCluster(keyword))
               foundVideoMetadataList.add((IVideoMetadata)videoDescriptionMetadata);
        }
        return foundVideoMetadataList;

    }

    public void storeVideoMetadata(IVideoMetadata videoMetadata)
    {
        videoMetadataList.add(videoMetadata);
    }

}
