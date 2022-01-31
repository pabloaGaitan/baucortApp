import React from 'react';



// Styles
import styles from './CobrarDia.module.scss';

// Components
import { Table, Input, DatePicker, Button} from 'antd';
import { Typography } from 'antd';

import axios from 'axios';
  
const { Title } = Typography;



let fecha : Date;
const IndexPage: React.FC = () => {

  const [fechas, setFechas] = React.useState([]);
  
  let headers = [
    {
      title: 'Fecha',
      dataIndex: 'fecha',
      key: 'fecha'
    }
  ]

  const buscarFechas = React.useCallback (() => {
      axios.get('http://104.196.249.226:8080/pago/fechasCobradas')
      .then(res =>{
        console.log(res.data)
        setFechas(res.data)
      })
  })

  const handleChange = date => {
    fecha = date != null ? date.format("DD/MM/YYYY"): null
    console.log(fecha)
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

  const cobrarDia = () => {
    if( fecha ){
      axios.post('http://104.196.249.226:8080/estudiante',
      {
        fecha: fecha,
      })
        .then(res => {
          alert("Cobro terminado");
        })
    } else {
      alert("Por favor llenar la fecha a descontar.")
    }
  }

    const onClick = React.useCallback(() => {
    if(mes && anio && codigoEstudiante){
      axios.get('http://104.196.249.226:8080/asistencia/estudiante/'+codigoEstudiante+'/'+mes + '/' + anio)
        .then(res =>{
          setListaDataBack(res.data)
          console.log(listDataBack)
        })
    }
  })

  return (<div>
    <div className={styles.titleContainer}>
      <Title>Cobrar dia</Title>
    </div>
      <Table columns={headers} dataSource={fechas} />
      <div className={styles.titleContainer}>
      <Button type="primary" onClick={buscarFechas}>Buscar fechas</Button>
    </div>
    <div className={styles.titleContainer}>
      <DatePicker placeholder = "Seleccione la fecha" style={{ width: '50%' }} onChange = {handleChange} />
    </div>
    <div className={styles.titleContainer}>
      <Button type="primary" onClick={cobrarDia}>Cobrar dia</Button>
    </div>
    
  </div>);
};

export default IndexPage;
