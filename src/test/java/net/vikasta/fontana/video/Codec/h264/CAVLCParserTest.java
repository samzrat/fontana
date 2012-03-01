/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.video.Codec.h264;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.vikasta.fontana.video.Codec.h264.CAVLCParser;
import static org.junit.Assert.*;
import net.vikasta.fontana.common.Pair;
import net.vikasta.fontana.video.Codec.*;

/**
 *
 * @author ratulmukh
 */
public class CAVLCParserTest {

    public CAVLCParserTest() {
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
    public void testGetCoeffToken_FirstTable_PositiveCases()
    {
        System.out.println("testGetCoeffToken_FirstTable_PositiveCases");

        try
        {
            byte[] b = new byte[2];
            RawDataStream videoStream;
            Pair<Integer, Integer> result;

            //CoeffToken = 1
            b[0] = (byte)0x80;
            videoStream = new RawDataStream(b, 1);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 0);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 0);
            assertEquals(result.second.intValue(), 0);

            //CoeffToken = 000101
            b[0] = 0x14;   //"000101"
            videoStream = new RawDataStream(b, 1);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 0);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 0);
            assertEquals(result.second.intValue(), 1);

            //CoeffToken = 000000111
            b[0] = 0x03;         //"00000011"
            b[1] = (byte)0x80;   //"10000000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 0);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 0);
            assertEquals(result.second.intValue(), 3);

            //CoeffToken = 00000000101
            b[0] = 0x00;          //"00000000"
            b[1] = (byte)0xA0;   //"10100000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 0);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 2);
            assertEquals(result.second.intValue(), 7);

            //CoeffToken = 000000000000001
            b[0] = 0x00;   //"00000000"
            b[1] = 0x02;   //"00000010"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 0);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 1);
            assertEquals(result.second.intValue(), 13);

            //CoeffToken = 0000000000001000
            b[0] = 0x00;   //"00000000"
            b[1] = 0x08;   //"00001000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 0);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 3);
            assertEquals(result.second.intValue(), 16);



        }
        catch (InvalidVideoStreamException e1)
        {
            fail();
        }
        catch(EndOfVideoStreamException e2)
        {
            fail();
        }

    }



    @Test
    public void testGetCoeffToken_FirstTable_NegativeCases()
    {
        System.out.println("testGetCoeffToken_FirstTable_NegativeCases");

                    try
        {
            byte[] b = new byte[2];
            b[0] = 0x00;
            b[1] = 0x00;
            RawDataStream videoStream;
            Pair<Integer, Integer> result = null;


        //CoeffToken = 0000000001000
            b[0] = (byte)0x00;         //"00000000"
            b[1] = (byte)0x00;   //"00000000"
            videoStream = new RawDataStream(b, 2);
        result = CAVLCParser.getCoeffTokenMaping(videoStream, 1);
        assertNull(result);


        }
        catch (InvalidVideoStreamException e1)
        {

        }
        catch(EndOfVideoStreamException e2)
        {
            fail();

        }
        

    }


    @Test
    public void testGetCoeffToken_SecondTable_PositiveCases()
    {
        System.out.println("testGetCoeffToken_SecondTable_PositiveCases");

        try
        {
            byte[] b = new byte[2];
            RawDataStream videoStream;
            Pair<Integer, Integer> result;

            //CoeffToken = 000111
            b[0] = 0x1C;   //"00011100"
            videoStream = new RawDataStream(b, 1);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 2);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 0);
            assertEquals(result.second.intValue(), 2);

            //CoeffToken = 0100
            b[0] = 0x40;   //"01000000"
            videoStream = new RawDataStream(b, 1);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 3);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 3);
            assertEquals(result.second.intValue(), 4);

            //CoeffToken = 000100
            b[0] = 0x10;   //"00010000"
            videoStream = new RawDataStream(b, 1);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 2);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 3);
            assertEquals(result.second.intValue(), 7);

            //CoeffToken = 000000001111
            b[0] = 0x00;   //"00000000"
            b[1] = (byte)0xF0;   //"11110000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 2);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 0);
            assertEquals(result.second.intValue(), 9);

            //CoeffToken = 0000000001000
            b[0] = 0x00;   //"00000000"
            b[1] = 0x40;   //"01000000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 3);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 3);
            assertEquals(result.second.intValue(), 14);

            //CoeffToken = 00000000000100
            b[0] = 0x00;   //"00000000"
            b[1] = 0x10;   //"00010000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 3);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 3);
            assertEquals(result.second.intValue(), 16);


        }
        catch (InvalidVideoStreamException e1)
        {
            fail();
        }
        catch(EndOfVideoStreamException e2)
        {
            fail();
        }
     
    }



    @Test
    public void testGetCoeffToken_SecondTable_NegativeCases()
    {
        System.out.println("testGetCoeffToken_SecondTable_NegativeCases");

                try
        {
            byte[] b = new byte[2];
            b[0] = 0x00;
            b[1] = 0x00;
            RawDataStream videoStream;
            Pair<Integer, Integer> result = null;


        //CoeffToken = 0000000001000
            b[0] = (byte)0x00;         //"00000000"
            b[1] = (byte)0x00;   //"00000000"
            videoStream = new RawDataStream(b, 2);
        result = CAVLCParser.getCoeffTokenMaping(videoStream, 2);
        assertNull(result);


        }
        catch (InvalidVideoStreamException e1)
        {

        }
        catch(EndOfVideoStreamException e2)
        {
            fail();

        }
    }

    @Test
    public void testGetCoeffToken_ThirdTable_PositiveCases()
    {
        System.out.println("testGetCoeffToken_ThirdTable_PositiveCases");

        try
        {
            byte[] b = new byte[2];
            RawDataStream videoStream;
            Pair<Integer, Integer> result;

            //CoeffToken = 1111
            b[0] = (byte)0xF0;   //"11110000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 4);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 0);
            assertEquals(result.second.intValue(), 0);

            //CoeffToken = 1100
            b[0] = (byte)0xc0;   //"11000000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 4);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 3);
            assertEquals(result.second.intValue(), 3);

            //CoeffToken = 1010
            b[0] = (byte)0xA0;   // 10100000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 5);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 3);
            assertEquals(result.second.intValue(), 5);

            //CoeffToken = 00001001
            b[0] = 0x09;   //"00001001"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 6);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 2);
            assertEquals(result.second.intValue(), 11);

            //CoeffToken = 000000111
            b[0] = 0x03;   //"00000011"
            b[1] = (byte)0xF0;   //"10000000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 7);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 1);
            assertEquals(result.second.intValue(), 13);

            //CoeffToken = 0000000100
            b[0] = 0x01;   //"00000001"
            b[1] = 0x00;   //"00000000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 5);
            assertNotNull(result);
            assertEquals(result.first.intValue(), 1);
            assertEquals(result.second.intValue(), 16);


        }
        catch (InvalidVideoStreamException e1)
        {
            fail();
        }
        catch(EndOfVideoStreamException e2)
        {
            fail();
        }


    }


    @Test
    public void testGetCoeffToken_ThirdTable_NegativeCases()
    {
        System.out.println("testGetCoeffToken_ThirdTable_PositiveCases");

        try
        {
            byte[] b = new byte[2];
            b[0] = 0x00;
            b[1] = 0x00;
            RawDataStream videoStream;
            Pair<Integer, Integer> result = null;


            //CoeffToken = 00
            b[0] = (byte)0x00;   //"00000000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 4);
            assertNull(result);

            //CoeffToken = 00000000001100
            b[0] = 0x00;         //"00000000"
            b[1] = (byte)0x30;   //"00110000"
            videoStream = new RawDataStream(b, 2);
            result = CAVLCParser.getCoeffTokenMaping(videoStream, 4);
            assertNull(result);


        }
        catch (InvalidVideoStreamException e1)
        {
            
        }
        catch(EndOfVideoStreamException e2)
        {
            fail();

        }


    }

}