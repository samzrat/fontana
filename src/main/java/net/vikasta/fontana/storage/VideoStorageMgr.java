/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage;

import net.vikasta.fontana.video.*;
import net.vikasta.fontana.storage.metadatastorage.*;
import net.vikasta.fontana.storage.videocontentstorage.*;

import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class VideoStorageMgr {
    private VideoMetadataMgr videoMetadataMgr  = new VideoMetadataMgr(null, 0);
    //private VideoContentMgr videoContentMgr = new VideoContentMgr();
    
    public List<IVideoMetadata> videoSearch(String keyword)

    {
        List<IVideoMetadata> videoMetadataList = videoMetadataMgr.searchVideoMetadataStore(keyword);
        return videoMetadataList;
    }

    public List<Video> rankOrderedVideoSearch(String keyword)
    {
        //List<Video> searchedVideoList = videoSearch(keyword);
        return null;
    }

//    public void playVideo(Video video, IVideoDecoder videoDecoder)
//    {
//        videoContentMgr.playVideo(video, videoDecoder);
//    }

    public IVideoDescriptionMetadata storeVideo(Video video, List<String> tags)
    {
//        IVideoMetadata iVideoMetadata = videoContentMgr.storeVideo(video);
  //      return (IVideoDescriptionMetadata)(videoMetadataMgr.createAndStoreVideoMetadata(iVideoMetadata, tags));
        return null;
    }

    public void streamInVideo(IVideoDescriptionMetadata videoDescriptionMetadata, IVideoSink videoSink)
    {
    //    IVideoLocationMetadata iVideoLocationMetdata = (IVideoLocationMetadata)videoDescriptionMetadata;
      //  videoContentMgr.streamInVideo(iVideoLocationMetdata, videoSink);
    }
}
