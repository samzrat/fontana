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
