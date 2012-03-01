/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.core;

import net.vikasta.fontana.common.UninitializedException;
import net.vikasta.fontana.storage.clusterkeyeddatastorage.ClusterKeyedData;
import java.util.*;
import net.vikasta.fontana.socialnetworking.*;
import net.vikasta.fontana.storage.clusterkeyeddatastorage.*;

/**
 *
 * @author ratulmukh
 */
public class SocialNetworkAndStorageMediator<Resource> implements IIOCSocialNetworkStore<Resource>, IIOCClusterKeyedDataLocalStoreDaemon {

    private ClusterKeyedDataStore resourceStore;
    private ClusterKeyedDataStore userStore;
    private SocialNetwork socialNetwork;

    private boolean initialized = false;

    public void init(ClusterKeyedDataStore resourceStore,  ClusterKeyedDataStore userStore, SocialNetwork folksonomy)
    {
        this.resourceStore = resourceStore;
        this.socialNetwork = folksonomy;
        this.userStore = userStore;
        initialized = true;
    }

    public SocialNetworkAndStorageMediator()
    {

    }
    
    //IIOCClusterKeyedDataLocalStoreDaemon method
    public void performMaintenanceOnKeywordClusterInLocalStore(Iterator<ClusterKeyedData> KeywordClusterListIterator)  throws UninitializedException
    {
        if(initialized==false)
             throw new UninitializedException();

    }

    //IIOCSocialNetworkStore methods
    public void storeResource(Resource resource, String name, List<String> tags, User user)  throws UninitializedException
    {
        if(initialized==false)
            throw new UninitializedException();

        ClusterKeyedData keywordCluster = new ClusterKeyedData(resource, name);
        Iterator<String> tagListIterator = tags.iterator();
        while(tagListIterator.hasNext())
            keywordCluster.addKeywordToCluster(tagListIterator.next());

        resourceStore.storeKeywordCluster(keywordCluster);

        //not storing user as part of associatedData as of now ...
        //might be required when it comes into the picture later

    }
    public void storeNewUser(User user) throws UninitializedException
    {
        if(initialized==false)
            throw new UninitializedException();


        ClusterKeyedData userProfile = new ClusterKeyedData(user, user.getName());
        userStore.storeKeywordCluster(userProfile);
    }


    public List<Resource> searchResource(List<String> keyList)  throws UninitializedException
    {
        if(initialized==false)
           throw new UninitializedException();

        List<ClusterKeyedData> clusterKeyedDataList = resourceStore.search(keyList);
        List<Resource> rankedResourceList = new ArrayList<Resource>(clusterKeyedDataList.size());
        Iterator<ClusterKeyedData> clusterKeyedDataListIterator = clusterKeyedDataList.iterator();
        while(clusterKeyedDataListIterator.hasNext())
            rankedResourceList.add((Resource)(clusterKeyedDataListIterator.next().getData()));
        return rankedResourceList;
    }

    public void updateUserProfile(User user, List<String> accessedResources)  throws UninitializedException
    {
        if(initialized==false)
            throw new UninitializedException();

        ClusterKeyedData userprofile =  userStore.retrieveKeywordClusterByName(user.getName());
        Iterator<String> accessedResourcesIterator = accessedResources.iterator();
        while(accessedResourcesIterator.hasNext())
            userprofile.addKeywordToCluster(accessedResourcesIterator.next());

        userStore.storeKeywordCluster(userprofile);
    }

}
