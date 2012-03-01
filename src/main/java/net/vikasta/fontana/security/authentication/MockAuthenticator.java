/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.security.authentication;

import net.vikasta.fontana.socialnetworking.*;
/**
 *
 * @author ratulmukh
 */
public class MockAuthenticator {
    public User Authenticate(String username, String password)
    {
        return new User();
    }

}
