/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.network;

import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.lang.*;
import java.security.*;
import net.vikasta.fontana.common.*;
/**
 *
 * @author ratulmukh
 */
public class MessageBus implements IMessageBus {

	public class MessageBusClientID {
		public final InetAddress systemIP;
		public final String UID;
		
		public MessageBusClientID(InetAddress systemIP, String UID)
		{
			this.systemIP = systemIP;
			this.UID = UID;
		}

	}
	
    private Hashtable<MessageBusClientID, IMessageBusClient> clientRegistry = new Hashtable<MessageBusClientID, IMessageBusClient>();
    private SecureRandom secureRandom = new SecureRandom();

    public void register(InetAddress IP, String clientID, IMessageBusClient callback) throws DuplicateCallbackIDException
    {

        if(clientRegistry.put(new MessageBusClientID(IP, clientID), callback) != null)
            throw new DuplicateCallbackIDException();
    }

    public void deregister(InetAddress IP, String clientID)
    {
        clientRegistry.remove(new MessageBusClientID(IP, clientID));
    
    }
//    
//    public void sendMessageInLosslessChannel(MessageBusMsg msg, IMessageBusClient sender, Object senderState) throws InvalidSourceException, InvalidReceiverException
//    {
//        getReceiver(msg).notifyMessageReceived(msg);
//
//        IMessageBusClient client = clientRegistry.get(new MessageBusClientID(msg.sourceIP, msg.senderComponentID));
//        client.notifyLoslessSendAckReceived();
//        
//    }
//
//    public void sendMessageInLossyChannel(MessageBusMsg msg, IMessageBusClient sender, Object senderState) throws InvalidSourceException, InvalidReceiverException
//    {
//        if(secureRandom.nextInt(255)<150)
//            return;
//
//        getReceiver(msg).notifyMessageReceived(msg);
//        
//
//    }

    private IMessageBusClient getReceiver(MessageBusMsg msg) throws InvalidSourceException, InvalidReceiverException
    {
        if(clientRegistry.get(new MessageBusClientID(msg.sourceIP, msg.senderComponentID)) == null)
        {
            assert(false);
            throw new InvalidSourceException();
        }

        IMessageBusClient receiver = clientRegistry.get(new MessageBusClientID(msg.destinationIP, msg.receiverComponentID));
        if(receiver == null)
        {
            assert(false);
            throw new InvalidReceiverException();
        }
        else
            return receiver;
    }

	@Override
	public void sendNotification(String notification, Address srcAddress,
			Address destAddress, Channel channel, Object callbackClientState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendNotification(String notification, Address srcAddress,
			Address destAddress, Channel channel, Object callbackClientState,
			int timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRequest(String request, Address srcAddress,
			Address destAddress, ResponseType responseType, Channel channel,
			Object callbackClientState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRequest(String request, Address srcAddress,
			Address[] destAddress, Channel channel, ResponseType responseType,
			Object callbackClientState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRequest(String request, Address srcAddress,
			Address destAddress, Channel channel, ResponseType responseType,
			Object callbackClientState, int timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRequest(String request, Address srcAddress,
			Address[] destAddress, Channel channel, ResponseType responseType,
			Object callbackClientState, int timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRelayRequest(String request, Address originalSrcAddress,
			Address relayerAddress, Address destAddress,
			ResponseType responseType, Channel channel,
			Object callbackClientState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRelayRequest(String request, Address originalSrcAddress,
			Address relayerAddress, Address[] destAddress, Channel channel,
			ResponseType responseType, Object callbackClientState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRelayRequest(String request, Address originalSrcAddress,
			Address relayerAddress, Address destAddress, Channel channel,
			ResponseType responseType, Object callbackClientState, int timeout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendRelayRequest(String request, Address originalSrcAddress,
			Address relayerAddress, Address[] destAddress, Channel channel,
			ResponseType responseType, Object callbackClientState, int timeout) {
		// TODO Auto-generated method stub
		
	}

}
