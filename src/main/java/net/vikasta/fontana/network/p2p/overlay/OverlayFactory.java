/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.network.p2p.overlay;

import net.vikasta.fontana.network.*;
import java.util.*;
import java.net.*;
/**
 *
 * @author ratulmukh
 */
public class OverlayFactory
{


    public enum OverlayType
    {
        CHORD,
        STREAMING_CHORD
    }

    private MessageBus messageBus;

    public OverlayFactory(MessageBus messageBus)
    {
        this.messageBus = messageBus;
    }

    public IExactKeyOverlay getOverlay(OverlayType overlayType, List<InetAddress> bootstrapNodes, boolean joinOverlay)
    {
        if(overlayType == OverlayType.CHORD)
            return new MockExactKeyOverlay(bootstrapNodes, joinOverlay, messageBus);
        else
            return null;
    }

    public IStreamingExactKeyOverlay getStreamingOverlay(OverlayType overlayType, List<InetAddress> bootstrapNodes, boolean joinOverlay)
    {
        if (overlayType == OverlayType.STREAMING_CHORD)
            return new MockStreamingExactKeyOverlay(bootstrapNodes, joinOverlay, messageBus);
        else
            return null;
    }
}
