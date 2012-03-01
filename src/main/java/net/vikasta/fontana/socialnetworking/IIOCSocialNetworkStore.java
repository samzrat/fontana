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
public interface IIOCSocialNetworkStore<Resource> {
    void storeResource(Resource resource, String name, List<String> tags, User user) throws UninitializedException;
    void storeNewUser(User user) throws UninitializedException;
    List<Resource> searchResource(List<String> keyList) throws UninitializedException;
    void updateUserProfile(User user, List<String> accessedresources) throws UninitializedException;

}
