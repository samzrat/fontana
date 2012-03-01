/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.clusterkeyeddatastorage;

import net.vikasta.fontana.common.UninitializedException;
import java.util.*;
import net.vikasta.fontana.core.*;

/**
 *
 * @author ratulmukh
 */
public interface IIOCClusterKeyedDataLocalStoreDaemon {
    void performMaintenanceOnKeywordClusterInLocalStore(Iterator<ClusterKeyedData> KeywordClusterListIterator) throws UninitializedException;

}
