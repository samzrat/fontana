/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.core;

import java.util.List;
import net.vikasta.fontana.common.*;
import net.vikasta.fontana.gui.IGUIServer;
import net.vikasta.fontana.gui.MockGUIClient;
import net.vikasta.fontana.security.authentication.MockAuthenticator;
import net.vikasta.fontana.socialnetworking.SocialNetwork;
import net.vikasta.fontana.socialnetworking.User;
import net.vikasta.fontana.video.Video;
import net.vikasta.fontana.video.VideoMgr;
/**
 *
 * @author ratulmukh
 */
public class GUIController implements IGUIServer{

    /**
     * @param args the command line arguments
     */

    MockAuthenticator authenticator = new MockAuthenticator();
    SocialNetwork<Video> socialNetwork;
    MockGUIClient _GUIClientMock;
    VideoMgr videoMgr;

    boolean inSession = false;
    User user = null;
    boolean initialized = false;

//    public static void main(String[] args)
//    {
//        new GUIController();
//
//    }

    public GUIController()
    {
        //_GUIClientMock = new MockGUIClient(this);
        //_GUIClientMock.activate();
    }

    public void streamInVideo(Video video, IStreamConsumer streamConsumer) throws InvalidDataException
    {
        socialNetwork.associateResourceWithUser(video, user);
        videoMgr.beginStreamingInVideo(video, streamConsumer, null);
    }

    public void init(SocialNetwork<Video> socialNetwork, MockGUIClient mockGUIClient, VideoMgr videoMgr )
    {
        this.socialNetwork = socialNetwork;
        this._GUIClientMock = mockGUIClient;
        this.videoMgr = videoMgr;
    }


    public List<Video> getRecommendedVideos() throws OutOfUserSessionException
    {
        if(!inSession)
            throw new OutOfUserSessionException();

        return socialNetwork.retrieveRecommendedResources(user);
    }

    public List<Video> getRelatedVideos(Video video, int noOfVideos)
    {
        return socialNetwork.retrieveRelatedResources(video.getName(), noOfVideos);
    }

    public Video addVideo(String videoLocalPath, String name, List<String> tags) throws UninitializedException
    {
        Video video = videoMgr.storeVideo(videoLocalPath, name);
        socialNetwork.addResource(video, name, tags, user);
        return video;
    }

    public boolean logIn(String username, String password)
    {
        user = authenticator.Authenticate(username, password);
        if(user != null)
        {
            inSession = true;
            return true;
        }
        else return false;
    }

    public void logOut()
    {
        inSession = false;
        user = null;
    }

    public List<Video> searchVideos(String searchKey)
    {
        return null;
    }

}
