import React from 'react';
import Button from '@material-ui/core/Button';
import { buscarTodos } from '../APIS/apiPersona';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
    root: {
      width: '90%',
      borderWidth:2, 
      borderColor:'black',
      borderStyle:'solid',
      
    }  
  }));
export const ListaPersonas = () => {
    const classes = useStyles();

    const onClick = ( ) => {
        buscarTodos( )
            .then( data => {
                var table = document.getElementById("tablaPersonas");
                for(var i = table.rows.length - 1; i > 0; i--){
                    table.deleteRow(i);
                }
                var table = document.getElementById("tablaPersonasJuridica");
                for(var i = table.rows.length - 1; i > 0; i--){
                    table.deleteRow(i);
                }
    
                if(data != null){
                    for (let index = 0; index < data.length; index++) {
                        const element = data[index];
                        let myHtmlContent = "<tr><td>" +  element.rut + "</td></tr>" +
                                            "<tr><td>" +  element.tipo+ "</td></tr>";
                        let tableRef="";
                        if(element.tipo === 'Fisica'){
                            myHtmlContent = myHtmlContent +                        
                            "<tr><td>" +  element.nombre+ "</td></tr>" +
                            "<tr><td>" +  element.apellido+ "</td></tr>" +
                            "<tr><td>" +  element.cuentaCorriente+ "</td></tr>";
                            tableRef = document.getElementById('tablaPersonas').getElementsByTagName('tbody')[0];

                        }else{
                            myHtmlContent = myHtmlContent +                        
                            "<tr><td>" +  element.razonSocial+ "</td></tr>" +
                            "<tr><td>" +  element.anioFundacion+ "</td></tr>";
                            tableRef = document.getElementById('tablaPersonasJuridica').getElementsByTagName('tbody')[0];

                        }
                        const newRow = tableRef.insertRow(tableRef.rows.length);
                        newRow.innerHTML = myHtmlContent;
                    }
                }
            })
    }

    return(
        <>
            <form style={{backgroundColor:'white'}}>
            <Button id="outlined-botonListar" name="outlined-botonListar" variant="contained" size="large" color="primary" onClick={ onClick }  >
                Traer todos
            </Button>
            </form>
            <div style={{ width: '90%', backgroundColor:'white', color:'black',paddingTop:10,paddingLeft:"10%",paddingBottom:10}} id="divTablaPersonas">
            <table id="tablaPersonas" className={classes.root}>
                <thead>
                    <tr><th style={{width:'20%'}}>RUT</th>
                        <th style={{width:'20%'}}>Tipo</th>
                        <th style={{width:'20%'}}>Nombre</th>
                        <th style={{width:'20%'}}>Apellido</th>
                        <th style={{width:'20%'}}>CC</th>
                    </tr>
                </thead>
            <tbody>
            </tbody>
            </table>

            <table id="tablaPersonasJuridica" className={classes.root}>
                <thead>
                    <tr><th style={{width:'25%'}}>RUT</th>
                        <th style={{width:'25%'}}>Tipo</th>
                        <th style={{width:'25%'}}>Razon Social</th>
                        <th style={{width:'25%'}}>Año de Fundacion.</th>
                    </tr>
                </thead>
            <tbody>
            </tbody>
            </table>
            </div>
        </>
        );
    

                        
}