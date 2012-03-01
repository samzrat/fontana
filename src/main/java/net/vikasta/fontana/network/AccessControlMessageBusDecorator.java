/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.network;

import java.net.*;

/**
 *
 * @author ratulmukh
 */
public class AccessControlMessageBusDecorator implements IMessageBus, IMessageBusAccessControl {

    public void sendMessageInLosslessChannel(MessageBusMsg msg) throws InvalidSourceException, InvalidReceiverException
    {

    }

    public void sendMessageInLossyChannel(MessageBusMsg msg) throws InvalidSourceException, InvalidReceiverException
    {

    }

    public void register(InetAddress IP, String clientID, IMessageBusClient callback) throws DuplicateCallbackIDException
    {

    }

    public void deregister(InetAddress IP, String clientID)
    {

    }

    public void restrictAccess(InetAddress IP)
    {

    }

    public void allowAccess(InetAddress IP)
    {

    }

}
