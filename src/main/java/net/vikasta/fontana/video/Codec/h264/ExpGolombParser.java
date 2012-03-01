/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.video.Codec.h264;

import net.vikasta.fontana.video.Codec.*;

/**
 *
 * @author ratulmukh
 */
public class ExpGolombParser {

    public int getSignedInteger(RawDataStream rawDataStream) throws NotEnoughBitsForExpGolumbParsingException
    {
        try
        {
            int residual = calculateResidual(rawDataStream);
                       
            if((Math.ceil(residual/2))*2==residual)
            {
                System.out.println("ExpGolumbParser::setSignedInteger residual is even");
                return -(residual/2);
            }
            else
            {
                System.out.println("ExpGolumbParser::setSignedInteger residual is odd");
                return (int)(Math.ceil(residual/2)+1);
            }
        } 
        catch (NotEnoughBitsForExpGolumbParsingException e)
        {

            throw e;

        }
            
            
    }
    
    public int getUnsignedInteger(RawDataStream videoStream) throws NotEnoughBitsForExpGolumbParsingException
    {
        try
        {
            return calculateResidual(videoStream);
        }
        catch (NotEnoughBitsForExpGolumbParsingException e)
        {

            throw e;

        }
    }
    private int calculateResidual(RawDataStream videoStream) throws NotEnoughBitsForExpGolumbParsingException
    {
        int zeroCount = 0;
        boolean readBit;
        while(true)
        {
            int residual =0;
            try
            {
                readBit = videoStream.readBit();
                System.out.println("ExpGolumbParser::GetUnsignedInteger 1st readBit " + readBit);
                if(readBit==false)
                {
                    zeroCount++;

                }
                else
                {
                    residual += Math.pow(2, zeroCount);
                    for(int i =zeroCount; i>0; i--)
                    {
                        readBit = videoStream.readBit();
                        System.out.println("ExpGolumbParser::GetUnsignedInteger 2nd readBit " + readBit);
                        if(readBit==true)
                            residual +=Math.pow(2, i-1);
                        
                    }

                    residual--;
                    System.out.println("ExpGolumbParser::GetUnsignedInteger residual " + residual);
                    return residual;

                }

            }
            catch (EndOfVideoStreamException e)
            {
                throw new NotEnoughBitsForExpGolumbParsingException();
            }
            

        }


        

    }

}
