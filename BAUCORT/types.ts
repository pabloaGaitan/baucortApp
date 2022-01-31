//eslint-disable-next-line @typescript-eslint/ban-types
export type AnyData = {};

export interface AsyncActionType {
  request: string;
  success: string;
  error: string;
}

export type Student = {
  id: number;
  name: string;
};

export type Entrie = {
  API?: string;
  Description?: string;
  HTTPS?: boolean;
};
