package com.zy.gateway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zy.interfaces.GatewayObserver;
import com.zy.tools.LogRecoder;

import java.util.ArrayList;
import java.util.List;

public class GatewayManger implements GatewayObserver {
    private List<VoiceGateway> voiceGateways = new ArrayList<VoiceGateway>();
    private LogRecoder recoder = new LogRecoder();
    private Gson gson;
    private GsonBuilder gsonBuilder;

    public void CreatGateway(String ipString){
        VoiceGateway voiceGateway = new VoiceGateway(ipString);
        voiceGateways.add(voiceGateway);
    }

    public void onGatewayCreat(VoiceGateway voiceGateway) {

    }

    public void onGatewayLinkStart(VoiceGateway voiceGateway) {

    }

    public void onGatewayLinkSuccess(VoiceGateway voiceGateway) {

    }

    public void onGatewayLinkFailed(VoiceGateway voiceGateway) {

    }

    public void onGatewayClose(VoiceGateway voiceGateway) {

    }

    public void onGatewaySend(VoiceGateway voiceGateway, byte[] data) {

    }

    public void onGatewatRecv(VoiceGateway voiceGateway, byte[] data) {

    }
}
