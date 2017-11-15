/**
 * Created by huangzc on 2017/11/9.
 */
'use strict';
let arr = [];
exports.saveM = function (str) {
  arr.unshift(str)
};

exports.getM = function () {
  return arr;
};