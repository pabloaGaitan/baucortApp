import { combineReducers } from 'redux';

// Reducers
import estudianteReducer, {
  Estudiante,
  initialState as initialEstudiantesData,
} from './estudiantes.reducer';
import entrieReducer, {
  EntrieState,
  initialState as initialEntrieData,
} from './entries.reducer';
import userInfoReducer, {
  UserInfoState,
  initialState as initialUserInfoData,
} from './userInfo.reducer';

// Types
import { AnyData } from '../../types';

type PreloadedStateType = (appData: AnyData) => {
  estudiantes: Estudiante;
  entries: EntrieState;
  userInfo: UserInfoState;
};

const preloadedState: PreloadedStateType = (appData) => ({
  estudiantes: initialEstudiantesData,
  entries: initialEntrieData,
  userInfo: initialUserInfoData,
  ...appData,
});

export interface State {
  estudiantes: Estudiante;
  entries: EntrieState;
  userInfo: UserInfoState;
}

export { preloadedState };
export default combineReducers<State>({
  estudiantes: estudianteReducer,
  entries: entrieReducer,
  userInfo: userInfoReducer,
});
