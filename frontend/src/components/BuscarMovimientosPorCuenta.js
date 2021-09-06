import React from 'react';
import Button from '@material-ui/core/Button';
import { buscarMovimientoPorCuenta } from '../APIS/apiCC';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';

const useStyles = makeStyles((theme) => ({
    root: {
      width: '90%',
      borderWidth:2, 
      borderColor:'black',
      borderStyle:'solid',
    },
    root2: {
        '& > *': {
          margin: theme.spacing(1),
          width: '25ch',
        },
      } 
  }));
export const BuscarMovimientosPorCuenta = () => {
    const classes = useStyles();

    const onClick = ( ) => {
        buscarMovimientoPorCuenta( document.getElementById('outlined-movimientosPorCuenta').value )
            .then( data => {
                var table = document.getElementById("tablaMovimientos");
                for(var i = table.rows.length - 1; i > 0; i--){
                    table.deleteRow(i);
                }
    
                if(data != null){
                    for (let index = 0; index < data.length; index++) {
                        const element = data[index];
                        const myHtmlContent = "<tr><td>" +  element.cuenta + "</td></tr>" +
                                              "<tr><td>" +  element.fecha + "</td></tr>"  +
                                              "<tr><td>" +  element.tipo  + "</td></tr>"  +
                                              "<tr><td>" +  element.importe  + "</td></tr>"  +
                                              "<tr><td>" +  element.descripcion  + "</td></tr>";
                        const tableRef = document.getElementById('tablaMovimientos').getElementsByTagName('tbody')[0];
                        const newRow = tableRef.insertRow(tableRef.rows.length);
                        newRow.innerHTML = myHtmlContent;
                    }
                }
            })
    }

    return(
        <>
            <form className={classes.root2} style={{backgroundColor:'white'}}>
            <TextField id="outlined-movimientosPorCuenta" name="movimientosPorCuenta" label="Cuenta" variant="outlined" />
            <Button id="outlined-botonListar" name="outlined-botonListar" variant="contained" size="large" color="primary" onClick={ onClick }  >
                Traer todos
            </Button>
            </form>
            <div style={{ width: '90%', backgroundColor:'white', color:'black',paddingTop:10,paddingLeft:"10%",paddingBottom:10}} id="divTablaMovimientos">
            <table id="tablaMovimientos" className={classes.root}>
                <thead><tr><th style={{width:230}}>cuenta</th><th style={{width:155}}>fecha</th><th style={{width:155}}>tipo</th><th style={{width:155}}>importe</th><th style={{width:555}}>descripcion</th></tr></thead>
            <tbody>
            </tbody>
            </table>
            </div>
        </>
        );
    
    
}
