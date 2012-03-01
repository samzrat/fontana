/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.video.Codec.h264;

import net.vikasta.fontana.common.Pair;
import net.vikasta.fontana.datastructure.*;
import net.vikasta.fontana.video.Codec.*;
import java.util.*;
import java.io.*;


/**
 *
 * @author ratulmukh
 */
public class CAVLCParser {

    static public class CoeffTokenMap
    {
        public BinaryTrie binaryTrie;
        public int nC_Min;
        public int nC_Max;

        public CoeffTokenMap()
        {


        }
    }

         
    private static final String  coeffToken_file[] = {"src\\pygmy\\Video\\Codec\\H264\\CoeffToken_Mapping_nC_0_1.dat", "src\\pygmy\\Video\\Codec\\H264\\CoeffToken_Mapping_nC_2_3.dat", "src\\pygmy\\Video\\Codec\\H264\\CoeffToken_Mapping_nC_4_7.dat"};
    private static final int noOfCoeffTokenFiles = 3;
    private static final List<CoeffTokenMap> coeffTokenMapList = new ArrayList<CoeffTokenMap>(noOfCoeffTokenFiles);
    private static final int coefftokenListLength = 62;
    private static final List<Pair<Integer, Integer>> nC_rangeList = new ArrayList<Pair<Integer, Integer>>(noOfCoeffTokenFiles);
     static
     {
        init();
     }


     private static void addCoeffToken_cN_range()
     {
         nC_rangeList.add(new Pair<Integer, Integer>(0, 1));
         nC_rangeList.add(new Pair<Integer, Integer>(2, 3));
         nC_rangeList.add(new Pair<Integer, Integer>(4, 7));
     }

     private static void readCoeffTokens(String coeffToken_file, List<Pair<String, Object>> sequenceKeyValuePairList)
     {
         try
         {
            Scanner scanner = new Scanner(new File(coeffToken_file));

            while (scanner.hasNext())
            {
                String trailingOnes = scanner.next();
                String totalCoeff = scanner.next();
                String coeffToken = scanner.next();

                Pair<Integer, Integer> coeffData = new Pair<Integer, Integer>(Integer.valueOf(trailingOnes), Integer.valueOf(totalCoeff));
                Pair<String, Object> sequenceKeyValuePair = new Pair<String, Object>(coeffToken, coeffData);
                sequenceKeyValuePairList.add(sequenceKeyValuePair);
            }
         }
         catch (FileNotFoundException e)
         {
             assert(false);
         }
     }

     private static void init()
     {
        addCoeffToken_cN_range();

        for(int i=0; i<noOfCoeffTokenFiles; i++)
        {
            List<Pair<String, Object>> sequenceKeyValuePairList = new ArrayList<Pair<String, Object>>(coefftokenListLength);
            readCoeffTokens(coeffToken_file[i], sequenceKeyValuePairList);
            addTocoeffTokenMapList(i, sequenceKeyValuePairList);
        }
    }

    private static void  addTocoeffTokenMapList(int coeffTokenMapIndex, List<Pair<String, Object>> sequenceKeyValuePairList)
    {
        CoeffTokenMap coeffTokenMap = new CoeffTokenMap();

        coeffTokenMap.binaryTrie = new BinaryTrie(sequenceKeyValuePairList);
        coeffTokenMap.nC_Min = nC_rangeList.get(coeffTokenMapIndex).first;
        coeffTokenMap.nC_Max = nC_rangeList.get(coeffTokenMapIndex).second;

        coeffTokenMapList.add(coeffTokenMap);
    }

    public static Pair<Integer, Integer> getCoeffTokenMaping(RawDataStream rawDataStream, int nC) throws EndOfVideoStreamException, InvalidVideoStreamException
    {
        Pair<Integer, Integer> coeffData = null;
        Iterator<CoeffTokenMap> coeffTokenMapIterator = coeffTokenMapList.iterator();
        while (coeffTokenMapIterator.hasNext())
        {
            CoeffTokenMap coeffTokenMap = coeffTokenMapIterator.next();
            if(nC>=coeffTokenMap.nC_Min && nC<=coeffTokenMap.nC_Max)
            {
                String sequence = new String("");
                while (coeffData==null && sequence.length()<16)
                {
                    try
                    {
                        if(rawDataStream.readBit())
                            sequence = sequence.concat("1");
                        else
                            sequence = sequence.concat("0");
                        coeffData =  (Pair<Integer, Integer>) (coeffTokenMap.binaryTrie.find(sequence));
                    }
                    catch(EndOfVideoStreamException e)
                    {
                        throw e;

                    }
                }
                if(coeffData != null)
                    return coeffData;
                else
                    throw new InvalidVideoStreamException();
            }

        }
        return null;

    }

    public static Pair<Integer, Integer> getCoeffTokenMaping(String bitSequence, int nC)
    {
        Iterator<CoeffTokenMap> coeffTokenMapIterator = coeffTokenMapList.iterator();
        while (coeffTokenMapIterator.hasNext())
        {
            CoeffTokenMap coeffTokenMap = coeffTokenMapIterator.next();
            if(nC>=coeffTokenMap.nC_Min && nC<=coeffTokenMap.nC_Max)
            {
                return (Pair<Integer, Integer>)(coeffTokenMap.binaryTrie.find(bitSequence));
            }

        }
        return null;


        
    }

}


    





