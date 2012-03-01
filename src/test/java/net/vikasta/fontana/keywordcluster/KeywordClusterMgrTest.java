/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.keywordcluster;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.vikasta.fontana.keywordcluster.KeywordClusterMgr;
import static org.junit.Assert.*;
import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class KeywordClusterMgrTest {

    KeywordClusterMgr keywordClusterMgr = new KeywordClusterMgr();

    public KeywordClusterMgrTest() {
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
     * Test of KeywordClusterMgr method, of class KeywordClusterMgr.
     */
    @Test
    public void testKeywordClusterMgr() {
        System.out.println("KeywordClusterMgr");
        //KeywordClusterMgr instance = new KeywordClusterMgr();
        keywordClusterMgr.KeywordClusterMgr();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of createKeywordCluster method, of class KeywordClusterMgr.
     */
    @Test
    public void testCreateKeywordCluster_String() {
        System.out.println("createKeywordCluster");
        String keyword = "hello";
        //KeywordClusterMgr instance = new KeywordClusterMgr();
        List<String> cluster = keywordClusterMgr.createKeywordCluster(keyword);

            assertTrue( cluster.get(0).equals("hello"));
            assertTrue( cluster.get(1).equals("helloa"));
            assertTrue( cluster.get(2).equals("hellob"));
            assertTrue( cluster.get(3).equals("helloc"));

   }

    /**
     * Test of createKeywordCluster method, of class KeywordClusterMgr.
     */
    @Test
    public void testCreateKeywordCluster_List() {
        System.out.println("createKeywordCluster");
        List<String> keywordList = new ArrayList<String>(2);
        keywordList.add("hello");
        keywordList.add("animal");

        //KeywordClusterMgr instance = new KeywordClusterMgr();
        List<String> cluster = keywordClusterMgr.createKeywordCluster(keywordList);



            assertTrue( cluster.get(0).equals("hello"));
            assertTrue( cluster.get(1).equals("helloa"));
            assertTrue( cluster.get(2).equals("hellob"));
            assertTrue( cluster.get(3).equals("helloc"));

            assertTrue( cluster.get(4).equals("animal"));
            assertTrue( cluster.get(5).equals("animala"));
            assertTrue( cluster.get(6).equals("animalb"));
            assertTrue( cluster.get(7).equals("animalc"));

    }

}