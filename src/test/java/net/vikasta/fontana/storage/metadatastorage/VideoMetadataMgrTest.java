/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.metadatastorage;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.vikasta.fontana.storage.metadatastorage.IVideoMetadata;
import net.vikasta.fontana.storage.metadatastorage.InMemoryVideoMetadata;
import net.vikasta.fontana.storage.metadatastorage.VideoMetadataMgr;
import static org.junit.Assert.*;
import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class VideoMetadataMgrTest {

    public VideoMetadataMgrTest() {
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
     * Test of createAndStoreVideoMetadata method, of class VideoMetadataMgr.
     */
    @Test
    public void testCreateStoreAndSearchVideoMetadata() {
        System.out.println("createAndStoreVideoMetadata");

        IVideoMetadata iVideoMetadata = new InMemoryVideoMetadata();
        List<String> tags = new ArrayList<String>(1);
        tags.add("hello");
        VideoMetadataMgr instance = new VideoMetadataMgr(null, 0);
        IVideoMetadata result = instance.createAndStoreVideoMetadata(iVideoMetadata, tags);

        List<IVideoMetadata> foundMetadata = instance.searchVideoMetadataStore("helloc");
        assertFalse(foundMetadata.isEmpty());

        foundMetadata = instance.searchVideoMetadataStore("hellc");
        assertTrue(foundMetadata.isEmpty());

        
    }

}