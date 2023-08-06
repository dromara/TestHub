'use strict';

const path = require('path');
const fs = require('fs');
const { exec } = require('child_process');
const { Controller } = require('ee-core');
const { app: electronApp, shell } = require('electron');
const dayjs = require('dayjs');
const Ps = require('ee-core/ps');
const Log = require('ee-core/log');
const Services = require('ee-core/services');
const Conf = require('ee-core/config');
const Addon = require('ee-core/addon');
const EE = require('ee-core/ee');

/**
 * electron-egg framework - 功能demo
 * @class
 */
class FrameworkController extends Controller {

  constructor(ctx) {
    super(ctx);
  }

  /**
   * java运行状态
   */ 
  async runStatus() {
    let data = {
      code: 0,
      msg: '',
      flag: false
    }
    const flag =  await Addon.get('javaServer').check();
    Log.info("[FrameworkController:runStatus] flag-----------"+flag);
    data.flag = flag;
    
    return data;
  }
  /**
   * 启动java项目
   */ 
  async startJavaServer(port) {
    let data = {
      code: 0,
      msg: '',
      server: ''
    }
    console.log()
    const javaCfg = Conf.getValue('addons.javaServer') || {};
    if (!javaCfg.enable) {
      data.code = -1;
      data.msg = 'addon not enabled!';
      return data;
    }

    await Addon.get('javaServer').createServer(port);

    data.server = 'http://localhost:' + javaCfg.port;

    return data;
  }
  /**
   * 关闭java项目
   */ 
  async closeJavaServer() {
    let data = {
      code: 0,
      msg: '',
    }
    const javaCfg = Conf.getValue('addons.javaServer') || {};
    if (!javaCfg.enable) {
      data.code = -1;
      data.msg = 'addon not enabled!';
      return data;
    }

    await Addon.get('javaServer').kill();

    return data;
  }
}

FrameworkController.toString = () => '[class FrameworkController]';
module.exports = FrameworkController;  