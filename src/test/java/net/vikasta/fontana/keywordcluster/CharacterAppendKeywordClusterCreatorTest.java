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

import net.vikasta.fontana.keywordcluster.CharacterAppendKeywordClusterCreator;
import static org.junit.Assert.*;
import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class CharacterAppendKeywordClusterCreatorTest {

    CharacterAppendKeywordClusterCreator characterAppendKeywordClusterCreator = new CharacterAppendKeywordClusterCreator();

    
    public CharacterAppendKeywordClusterCreatorTest() {
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
     * Test of createKeywordCluster method, of class CharacterAppendKeywordClusterCreator.
     */
    @Test
    public void testCreateKeywordCluster_String() {
        System.out.println("createKeywordCluster");
        String keyword = "hello";
        List<String> cluster = characterAppendKeywordClusterCreator.createKeywordCluster(keyword);



            assertTrue( cluster.get(0).equals("hello"));
            assertTrue( cluster.get(1).equals("helloa"));
            assertTrue( cluster.get(2).equals("hellob"));
            assertTrue( cluster.get(3).equals("helloc"));

    }
    /**
     * Test of createKeywordCluster method, of class CharacterAppendKeywordClusterCreator.
     */
    @Test
    public void testCreateKeywordCluster_List() {
        System.out.println("createKeywordCluster");
        List<String> keywordList = new ArrayList<String>(2);
        keywordList.add("hello");
        keywordList.add("animal");

         List<String> cluster = characterAppendKeywordClusterCreator.createKeywordCluster(keywordList);



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