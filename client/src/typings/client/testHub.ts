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