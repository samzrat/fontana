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
public interface IMessageBusAccessControl {
    void restrictAccess(InetAddress IP);
    void allowAccess(InetAddress IP);

}
