/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.video;

import java.util.ArrayList;
import java.util.List;

import net.vikasta.fontana.common.IStreamConsumer;
import net.vikasta.fontana.common.InvalidDataException;
import net.vikasta.fontana.common.Pair;
import net.vikasta.fontana.storage.videocontentstorage.VideoContentStore;
/**
 *
 * @author ratulmukh
 */
public class VideoMgr<Data> implements IStreamConsumer<Data> {

    private VideoContentStore videoContentStore;

    public Video storeVideo(String videoLocalPath, String name)
    {
        Object storageMetadata = videoContentStore.storeVideoChunks(name, createChunks(videoLocalPath));
        return new Video(name, storageMetadata);
    }

    public void beginStreamingInVideo(Video video, IStreamConsumer streamConsumer, Object callerState) throws InvalidDataException
    {
        videoContentStore.beginStreamingInVideo(video.getName(), video.getStorageMetadata(), this, new Pair<IStreamConsumer, Object>(streamConsumer,callerState));
    }

    public void deliver(Data data, int streamPosition, Object callerState)
    {
        Pair<IStreamConsumer, Object> pair = (Pair<IStreamConsumer, Object>)(callerState);
        pair.first.deliver(data, streamPosition, pair.second);

    }

    public void endStreamingInVideo(Video video) throws InvalidDataException
    {
        videoContentStore.endStreamingInVideo(video.getName());
    }

    private List<String> createChunks(String videoLocalPath)
    {
        int noOfChunks = 10;
        List<String> videoChunks = new ArrayList<String>(noOfChunks);
        for(int i=0; i<noOfChunks; i++)
            videoChunks.add(videoLocalPath+"Chunk "+i);
        return videoChunks;
    }

    public VideoMgr(VideoContentStore videoContentStore)
    {
        this.videoContentStore = videoContentStore;
    }
//    public void streamInVideo(IVideoLocationMetadata videoContentLocationMetadata, IVideoSink videoSink)
//    {
//        videoContentStore.streamInVideo(videoContentLocationMetadata, videoSink);
//    }
}
