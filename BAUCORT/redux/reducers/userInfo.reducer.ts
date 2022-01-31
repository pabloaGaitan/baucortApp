import { AnyAction } from 'redux';

// Types
import { UPDATE_USER_INFO } from '../actionTypes';

export interface UserInfoState {
  isLoggedIn: boolean;
  name: string;
}

export const initialState: UserInfoState = {
  isLoggedIn: true,
  name: '',
};

function userInfoReducer(
  state = initialState,
  action: AnyAction,
): UserInfoState {
  switch (action.type) {
    case UPDATE_USER_INFO.login:
      return {
        ...state,
        isLoggedIn: action.payload,
      };
    case UPDATE_USER_INFO.userName:
      return {
        ...state,
        name: action.payload,
      };

    default:
      return state;
  }
}

export default userInfoReducer;
