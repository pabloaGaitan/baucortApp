import React from 'react';

// Ant Design
import { Calendar as AntCalendar, Badge, Button, Input } from 'antd';
import { PresetStatusColorType } from '../../node_modules/antd/lib/_util/colors';

// Styles
import styles from './ReporteAsistencias.module.scss';
import axios from 'axios';

interface ReporteAsistenciasData {
  type: PresetStatusColorType;
  content: string;
}


let mes;
let anio;
let codigoEstudiante;
function getListData(value: moment.Moment, listDataBackEnd): ReporteAsistenciasData[] {
  
  let listData 
  switch (value.date()) {
    case 1: 
      listData = listDataBackEnd[0]
    break;
    case 2: 
      listData = listDataBackEnd[1]
    break;
    case 3: 
      listData = listDataBackEnd[2]
    break;
    case 4: 
      listData = listDataBackEnd[3]
    break;
    case 5: 
      listData = listDataBackEnd[4]
    break;
    case 6: 
      listData = listDataBackEnd[5]
    break;
    case 7: 
      listData = listDataBackEnd[6]
    break;
    case 8: 
      listData = listDataBackEnd[7]
    break;
    case 9: 
      listData = listDataBackEnd[8]
    break;
    case 10: 
      listData = listDataBackEnd[9]
    break;
    case 11: 
      listData = listDataBackEnd[10]
    break;
    case 12: 
      listData = listDataBackEnd[11]
    break;
    case 13: 
      listData = listDataBackEnd[12]
    break;
    case 14: 
      listData = listDataBackEnd[13]
    break;
    case 15: 
      listData = listDataBackEnd[14]
    break;
    case 16: 
      listData = listDataBackEnd[15]
      console.log(listData)
    break;
    case 17: 
      listData = listDataBackEnd[16]
    break;
    case 18: 
      listData = listDataBackEnd[17]
    break;
    case 19: 
      listData = listDataBackEnd[18]
    break;
    case 20: 
      listData = listDataBackEnd[19]
    break;
    case 21: 
      listData = listDataBackEnd[20]
    break;
    case 22: 
      listData = listDataBackEnd[21]
    break;
    case 23: 
      listData = listDataBackEnd[22]
    break;
    case 24: 
      listData = listDataBackEnd[23]
    break;
    case 25: 
      listData = listDataBackEnd[24]
    break;
    case 26: 
      listData = listDataBackEnd[25]
    break;
    case 27: 
      listData = listDataBackEnd[26]
    break;
    case 28: 
      listData = listDataBackEnd[27]
    break;
    case 29: 
      listData = listDataBackEnd[28]
    break;
    case 30: 
      listData = listDataBackEnd[29]
    break;
    case 31: 
      listData = listDataBackEnd[30]
    break;

    default:
  }
  return listData || [];
}

const ReporteAsistencias: React.FC = () => {
  const [listDataBack, setListaDataBack] = React.useState([]);
  const dateCellRender = (value) => {
    anio = value.year()
    mes = value.month()
    console.log(mes)
    console.log(anio)
    console.log(listDataBack)
    const listData = getListData(value,listDataBack);
    return (
      <ul className={styles.events}>
        {listData.map((item) => (
          <li key={item.content}>
            <Badge status={item.type} text={item.content} />
          </li>
        ))}
      </ul>
    );
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

  const changeCodigo = value =>{
    codigoEstudiante = value.target.value
    console.log(codigoEstudiante)
  }

  return (
    <div className={styles.container}>
      <section>
        <h1>REPORTE ASISTENCIAS</h1>
      </section>
      <section className={styles.titleContainer}>
        <Input placeholder='Codigo del estudiante' onChange = {changeCodigo}/>
        <Button onClick={onClick}>Buscar</Button>
      </section>
      <AntCalendar
        className={styles.calendar}
        dateCellRender={dateCellRender}
      />
    </div>
  );
};

export default ReporteAsistencias;
