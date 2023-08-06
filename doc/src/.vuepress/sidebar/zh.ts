import { sidebar } from "vuepress-theme-hope";

export const zhSidebar = sidebar({
  "/": [
    "",
    {
      text: "可以干什么",
      icon: "laptop-code",
      prefix: "ability/",
      link: "ability/",
      children: "structure",
    },
    {
      text: "灵活定制",
      icon: "code",
      prefix: "custom/",
      link: "custom/",
      children: "structure",
    },
    {
      text: "原理介绍",
      icon: "book",
      prefix: "principle/",
      link: "principle/",
      children: "structure",
    },
    {
      text: "全局配置",
      icon: "gears",
      prefix: "global/",
      link: "global/",
      children: "structure",
    },
  ],
});
