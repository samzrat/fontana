/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.datastructure;

import net.vikasta.fontana.common.Pair;
import java.util.*;

/**
 *
 * @author ratulmukh
 */
public class BinaryTrie
{
    class TrieNode
    {
        public TrieNode zeroChild = null;
        public TrieNode oneChild = null;
        public Object value = null;
        public boolean isLeafNode = true;   //true if zeroChild and oneChild are null

    }


    TrieNode root = new TrieNode();


    public BinaryTrie(List<Pair<String, Object>> sequenceValuePairList)
    {
        for(int i =0; i<sequenceValuePairList.size(); i++)
            addSequence(sequenceValuePairList.get(i).first, sequenceValuePairList.get(i).second);
   }

    private void addSequence(String sequenceKey, Object value )
    {
        TrieNode nodePtr = root;
        char charKey[] = sequenceKey.toCharArray();
        for(int i=0; i<charKey.length; i++)
        {
            if(charKey[i]=='0')
            {
                if(nodePtr.zeroChild==null)
                    nodePtr.zeroChild = new TrieNode();
                nodePtr = nodePtr.zeroChild;

            }
            else if(charKey[i]=='1')
            {
                if(nodePtr.oneChild==null)
                nodePtr.oneChild = new TrieNode();
                nodePtr = nodePtr.oneChild;
            }
            else
                assert(false);
        }
        nodePtr.value = value;

    }

    public Object find(String sequenceKey)
    {
        TrieNode nodePtr = root;
        char charKey[] = sequenceKey.toCharArray();
        //for(int i=0; i<charKey.length; i++)
        //{
            int i = 0;
            while(i<charKey.length && ((nodePtr.zeroChild!=null && charKey[i]=='0') || (nodePtr.oneChild!=null && charKey[i]=='1')))
            {
                nodePtr = charKey[i]=='0'?nodePtr.zeroChild:nodePtr.oneChild;
                i++;

            }
            if(i<charKey.length)
                return null;
            return nodePtr.value;



        //}
    }

}
