import { configureStore, DeepPartial } from '@reduxjs/toolkit';
import { MakeStore, createWrapper, Context } from 'next-redux-wrapper';

// Reducer
import rootReducer, { State } from './reducers/root.reducers';

// create a makeStore function
export const makeStore: MakeStore<State> = (
  // @ts-ignore
  context?: Context,
  preloadedState?: DeepPartial<State>,
) =>
  configureStore({
    reducer: rootReducer,
    // @ts-ignore
    preloadedState,
  });

// export an assembled wrapper
export const wrapper = createWrapper<State>(makeStore, { debug: true });
