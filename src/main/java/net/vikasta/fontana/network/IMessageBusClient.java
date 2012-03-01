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
public interface IMessageBusClient {
    void messageReceivedCallback(MessageBusMsg msg);
    void notifyLoslessSendAckReceived();

}
