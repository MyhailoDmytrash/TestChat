package com.test.chat.services.impl;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.test.chat.enums.EventType;
import com.test.chat.models.dtos.MessageDTO;
import com.test.chat.models.entities.Message;
import com.test.chat.services.SocketIOService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocketIOServiceImpl implements SocketIOService
{
    protected static Map<String, SocketIOClient> admins = new ConcurrentHashMap<>();

    protected final SocketIOServer socketIOServer;
    protected final ModelMapper modelMapper;

    @PostConstruct
    protected void onInit()
    {
        start();
    }

    @PreDestroy
    private void onDestroy()
    {
        stop();
    }

    @Override
    public void start()
    {
        socketIOServer.addConnectListener(client -> {
            Optional.ofNullable(getParamsByClient(client))
                    .ifPresent(adminEmail -> {
                        log.info("Listener " + adminEmail + " connected");
                        admins.put(adminEmail, client);
                    });
        });

        socketIOServer.addDisconnectListener(client -> {
            Optional.ofNullable(getParamsByClient(client))
                    .ifPresent(adminEmail -> {
                        log.info("Listener" + adminEmail + " connected");
                        admins.remove(adminEmail);
                        client.disconnect();
                    });
        });

        //socketIOServer.addEventListener("a", String.class, (client, data, ackSender) -> {});

        socketIOServer.start();
        log.info("WebSocket has started");
    }

    @Override
    public void stop()
    {
        socketIOServer.stop();
        log.info("WebSocket has stopped");
    }

    @Override
    public void sendMessageToUser(@NonNull Message message)
    {
        Optional.ofNullable(message.getChat().getAdmin()).ifPresentOrElse(admin -> {
            Optional.ofNullable(admins.get(admin.getEmail()))
                    .ifPresent(adminListener -> sendMessageToAdmin(adminListener, message));
        },
                () -> {
                    admins.forEach((key, value) -> sendMessageToAdmin(value, message));
                });
    }

    protected void sendMessageToAdmin(SocketIOClient admin, Message message)
    {
        admin.sendEvent(EventType.SEND_MESSAGE_EVENT.name(), modelMapper.map(message, MessageDTO.class));
    }

    protected String getParamsByClient(SocketIOClient client)
    {
        Map<String, List<String>> params = client.getHandshakeData().getUrlParams();
        Optional<List<String>> usernameList = Optional.ofNullable(params.get(USERNAME_SOCKET_HEADER));

        return usernameList.map(list -> list.get(0)).orElse(null);
    }
}
