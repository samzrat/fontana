package net.vikasta.fontana.network;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import java.util.concurrent.*;
import java.security.*;

import net.vikasta.fontana.common.*;

/**
 *
 * @author ratulmukh
 */
public class MessageBusTest implements IMessageBusClient {

    public MessageBusTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }


    BlockingQueue<MessageBusMsg> incomingMsgQueue = new LinkedBlockingQueue<MessageBusMsg>();
    BlockingQueue<MessageBusMsg> outgoingMsgQueue = new LinkedBlockingQueue<MessageBusMsg>();


    /**
     * Test of register method, of class MessageBus.
     */
    @Test
    public void testRegisterCallback() {
        System.out.println("registerCallback");

        BlockingQueue<MessageBusMsg> incomingMsgQueue = new LinkedBlockingQueue<MessageBusMsg>();
        BlockingQueue<MessageBusMsg> RoundTripMsgVerifier = new LinkedBlockingQueue<MessageBusMsg>();

            try
            {
                InetAddress inetAddress = Utility.getRandomIP();
                MessageBus messageBus = new MessageBus(); //incomingMsgQueue, outgoingMsgQueue, inetAddress );
                String ID = getUniqueID();
                messageBus.register(inetAddress, ID, this);
                
                incomingMsgQueue.put(new MessageBusMsg(ID+"Msg back to sender", inetAddress, inetAddress));
                Thread.sleep(50);
                assertEquals(receivedMsg, true);
            }
            catch(InterruptedException e)
            {
                fail("Interrupted exception");
            }
            catch (DuplicateCallbackIDException e)
            {
                try
                {
                InetAddress inetAddress = Utility.getRandomIP();
                MessageBus messageBus = new MessageBus(incomingMsgQueue, outgoingMsgQueue, inetAddress );
                String ID = getUniqueID();
                messageBus.register(ID, this);
                incomingMsgQueue.put(new MessageBusMsg(ID+"Msg back to sender", inetAddress, inetAddress));
                Thread.sleep(50);
                assertEquals(receivedMsg, true);
                }
                catch (DuplicateCallbackIDException e1)
                {
                    fail("DuplicateCallbackIDException twice - test going down");
                }
                catch(InterruptedException e2)
                {
                    fail("Interrupted exception");
                }

        }
    }

    private SecureRandom secureRandom = new SecureRandom();

    private String getUniqueID()
    {
        byte bytes[] = new byte[20];
        secureRandom.nextBytes(bytes);
        String ID = bytes.toString();
        int len = ID.length();
        if(ID.length()<20)
            for(int i = 20; i>len; i--)
                ID = ID + " ";
        len = ID.length();

        return ID;
    }

    private boolean receivedMsg = false;

    public void messageReceivedCallback(String msg, InetAddress sourceIP)
    {
        System.out.println("Received message");
        receivedMsg = true;

    }


    /**
     * Test of messageReceivedCallback method, of class MessageBus.
     */
    @Test
    public void testSendMessage() throws Exception {
        System.out.println("sendMessage");
        BlockingQueue<MessageBus.MessageBusMsg> incomingMsgQueue = new LinkedBlockingQueue<MessageBus.MessageBusMsg>();
        BlockingQueue<MessageBus.MessageBusMsg> RoundTripMsgVerifier = new LinkedBlockingQueue<MessageBus.MessageBusMsg>();

            try
            {
                InetAddress inetAddress = Utility.getRandomIP();
                MessageBus messageBus = new MessageBus(incomingMsgQueue, outgoingMsgQueue, inetAddress );
                String ID = getUniqueID();

                messageBus.register(ID, this);
                messageBus.sendMessage(ID, "Msg back to sender", inetAddress);
                MessageBusMsg msg = outgoingMsgQueue.take();

                assertEquals(msg.msg.substring(0, 20), ID);
                assertEquals(msg.msg.substring(20), "Msg back to sender");
                assertEquals(msg.destinationIP, inetAddress);
                assertEquals(msg.sourceIP, inetAddress);
            }
            catch(InterruptedException e)
            {
                fail("Interrupted exception");
            }
            catch(InvalidSourceException e)
            {
                fail("Invalid msg source exception");
            }

    }

    class RoundTripMsgVerifier implements IMessageBusClient
    {
        private BlockingQueue<MessageBusMsg> outgoingMsgQueue;
        private InetAddress thisIP;
        private InetAddress destinationIP;
        private String ID;
        private String name;

        public RoundTripMsgVerifier(String name, BlockingQueue<MessageBusMsg> outgoingMsgQueue, InetAddress thisIP, InetAddress destinationIP, String ID)
        {
            this.outgoingMsgQueue = outgoingMsgQueue;
            this.thisIP = thisIP;
            this.destinationIP = destinationIP;
            this.ID = ID;
            this.name = name;

        }

        public void messageReceivedCallback(String msg, InetAddress sourceIP)
        {
            System.out.println("Message"+msgReceivedCount+ " received by " + name);
            msgReceived = true;
            msgReceivedCount++;

        }

        private int msgReceivedCount = 0;
        private boolean msgReceived = false;

        public boolean getMsgReceivedStatus()
        {
            return msgReceived;
        }

        public int getMsgReceivedCount()
        {
            return msgReceivedCount;
        }

        public void init()
        {
            display authentication window
            Runnable task = new Runnable() {
                public void run() {
                    generateMsgs();
                }
            };
            new Thread(task).start();
        }

        private void generateMsgs()
        {
            try
            {
                for(int i=0; i<250; i++)
                {

                    MessageBusMsg msg = new MessageBusMsg(ID + "gen msg", thisIP, destinationIP);
                    outgoingMsgQueue.put(msg);
                }
            }
            catch(InterruptedException e)
            {
                fail("Interrupted Exception");
            }
        }
    }
        /**
     *
     */
    @Test
    public void testConcurrentReceiveMessage() throws Exception {
        System.out.println("testConcurrentReceiveMessage");
        BlockingQueue<MessageBus.MessageBusMsg> incomingMsgQueue = new LinkedBlockingQueue<MessageBus.MessageBusMsg>();
        BlockingQueue<MessageBus.MessageBusMsg> RoundTripMsgVerifier = new LinkedBlockingQueue<MessageBus.MessageBusMsg>();

                InetAddress messageBusIP = Utility.getRandomIP();
                MessageBus messageBus = new MessageBus(incomingMsgQueue, outgoingMsgQueue, messageBusIP );

                List<RoundTripMsgVerifier> verifierList = new ArrayList<RoundTripMsgVerifier>(10);

                for(int i =0; i<50; i++)
                {
                    String ID = getUniqueID();
                    RoundTripMsgVerifier verifier = new RoundTripMsgVerifier("Verifier"+i, incomingMsgQueue, Utility.getRandomIP(), messageBusIP, ID);
                    verifierList.add(verifier);
                    messageBus.register(ID, verifier);
                }

                Iterator<RoundTripMsgVerifier> verifierListIterator = verifierList.iterator();
                while(verifierListIterator.hasNext())
                {
                    verifierListIterator.next().init();
                }
                Thread.sleep(5320);

                verifierListIterator = verifierList.iterator();
                for(int i=0; i<verifierList.size();i++)
                {
                    int count = verifierList.get(i).getMsgReceivedCount();
                    System.out.println("Total msgs received by verifier"+i +" = " + count);
                    assertEquals(count, 250);
                }
    }

    @Test
    public void testConcurrentSendMessage() throws Exception {
        System.out.println("testConcurrentReceiveMessage");
        BlockingQueue<MessageBus.MessageBusMsg> incomingMsgQueue = new LinkedBlockingQueue<MessageBus.MessageBusMsg>();
        BlockingQueue<MessageBus.MessageBusMsg> RoundTripMsgVerifier = new LinkedBlockingQueue<MessageBus.MessageBusMsg>();

                InetAddress messageBusIP = Utility.getRandomIP();
                MessageBus messageBus = new MessageBus(incomingMsgQueue, outgoingMsgQueue, messageBusIP );

                List<RoundTripMsgVerifier> verifierList = new ArrayList<RoundTripMsgVerifier>(10);

                for(int i =0; i<50; i++)
                {
                    String ID = getUniqueID();
                    RoundTripMsgVerifier verifier = new RoundTripMsgVerifier("Verifier"+i, outgoingMsgQueue, Utility.getRandomIP(), messageBusIP, ID);
                    verifierList.add(verifier);
                    messageBus.register(ID, verifier);
                }

                Iterator<RoundTripMsgVerifier> verifierListIterator = verifierList.iterator();
                while(verifierListIterator.hasNext())
                {
                    verifierListIterator.next().init();
                }
                Thread.sleep(5320);

                verifierListIterator = verifierList.iterator();
                for(int i=0; i<verifierList.size();i++)
                {
                    int count = verifierList.get(i).getMsgReceivedCount();
                    System.out.println("Total msgs received by verifier"+i +" = " + count);
                    assertEquals(count, 250);
                }
    }

	@Override
	public void messageReceivedCallback(MessageBusMsg msg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyLoslessSendAckReceived() {
		// TODO Auto-generated method stub
		
	}

}