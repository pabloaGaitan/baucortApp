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
import styles from './ListaEstudiantes.module.scss';

// Components
import { Table, Input,InputNumber, DatePicker, Button, Select, Checkbox} from 'antd';
import { Typography } from 'antd';

import axios from 'axios';
  
const { Title } = Typography;
const { Search } = Input;
const { Option } = Select;




const IndexPage: React.FC = () => {

  const [estudiantes, setEstudiantes] = React.useState([]);
  let headers = [
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
    },
    {
      title: 'Saldo',
      dataIndex: 'saldoAlmuerzo',
      key: 'saldoAlmuerzo'
    },

  ]
  var orden = 'ASC'
  const onSearch = React.useCallback ((value) => {
    if(value){
      axios.get('http://104.196.249.226:8080/estudiante/' + value)
      .then(res =>{
        estudianteId = value
        setEstudiantes([res.data]);
        console.log(estudianteId)
      })
    }else {
      alert("Por favor llenar el codigo del estudiante.")
    }
  })

  const registrarPago = () => {
      axios.get('http://104.196.249.226:8080/estudiante?order=' + orden)
        .then(res => {
          setEstudiantes(res.data)
        })
  }

  const handleChangeTipo = value =>{
    orden = value
    console.log(orden)
  }

  return (<div>
    <div className={styles.titleContainer}>
      <Title>Lista de estudiantes</Title>
      
    </div>
    <div className={styles.titleContainer}>
      <Select placeholder="Orden" style={{ width: 700 }} onChange={handleChangeTipo}>
        <Option value="DESC">Mayor a menor</Option>
        <Option value="ASC">Menor a mayor</Option>
      </Select>
    </div>
    <div className={styles.titleContainer}>
      <Button type="primary" onClick={registrarPago}>Buscar</Button>
    </div>
      <Table columns={headers} dataSource={estudiantes} />


    
    
  </div>);
};

export default IndexPage;
