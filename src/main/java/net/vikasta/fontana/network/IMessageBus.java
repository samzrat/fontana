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
public interface IMessageBus {
    void sendMessageInLosslessChannel(MessageBusMsg msg) throws InvalidSourceException, InvalidReceiverException;
    void sendMessageInLossyChannel(MessageBusMsg msg) throws InvalidSourceException, InvalidReceiverException;
    void register(InetAddress IP, String clientID, IMessageBusClient callback) throws DuplicateCallbackIDException;
    void deregister(InetAddress IP, String clientID);

}
