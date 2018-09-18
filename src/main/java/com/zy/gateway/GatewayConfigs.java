package com.zy.gateway;

import java.util.ArrayList;
import java.util.List;

public class GatewayConfigs {
    private List<GatewayConfig> configs = new ArrayList<GatewayConfig>();

    public void addGatewayConfig(GatewayConfig config){
        int i;
        for(i=0;i<configs.size();i++){
            if(configs.get(i).getGatewayIp().equals(config.getGatewayIp())){
                configs.set(i,config);
            }
        }
    }
}
