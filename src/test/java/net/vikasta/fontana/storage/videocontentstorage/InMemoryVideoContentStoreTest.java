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

import net.vikasta.fontana.storage.videocontentstorage.MemoryBasedVideoContentStore;
import static org.junit.Assert.*;
import net.vikasta.fontana.storage.metadatastorage.IVideoLocationMetadata;
import net.vikasta.fontana.storage.metadatastorage.IVideoMetadata;
import net.vikasta.fontana.video.*;

/**
 *
 * @author ratulmukh
 */
public class InMemoryVideoContentStoreTest implements IVideoSink {

    int videoChunkSentCount = 0;

    public InMemoryVideoContentStoreTest() {
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

    /**
     * Test of streamInVideo method, of class MemoryBasedVideoContentStore.
     */
//    @Test
//    public void testStreamInVideo() {
//        System.out.println("streamInVideo");
//        IVideoLocationMetadata videoContentLocationMetadata = null;
//        IVideoSink videoSink = null;
//        MemoryBasedVideoContentStore instance = new MemoryBasedVideoContentStore();
//        instance.streamInVideo(videoContentLocationMetadata, videoSink);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of storeVideo method, of class MemoryBasedVideoContentStore.
     */
//    @Test
//    public void testStoreVideo() {
//        System.out.println("storeVideo");
//        Video video = null;
//        MemoryBasedVideoContentStore instance = new MemoryBasedVideoContentStore();
//        IVideoMetadata expResult = null;
//        IVideoMetadata result = instance.storeVideo(video);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//

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
        
        MemoryBasedVideoContentStore inMemoryVideoContentStore = new MemoryBasedVideoContentStore();
        IVideoMetadata videoMetadata = inMemoryVideoContentStore.storeVideo(video);

        inMemoryVideoContentStore.streamInVideo((IVideoLocationMetadata)videoMetadata, this);
        System.out.println("videoChunkSentCount=" + videoChunkSentCount);
        assertEquals(videoChunkSentCount, 4);

        //fail("The test case is a prototype.");
    }

    public void sendVideoChunk(VideoChunk videoChunk)
    {
        videoChunkSentCount++;
    }
}