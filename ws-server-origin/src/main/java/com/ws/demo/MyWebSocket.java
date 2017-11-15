package com.ws.demo;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

@ServerEndpoint("/websocket")  
@Component  
public class MyWebSocket {  
  
    private static int onlineCount = 0;  
  
    private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<>();  
  
    private Session session;  
  
    @OnOpen  
    public void onOpen (Session session){  
        this.session = session;
        session.setMaxIdleTimeout(432000000);//5*86400*1000ms，这项修改了没用，会话还是会断开
        System.out.println(session.getMaxIdleTimeout());
        webSocketSet.add(this);  
        addOnlineCount();  
        System.out.println("有新链接加入!当前在线人数为" + getOnlineCount()+"time:"+System.currentTimeMillis()+new Date().toString());  
    }  
  
    @OnClose  
    public void onClose (){  
        webSocketSet.remove(this);  
        subOnlineCount();  
        System.out.println("有一链接关闭!当前在线人数为" + getOnlineCount()+"time:"+System.currentTimeMillis()+new Date().toString());  
    }  
  
    @OnMessage  
    public void onMessage (String message, Session session) throws IOException {  
        System.out.println("来自客户端的消息:" + message+new Date().toString());  
//        // 群发消息  
//        for ( MyWebSocket item : webSocketSet ){  
//                item.sendMessage(message);  
//        }  
    }  
  
    public void sendMessage (String message) throws IOException {  
        this.session.getBasicRemote().sendText(message);  
    } 
    
    public static void sendToAll(String message) throws IOException{
    	 // 群发消息  
        for ( MyWebSocket item : webSocketSet ){  
                item.sendMessage(message);  
        }  
    }
  
    public static synchronized  int getOnlineCount (){  
        return MyWebSocket.onlineCount;  
    }  
  
    public static synchronized void addOnlineCount (){  
        MyWebSocket.onlineCount++;  
    }  
  
    public static synchronized void subOnlineCount (){  
        MyWebSocket.onlineCount--;  
    }  
  
} 