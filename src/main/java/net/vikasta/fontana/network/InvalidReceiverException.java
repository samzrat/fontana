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
public class InvalidReceiverException extends Exception {
    public MessageBusMsg msg;

    public InvalidReceiverException(MessageBusMsg msg)
    {
            this.msg = msg;

    }

    public InvalidReceiverException()
    {

    }

}
