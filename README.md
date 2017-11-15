# websocket
原材料：</br>
jdk8</br>
spring tool suite</br>
node 8.9.1</br>
webstorm（node ide 推荐使用）</br>
</br>
spring+websocket server&amp;nodejs websocket client</br>
包含websocket nodejs客户端和websocket Java服务端</br>
</br>
server：</br>
</br>
采用spring + websocket，并执行了一个定时推送任务</br>
1.将ws-server-origin目录导入spring tool suite（创建spring项目的ide）</br>
2.运行其中的application，即可跑起来ws server，默认监听8080端口</br>
</br>
client：</br>
</br>
包含一个主进程和一个子进程，主进程运行restify服务，提供推送消息展示接口，子进程运行websocket服务</br>
1.进入ws-client-origin目录</br>
2.执行npm install，将所需依赖下载到项目下面</br>
3.执行node server.js即可连接ws服务端</br>
</br>
访问localhost:8081/messages，可以查看客户端接收到的推送消息
