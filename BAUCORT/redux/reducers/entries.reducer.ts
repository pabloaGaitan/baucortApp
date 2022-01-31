import { AnyAction } from 'redux';

// Types
import { FETCH_ENTRIES } from '../actionTypes';
import { Entrie } from '../../types';

export interface EntrieState {
  isLoading: boolean;
  data: Entrie[];
  error: string | null;
}

export const initialState: EntrieState = {
  isLoading: false,
  data: [],
  error: null,
};

function entrieReducer(state = initialState, action: AnyAction): EntrieState {
  switch (action.type) {
    case FETCH_ENTRIES.request:
      return {
        ...state,
        isLoading: true,
        error: null,
      };
    case FETCH_ENTRIES.success:
      return {
        ...state,
        isLoading: false,
        data: action.payload,
      };
    case FETCH_ENTRIES.error:
      return {
        ...state,
        isLoading: false,
        error: action.payload,
      };
    default:
      return state;
  }
}

export default entrieReducer;
