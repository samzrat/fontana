/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.socialnetworking;

import net.vikasta.fontana.common.UninitializedException;
import java.util.*;

/**
 *
 * @author ratulmukh
 */
public class SocialNetwork<Resource> {

    private IIOCSocialNetworkStore socialNetworkStore;

    public SocialNetwork(IIOCSocialNetworkStore socialNetworkStore)
    {
        this.socialNetworkStore = socialNetworkStore;
    }

    public List<Resource> retrieveRecommendedResources(User user)
    {
        return new ArrayList<Resource>();
    }

    public List<Resource> retrieveRelatedResources(String resourceName, int noOfRelatedResources)
    {
        return new ArrayList<Resource>();
    }

    public void addResource(Resource resource, String name, List<String> tags, User user) throws UninitializedException
    {
        socialNetworkStore.storeResource(resource, name, tags, user);

    }

    public void associateResourceWithUser(Resource resource, User user)
    {

    }

    public void addUser(User user)
    {

    }

    public List<Resource> searchResource(String tag)
    {
        return null;
    }
}
