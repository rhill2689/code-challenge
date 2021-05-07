import { IUser } from 'app/shared/model/user.model';

export interface IPlan {
  id?: number;
  plan?: string;
  deductible?: number;
  coPay?: number;
  user?: IUser;
}

export const defaultValue: Readonly<IPlan> = {};
