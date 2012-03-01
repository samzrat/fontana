/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.network.p2p.overlay;

import java.util.*;
import java.net.*;
/**
 *
 * @author ratulmukh
 */
public interface IOverlayCylinderBootstrapDiscovery {
    List<InetAddress> getBootstrapNodes(Double storagePoint, int maxBootstrapNodesCount);
}
