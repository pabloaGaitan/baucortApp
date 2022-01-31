import React from 'react';

// Redux
import { useSelector, useDispatch } from 'react-redux';

// Actions
import {
  setIsUserLoggedIn,
  setIsUserName,
} from '../../redux/actions/userInfo.actions';

// Types
import { State } from '../../redux/reducers/root.reducers';

// Styles
import styles from './Login.module.scss';

// Components
import { Form, Input, Button, Checkbox } from 'antd';
import { Typography } from 'antd';

const { Title } = Typography;

interface LoginData {
  userName: string;
  password: string;
  remember: boolean;
}

const IndexPage: React.FC = () => {
  const dispatch = useDispatch();

  const isLoggedIn = useSelector<State, boolean>(
    (state) => state.userInfo.isLoggedIn,
  );

  // Login básico, ya toca que vea la sesión para que se mantenga si entra por url
  const onFinish = React.useCallback(
    (value: LoginData) => {
      console.log('Success:', value);
      dispatch(setIsUserLoggedIn(true));
      dispatch(setIsUserName(value.userName));
    },
    [dispatch],
  );

  //eslint-disable-next-line @typescript-eslint/no-explicit-any
  const onFinishFailed = React.useCallback((errorInfo: any) => {
    console.log('Failed:', errorInfo);
  }, []);

  // https://ant.design/components/form/#components-form-demo-nest-messages
  // Este es un ejemplo de un Form con la librería, ahí puede ver el cod
  return (
    <>
      {isLoggedIn ? (
        <Title>You´re logged in</Title>
      ) : (
        <div className={styles.loginContent}>
          <Title>Login</Title>
          <div className={styles.content}>
            <Form
              name="basic"
              // labelCol={{ span: 8 }}
              // wrapperCol={{ span: 16 }}
              initialValues={{ remember: true }}
              onFinish={onFinish}
              onFinishFailed={onFinishFailed}
              autoComplete="off"
            >
              <Form.Item
                label="Username"
                name="userName"
                rules={[
                  { required: true, message: 'Please input your username!' },
                ]}
                className={styles.formLoginItem}
              >
                <Input />
              </Form.Item>

              <Form.Item
                label="Password"
                name="password"
                rules={[
                  { required: true, message: 'Please input your password!' },
                ]}
                className={styles.formLoginItem}
              >
                <Input.Password />
              </Form.Item>

              <Form.Item
                name="remember"
                valuePropName="checked"
                className={styles.formActions}
              >
                <Checkbox>Remember me</Checkbox>
              </Form.Item>

              <Form.Item className={styles.formActions}>
                <Button type="primary" htmlType="submit">
                  Submit
                </Button>
              </Form.Item>
            </Form>
          </div>
        </div>
      )}
    </>
  );
};

export default IndexPage;
