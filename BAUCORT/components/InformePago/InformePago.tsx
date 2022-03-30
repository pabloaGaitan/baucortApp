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
import styles from './InformePago.module.scss';

// Components
import { Table, Input,InputNumber, DatePicker, Button, Select, Checkbox} from 'antd';
import { Typography } from 'antd';

import axios from 'axios';
  
const { Title } = Typography;
const { Search } = Input;
const { Option } = Select;


let codigoEstudiante = null;
const IndexPage: React.FC = () => {

  const [informePago, setInformePago] = React.useState({"pagos":[],"saldo": 0});
  const [estudiantes, setEstudiantes] = React.useState([]);
  let headersEst = [
    {
      title: 'Codigo',
      dataIndex: 'codigo',
      key: 'codigo'
    },
    {
      title: 'Nombre',
      dataIndex: 'nombresApellidos',
      key: 'nombresApellidos'
    },
    {
      title: 'Curso',
      dataIndex: 'curso',
      key: 'curso'
    },
    {
      title: 'Grupo',
      dataIndex: 'grupo',
      key: 'grupo'
    }
  ]
  let headers = [
    {
      title: 'Fecha',
      dataIndex: 'fecha',
      key: 'fecha'
    },
    {
      title: 'Tipo',
      dataIndex: 'tipo',
      key: 'tipo'
    },
    {
      title: 'Numero',
      dataIndex: 'numero',
      key: 'numero'
    },
    {
      title: 'Valor',
      dataIndex: 'valor',
      key: 'valor'
    }
  ]

  const onClick = React.useCallback(() => {
    if(codigoEstudiante){
      axios.get(`http://104.196.249.226:8080/pago/estudiante/${codigoEstudiante}`)
        .then(res =>{
          setInformePago(res.data)
        })
        axios.get('http://104.196.249.226:8080/estudiante/' + codigoEstudiante)
        .then(res =>{
          setEstudiantes([res.data])
    })
    }
  })

  const changeCodigo = value =>{
    codigoEstudiante = value.target.value
    console.log(codigoEstudiante)
  }

  return (<div>
    <div className={styles.titleContainer}>
      <Title>Reporte Pagos</Title>
      </div>
      <section className={styles.titleContainer}>
        <Input placeholder='Codigo del estudiante' style={{ width: 500 }} onChange = {changeCodigo}/>
        <Button onClick={onClick}>Buscar</Button>
      </section>
      <Table columns={headersEst} dataSource={estudiantes} />
      <Table columns={headers} dataSource={informePago.pagos} />
      <div className={styles.titleContainer}>
      <Input  style={{ width: 500 }} value={informePago.saldo} disabled />
      </div>
  </div>);
};

export default IndexPage;
