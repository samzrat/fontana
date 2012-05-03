/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.core;

import net.vikasta.fontana.socialnetworking.*;
import net.vikasta.fontana.gui.*;
import net.vikasta.fontana.storage.clusterkeyeddatastorage.*;
import net.vikasta.fontana.network.*;

/**
 *
 * @author ratulmukh
 */
public class FontanaMgr {
    
    public static void main(String[] args)
   {
        FontanaMgr pygmyMgr = new FontanaMgr(null);
        

    }

    
    FontanaConfigurator pygmyConfigurator;

    public FontanaMgr(MessageBus messageBus)
    {
        pygmyConfigurator = new FontanaConfigurator(messageBus);
        pygmyConfigurator.configureSystem();


    }

}
