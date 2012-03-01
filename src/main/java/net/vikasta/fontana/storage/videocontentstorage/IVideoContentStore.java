/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.videocontentstorage;

import net.vikasta.fontana.video.*;
import net.vikasta.fontana.storage.metadatastorage.*;
/**
 *
 * @author ratulmukh
 */
public interface IVideoContentStore {
    //void streamInVideo(IVideoLocationMetadata videoContentLocationMetadata, IVideoSink videoSink);

    IVideoMetadata storeVideo(Video video);


}
