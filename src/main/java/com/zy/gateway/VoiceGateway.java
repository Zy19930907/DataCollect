package com.zy.gateway;

import com.zy.interfaces.GatewayObserver;
import com.zy.tools.IpSwitch;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class VoiceGateway {
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;
    private InetSocketAddress address;
    private Link link = new Link();
    private volatile boolean listen = true;
    private List<GatewayObserver> observers = new ArrayList<GatewayObserver>();

    public VoiceGateway(String ipString){
        address = IpSwitch.getInetSocketAdress(ipString);
        link.start();
    }

    public void addObserver(GatewayObserver observer){
        observers.add(observer);
    }

    public void CreatNotice(){
        int i;
        for(i=0;i<observers.size();i++){
            observers.get(i).onGatewayCreat(this);
        }
    }

    public void LinkStartNotice(){
        int i;
        for(i=0;i<observers.size();i++){
            observers.get(i).onGatewayLinkStart(this);
        }
    }

    public void LinkSuccessNotice(){
        int i;
        for(i=0;i<observers.size();i++){
            observers.get(i).onGatewayLinkSuccess(this);
        }
    }

    public void LinkFailed(){
        int i;
        for(i=0;i<observers.size();i++){
            observers.get(i).onGatewayLinkFailed(this);
        }
    }

    public void SendNotice(byte[] data){
        int i;
        for(i=0;i<observers.size();i++){
            observers.get(i).onGatewaySend(this,data);
        }
    }

    public void RecvNotice(byte[] data){
        int i;
        for(i=0;i<observers.size();i++){
            observers.get(i).onGatewatRecv(this,data);
        }
    }

    public void CloseNotice(){
        int i;
        for(i=0;i<observers.size();i++){
            observers.get(i).onGatewayClose(this);
        }
    }

    public void sendData(byte[] data){
        try {
            outputStream.write(data);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        if(socket != null){
            try {
                listen = false;
                inputStream.close();
                outputStream.close();
                socket.close();
                inputStream = null;
                outputStream = null;
                socket = null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class Listen extends  Thread{
        private byte[] msg = new byte[4096];
        private byte[] data;
        private int recvLen;

        @Override
        public void run() {
            while(listen){
                try {
                    if((recvLen = inputStream.read(msg,0,msg.length)) != -1){
                        data = null;
                        data = new byte[recvLen];
                        System.arraycopy(msg,0,data,0,recvLen);
                        RecvNotice(data);
                    }
                } catch (IOException e) {
                    listen = false;
                    CloseNotice();
                    e.printStackTrace();
                }
            }
        }
    }

    public class Link extends Thread{
        @Override
        public void run() {
            listen = true;
            socket = null;
            outputStream = null;
            inputStream = null;
            socket = new Socket();
            try {
                socket.connect(address,2000);
                if(socket != null){
                    outputStream = socket.getOutputStream();
                    inputStream = socket.getInputStream();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
