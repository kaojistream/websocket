package com.ws.demo;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WsOriginApplication {
	static int i=0;
	public static void main(String[] args) {
		SpringApplication.run(WsOriginApplication.class, args);
		schMessgae();
	}
	
	public static void schMessgae() {
		TimerTask task = new TimerTask() {  
            @Override  
            public void run() {  
                // task to run goes here  
                System.out.println("发送消息："+"time:"+System.currentTimeMillis()+"date:"+new Date().toString());
                try {
                	 convertAndSend(i++);
                }catch(IOException e) {
                		e.printStackTrace();
                }
                
            }  
        };  
        Timer timer = new Timer();  
        long delay = 40*1000; //首次执行延迟40s,给初始化留出时间 
        long intevalPeriod =  30*60*1000; //推送间隔30分钟 
        // schedules the task to be run in an interval  
        timer.scheduleAtFixedRate(task, delay, intevalPeriod); 
	}
	
	 public static void convertAndSend(int i) throws IOException{
		 MyWebSocket.sendToAll("This is a message from ws server,No."+i);
	 }
}
