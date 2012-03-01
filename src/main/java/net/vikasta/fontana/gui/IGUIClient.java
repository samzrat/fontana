/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.gui;
import net.vikasta.fontana.video.*;
import java.util.*;

/**
 *
 * @author ratulmukh
 */
public interface IGUIClient {
    void activate();
    
    void displayRecommendations(List<Video> videoList);
    void playVideo(Video video);
    void displaySimilarVideos(List<Video> videoList);


}
