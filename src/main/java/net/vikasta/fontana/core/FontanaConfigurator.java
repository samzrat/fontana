/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.core;

import net.vikasta.fontana.network.p2p.*;
import net.vikasta.fontana.network.p2p.overlay.*;
import net.vikasta.fontana.network.*;
import net.vikasta.fontana.socialnetworking.*;
import net.vikasta.fontana.gui.*;
import net.vikasta.fontana.storage.clusterkeyeddatastorage.*;
import net.vikasta.fontana.video.*;
import net.vikasta.fontana.storage.videocontentstorage.*;
/**
 *
 * @author ratulmukh
 */
public class FontanaConfigurator {

    private String bootstrapWebsite = "";
    private String bootstrapFile = "";
    private String overlayCylinderStateFile = "";
    private String haloStateFile = "";
    private String superNodeOverlayFile = "";

    GUIController socialNetworkAndGUIMediator = new GUIController();
    SocialNetworkAndStorageMediator socialNetworkAndStorageMediator = new SocialNetworkAndStorageMediator();
    IExactKeyOverlay superNodeOverlay;
    OverlayCylinder overlayCylinder;
    SocialNetwork socialNetwork;
    MockGUIClient mockGUIClient;
    ClusterKeyedDataStore resourceStore;
    ClusterKeyedDataStore userStore;
    VideoMgr videoMgr;
    MessageBus messageBus;
   OverlayFactory overlayFactory;

    public FontanaConfigurator(MessageBus messageBus)
    {
        this.messageBus = messageBus;

    }
    public void configureSystem()
    {
        System.out.println("Start system configuration");
        configureP2PNetwork();
        configureSocialNetwork();
        configureVideoContentSubsystem();
        configureMediators();
        System.out.println("End system configuration");
    }

    private void configureP2PNetwork()
    {
        System.out.println("Start P2P network configuration");

        System.out.println("Creating BootstrapMgr");
        BootstrapMgr bootstrapMgr = new BootstrapMgr(bootstrapWebsite, bootstrapFile);
        
        System.out.println("Creating OverlayFactory");
        overlayFactory = new OverlayFactory(messageBus);

        System.out.println("Creating SuperNodeOverlay");
        superNodeOverlay = overlayFactory.getOverlay(OverlayFactory.OverlayType.CHORD, bootstrapMgr.getBootstrapNodes("SuperNodeOverlay"), isPartOfSuperNodeOverlay());
        Halo halo = new Halo(bootstrapMgr, superNodeOverlay, haloStateFile); //superNodeOverlay is used by the Halo

        System.out.println("Creating OverlayCylinder");
        overlayCylinder = new OverlayCylinder(bootstrapMgr, overlayCylinderStateFile, halo, overlayFactory);

        System.out.println("End P2p network configuration");
    }

    private void configureSocialNetwork()
    {
        System.out.println("Start Social Network configuration");
        System.out.println("Creating new social network");
        socialNetwork = new SocialNetwork(socialNetworkAndStorageMediator);
        System.out.println("Creating new GUI");

        mockGUIClient = new MockGUIClient(socialNetworkAndGUIMediator);

        //all metadata gets stored in the superNodeOverlay
        System.out.println("Creating resource and user stores of social network");
        resourceStore = new OverlayBasedClusterKeyedDataStore(superNodeOverlay, 10, "dummyPolicy", 10, socialNetworkAndStorageMediator);
        userStore = new OverlayBasedClusterKeyedDataStore(superNodeOverlay, 10, "dummyPolicy", 10, socialNetworkAndStorageMediator);

        

        System.out.println("End Social Network configuration");
    }

    private void configureVideoContentSubsystem()
    {
        System.out.println("Begin Video content subystem configuration");


        System.out.println("Creating new Video content store");
        VideoContentStore videoContentStore = new VideoContentStore(overlayCylinder);
        overlayCylinder.init(videoContentStore);
        
        System.out.println("Creating new Video content manager");
        videoMgr = new VideoMgr(videoContentStore);
        System.out.println("End Video content subystem configuration");
    }

    private void configureMediators()
    {
        System.out.println("Begin configuration of mediators");

        System.out.println("Initializing mediators");
        socialNetworkAndGUIMediator.init(socialNetwork, mockGUIClient, videoMgr);
        socialNetworkAndStorageMediator.init(resourceStore, userStore, socialNetwork);

        mockGUIClient.init();
        
        System.out.println("End configuration of mediators");
    }

    private boolean isPartOfSuperNodeOverlay()
    {
        //use superNodeOverlayFile to find out if the present computer is part of Halo
        return false;
    }


}
