/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.metadatastorage;

import java.util.*;
import net.vikasta.fontana.video.*;

/**
 *
 * @author ratulmukh
 */
public interface IVideoMetadataStore {

    public List<IVideoMetadata> searchVideoMetadataStore(String keyword);
    public List<IVideoMetadata> searchVideoMetadataStore(List<String> keywordCluster);
    public void storeVideoMetadata(IVideoMetadata videometadata);
    
}
