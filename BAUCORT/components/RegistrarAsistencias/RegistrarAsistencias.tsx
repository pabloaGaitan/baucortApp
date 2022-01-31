import React from 'react';

// Ant Design
import { Calendar as AntCalendar, Badge, Button, Input, DatePicker, Upload, message  } from 'antd';
import { PresetStatusColorType } from '../../node_modules/antd/lib/_util/colors';
import { Typography } from 'antd';
import { UploadOutlined } from '@ant-design/icons';

// Styles
import styles from './RegistrarAsistencias.module.scss';
import axios from 'axios';

let dia;
let mes;
let anio;
let codigoEstudiante;
let file;
const { Title } = Typography;

const ReporteAsistencias: React.FC = () => {
  const onClick = React.useCallback(() => {
    if(mes && anio && codigoEstudiante){
      axios.get('http://104.196.249.226:8080/asistencia/estudiante/'+codigoEstudiante+'/'+mes + '/' + anio)
        .then(res =>{
          setListaDataBack(res.data)
          console.log(listDataBack)
        })
    }
  })

  const changeCodigo = value =>{
    codigoEstudiante = value.target.value
    console.log(codigoEstudiante)
  }

  const handleChange = date => {
    if (date){
      dia = date.toDate().getDate()
      mes = date.toDate().getMonth() + 1
      anio = date.toDate().getFullYear()
    }
    console.log(dia)
    console.log(mes)
    console.log(anio)
    
    
  }


  const onChangeFile = (evento) => {
    let info = evento.file
    const formData = new FormData();
    formData.append('file',info);
    axios.post(`http://104.196.249.226:8080/asistencia?dia=${dia}&mes=${mes}&anio=${anio}`,formData)
      .then(res => {
        alert("Asistencias cargadas")
    })
  }

  const enviarAsistencias = () => {

    const formData = new FormData();
    formData.append('file',file);
    console.log(anio)
    const config = {
      headers: {
          'Content-type': 'multipart/form-data'
      }
  }
    axios.post(`http://104.196.249.226:8080/asistencia?dia=${dia}&mes=${mes}&anio=${anio}`,formData,config)
      .then(res => {
        alert("Asistencias cargadas")
      })
    console.log(file.files)
  }

  return (
    <div >
      <div className={styles.titleContainer}>
        <Title>REGISTRAR ASISTENCIAS</Title>
      </div>
      <div className={styles.titleContainer}>
      <DatePicker placeholder = "Seleccione la fecha" style={{ width: '50%' }} onChange = {handleChange} />
    </div>

    <Upload onChange={onChangeFile}>
      <Button icon={<UploadOutlined />}>Cargar archivo</Button>
    </Upload>

    <div className={styles.titleContainer}>
      <Button type="primary" onClick={enviarAsistencias}>Registrar Inasistencia</Button>
    </div>
    </div>
  );
};

export default ReporteAsistencias;
