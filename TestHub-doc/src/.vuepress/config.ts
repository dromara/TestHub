import { defineUserConfig } from "vuepress";
import theme from "./theme.js";

export default defineUserConfig({
  base: "/",

  lang: "zh-CN",
  title: "TestHub",
  description: "TestHub 的文档演示",

  theme,

  // Enable it with pwa
  // shouldPrefetch: false,
});
