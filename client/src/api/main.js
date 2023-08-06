
/**
 * 主进程与渲染进程通信频道定义
 * Definition of communication channels between main process and rendering process
 */
const ipcApiRoute = {
  // framework
  javaServerStatus: 'controller.framework.runStatus',
  startJavaServer: 'controller.framework.startJavaServer',
  closeJavaServer: 'controller.framework.closeJavaServer',
  
}

export {
  ipcApiRoute
}

