import * as React from 'react';
import { AppProps, AppContext } from 'next/app';

// Store
import { wrapper } from '../redux/store';

// Components
import Layout from '../components/Layout';
import Login from '../components/Login';

// Redux
import { useSelector } from 'react-redux';

// Types
import { State } from '../redux/reducers/root.reducers';

// Hooks
import { useRouter } from 'next/router';

type GetInitialProps = (context: AppContext) => unknown;

const WrappedApp: React.FC<AppProps> & { getInitialProps: GetInitialProps } = ({
  Component,
  pageProps,
}) => {
  const isLoggedIn = useSelector<State, boolean>(
    (state) => state.userInfo.isLoggedIn,
  );

  const router = useRouter();

  React.useEffect(() => {
    if (!isLoggedIn) {
      router.push('/Login');
    }

    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  return (
    <Layout>{isLoggedIn ? <Component {...pageProps} /> : <Login />}</Layout>
  );
};

// Acá puede llamar data cada vez que se hace el build de la applicacion, es decir, puede llamar data una vez para toda la app, si lo necesita me avisa,
// o si necesita data cada vez que cambie de página también lo puede hacer, todo esto desde el server, así le llega cargada al momento de renderizar
// o la puede llamar desde el client side (más fácil) como le dejé de ejemplo
WrappedApp.getInitialProps = async ({ Component, ctx }: AppContext) => {
  return {
    pageProps: {
      // Call page-level getInitialProps
      ...(Component.getInitialProps
        ? await Component.getInitialProps(ctx)
        : {}),
      // Some custom thing for all pages
      pathname: ctx.pathname,
    },
  };
};

export default wrapper.withRedux(WrappedApp);
