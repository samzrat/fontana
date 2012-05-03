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
	
	//1 way msg; no reply expected
    void sendNotification(String notification, Address srcAddress, Address destAddress, Channel channel, Object callbackClientState);
    void sendNotification(String notification, Address srcAddress, Address destAddress, Channel channel, Object callbackClientState, int timeout);

    //2 way msg; reply expected
    void sendRequest(String request, Address srcAddress, Address destAddress, ResponseType responseType, Channel channel, Object callbackClientState);
    void sendRequest(String request, Address srcAddress, Address destAddress[], Channel channel, ResponseType responseType, Object callbackClientState);
    void sendRequest(String request, Address srcAddress, Address destAddress, Channel channel, ResponseType responseType, Object callbackClientState, int timeout);
    void sendRequest(String request, Address srcAddress, Address destAddress[], Channel channel, ResponseType responseType, Object callbackClientState, int timeout);
  
    void sendRelayRequest(String request, Address originalSrcAddress, Address relayerAddress, Address destAddress, ResponseType responseType, Channel channel, Object callbackClientState);
    void sendRelayRequest(String request, Address originalSrcAddress, Address relayerAddress, Address destAddress[], Channel channel, ResponseType responseType, Object callbackClientState);
    void sendRelayRequest(String request, Address originalSrcAddress, Address relayerAddress, Address destAddress, Channel channel, ResponseType responseType, Object callbackClientState, int timeout);
    void sendRelayRequest(String request, Address originalSrcAddress, Address relayerAddress, Address destAddress[], Channel channel, ResponseType responseType, Object callbackClientState, int timeout);

    
    
    void register(InetAddress IP, String clientID, IMessageBusClient callback) throws DuplicateCallbackIDException;
    void deregister(InetAddress IP, String clientID);
    
    
    
    enum MsgDeliveryFailureReason
    {
    	SOURCE_NOT_REGISTERED,
    	DESTINATION_NOT_REGISTERED,
    	DESTINATION_NODE_DOWN,
    	DESTINATION_COMPONENT_UNKNOWN,
    	TIMEOUT
    }
    
    enum ResponseType
    {
    	SINGLE,
    	MULTI_PART
    }
    
    enum Channel
    {
    	LossLess,
    	Lossy
    }

}
