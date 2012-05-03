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

	//Delivery failure callback for both lossy and lossless transmissions
	//For lossy transmissions, delivery failure for channel loss will not be notified, because it is expected
	//but delivery failure will not be notified when destination node is down or destination component is unknown 
    void msgDeliveryFailureCallback(IMessageBus.MsgDeliveryFailureReason msgDeliveryFailureReason, Object clientState);

    void responseReceivedCallback(String reply, Address responseSenderAddress, Object clientState);
}


