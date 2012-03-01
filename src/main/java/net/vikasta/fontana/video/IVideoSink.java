/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.video;

/**
 *
 * @author ratulmukh
 */
public interface IVideoSink {
    void sendVideoChunk(VideoChunk videoChunk);

}
