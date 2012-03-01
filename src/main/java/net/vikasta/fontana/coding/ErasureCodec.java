/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.coding;

import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class ErasureCodec {
    public static List<String> code(String data, int TotalNoOfParts, int minNoOfPartsForDecoding)
    {
        List<String> codedData = new ArrayList<String>(TotalNoOfParts);
        for(int i=0; i<TotalNoOfParts; i++)
            codedData.add("erasure coded data-"+data+i);
        return codedData;

    }

    public static String decode(List<String> codedData)
    {
        return codedData.get(0).substring(19, codedData.get(0).length()-1);
    }

}
