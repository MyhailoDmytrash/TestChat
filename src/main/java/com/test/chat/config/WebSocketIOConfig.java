package com.test.chat.config;

import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketIOConfig
{
    @Value("${socketio.host}")
    protected String host;

    @Value("${socketio.port}")
    protected Integer port;

    @Value("${socketio.bossCount}")
    protected int bossCount;

    @Value("${socketio.workCount}")
    protected int workCount;

    @Value("${socketio.allowCustomRequests}")
    protected boolean allowCustomRequests;

    @Value("${socketio.upgradeTimeout}")
    protected int upgradeTimeout;

    @Value("${socketio.pingTimeout}")
    protected int pingTimeout;

    @Value("${socketio.pingInterval}")
    protected int pingInterval;

    @Bean
    public SocketIOServer socketIOServer()
    {
        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);

        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setSocketConfig(socketConfig);
        config.setHostname(host);
        config.setPort(port);
        config.setBossThreads(bossCount);
        config.setWorkerThreads(workCount);
        config.setAllowCustomRequests(allowCustomRequests);
        config.setUpgradeTimeout(upgradeTimeout);
        config.setPingTimeout(pingTimeout);
        config.setPingInterval(pingInterval);

        return new SocketIOServer(config);
    }
}
