// Client
import { doAsync } from '../../api/clientApi';

// Redux
import { ThunkDispatch } from 'redux-thunk';
import { AnyAction } from 'redux';
import { State } from '../reducers/root.reducers';

// Types
import { AnyData, Entrie } from '../../types';
import { FETCH_ENTRIES } from '../actionTypes';

type APIResponse = {
  count?: number;
  entries: Entrie[];
};

export const fetchEntries =
  () =>
  async (
    dispatch: ThunkDispatch<State, undefined, AnyAction>,
  ): Promise<AnyData | void> => {
    try {
      return await doAsync(
        dispatch,
        FETCH_ENTRIES,
        `https://api.publicapis.org/entries`,
        {
          method: 'get',
        },
        {
          // enviamos esto para que arrojé el error y lo tomemos en el catch de abajo y no maté el app sino que nos guarde el error en el state y lo podamos leer
          throwError: true,
          mapSuccess: (ApiEntries: APIResponse) => {
            // como ejemplo, vamos a filtrar solo las que son HTTPS, sabemos
            // UNA VEZ LLAMAMOS ESTO, PODEMOS VEL EL ESTATE DE TODA LA APP POR EL TAB DE REDUX EN LA CONSOLA
            return ApiEntries?.entries.filter((entrie) => entrie.HTTPS);
          },
        },
      );
    } catch (err) {
      console.trace(err.message);
      dispatch({
        type: FETCH_ENTRIES.error,
        payload: err.message,
      });
    }
  };
