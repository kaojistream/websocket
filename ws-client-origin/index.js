/**
 * Created by huangzc on 2017/11/9.
 * 子进程 进行websocket通信，基于stomp消息内容协议
 */
'use strict';
const WebSocket = require('ws');
let ws;

function init() {
  // ws = new WebSocket('ws://localhost:8080/websocket');
  ws = new WebSocket('ws://139.219.60.202:8080/websocket');
  ws.on('open', function open() {
    console.log('连接ws成功');
    // ws.send('something');
    setInterval(function () {
      check()
    }, 2 * 60 * 1000);
  });

  ws.on('message', function incoming(data) {
    console.log('收到服务端消息：', data);
    saveM('content:' + data + ',time:' + Date.now() + ':' + new Date().toLocaleString());
  });

  ws.on('close', function close() {
    console.log('ws disconnected');
    init();
  });

  ws.on('error', function (reason) {
    console.log('ws error', reason);
    init();
  });
}

function check() {
  ws.send('heart beat');//发送心跳包，防止一段时间没有信息后，服务器会断开会话
}

init();


//连接server
// client.connect({}, function (frame) {
//   console.log('Connected:' + frame);
//   //订阅ws server消息
//   client.subscribe('/topic/getResponse', function (response) {
//     let message = JSON.parse(response.body).responseMessage;
//     console.log(message);
//     //保存消息到本地
//     saveM('content:' + message + ',time:' + Date.now() + ':' + new Date().toLocaleString());
//   });
//
//   // client.send("/welcome", {}, JSON.stringify({'name': 'hzc'}));
// }, function (err) {
//   console.log(err)
// });


function saveM(str) {
  //向父进程发消息
  process.send(str);
}





