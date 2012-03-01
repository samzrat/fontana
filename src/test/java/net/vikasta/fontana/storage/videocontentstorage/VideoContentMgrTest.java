/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.videocontentstorage;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import net.vikasta.fontana.storage.metadatastorage.IVideoLocationMetadata;
import net.vikasta.fontana.storage.metadatastorage.IVideoMetadata;
import net.vikasta.fontana.video.IVideoSink;
import net.vikasta.fontana.video.*;

/**
 *
 * @author ratulmukh
 */
public class VideoContentMgrTest implements IVideoSink {

    int videoChunkSentCount;

    public VideoContentMgrTest() {
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

//        VideoMgr videoContentMgr = new VideoMgr();
//        IVideoMetadata videoMetadata = videoContentMgr.storeVideo(video);
//
//        videoContentMgr.streamInVideo((IVideoLocationMetadata)videoMetadata, this);
//        System.out.println("videoChunkSentCount=" + videoChunkSentCount);
//        assertEquals(videoChunkSentCount, 4);

        //fail("The test case is a prototype.");
    }

    public void sendVideoChunk(VideoChunk videoChunk)
    {
        videoChunkSentCount++;
    }



}