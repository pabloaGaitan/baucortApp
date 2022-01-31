import React from 'react';
import Link from 'next/link';

// Redux
import { useSelector } from 'react-redux';

// Types
import { State } from '../../redux/reducers/root.reducers';

// Styles
import styles from './Menu.module.scss';

const IndexPage: React.FC = () => {
  const isLoggedIn = useSelector<State, boolean>(
    (state) => state.userInfo.isLoggedIn,
  );

  return (
    <>
      {isLoggedIn && (
        <div className={styles.container}>
          <Link href="/Reporte-asistencias">
            <a>Reporte de Asistencias</a>
          </Link>

          <Link href="/Example">
            <a>Example</a>
          </Link>
        </div>
      )}
    </>
  );
};

export default IndexPage;
