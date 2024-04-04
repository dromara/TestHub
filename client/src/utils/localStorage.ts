import { ThemeType, PrimaryColorType, LangType } from '@/constants';
import { RuleProjectSimpleResDto } from '@/typings/server/project';
import { IUserData } from '@/typings/server/user';


export function getCurrentProject(): RuleProjectSimpleResDto | null {
  const project = localStorage.getItem('currentProject');
  if (project) {
    return JSON.parse(project);
  }
  return null;
}
export function setCurrentProject(project: RuleProjectSimpleResDto) {
  return localStorage.setItem('currentProject', JSON.stringify(project));
}

export function removeCurrentProject() {
  return localStorage.removeItem('currentProject');
}

export function getSatoken(): string {
  return (localStorage.getItem('satoken') as string) || "";
}
export function setSatoken(satoken: string) {
  return localStorage.setItem('satoken', satoken);
}
export function removeSatoken() {
  return localStorage.removeItem('satoken');
}

export function getUserInfo(): IUserData {
  const userInfo = localStorage.getItem('userInfo');
  if (userInfo) {
    return JSON.parse(userInfo);
  }
  return { remember: true } as IUserData;
}

export function setUserInfo(userInfo: IUserData) {
  return localStorage.setItem('userInfo', JSON.stringify(userInfo));
}

export function removeUserInfo() {
  return localStorage.removeItem('userInfo');
}


export function getLocalPort(): string {
  let port = localStorage.getItem('_APP_PORT')
  if (port != null) {
    try {
      return parseInt(port) + "";
    } catch (e) {
      return __APP_PORT__;
    }
  }
  return __APP_PORT__;
}

export function setLocalPort(port: number) {
  return localStorage.setItem('_APP_PORT', port + "");
}

export function getModel(): string {
  return (localStorage.getItem('_MODEL') as LangType) || null;
}

export function setModel(model: string) {
  return localStorage.setItem('_MODEL', model);
}

export function getRemoteUrl(): string {
  return (localStorage.getItem('_BaseURL') as string) || window._BaseURL;
}

export function setRemoteUrl(url: string) {
  return localStorage.setItem('_BaseURL', url);
}

export function getLang(): LangType {
  return (localStorage.getItem('lang') as LangType) || 'zh-cn';
}

export function setLang(lang: LangType) {
  return localStorage.setItem('lang', lang);
}

export function getTheme(): ThemeType {
  const themeColor: any = localStorage.getItem('theme') as ThemeType
  if (themeColor) {
    return themeColor
  }
  localStorage.setItem('theme', ThemeType.FollowOs)
  // 默认主题色
  return ThemeType.FollowOs
}

export function setTheme(theme: ThemeType) {
  return localStorage.setItem('theme', theme);
}

export function getPrimaryColor(): PrimaryColorType {
  const primaryColor = localStorage.getItem('primary-color') as PrimaryColorType
  if (primaryColor) {
    return primaryColor
  }
  localStorage.setItem('primary-color', PrimaryColorType.Golden_Purple)
  // 默认主题色
  return PrimaryColorType.Golden_Purple
}

export function setPrimaryColor(primaryColor: PrimaryColorType) {
  return localStorage.setItem('primary-color', primaryColor);
}

