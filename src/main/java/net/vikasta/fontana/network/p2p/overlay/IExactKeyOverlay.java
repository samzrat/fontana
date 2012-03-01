/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.network.p2p.overlay;

import java.net.*;
import java.util.*;
/**
 *
 * @author ratulmukh
 */
public interface IExactKeyOverlay<Data> {
    void store(String key, Data data);
    Data retrieve(String key);
    List<InetAddress> getBootstrapNodes(int maxBootstrapNodesCount);
    void addBootstrapNodes(List<InetAddress> bootstrapNodes);
    void addBootstrapNode(InetAddress bootstrapNode);

}
