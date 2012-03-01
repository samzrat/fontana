/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.network.p2p.overlay;
import java.net.InetAddress;
import java.util.List;

import net.vikasta.fontana.common.IStreamConsumer;
/**
 *
 * @author ratulmukh
 */
public interface IStreamingExactKeyOverlay<Data> {
    void storeStreamSegment(String key, Data data, int streamPosition);
    void startStreamingIn(String key, IStreamConsumer<Data> streamConsumer, Object callerState);

    List<InetAddress> getBootstrapNodes(int maxBootstrapNodesCount);
    void addBootstrapNodes(List<InetAddress> bootstrapNodes);
    void addBootstrapNode(InetAddress bootstrapNode);

}
