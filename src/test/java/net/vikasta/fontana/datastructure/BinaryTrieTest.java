/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.datastructure;

import net.vikasta.fontana.common.Pair;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.vikasta.fontana.datastructure.BinaryTrie;
import static org.junit.Assert.*;

import java.util.*;

/**
 *
 * @author ratulmukh
 */
public class BinaryTrieTest {

    public BinaryTrieTest() {
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
     * Test of find method, of class BinaryTrie.
     */
    @Test
    public void testFind() {
        System.out.println("testFind");

        List<Pair<String, Object>> sequenceKeyValuePairList = new ArrayList<Pair<String, Object>>(4);
        
        Pair<String, Object> sequenceKeyValuePair;
        sequenceKeyValuePair = new Pair<String, Object>("000", this);
        sequenceKeyValuePairList.add(sequenceKeyValuePair);
        sequenceKeyValuePair = new Pair<String, Object>("001", this);
        sequenceKeyValuePairList.add(sequenceKeyValuePair);
        sequenceKeyValuePair = new Pair<String, Object>("1", this);
        sequenceKeyValuePairList.add(sequenceKeyValuePair);
        sequenceKeyValuePair = new Pair<String, Object>("111001", this);
        sequenceKeyValuePairList.add(sequenceKeyValuePair);
                
        BinaryTrie binaryTrie = new BinaryTrie(sequenceKeyValuePairList);

        Object result = binaryTrie.find("000");
        assertNotNull(result);
        result = binaryTrie.find("001");
        assertNotNull(result);
        result = binaryTrie.find("1");
        assertNotNull(result);
        result = binaryTrie.find("111001");
        assertNotNull(result);

        result = binaryTrie.find("0");
        assertNull(result);
        result = binaryTrie.find("010");
        assertNull(result);
        result = binaryTrie.find("111");
        assertNull(result);
        result = binaryTrie.find("0001");
        assertNull(result);

    }

}