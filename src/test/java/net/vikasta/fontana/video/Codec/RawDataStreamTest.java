/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.video.Codec;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.vikasta.fontana.video.Codec.EndOfVideoStreamException;
import net.vikasta.fontana.video.Codec.RawDataStream;
import static org.junit.Assert.*;

/**
 *
 * @author ratulmukh
 */
public class RawDataStreamTest {

    public RawDataStreamTest() {
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
     * Test of readBit method, of class RawDataStream.
     */


    @Test
    public void testReadBit_EndOfSteamException() {
        System.out.println("testRead8Bits");

        byte[] b = new byte[1];
        RawDataStream instance = new RawDataStream(b, 1);
        try
        {
            for(int i =0; i<8;i++)
                instance.readBit();
        }
        catch(EndOfVideoStreamException e)
        {
            fail();

        }

        try
        {
            instance.readBit();
            fail();
        }
        catch(EndOfVideoStreamException e)
        {
            

        }
        

    }

    @Test
    public void testReadBit_Read8Bits() {
        System.out.println("testRead8Bits");

        byte[] b = new byte[1];
        b[0] = 0x2D;   //b00101101
        RawDataStream instance = new RawDataStream(b, 1);
        //boolean expResult = false;
        try
        {
            boolean result = instance.readBit();
            assertEquals(result, false);

            result = instance.readBit();
            assertEquals(result, false);

            result = instance.readBit();
            assertEquals(result, true);

            result = instance.readBit();
            assertEquals(result, false);

            result = instance.readBit();
            assertEquals(result, true);

            result = instance.readBit();
            assertEquals(result, true);

            result = instance.readBit();
            assertEquals(result, false);

            result = instance.readBit();
            assertEquals(result, true);
        }
        catch (EndOfVideoStreamException e)
        {
            fail();

        }

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

        @Test
    public void testReadBit_ReadMoreThan8Bits() {
        System.out.println("testRead13Bits");

        byte[] b = new byte[2];
        b[0] = 0x2D;   //b00101101
        b[1] = 0x2d;
        RawDataStream instance = new RawDataStream(b, 2);
        //boolean expResult = false;
        try
        {
            boolean result = instance.readBit();
            assertEquals(result, false);

            result = instance.readBit();
            assertEquals(result, false);

            result = instance.readBit();
            assertEquals(result, true);

            result = instance.readBit();
            assertEquals(result, false);

            result = instance.readBit();
            assertEquals(result, true);

            result = instance.readBit();
            assertEquals(result, true);

            result = instance.readBit();
            assertEquals(result, false);

            result = instance.readBit();
            assertEquals(result, true);

            result = instance.readBit();
            assertEquals(result, false);

            result = instance.readBit();
            assertEquals(result, false);

            result = instance.readBit();
            assertEquals(result, true);
        }
        catch (EndOfVideoStreamException e)
        {
            fail();
        }
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of returnBit method, of class RawDataStream.
     */
    @Test
    public void testReturnBit() {
        System.out.println("returnBit");
        boolean bitToBeReturned = false;
        RawDataStream instance = null;
        instance.returnBit(bitToBeReturned);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}