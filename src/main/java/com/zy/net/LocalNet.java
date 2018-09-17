package com.zy.net;

import com.zy.tools.IpSwitch;

import java.net.InetSocketAddress;
import java.net.Socket;

public class LocalNet {
    private Socket socket;
    private InetSocketAddress address;
    public LocalNet(String ipString){
        address = IpSwitch.getInetSocketAdress(ipString);
        new Link().start();
    }

    public class Link extends Thread{
        @Override
        public void run() {

        }
    }
}
