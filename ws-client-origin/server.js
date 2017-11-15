/**
 * Created by huangzc on 2017/11/9.
 */
'use strict';
const restify = require('restify');
const messages = require('./messages');
const exe = require('child_process');
let childP;//子进程

const server = restify.createServer();

//提供接口查看来自sever的消息
server.get('/messages', function (req, res, next) {
  res.send(messages.getM());
  next();
});

server.listen(8081, function () {
  console.log('%s listening at %s', server.name, server.url);
  childP = child();
  //接收来自子进程的消息
  childP.on('message', (m) => {
    console.log('PARENT got message', m);
    messages.saveM(m);
  });
});

function child() {
  //启动子进程
  return exe.fork('index.js')
}



