/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.common;

import java.net.*;
import java.security.*;

/**
 *
 * @author ratulmukh
 */
public class Utility {

    static private SecureRandom secureRandom = new SecureRandom();
    
    static public InetAddress getRandomIP()
    {
        String strIP = new String("");
        for(int j=0;j<4;j++)
            strIP += Integer.toString(secureRandom.nextInt(255)) + ".";
        strIP = strIP.substring(0, strIP.length()-1);

        try
        {
                return InetAddress.getByName(strIP);
        }
        catch(UnknownHostException e)
        {
            System.out.println("Unknown host exception");
            assert(false);
        }
        return null;
    }

    static public InetAddress getLocalLoopIP()
    {
        String strIP = "127.0.0.1";

        try
        {
                return InetAddress.getByName(strIP);
        }
        catch(UnknownHostException e)
        {
            System.out.println("Unknown host exception");
            assert(false);
        }
        return null;
    }

    static public String getSimStoreID()
    {
        return "SimStoreID";
    }


}
