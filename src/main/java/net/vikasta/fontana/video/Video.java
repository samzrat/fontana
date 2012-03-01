/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.video;
import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class Video {

    public enum VideoFormat
    {
        MPEG,
        H264,
        DummyChunk
    }

    private VideoFormat videoFormat;
    List<VideoChunk> videoChunkList = new ArrayList<VideoChunk>();;
    private String name;
    //private String videoID;
    private Object storageMetadata;

    public Video(String name, Object storageMetadata)
    {
        this.name = name;
        this.storageMetadata = storageMetadata;
    }

    public Object getStorageMetadata()
    {
        return storageMetadata;
    }

    public String getName()
    {
        return name;
    }

    public VideoFormat getVideoFormat()
    {
        return videoFormat;
    }

    public void addVideoChunk(VideoChunk videoChunk)
    {
        videoChunkList.add(videoChunk);
    }

    public List<VideoChunk> getVideoChunkList()
    {
        return videoChunkList;
    }


}
