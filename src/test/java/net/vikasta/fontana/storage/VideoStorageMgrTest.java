/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.vikasta.fontana.storage.VideoStorageMgr;
import static org.junit.Assert.*;
import net.vikasta.fontana.storage.metadatastorage.*;
import net.vikasta.fontana.video.*;
import java.util.*;

/**
 *
 * @author ratulmukh
 */
public class VideoStorageMgrTest implements IVideoSink {

    int videoChunkSentCount;

    public VideoStorageMgrTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

   
    @Test
    public void testStoreAndStreamInVideo() {
        System.out.println("storeVideo");
        videoChunkSentCount = 0;

        Video video = new Video("Video1", null);
        VideoChunk videoChunk;

        for(int i=0;i<4;i++)
        {
            videoChunk = new VideoChunk();
            video.addVideoChunk(videoChunk);
        }

        VideoStorageMgr videoStorageMgr = new VideoStorageMgr();
        List<String> tags = new ArrayList<String>(1);
        tags.add("hello");
        IVideoMetadata videoMetadata = videoStorageMgr.storeVideo(video, tags);

        List<IVideoMetadata> foundVideoMetadataList = videoStorageMgr.videoSearch("hellob");
        assertFalse(foundVideoMetadataList.isEmpty());


        videoStorageMgr.streamInVideo((IVideoDescriptionMetadata)(foundVideoMetadataList.get(0)), this);
        System.out.println("videoChunkSentCount=" + videoChunkSentCount);
        assertEquals(videoChunkSentCount, 4);

        //fail("The test case is a prototype.");
    }

    public void sendVideoChunk(VideoChunk videoChunk)
    {
        videoChunkSentCount++;
    }


}