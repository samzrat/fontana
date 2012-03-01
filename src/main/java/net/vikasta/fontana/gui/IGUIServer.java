/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.gui;

import net.vikasta.fontana.video.*;
import net.vikasta.fontana.common.*;
import java.util.*;
/**
 *
 * @author ratulmukh
 */
public interface IGUIServer {
    List<Video> getRecommendedVideos() throws OutOfUserSessionException;
    List<Video> getRelatedVideos(Video video, int noOfVideos);
    List<Video> searchVideos(String searchKey);

    void streamInVideo(Video video, IStreamConsumer streamConsumer) throws InvalidDataException;

    Video addVideo(String videoLocalPath, String name, List<String> tags) throws UninitializedException;

    boolean logIn(String username, String password);
    void logOut();


}
//Urvashi
//www.soscvindia.org