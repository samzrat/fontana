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
import java.util.SortedMap;
import java.util.UUID;

import net.vikasta.fontana.common.IStreamConsumer;
import net.vikasta.fontana.network.Address;
import net.vikasta.fontana.network.IMessageBus.MsgDeliveryFailureReason;
import net.vikasta.fontana.network.IMessageBusClient;
import net.vikasta.fontana.network.MessageBus;
import net.vikasta.fontana.network.MessageBusMsg;

/**
 *
 * @author ratulmukh
 */
public class MockStreamingExactKeyOverlay<Data> implements IOverlayBootstrapDiscovery, IStreamingExactKeyOverlay<Data>, IMessageBusClient {

    private Dictionary<String, SortedMap<Integer, Data>> streamRegistry = new Hashtable<String, SortedMap<Integer,Data>>();
    private List<InetAddress> bootstrapNodes;
    private boolean joinedOverlay = false;
    private MessageBus messageBus;
    private String messageBusClientID;


    public MockStreamingExactKeyOverlay(List<InetAddress> bootstrapNodes, boolean joinOverlay, MessageBus messageBus)
    {
        this.messageBus = messageBus;
        messageBusClientID = UUID.randomUUID().toString();
//        try {
//            messageBus.register(messageBusClientID, this);
//        }
//        catch(DuplicateCallbackIDException e)
//        {
//            try {
//                messageBusClientID = UUID.randomUUID().toString();
//                messageBus.register(messageBusClientID, this);
//            }
//            catch(DuplicateCallbackIDException e1)
//            {
//                assert(false);
//            }
//        }

        this.bootstrapNodes = bootstrapNodes;
        if(joinOverlay)
            join(bootstrapNodes);

    }

    public void notifyLoslessSendAckReceived()
    {

    }
    public void notifyMessageReceived(MessageBusMsg msg)
    {
        String[] tokens = msg.msg.split("\\r");
        String command = tokens[0];
        String status = tokens[1];
        String streamRequestID = tokens[2];

        if(command.compareTo("RETURN")==0)
            if(status.compareTo("VALUE_FOUND")==0)
            {
                StreamRequest streamRequest = streamRequestRegistry.get(streamRequestID);
                if(streamRequest!=null)
                {
                    String streamPosition = tokens[3];
                    String streamElement = tokens[4];
                    streamRequest.streamConsumer.deliver((Data)streamElement, Integer.parseInt(streamPosition), streamRequest.callerState);
                }
                else
                    assert(false);
            }
            else
                assert(false);
        else
            assert(false);
        
    }


    private MockStreamingExactKeyOverlay()
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

    public void storeStreamSegment(String key, Data data, int streamPosition)
    {
//        SortedMap<Integer, Data> stream = streamRegistry.get(key);
//        if(stream == null)
//        {
//            stream = new TreeMap<Integer, Data>();
//            streamRegistry.put(key, stream);
//        }
//        stream.put(streamPosition, data);
//        try
//        {
//            messageBus.sendMessage(messageBusClientID, Utility.getSimStoreID(), "PUT"+'\r'+key+'\r'+data.toString()+'\r'+streamPosition, Utility.getLocalLoopIP());
//        }
//        catch(InvalidSourceException e)
//        {
//            assert(false);
//        }
    }

    private Dictionary<String, StreamRequest> streamRequestRegistry = new Hashtable<String, StreamRequest>();

    public class StreamRequest
    {
        public IStreamConsumer<Data> streamConsumer;
        public Object callerState;
    }


    public void startStreamingIn(String key, IStreamConsumer<Data> streamConsumer, Object callerState)
    {
//        try
//        {
//            StreamRequest streamRequest = new StreamRequest();
//            streamRequest.streamConsumer = streamConsumer;
//            streamRequest.callerState = callerState;
//            String streamRequestID = UUID.randomUUID().toString();
//            streamRequestRegistry.put(streamRequestID, streamRequest);
//            messageBus.sendMessage(messageBusClientID, Utility.getSimStoreID(), "GET"+'\r'+key+'\r'+streamRequestID, Utility.getLocalLoopIP());
//        }
//        catch (InvalidSourceException e)
//        {
//            assert(false);
//        }
    }

    public Data retrieve(String key, int streamPosition)
    {
        return streamRegistry.get(key).get(streamPosition);

    }

	@Override
	public void msgDeliveryFailureCallback(
			MsgDeliveryFailureReason msgDeliveryFailureReason,
			Object clientState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void responseReceivedCallback(String reply,
			Address responseSenderAddress, Object clientState) {
		// TODO Auto-generated method stub
		
	}


}
