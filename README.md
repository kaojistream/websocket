# websocket
spring+websocket server&amp;nodejs websocket client
包含websocket nodejs客户端和websocket Java服务端

server：

采用spring + websocket，并执行了一个定时推送任务
1.将ws-server-origin目录导入spring tool suite（创建spring项目的ide）
2.运行其中的application，即可跑起来ws server，默认监听8080端口

client：

包含一个主进程和一个子进程，主进程运行restify服务，提供推送消息展示接口，子进程运行websocket服务
1.进入ws-client-origin目录
2.执行npm install，将所需依赖下载到项目下面
3.执行node server.js即可连接ws服务端
