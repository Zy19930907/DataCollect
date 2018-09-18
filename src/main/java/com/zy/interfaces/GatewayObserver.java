package com.zy.interfaces;

import com.zy.gateway.VoiceGateway;

public interface GatewayObserver {
    public void onGatewayCreat(VoiceGateway voiceGateway);
    public void onGatewayLinkStart(VoiceGateway voiceGateway);
    public void onGatewayLinkSuccess(VoiceGateway voiceGateway);
    public void onGatewayLinkFailed(VoiceGateway voiceGateway);
    public void onGatewayClose(VoiceGateway voiceGateway);
    public void onGatewaySend(VoiceGateway voiceGateway,byte[] data);
    public void onGatewatRecv(VoiceGateway voiceGateway,byte[] data);
}
