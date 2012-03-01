/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.network.p2p.overlay;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import net.vikasta.fontana.common.Pair;
import net.vikasta.fontana.network.p2p.BootstrapMgr;
/**
 *
 * @author ratulmukh
 */
public class Halo {
    private IExactKeyOverlay haloOverlay;

    List<Pair<Double, Double>> cylinderRanges;
    public Halo(BootstrapMgr bootstrapMgr, IExactKeyOverlay overlay, String haloStateFile)
    {
        haloOverlay = overlay;

        cylinderRanges = new ArrayList<Pair<Double, Double>>(10);
        cylinderRanges.add(new Pair<Double, Double>(0.0, 1.99999));
        cylinderRanges.add(new Pair<Double, Double>(2.0, 2.99999));
        cylinderRanges.add(new Pair<Double, Double>(3.0, 5.99999));
        cylinderRanges.add(new Pair<Double, Double>(6.0, 8.99999));
        cylinderRanges.add(new Pair<Double, Double>(9.0, 10.0));

        haloOverlay.store("CylinderRanges", cylinderRanges);

    }

    public List<Pair<Double, Double>> getRanges()
    {
        return cylinderRanges;
        //return (List<Pair<Double, Double>>)(haloOverlay.retrieve("CylinderRanges"));
    }

    public List<InetAddress> getBootstrapNodes(Double storagePoint)
    {
        return (List<InetAddress>)(haloOverlay.retrieve("getBootstrapNodes-StoragePoint-"+storagePoint.toString()));
    }

}
