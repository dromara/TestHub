import React, { PureComponent } from 'react';
import classnames from 'classnames';
// import desktopStyle from './desktop.less';
import styles from './index.less';

// 只有本地开发时使用cdn，发布线上时要下载iconfont到 /assets/font
console.log("iconfont===" + __UMI_ENV__);
if (__UMI_ENV__ == 'local') {
  // if (__ENV === 'local') {
  let container = `
  /* 在线链接服务仅供平台体验和调试使用，平台不承诺服务的稳定性，企业客户需下载字体包自行发布使用并做好备份。 */
@font-face {
  font-family: 'iconfont';  /* Project id 4148545 */
  src: url('//at.alicdn.com/t/c/font_4148545_sx51mzjhc3g.woff2?t=1696732945326') format('woff2'),
       url('//at.alicdn.com/t/c/font_4148545_sx51mzjhc3g.woff?t=1696732945326') format('woff'),
       url('//at.alicdn.com/t/c/font_4148545_sx51mzjhc3g.ttf?t=1696732945326') format('truetype');
}
    `
  let style = document.createElement("style");
  style.type = "text/css";
  document.head.appendChild(style);
  style.appendChild(document.createTextNode(container));
}
export default class Iconfont extends PureComponent<
  {
    code: string;
    size?: number;
  } & React.DetailedHTMLProps<React.HTMLAttributes<HTMLElement>, HTMLElement>
> {
  render() {
    return (
      <i {...this.props} className={classnames(this.props.className, styles.iconfont)} style={{ fontSize: this.props.size ? this.props.size + "px" : undefined }}>
        {this.props.code}
      </i>
    );
  }
}
