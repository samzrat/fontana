/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.videocontentstorage;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import net.vikasta.fontana.coding.ErasureCodec;
import net.vikasta.fontana.common.IStreamConsumer;
import net.vikasta.fontana.common.InvalidDataException;
import net.vikasta.fontana.common.Pair;
import net.vikasta.fontana.network.p2p.overlay.OverlayCylinder;

/**
 *
 * @author ratulmukh
 */
public class VideoContentStore<Data> implements IStreamConsumer<Data>/*implements IVideoContentStore*/ {

    OverlayCylinder overlayCylinder;

    public VideoContentStore(OverlayCylinder overlayCylinder)
    {
        this.overlayCylinder = overlayCylinder;
    }

    private SecureRandom secureRandom = new SecureRandom();
    
    public Object storeVideoChunks(String videoName, List<String> videoChunks)
    {
        String videoID = videoName+"-"+UUID.randomUUID().toString();   //need to replace with SHA
        String sessionID = overlayCylinder.beginUploadSession(videoID);
        int segmentCount = overlayCylinder.getUploadSegmentCount(sessionID);

        for(int i=0; i<videoChunks.size(); i++)
            overlayCylinder.uploadSegments(sessionID, ErasureCodec.code(videoChunks.get(i), segmentCount, segmentCount), i);
        return overlayCylinder.endUploadSession(sessionID);
    }

    public void beginStreamingInVideo(String videoName, Object storageMetadata, IStreamConsumer<Data> streamConsumer, Object callerState) throws InvalidDataException
    {
        String sessionID = overlayCylinder.beginDownloadSession(storageMetadata, this, new Pair<IStreamConsumer<Data>, Object>(streamConsumer, callerState));
        videoNameRegistry.put(videoName, sessionID);
    }

    private Dictionary<String, String> videoNameRegistry = new Hashtable<String, String>();

    private Dictionary<String, List<Data>> erasureCodedStreamElements = new Hashtable<String, List<Data>>();

    public void deliver(Data data, int streamPosition, Object callerState)
    {
        IStreamConsumer<Data> streamConsumer = ((Pair<IStreamConsumer<Data>, Object>)(((Pair<Integer, Object>)(callerState)).second)).first;
        Object sessionAndCallerState = ((Pair<IStreamConsumer<Data>, Object>)(callerState)).second;
        String sessionID = ((Pair<String, Object>)callerState).first;
        Object appState = ((Pair<Integer, Object>)sessionAndCallerState).second;
        
        int segmentCount = overlayCylinder.getDownloadSegmentCount(sessionID);
  //      String key = "erasure coded data-"+data+i
        String key = String.valueOf(sessionID) + "+" + String.valueOf(streamPosition);

        List<Data> erasureCodedElements = erasureCodedStreamElements.get(key);
        if(erasureCodedElements == null)
        {
            erasureCodedElements = new ArrayList<Data>();
            erasureCodedStreamElements.put(key, erasureCodedElements);
        }
        if(erasureCodedElements.size()==segmentCount-1)
        {
            erasureCodedElements.add(data);
            String decodedChunk = ErasureCodec.decode((List<String>)erasureCodedElements);
            
            streamConsumer.deliver((Data)(decodedChunk), streamPosition, appState);
            erasureCodedStreamElements.remove(key);
        }
        else
            erasureCodedElements.add(data);
    }

    public void endStreamingInVideo(String videoName)
    {
        String sessionID = videoNameRegistry.get(videoName);
        overlayCylinder.endDownloadSession(sessionID);

    }

    



//    public IVideoMetadata storeVideo(Video video)
//    {
//        
//    }

//    public void streamInVideo(IVideoLocationMetadata videoContentLocationMetadata, IVideoSink videoSink)
//    {
//
//    }

}
