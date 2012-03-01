/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.network.p2p.overlay;

import java.net.InetAddress;
import java.security.SecureRandom;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import net.vikasta.fontana.common.IStreamConsumer;
import net.vikasta.fontana.common.InvalidDataException;
import net.vikasta.fontana.common.Pair;
import net.vikasta.fontana.network.p2p.BootstrapMgr;


/**
 *
 * @author ratulmukh
 */
public class OverlayCylinder<Data> implements IOverlayCylinderBootstrapDiscovery, IStreamConsumer {

    class RangeStreamingOverlayStruct<Data>
    {
        public Double min;
        public Double max;
        public IStreamingExactKeyOverlay<Data> overlay;
        public RangeStreamingOverlayStruct(Double min, Double max,IStreamingExactKeyOverlay<Data> overlay)
        {
            this.min = min;
            this.max = max;
            this.overlay = overlay;
        }
    }

    class Range
    {
        public Double min;
        public Double max;
    }

    private List<RangeStreamingOverlayStruct> cylinderOverlayList = new LinkedList<RangeStreamingOverlayStruct>();
    private Halo halo;
    IOverlayCylinderBootstrapDiscovery overlayCylinderBootstrapDiscovery;
    BootstrapMgr bootstrapMgr;
    String overlayCylinderStateFile;
    Double selfStoragePoint;
    IStreamConsumer streamConsumer2;
    OverlayFactory overlayFactory;


    public OverlayCylinder(BootstrapMgr bootstrapMgr, String overlayCylinderStateFile, Halo halo, OverlayFactory overlayFactory)
    {
        this.bootstrapMgr = bootstrapMgr;
        this.halo = halo;
        this.overlayFactory = overlayFactory;

        selfStoragePoint = getSelfStoragePoint();

    }

    public void init(IStreamConsumer streamConsumer2)
    {
        this.streamConsumer2 = streamConsumer2;
        initOverlays();
    }

    public void initOverlays()
    {
        List<Pair<Double, Double>> ranges = halo.getRanges();
        Iterator<Pair<Double, Double>> rangesIterator = ranges.iterator();
        while(rangesIterator.hasNext())
        {
            Pair<Double, Double> range = rangesIterator.next();
            Double rangeMidpoint = (range.first + range.second)/2;

            IStreamingExactKeyOverlay streamingOverlay = overlayFactory.getStreamingOverlay(OverlayFactory.OverlayType.STREAMING_CHORD, halo.getBootstrapNodes(rangeMidpoint), isPartOfOverlay(range));
            RangeStreamingOverlayStruct rangeOverlay = new RangeStreamingOverlayStruct(range.first, range.second, streamingOverlay);
            cylinderOverlayList.add(rangeOverlay);
        }
    }

    private Double getSelfStoragePoint()
    {
        return 5.9;
    }

    private boolean isPartOfOverlay(Pair<Double, Double> range)
    {
        if(selfStoragePoint>range.first && selfStoragePoint<=range.second)
            return true;
        else
            return false;
    }

    public OverlayCylinder(List<Range> rangeList, IOverlayCylinderBootstrapDiscovery overlayCylinderBootstrapDiscovery )
    {
        this.overlayCylinderBootstrapDiscovery = overlayCylinderBootstrapDiscovery;

    }
    
    public void modifyRange(List<Range> deletedRangeList, List<Range> newRangeList)
    {

    }
    public List<InetAddress> getBootstrapNodes(Double storagePoint, int maxBootstrapNodesCount)
    {
        RangeStreamingOverlayStruct foundRangeOverlay = getOverlay(storagePoint);
        if(foundRangeOverlay != null)
            return foundRangeOverlay.overlay.getBootstrapNodes(maxBootstrapNodesCount);
        else
            return null;
    }

//    public void store(String key, Data data, Double storagePoint)
//    {
//        RangeStreamingOverlayStruct foundRangeOverlay = getOverlay(storagePoint);
//        if(foundRangeOverlay != null)
//            foundRangeOverlay.overlay.store(key, data);
//        //else throw exception
//
//    }

//    public Data retrieve(String key, Data data, Double storagePoint)
//    {
//
//        RangeStreamingOverlayStruct foundRangeOverlay = getOverlay(storagePoint);
//        if(foundRangeOverlay != null)
//            return (Data)(foundRangeOverlay.overlay.retrieve(key));
//        else
//            return null;
//    }

