/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.socialnetworking;

import java.util.*;
import net.vikasta.fontana.common.*;
/**
 *
 * @author ratulmukh
 */
public class MockSocialNetwork<Resource> {

    private IIOCSocialNetworkStore socialNetworkStore;

    public MockSocialNetwork(IIOCSocialNetworkStore socialNetworkStore)
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

    public void addUser(User user)
    {

    }

    public List<Resource> searchResource(String tag)
    {
        return null;
    }
}
