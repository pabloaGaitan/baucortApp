import { AnyAction } from '@reduxjs/toolkit';

// Types
import { GET_DATA } from '../actionTypes';
import { Student } from '../../types';

export interface Estudiante {
  data: Student[];
  error?: string;
  isLoading: boolean;
}

export const initialState = {
  data: [{ id: 0, name: '' }],
  error: '',
  isLoading: false,
};

const dataReducer = (
  state: Estudiante = initialState,
  action: AnyAction,
): Estudiante => {
  switch (action.type) {
    case GET_DATA:
      return { ...state, data: action.payload };
    default:
      return state;
  }
};

export default dataReducer;
