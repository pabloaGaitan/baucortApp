import React from 'react';

// Actions
import { setEstudiantes } from '../../redux/actions/estudiantes.actions';
import { fetchEntries } from '../../redux/actions/entries.actions';

// Redux
import { useDispatch, useSelector } from 'react-redux';

// Types
import { Student, Entrie } from '../../types';
import { State } from '../../redux/reducers/root.reducers';

// MockData
import { sampleUserData } from '../../mockData/example';

const ReporteAsistencias: React.FC = () => {
  const dispatch = useDispatch();

  // ejemplo para usar un state interno, inicializamos con 0
  const [cont, setCont] = React.useState(0);

  // ejemplo de lectura del state  ala data de estudiantes
  const estudiantes = useSelector<State, Student[]>(
    (state) => state.estudiantes.data,
  );
  const entries = useSelector<State, Entrie[]>((state) => state.entries.data);
  const isEntriesLoading = useSelector<State, boolean>(
    (state) => state.entries.isLoading,
  );
  const entriesError = useSelector<State, null | string>(
    (state) => state.entries.error,
  );

  React.useEffect(
    () => {
      // Vamos a modificar un state interno (estudiantes)
      dispatch(setEstudiantes(sampleUserData));

      // Vamos a llamar el endpoint de ejemplo (el timeout es para simular demora): verá el loading justo antes de la repsuesta porque el delay es para todo el llamado
      // igual puede simular una red lenta en la consola y vera el comportamiento
      const timer = setTimeout(() => {
        dispatch(fetchEntries());
      }, 5000);
      return () => clearTimeout(timer);
    },

    /*
    esto se conoce como hook, le recomiendo leer osbre este al menos pero en resumen:
    Si deja el array de abajo (dependencia) vacío, solo se va a ejectura lo que haya en el hook una vez, si agrega alguna dependencia
    cada vez que esta cambien, se va a ejecutar lo que hay internamente.

    Si crea algun listener o algo, con agregar return lo puede destruir, el return es que hacer antes de destruir el componentes (como el timer que simula un delay en el enpoint)
  */
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [],
  );

  // este hook se usa tipicamente para funciones
  const onButtonClick = React.useCallback(
    () => {
      setCont(cont + 1);
    },

    // en este caso es importante siempre agregar las dependencias para el correcto funcionamiento
    [setCont, cont],
  );

  // este hook también necesita todas las dependencias, se usa usualmente para objetos que se component de varias cosas
  // se usan los hooks para que no se renderice siempre, esto revisa si las dependencias cambian o no para ejecutarse.
  const currentStudents = React.useMemo(
    () =>
      estudiantes.map((estudiante) => ({
        ...estudiante,
        id: estudiante.id + cont,
      })),
    [estudiantes, cont],
  );

  return (
    <div>
      <h1>Reporte de Asistencias</h1>
      <button onClick={onButtonClick}>
        Click para aumentar conteo: {cont}
      </button>
      <ul>
        {currentStudents.map((student, index) => (
          <li key={index}>
            <span>{student.id}</span>
            <span>{student.name}</span>
          </li>
        ))}
      </ul>

      {entriesError ? (
        `Error al llamar Entries: ${entriesError}`
      ) : isEntriesLoading ? (
        'Loading...'
      ) : (
        <ul>
          {entries.map((entrie, index) => (
            <li key={index}>
              <span>{entrie?.API}</span>
              <span>{entrie?.Description}</span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default ReporteAsistencias;
