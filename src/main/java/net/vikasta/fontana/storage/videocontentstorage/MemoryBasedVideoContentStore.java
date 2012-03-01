/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.videocontentstorage;

import net.vikasta.fontana.video.*;
import net.vikasta.fontana.storage.metadatastorage.*;
import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class MemoryBasedVideoContentStore implements IVideoContentStore {

    List<Video> videoList = new ArrayList<Video>();
    public void streamInVideo(IVideoLocationMetadata videoContentLocationMetadata, IVideoSink videoSink)
    {

        int inMemoryIndex = ((IInMemoryVideoLocationMetadata)videoContentLocationMetadata).getInMemoryIndex();
        List<VideoChunk> videoChunkList = videoList.get(inMemoryIndex).getVideoChunkList();

       Iterator<VideoChunk> videoChunkListIterator = videoChunkList.iterator();
       while(videoChunkListIterator.hasNext())
           videoSink.sendVideoChunk(videoChunkListIterator.next());
        
    }
    
    public IVideoMetadata storeVideo(Video video)
    {
        videoList.add(video);
        InMemoryVideoMetadata inMemoryVideoMetadata = new InMemoryVideoMetadata();
        inMemoryVideoMetadata.inMemoryIndex = videoList.size()-1;
        return inMemoryVideoMetadata;
    }

}
