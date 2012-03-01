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
public class PygmyMgr {
    
    public static void main(String[] args)
   {
        PygmyMgr pygmyMgr = new PygmyMgr(null);
        

    }

    
    PygmyConfigurator pygmyConfigurator;

    public PygmyMgr(MessageBus messageBus)
    {
        pygmyConfigurator = new PygmyConfigurator(messageBus);
        pygmyConfigurator.configureSystem();


    }

}
