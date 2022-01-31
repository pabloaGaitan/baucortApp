// Types
import { GET_DATA } from '../actionTypes';
import { Student } from '../../types';
import { AnyAction } from 'redux';

// @typescript-eslint/explicit-function-return-type

export const setEstudiantes = (data: Student[]): AnyAction => ({
  type: GET_DATA,
  payload: data,
});
