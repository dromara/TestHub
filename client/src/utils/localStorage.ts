import { IUserInfo } from "@/typings";
import { LangType, PrimaryColorType, ThemeType } from "./constants";

export function getLang(): LangType {
  return (localStorage.getItem('lang') as LangType) || 'en-us';
}

export function setLang(lang: LangType) {
  return localStorage.setItem('lang', lang);
}

export function getTheme(): ThemeType {
  return (localStorage.getItem('theme') as ThemeType) || ThemeType.Default;
}

export function setTheme(theme: ThemeType) {
  return localStorage.setItem('theme', theme);
}

export function getPrimaryColor(): PrimaryColorType {
  return (localStorage.getItem('primary-color') as PrimaryColorType) || PrimaryColorType.Polar_Blue;
}

export function setPrimaryColor(primaryColor: PrimaryColorType) {
  return localStorage.setItem('primary-color', primaryColor);
}

export function getSatoken(): string {
  return (localStorage.getItem('satoken') as string) || "";
}
export function setSatoken(satoken: string) {
  return localStorage.setItem('satoken', satoken);
}
export function getUserInfo(): IUserInfo {
  const userInfo = localStorage.getItem('userInfo');
  if (userInfo) {
    return JSON.parse(userInfo);
  }
  return { rememberMe: true } as IUserInfo;
}

export function setUserInfo(userInfo: IUserInfo) {
  return localStorage.setItem('userInfo', JSON.stringify(userInfo));
}

export function removeUserInfo() {
  return localStorage.removeItem('userInfo');
}



