/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.video.Codec.h264;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.vikasta.fontana.video.Codec.h264.ExpGolombParser;
import net.vikasta.fontana.video.Codec.h264.NotEnoughBitsForExpGolumbParsingException;
import static org.junit.Assert.*;
import net.vikasta.fontana.video.Codec.RawDataStream;

/**
 *
 * @author ratulmukh
 */
public class ExpGolombParserTest {

    public ExpGolombParserTest() {
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
    public void testGetUnsignedInteger_Get1SignedInteger() {
        System.out.println("testGetUnsignedInteger_Get1SignedInteger");

        byte[] b = new byte[1];
        b[0] = 0x10;   //b0001000 0 - the last 0 is not part of the number, the rest before that is 7
        RawDataStream videoStream = new RawDataStream(b, 1);
        ExpGolombParser expGolombParser = new ExpGolombParser();

        try
        {
            int readUnsignedInt = expGolombParser.getUnsignedInteger(videoStream);
            assertEquals(readUnsignedInt, 7);
        }
        catch (NotEnoughBitsForExpGolumbParsingException e)
        {
            fail();
        }
    }

    @Test
    public void testGetUnsignedInteger_Get2SignedInteger() {
        System.out.println("testGetUnsignedInteger_Get2SignedInteger");

        byte[] b = new byte[2];
        //00111 0001010 0000 = 6 & 9 followed by 3 0s not part of the stram
        //00111000 10100000  = breaking up into 2 bytes
        //   38       A0     = hex representation of the 2 bytes
        b[0] = 0x38;
        b[1] = (byte)0xA0;
      
        RawDataStream videoStream = new RawDataStream(b, 2);
        ExpGolombParser expGolombParser = new ExpGolombParser();

        try
        {
            int readUnsignedInt = expGolombParser.getUnsignedInteger(videoStream);
            assertEquals(readUnsignedInt, 6);
      
            readUnsignedInt = expGolombParser.getUnsignedInteger(videoStream);
            assertEquals(readUnsignedInt, 9);
        }
        catch (NotEnoughBitsForExpGolumbParsingException e)
        {
            fail();
        }
       
    }
    

    @Test
    public void testGetSignedInteger_Get1SignedInteger() {
        System.out.println("testGetUnsignedInteger_Get1SignedInteger");

        byte[] b = new byte[1];
        b[0] = 0x10;   //b0001000 0 - the last 0 is not part of the number, the rest before that is 7
        RawDataStream videoStream = new RawDataStream(b, 1);
        ExpGolombParser expGolombParser = new ExpGolombParser();

        try
        {
            int readUnsignedInt = expGolombParser.getSignedInteger(videoStream);
            assertEquals(readUnsignedInt, 4);
        }
        catch (NotEnoughBitsForExpGolumbParsingException e)
        {
            fail();
        }
    }

    @Test
    public void testGetSignedInteger_Get2SignedInteger() {
        System.out.println("testGetUnsignedInteger_Get2SignedInteger");

        byte[] b = new byte[2];
        //00111 0001010 0000 = 6 & 9 followed by 3 0s not part of the stram
        //00111000 10100000  = breaking up into 2 bytes
        //   38       A0     = hex representation of the 2 bytes
        b[0] = 0x38;
        b[1] = (byte)0xA0;

        RawDataStream videoStream = new RawDataStream(b, 2);
        ExpGolombParser expGolombParser = new ExpGolombParser();

        try
        {
            int readUnsignedInt = expGolombParser.getSignedInteger(videoStream);
            assertEquals(readUnsignedInt, -3);

            readUnsignedInt = expGolombParser.getSignedInteger(videoStream);
            assertEquals(readUnsignedInt, 5);
        }
        catch (NotEnoughBitsForExpGolumbParsingException e)
        {
            fail();
        }

    }


}