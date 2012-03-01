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

    private Hashtable<Pair<InetAddress, String>, IMessageBusClient> clientRegistry = new Hashtable<Pair<InetAddress, String>, IMessageBusClient>();
    private SecureRandom secureRandom = new SecureRandom();

    public void register(InetAddress IP, String clientID, IMessageBusClient callback) throws DuplicateCallbackIDException
    {

        if(clientRegistry.put(new Pair<InetAddress, String>(IP, clientID), callback) != null)
            throw new DuplicateCallbackIDException();
    }

    public void deregister(InetAddress IP, String clientID)
    {
        clientRegistry.remove(new Pair<InetAddress, String>(IP, clientID));
    
    }
    
    public void sendMessageInLosslessChannel(MessageBusMsg msg) throws InvalidSourceException, InvalidReceiverException
    {
        getReceivingClient(msg).messageReceivedCallback(msg);

        IMessageBusClient client = clientRegistry.get(new Pair<InetAddress, String>(msg.sourceIP, msg.senderComponentID));
        client.notifyLoslessSendAckReceived();
        
    }

    public void sendMessageInLossyChannel(MessageBusMsg msg) throws InvalidSourceException, InvalidReceiverException
    {
        if(secureRandom.nextInt(255)<150)
            return;

        getReceivingClient(msg).messageReceivedCallback(msg);

    }

    private IMessageBusClient getReceivingClient(MessageBusMsg msg) throws InvalidSourceException, InvalidReceiverException
    {
        if(clientRegistry.get(new Pair<InetAddress, String>(msg.sourceIP, msg.senderComponentID)) == null)
        {
            assert(false);
            throw new InvalidSourceException();
        }

        IMessageBusClient receiver = clientRegistry.get(new Pair<InetAddress, String>(msg.destinationIP, msg.receiverComponentID));
        if(receiver == null)
        {
            assert(false);
            throw new InvalidReceiverException();
        }
        else
            return receiver;
    }

}
