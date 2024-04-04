export interface Exception {
  cause?: Throwable;
  localizedMessage?: string;
  message?: string;
  stackTrace?: StackTraceElement[];
  suppressed?: Throwable[];
}


export interface StackTraceElement {
  classLoaderName?: string;
  className?: string;
  fileName?: string;
  lineNumber?: number;
  methodName?: string;
  moduleName?: string;
  moduleVersion?: string;
  nativeMethod?: boolean;
}

export interface Throwable {
  cause?: Throwable;
  localizedMessage?: string;
  message?: string;
  stackTrace?: StackTraceElement[];
  suppressed?: Throwable[];
}


export interface IFormulaLog {
  exec?: number;
  data?: Record<string, any>;
  text?: string;
  type?: string;

  path?: string;
  val?: string;

  func?: string;
  paramLogs?: ParamLog[];
  mapLog?: Record<string, ParamLog>;

  lastAttr?: string;

  nodes?: ParamLog[];
  dataLog?: ParamLog[];

  suffix?: string;
  index?: number;
  indexLog?: IFormulaLog;
}

export interface ParamLog {
  code?: string;
  log?: IFormulaLog;
}
