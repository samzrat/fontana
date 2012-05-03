/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.simulation;


import net.vikasta.fontana.core.*;
import net.vikasta.fontana.network.*;
import net.vikasta.fontana.common.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.security.*;

/**
 *
 * @author ratulmukh
 */
public class SystemSimulator {

    public static void main(String[] args)
    {

        SystemSimulator simulator = new SystemSimulator();
    }

    Dictionary<String, SortedMap<Integer, String>> simStore = new Hashtable<String, SortedMap<Integer, String>>();

    BlockingQueue<MessageBusMsg> simStoreIncomingMsgQueue = new LinkedBlockingQueue<MessageBusMsg>();
    BlockingQueue<MessageBusMsg> simStoreOutgoingMsgQueue = new LinkedBlockingQueue<MessageBusMsg>();
    MessageBus simStoreMessageBus;

    List<BlockingQueue<MessageBusMsg>> incomingMsgQueueList = new ArrayList<BlockingQueue<MessageBusMsg>>();
    List<BlockingQueue<MessageBusMsg>> outgoingMsgQueueList = new ArrayList<BlockingQueue<MessageBusMsg>>();

    int threadInitializationCounter = -1;

    private SystemSimulator()
    {
  
            InetAddress simStoreIP = Utility.getRandomIP();
            //simStoreMessageBus = new MessageBus(simStoreIncomingMsgQueue, simStoreOutgoingMsgQueue, simStoreIP);

             Runnable task = new Runnable() {
                    public void run() {
                        startSimStoreThread();
                    }
                };
                new Thread(task).start();


            for(int i=0; i<pygmyVirtualNodeCount; i++)
            {
                BlockingQueue<MessageBusMsg> incomingMsgQueue = new LinkedBlockingQueue<MessageBusMsg>();
                BlockingQueue<MessageBusMsg> outgoingMsgQueue = new LinkedBlockingQueue<MessageBusMsg>();

                incomingMsgQueueList.add(incomingMsgQueue);
                outgoingMsgQueueList.add(outgoingMsgQueue);



            }


            for(int i=0; i<pygmyVirtualNodeCount; i++)
            {
                task = new Runnable() {
                    public void run() {
                        setuppygmyVirtualNode();
                    }
                };
                new Thread(task).start();
            }


            for(int i=0; i<pygmyVirtualNodeCount; i++)
            {
                InetAddress IP = Utility.getRandomIP();
                pygmyMgrRegistry.put(IP, incomingMsgQueueList.get(i));
//                MessageBus messageBus = new MessageBus(incomingMsgQueueList.get(i), outgoingMsgQueueList.get(i), IP);
//                pygmyMgr pygmyMgr = new pygmyMgr(messageBus);
            }

           
            


    }

    private void startSimStoreThread()
    {
        //This thread only reads incoming msgs for the simStore
        System.out.println("Starting Sim store thread");
        Thread.currentThread().setName("SimStoreThread");


        try
        {
            while(true)
            {
               MessageBusMsg msg = simStoreIncomingMsgQueue.take();

               String[] tokens = msg.msg.split("\\r");
               String command = tokens[0];
               String key = tokens[1];
               
               if(command.compareTo("PUT")==0)
               {
                   String data = tokens[2];
                   String streamPosition = tokens[3];
                   SortedMap<Integer, String> stream = simStore.get(key);
                   if(stream==null)
                   {
                        stream = new TreeMap<Integer, String>();
                        simStore.put(key, stream);
                   }
                   stream.put(new Integer(streamPosition), data);
               }
               else if(command.compareTo("GET")==0)
               {
                    String appData = tokens[2];
                    SortedMap<Integer, String> stream = simStore.get(key);
                    BlockingQueue<MessageBusMsg> pygmyNodeIncomingMsgQueue = pygmyMgrRegistry.get(msg.sourceIP);
                    if(pygmyNodeIncomingMsgQueue != null)
                    {
                        MessageBusMsg returnMsg;
                        if(stream!=null)
                        {
                            Collection<String> streamCollection = stream.values();
                            Iterator<String> streamCollectionIterator = streamCollection.iterator();
                            int i=0;
                            while(streamCollectionIterator.hasNext())
                            {
                                returnMsg = new MessageBusMsg("RETURN"+'\r'+"VALUE_FOUND"+'\r'+appData+'\r'+i+'\r'+streamCollectionIterator.next(), Utility.getSimStoreID(), msg.senderComponentID, msg.destinationIP, msg.sourceIP);
                                pygmyNodeIncomingMsgQueue.put(returnMsg);
                            }
                        }
                        else
                        {
                            returnMsg = new MessageBusMsg("RETURN"+'\r'+"VALUE_NOT_FOUND"+'\r'+appData, Utility.getSimStoreID(), msg.senderComponentID, msg.destinationIP, msg.sourceIP);
                            pygmyNodeIncomingMsgQueue.put(returnMsg);
                        }
                    }
                    else
                        assert(false);
               }
               else
                   assert(false);
            }
        }
        catch(InterruptedException e)
        {
            assert(false);
        }

    }

        private void setuppygmyVirtualNode()
        {
            System.out.println("Setting up pygmy virtual nodes");
            //BlockingQueue<MessageBusMsg> incomingMsgQueue = new LinkedBlockingQueue<MessageBusMsg>();
            //BlockingQueue<MessageBusMsg> outgoingMsgQueue = new LinkedBlockingQueue<MessageBusMsg>();
//            InetAddress IP = Utility.getRandomIP();
//            MessageBus messageBus = new MessageBus(incomingMsgQueue, outgoingMsgQueue, IP);
//            pygmyMgr pygmyMgr = new pygmyMgr(messageBus);

            //pygmyMgrRegistry.put(IP, incomingMsgQueue);

            Thread.currentThread().setName("pygmyVirtualNode OutgoingMsgQueueReader thread");

            BlockingQueue<MessageBusMsg> outgoingMsgQueue;
            synchronized(this)
            {
                threadInitializationCounter++;
                outgoingMsgQueue = outgoingMsgQueueList.get(threadInitializationCounter);
            }
            
            try
            {
                while(true)
                {
                    MessageBusMsg msg = outgoingMsgQueue.take();
                    //when messages go only to SimStore - special IP: 127.0.0.1
                    //if(msg.destinationIP.equals(Utility.getLocalLoopIP()))
                        simStoreIncomingMsgQueue.put(msg);

                    //when messages flow between virtual nodes - required when we have overlays ready
//                  BlockingQueue<MessageBusMsg> pygmyIncomingMsgQueue = pygmyMgrRegistry.get(msg.destinationIP);
//                      if(pygmyIncomingMsgQueue!=null)
//                          pygmyIncomingMsgQueue.put(msg);
                }
            }
            catch (InterruptedException e)
            {
                assert(false);
            }
        }

    private int pygmyVirtualNodeCount = 1;
    private FontanaMgr[] pygmyMgrList;
    private Dictionary<InetAddress, BlockingQueue<MessageBusMsg>> pygmyMgrRegistry = new Hashtable<InetAddress, BlockingQueue<MessageBusMsg>>();

}
