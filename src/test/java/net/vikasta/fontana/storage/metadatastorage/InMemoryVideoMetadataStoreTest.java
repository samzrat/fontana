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

import net.vikasta.fontana.storage.metadatastorage.*;
import static org.junit.Assert.*;
import net.vikasta.fontana.keywordcluster.*;

/**
 *
 * @author ratulmukh
 */
public class InMemoryVideoMetadataStoreTest {

    public InMemoryVideoMetadataStoreTest() {
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
    public void testStoreAndSearchVideoMetadata() {

        System.out.println("storeVideoMetadata");
        KeywordClusterMgr keywordClusterMgr = new KeywordClusterMgr();
        List<String> keywordCluster = keywordClusterMgr.createKeywordCluster("hello");
        IVideoDescriptionMetadata videoDescriptonMetadata = new InMemoryVideoMetadata();
        videoDescriptonMetadata.setKeywordCluster(keywordCluster);

        // /videoMetadataStore.storeVideoMetadata(iVideoMetadata);
        //IVideoMetadata videoMetadata = null;

        InMemoryVideoMetadataStore instance = new InMemoryVideoMetadataStore();
        instance.storeVideoMetadata(videoDescriptonMetadata); 
        List<IVideoMetadata> foundvideoMetadataList = instance.searchVideoMetadataStore("helloa"); 
        assertFalse(foundvideoMetadataList.isEmpty());

        foundvideoMetadataList = instance.searchVideoMetadataStore("hella"); 
        assertTrue(foundvideoMetadataList.isEmpty());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}