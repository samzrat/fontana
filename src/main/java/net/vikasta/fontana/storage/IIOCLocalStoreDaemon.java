/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package net.vikasta.fontana.storage;

import java.util.*;
import net.vikasta.fontana.storage.metadatastorage.UserProfileMetadata;
import net.vikasta.fontana.storage.metadatastorage.VideoMetadata;
/**
 *
 * @author ratulmukh
 */
public interface IIOCLocalStoreDaemon {
    void performOperationOnVideoMetadata(Iterator<VideoMetadata> videoMetadataListIterator);
    void performOperationOnUserProfileMetadata(Iterator<UserProfileMetadata> userProfileMetadataIterator);
    void performOperationOnAllMetadata(Iterator<VideoMetadata> videoMetadataListIterator, Iterator<UserProfileMetadata> userProfileMetadataIterator);

}
