import { AnyAction } from 'redux';

// Types
import { UPDATE_USER_INFO } from '../actionTypes';

export const setIsUserLoggedIn = (isLoggedIn: boolean): AnyAction => ({
  type: UPDATE_USER_INFO.login,
  payload: isLoggedIn,
});

export const setIsUserName = (name: string): AnyAction => ({
  type: UPDATE_USER_INFO.userName,
  payload: name,
});
