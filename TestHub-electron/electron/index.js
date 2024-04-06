const { Application } = require('ee-core');
const { ipcMain } = require("electron");
const url = require("url");
const UtilsPs = require('ee-core/ps');
const Log = require('ee-core/log');
const Conf = require('ee-core/config');

class Index extends Application {

  constructor() {
    super();
    // this === eeApp;
  }

  /**
   * core app have been loaded
   */
  async ready () {
    // do some things
  }

  /**
   * electron app ready
   */
  async electronAppReady () {
    // do some things
  }

  /**
   * main window have been loaded
   */
  async windowReady () {
    // do some things
    // 延迟加载，无白屏
    const winOpt = this.config.windowsOption;
    if (winOpt.show == false) {
      const win = this.electron.mainWindow;
      win.once('ready-to-show', () => {
        win.show();
      })
    }
    ipcMain.on("login-success", () => {

      Log.info("[mian:indexPath]"+Conf.getValue('mainServer.indexPath'));
      Log.info("[mian:getHomeDir] ", UtilsPs.getHomeDir());

      // 页面导航
      this.electron.mainWindow.loadURL(
        url.format({
          pathname: UtilsPs.getHomeDir()+"/"+Conf.getValue('mainServer.indexPath'),
          protocol: "file:",
          slashes: true,
        })
      );
    })
  }

  /**
   * before app close
   */  
  async beforeClose () {
    // do some things

  }
}

Index.toString = () => '[class Index]';
module.exports = Index;