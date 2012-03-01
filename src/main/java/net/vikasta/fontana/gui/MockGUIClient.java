/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.gui;

import net.vikasta.fontana.common.*;
import net.vikasta.fontana.video.*;
import net.vikasta.fontana.video.Codec.*;
import net.vikasta.fontana.video.Codec.h264.*;

import java.util.*;
/**
 *
 * @author ratulmukh
 */
public class MockGUIClient<Data> implements IStreamConsumer<Data>/*implements IGUIClient*/ {

    //VideoStorageMgr videoStorageMgr = new VideoStorageMgr();
    IVideoDecoder h = new H264Decoder();
    IGUIServer _GUIServer;
    int relatedVideosCount = 10;
    
    public MockGUIClient(IGUIServer _GUIServer)
    {
        this._GUIServer = _GUIServer;
        //init();
    }

    public void init()
    {
        //display authentication window
        Runnable task = new Runnable() {
            public void run() {
                MockUserInteraction();
            }
        };
        new Thread(task).start();
    }
    
    private void MockUserInteraction()
    {
        Thread.currentThread().setName("MockUserInteraction thread - GUI");

//        while(true)
  //      {
        try
        {
            //Thread.sleep(66666);
            OnLogInOkClicked("Ratul", "ratsaregreat");

            List<String> tags1 = new ArrayList<String>(3);
            tags1.add("howdy"); tags1.add("hello"); tags1.add("hi");

            List<String> tags2 = new ArrayList<String>(5);
            tags2.add("bye"); tags2.add("tata"); tags2.add("ciao");

            List<String> tags3 = new ArrayList<String>(2);
            tags3.add("present"); tags3.add("past"); tags3.add("future");

            OnAddVideoOkClicked("D:/Video1", "Video1", tags1);
//            OnAddVideoOkClicked("D:/Video2", "Video2", tags2);
//            OnAddVideoOkClicked("D:/Video3", "Video3", tags3);

            Iterator<Video> videoListIterator = videoList.iterator();
            while(videoListIterator.hasNext())
            {
                OnVideoClicked(videoListIterator.next());

            }
            
            OnLogOutOkClicked();


        }
        catch (Exception e)
        {
            System.out.println("Exception caught - ending MockuserInteraction");
            System.out.println("Exception message="+e.getMessage());
            e.printStackTrace();
        }
    //    }
    }

    List<Video> videoList = new ArrayList<Video>(3);

    private void OnAddVideoOkClicked(String videoLocalPath, String name, List<String> tags) throws UninitializedException
    {
        videoList.add(_GUIServer.addVideo(videoLocalPath, name, tags));
    }

    private void OnLogInOkClicked(String userName, String password) throws Exception
    {
        if(_GUIServer.logIn(userName, password))
        {
            System.out.println("Log in successful");
            displayRecommendedVideoList(_GUIServer.getRecommendedVideos());
        }
        else
        {
            System.out.println("Log in failed");
            throw new Exception();
        }
    }

    private void OnLogOutOkClicked() 
    {
        System.out.println("Log out");
        _GUIServer.logOut();
    }

    private void OnVideoClicked(Video video) throws InvalidDataException
    {
        playVideo(video);
        displayRelatedVideoList(_GUIServer.getRelatedVideos(video, relatedVideosCount));

    }

    private void playVideo(Video video) throws InvalidDataException
    {
        System.out.println("Command to play videoreceived");
        _GUIServer.streamInVideo(video, this);
    }

    public void deliver(Data data, int streamPosition, Object callerState)
    {
        System.out.println("Delivered video chunk:" + (String)data);
    }


    private void OnSearchOkClicked(String searchKey)
    {
        displaySearchedVideoList(_GUIServer.searchVideos(searchKey));
    }

    private void displaySearchedVideoList(List<Video> searchedVideoList)
    {
        System.out.println("Displaying SearchedVideoList");
    }

    private void displayRecommendedVideoList(List<Video> recommendedVideoList)
    {
        if(recommendedVideoList!=null)
        {
            Iterator<Video> recommendedVideoListIterator = recommendedVideoList.iterator();
            while(recommendedVideoListIterator.hasNext())
                System.out.println("Displaying RecommendedVideo " + recommendedVideoListIterator.next().toString());
        }
    }

    private void displayRelatedVideoList(List<Video> relatedVideoList)
    {
        if(relatedVideoList!=null)
        {
            Iterator<Video> relatedVideoListIterator = relatedVideoList.iterator();
            while(relatedVideoListIterator.hasNext())
                System.out.println("Displaying RecommendedVideo " + relatedVideoListIterator.next().toString());
        }
    }
}
