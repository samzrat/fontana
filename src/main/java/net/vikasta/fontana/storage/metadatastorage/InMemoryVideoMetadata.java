/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.vikasta.fontana.storage.metadatastorage;

/**
 *
 * @author ratulmukh
 */
public class InMemoryVideoMetadata extends VideoDescriptionMetadata implements IInMemoryVideoLocationMetadata {

     public int inMemoryIndex;
    public int getInMemoryIndex()
    {
        return inMemoryIndex;
    }

}
