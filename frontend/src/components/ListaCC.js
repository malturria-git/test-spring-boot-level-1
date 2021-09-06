import React from 'react';
import Button from '@material-ui/core/Button';
import { buscarTodasCuentas } from '../APIS/apiCC';
import { makeStyles } from '@material-ui/core/styles';

const useStyles = makeStyles((theme) => ({
    root: {
      width: '50%',
      borderWidth:2, 
      borderColor:'black',
      borderStyle:'solid',
      
    }  
  }));
export const ListaCC = () => {
    const classes = useStyles();

    const onClick = ( ) => {
        buscarTodasCuentas( )
            .then( data => {
                var table = document.getElementById("tablaCC");
                for(var i = table.rows.length - 1; i > 0; i--){
                    table.deleteRow(i);
                }

                if(data != null){
                    for (let index = 0; index < data.length; index++) {
                        const element = data[index];
                        const myHtmlContent = "<tr><td>" +  element.cuenta + "</td></tr><tr><td>" +  element.moneda+ "</td></tr><tr><td style='float: right;'>" +  element.saldo+ "</td></tr>" 
                        const tableRef = document.getElementById('tablaCC').getElementsByTagName('tbody')[0];
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
            <div style={{ width: '90%', backgroundColor:'white', color:'black',paddingTop:10,paddingLeft:"10%",paddingBottom:10}} id="divTablaCC">
            <table id="tablaCC" className={classes.root}>
                <thead><tr><th style={{width:150}}>cuenta</th><th style={{width:155}}>moneda</th><th style={{width:155}}>saldo</th></tr></thead>
            <tbody>
            </tbody>
            </table>
            </div>
        </>
        );
    
    
}
