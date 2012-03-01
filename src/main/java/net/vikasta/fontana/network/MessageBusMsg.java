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
public class MessageBusMsg
{
    public String msg;
    public String senderComponentID;
    public String receiverComponentID;
    public InetAddress sourceIP;
    public InetAddress destinationIP;

    public MessageBusMsg(String msg, String senderID, String receiverID, InetAddress sourceIP, InetAddress destinationIP)
    {
        this.msg = msg;
        this.senderComponentID = senderID;
        this.receiverComponentID = receiverID;
        this.sourceIP = sourceIP;
        this.destinationIP = destinationIP;

    }
}