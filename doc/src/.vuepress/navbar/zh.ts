import { navbar } from "vuepress-theme-hope";

export const zhNavbar = navbar([

  {
    text: "🏡首页",
    link: "/README.md",
    // // 仅在 `/zh/guide/` 激活
    // activeMatch: "^/zh/guide/$",
  },
  { text: "📖使用文档", link: "/ability/README.md" },
  // {
  //   text: "📒javaDoc",
  //   link: "https://apidoc.gitee.com/dromara/sms4j"
  // },
  { text: "🏮gitee", link: "https://gitee.com/failedgoddess/TestHub" },
  // { text: "🪀github", link: "https://github.com/dromara/SMSAggregation" },
  // { text: "🤝Dromara组织", link: "https://dromara.org/zh/" },
  { text: "🔍常见问题", link: "/issue" },
  { text: "💡更新日志", link: "/log" },
  // { text: "🎎贡献者", link: "/doc/developer.md" },
  { text: "👪加入交流群", link: "/group", },

]);
