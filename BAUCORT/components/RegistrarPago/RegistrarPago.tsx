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
import styles from './RegistrarPago.module.scss';

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
    }
  ]

  let fecha = null;
  let estudianteId = null;
  let valor = null;
  let tipoPago = null;
  let numeroPago = null;
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

  const handleChange = date => {
    fecha = date != null ? date.format("DD/MM/YYYY"): null
    console.log(fecha)
  }

  const registrarPago = () => {
    if( fecha && estudiantes[0] && valor && tipoPago && numeroPago){
      axios.post('http://104.196.249.226:8080/pago/estudiante/' + estudiantes[0].codigo,
      {
        fecha: fecha,
        tipo: tipoPago,
        numero: numeroPago,
        valor: valor
      })
        .then(res => {
          console.log(estudianteId)
          console.log(fecha)
        })
    } else {
      alert("Por favor llenar todos los campos.")
    }
  }

  const changeValor = value =>{
    valor = value
    console.log(valor)
  }

  const handleChangeTipo = value =>{
    tipoPago = value
    console.log(tipoPago)
  }

  const changeNumeroPago = value =>{
    numeroPago = value.target.value
    console.log(numeroPago)
  }

  return (<div>
    <div className={styles.titleContainer}>
      <Title>Registrar Pago</Title>
      
    </div>
    <div className={styles.titleContainer}>
      <Search placeholder="Codigo" onSearch={onSearch} style={{ width: 500 }} /> 
    </div>
      <Table columns={headers} dataSource={estudiantes} />
    <div className={styles.titleContainer}>
      <InputNumber addonAfter="$" placeholder='Valor' onChange = {changeValor} style={{ width: 700 }}/>
    </div>
    <div className={styles.titleContainer}>
      <DatePicker placeholder = "Seleccione la fecha" style={{ width: '50%' }} onChange = {handleChange} />
    </div>
    <div className={styles.titleContainer}>
      <Select placeholder="Tipo de pago" style={{ width: 700 }} onChange={handleChangeTipo}>
        <Option value="TRANSFERENCIA">Transferencia</Option>
        <Option value="RECIBO_CAJA">Recibo</Option>
        <Option value="NEQUI">Nequi</Option>
      </Select>
    </div>
    <div className={styles.titleContainer}>
      <Input placeholder='Numero del pago' onChange = {changeNumeroPago} style={{ width: 700 }}/>
    </div>
    <div className={styles.titleContainer}>
      <Button type="primary" onClick={registrarPago}>Registrar Pago</Button>
    </div>
    
  </div>);
};

export default IndexPage;
