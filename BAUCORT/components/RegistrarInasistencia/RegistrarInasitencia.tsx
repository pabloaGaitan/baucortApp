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
import styles from './RegistrarInasistencia.module.scss';

// Components
import { Table, Input, DatePicker, Button, Checkbox} from 'antd';
import { Typography } from 'antd';

import axios from 'axios';
  
const { Title } = Typography;
const { Search } = Input;




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
    }
  ]

  let fecha = null;
  let estudianteId = null;
  const onSearch = React.useCallback ((value) => {
    axios.get('http://104.196.249.226:8080/estudiante/' + value)
    .then(res =>{
      estudianteId = value
      setEstudiantes([res.data]);
      console.log(estudianteId)
    })
  })

  const handleChange = date => {
    fecha = date != null ? date.format("DD/MM/YYYY"): null
    console.log(fecha)
  }

  const registrarInasistencia = () => {
    if( fecha && estudiantes[0]){
      axios.post('http://104.196.249.226:8080/inasistencia/estudiante/' + estudiantes[0].codigo,{fecha: fecha})
        .then(res => {
          console.log(estudianteId)
          console.log(fecha)
          alert("Inasistencia registrada")
        })
    }
  }

  return (<div>
    <div className={styles.titleContainer}>
      <Title>Registrar Inasistencia</Title>
      
    </div>
    <div className={styles.titleContainer}>
      <Search placeholder="Codigo" onSearch={onSearch} style={{ width: 500 }} /> 
    </div>
      <Table columns={headers} dataSource={estudiantes} />
    <div className={styles.titleContainer}>
      <DatePicker placeholder = "Seleccione la fecha" style={{ width: '50%' }} onChange = {handleChange} />
    </div>

    <div className={styles.titleContainer}>
      <Button type="primary" onClick={registrarInasistencia}>Registrar Inasistencia</Button>
    </div>
  </div>);
};

export default IndexPage;
