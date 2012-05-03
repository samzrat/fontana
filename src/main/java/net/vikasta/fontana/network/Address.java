package net.vikasta.fontana.network;

import java.net.InetAddress;

public class Address {

    public final InetAddress IP;
    public final String componentID;
    
    
    
    public Address(InetAddress IP, String componentID)
    {
    	this.componentID = componentID;
    	this.IP = IP;
    }
}
