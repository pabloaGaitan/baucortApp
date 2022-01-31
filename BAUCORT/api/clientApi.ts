import Axios, { AxiosRequestConfig } from 'axios';

// Types
import { Dispatch } from 'react';
import { AnyAction } from 'redux';
import { AsyncActionType, AnyData } from '../types';

/* Esta es una funcion que sirve para multiples escenarios cuando llama un endpoint
  maneja los status de isLoading (cuando hace el llamada, así puede agregar un state de loading mientras llega la data si lo desea),
  maneja el caso de success o fail, si ud agregar ciertos atributos el endpoint falla y ud puede mostrar el error, digamos puede
  agregar un icono o mensjae de error y que vuelva a probar, si no envia estos atributos (throwError) el arrojara el error y lo manejara NEXTjs por default
  y creo que lo lleva a la página de error

  También hay una opcion de mapSuccess donde puede cambiar la data que le envía el endpoint a como la necesité, (la puede trasnformar antes de guardarla en redux)
*/
const doAsync = async <
  T extends AnyData,
  R = void,
  S = T,
  E = string | number | (string | number)[],
>(
  dispatch: Dispatch<AnyAction>,
  action: AsyncActionType,
  url: string,
  axiosConfig?: AxiosRequestConfig,
  options?: {
    throwError?: boolean;
    mapRequest?: () => R;
    mapSuccess?: (payload: T) => S;
    mapError?: (payload: unknown) => E;
  },
): Promise<T | void> => {
  const {
    mapRequest = () => undefined,
    mapSuccess = (x: T): T => x,
    mapError = (e: unknown) => e,
  } = options || {};

  dispatch({ type: action.request, payload: mapRequest() });

  try {
    const { data } = await Axios({
      url,
      ...axiosConfig,
    });

    dispatch({ type: action.success, payload: mapSuccess(data) });

    return data;
  } catch (err) {
    if (Axios.isCancel(err)) {
      // Si cancela el token del request
      console.warn('clientApi: the cancel token cancelled the request', err);
    } else {
      if (options?.throwError) {
        // Si en las opciones envía esto, manejara el error en el state de redux
        console.trace('Do Async ignoring and re-throwing error', err);
        throw err;
      } else {
        // arroja error normal en el APP
        console.error('clientApi Error', err);
        console.trace('clientApi Error', err);

        dispatch({ type: action.error, payload: mapError(err.message) });
      }
    }
  }
};

export { doAsync };
