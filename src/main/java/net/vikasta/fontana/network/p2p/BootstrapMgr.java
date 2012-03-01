/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.network.p2p;

import java.util.*;
import java.net.*;
/**
 *
 * @author ratulmukh
 */
public class BootstrapMgr {
    private String bootstrapWebsite;
    private String bootstrapFile;

    public BootstrapMgr(String bootstrapWebsite, String bootstrapFile)
    {
        System.out.println("Creating BootstrapMgr");
        this.bootstrapFile = bootstrapFile;
        this.bootstrapWebsite = bootstrapWebsite;
    }
    
    private List<InetAddress> getBootstrapNodesFromFile(String overlayID)
    {
        return null;
    }

    private List<InetAddress> getBootstrapNodesFromWebsite(String overlayID)
    {
        return null;
    }

    public List<InetAddress> getBootstrapNodes(String overlayID)
    {
        List<InetAddress> bootstrapnodes = getBootstrapNodesFromFile(overlayID);
        if(bootstrapnodes == null)
            bootstrapnodes = getBootstrapNodesFromWebsite(overlayID);
        return bootstrapnodes;
    }

}
