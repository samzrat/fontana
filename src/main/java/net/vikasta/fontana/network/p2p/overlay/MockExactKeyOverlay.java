/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.network.p2p.overlay;

import java.net.InetAddress;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import net.vikasta.fontana.common.Utility;
import net.vikasta.fontana.network.MessageBus;
/**
 *
 * @author ratulmukh
 */
public class MockExactKeyOverlay<Data> implements IExactKeyOverlay<Data>, IOverlayBootstrapDiscovery {

    private Dictionary<String, Data> registry = new Hashtable<String, Data>();
    private List<InetAddress> bootstrapNodes;
    private boolean joinedOverlay = false;
    MessageBus messageBus;
    InetAddress storageIP;
    static Integer mockExactKeyOverlayInSystemCount =0;
    String overlayID;

    public MockExactKeyOverlay(List<InetAddress> bootstrapNodes, boolean joinOverlay, MessageBus messageBus)
    {
        synchronized(this)
        {
            mockExactKeyOverlayInSystemCount++;
        }

        storageIP = Utility.getRandomIP();
        this.messageBus = messageBus;
        overlayID = "MockExactKeyOverlay" + mockExactKeyOverlayInSystemCount.toString();
        System.out.println("New MockExactKeyOverlay created with ID " + overlayID);
        //messageBus.registerCallback(overlayID, this);

        this.bootstrapNodes = bootstrapNodes;


        if(joinOverlay)
            join(bootstrapNodes);


    }

    public void messageReceivedCallback(String msg, InetAddress sourceIP)
    {

    }

    public MockExactKeyOverlay()
    {
        joinedOverlay = false;
    }

    private void join (List<InetAddress> bootstrapNodes)
    {
        joinedOverlay = true;
    }

    public void addBootstrapNodes(List<InetAddress> bootstrapNodes)
    {
        Iterator<InetAddress> bootstrapNodesIterator = bootstrapNodes.iterator();
        while(bootstrapNodesIterator.hasNext())
            this.bootstrapNodes.add(bootstrapNodesIterator.next());
    }

    public void addBootstrapNode(InetAddress bootstrapNode)
    {
        this.bootstrapNodes.add(bootstrapNode);
    }

    public List<InetAddress> getBootstrapNodes(int maxBootstrapNodesCount)
    {
        return null;
    }

    public void store(String key, Data data)
    {
//        try
//        {
//            messageBus.sendMessage(overlayID, "PUT"+key+data.toString(), storageIP);
//            //registry.put(key, data);
//        }
//        catch(InvalidMsgSourceException e)
//        {
//            assert(false);
//        }
    }
    
    public Data retrieve(String key)
    {
//        try
//        {
//            messageBus.sendMessage(overlayID, "GET"+key, storageIP);
//            //return registry.get(key);
//        }
//        catch(InvalidMsgSourceException e)
//        {
//            assert(false);
//        }
                return null;
    }
}
