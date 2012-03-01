/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.common;

/**
 *
 * @author ratulmukh
 */
public interface IStreamConsumer<Data> {
    void deliver(Data data, int streamPosition, Object callerState);

}