    private RangeStreamingOverlayStruct getOverlay(Double storagePoint)
    {
        RangeStreamingOverlayStruct foundRangeOverlay = null;
        RangeStreamingOverlayStruct currentRangeOverlay = null;
        Iterator<RangeStreamingOverlayStruct> cylinderOverlayListIterator = cylinderOverlayList.iterator();
        while(cylinderOverlayListIterator.hasNext() && foundRangeOverlay==null)
        {
            currentRangeOverlay = cylinderOverlayListIterator.next();
            if(storagePoint>currentRangeOverlay.min && storagePoint<=currentRangeOverlay.max)
                foundRangeOverlay = currentRangeOverlay;
        }
        return foundRangeOverlay;
    }

    public String beginDownloadSession(Object storageMetadata, IStreamConsumer<Data> streamConsumer, Object callerState) throws InvalidDataException
    {
        Session downloadSession = (Session)storageMetadata;
        String sessionID = UUID.randomUUID().toString();
        downloadSessionRegistry.put(sessionID, downloadSession);

        RangeStreamingOverlayStruct foundRangeOverlay = getOverlay(downloadSession.storagePoint);
        if(foundRangeOverlay == null)
            throw new InvalidDataException();
        else
        {
            for(int i=0; i<downloadSession.segmentCount; i++)
            {
                Object appState = new Pair<String, Pair<IStreamConsumer<Data>, Object>>(sessionID, new Pair<IStreamConsumer<Data>, Object>(streamConsumer, callerState));
                foundRangeOverlay.overlay.startStreamingIn(downloadSession.videoID + i, this, appState);

            }
        }

        return sessionID;
    }
    
   public void deliver(Object data, int streamPosition, Object callerState)
   {
       Pair<String, IStreamConsumer<Data>> statePair = (Pair<String, IStreamConsumer<Data>>)callerState;
        String sessionID = (String)statePair.first;

        if(downloadSessionRegistry.get(sessionID) != null)
        {
            Pair<IStreamConsumer<Data>, Object> pair = (Pair<IStreamConsumer<Data>, Object>)(statePair.second);
            IStreamConsumer<Data> streamConsumer1 = (IStreamConsumer<Data>) pair.first;
            Object appState = pair.second;

            Pair<String, Object> sessionAndCallerState = new Pair<String, Object>(sessionID, appState);
            streamConsumer1.deliver((Data)data, streamPosition, sessionAndCallerState);
        }
   }

   public void endDownloadSession(String sessionID)
    {
        downloadSessionRegistry.remove(sessionID);
    }
//
//   public interface IStreamConsumer<Data>
//    {
//        void deliver(Data data, int streamPosition, Object callerState);
//    }



    public String beginUploadSession(String videoId)
    {
        String sessionID = UUID.randomUUID().toString();
        Session uploadSession = new Session();
        uploadSession.videoID = videoId;
        uploadSessionRegistry.put(sessionID, uploadSession);
        return sessionID;
    }

    public Object endUploadSession(String sessionID)
    {
        return uploadSessionRegistry.remove(sessionID);
    }

    public int getDownloadSegmentCount(String sessionID)
    {
        return downloadSessionRegistry.get(sessionID).segmentCount;
    }

    public int getUploadSegmentCount(String sessionID)
    {
        Session uploadSession = uploadSessionRegistry.get(sessionID);
        uploadSession.storagePoint = selectStoragePoint();
        uploadSession.segmentCount = computeUploadSegmentCount(uploadSession.storagePoint);

        return uploadSession.segmentCount;
    }

    public void uploadSegments(String sessionID, List<String> segments, int streamPosition)
    {
        Session uploadSession = uploadSessionRegistry.get(sessionID);
        assert(segments.size() == uploadSession.segmentCount);

        RangeStreamingOverlayStruct foundRangeOverlay = getOverlay(uploadSession.storagePoint);
        if(foundRangeOverlay == null)
            return;
        else
        {
            for(int i=0; i<segments.size(); i++)
                foundRangeOverlay.overlay.storeStreamSegment(uploadSession.videoID+"-ECsegment"+i, segments.get(i), streamPosition);
        }
    }

    private double selectStoragePoint()
    {
        return secureRandom.nextDouble()*10;
    }

    private int computeUploadSegmentCount(double storagePoint)
    {
        return secureRandom.nextInt(10)+5;
    }

    SecureRandom secureRandom = new SecureRandom();
    private Dictionary<String, Session> uploadSessionRegistry = new Hashtable<String, Session>();
    private Dictionary<String, Session> downloadSessionRegistry = new Hashtable<String, Session>();

    private class Session
    {
        String videoID;
        public double storagePoint;
        public int segmentCount;
    }
    

}
