/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.video.Codec;

/**
 *
 * @author ratulmukh
 */
public class RawDataStream {

    byte[] memorySource;
    int bytePointer;
    int bitPointer;
    int streamLengthInBytes;
    boolean endOfStreamReached;

    public RawDataStream(byte[] memorySource, int noOfBytes)
    {
        this.memorySource = memorySource;
        this.streamLengthInBytes = noOfBytes;
        bytePointer = 0;
        bitPointer = 1;
        endOfStreamReached = false;

    }

//    RawDataStream(fileSource)
//    {
//
//    }

    public boolean readBit() throws EndOfVideoStreamException
    {
        if(endOfStreamReached)
            throw new EndOfVideoStreamException();

        byte selectedByte = memorySource[bytePointer];
        byte selectedBit = (byte)(selectedByte >> (8-bitPointer));
        bitPointer++;
        if(bitPointer==9)
        {
            bitPointer = 1;
            bytePointer++;
            if(bytePointer==streamLengthInBytes)
                endOfStreamReached = true;
        }
        selectedBit &= 0x1;
        if(selectedBit == 0x01)
            return true;
        else
            return false;

    }

    public void returnBit(boolean bitToBeReturned)
    {

    }




}
