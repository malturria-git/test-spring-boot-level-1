import React from 'react';
import { AltaCC } from '../components/AltaCC';
import { BajaCC } from '../components/BajaCC';
import { ListaCC } from '../components/ListaCC';
import { AltaMovimiento } from '../components/AltaMovimiento';
import { BuscarMovimientosPorCuenta } from '../components/BuscarMovimientosPorCuenta';

export default function MenuCC() {

  return (
  <>
    <h1 style={{textAlign: 'center'}}> Bienvenido al CRUD de Cuentas Corrientes y Movimientos.</h1>
    <div style={{margin:"5%"}}>
        <h3 style={{textAlign: 'center'}}> Alta de cuentas</h3>
        <AltaCC />
    <hr />
        <h3 style={{textAlign: 'center'}}> Baja de cuentas </h3>
        <BajaCC />
    <hr />
        <h3 style={{textAlign: 'center'}}> Listado de cuentas </h3>
          
        <ListaCC />
    <hr />
        <h3 style={{textAlign: 'center'}}> Alta de movimientos</h3>
        <AltaMovimiento />
    <hr />
        <h3 style={{textAlign: 'center'}}> Listado de movimientos por cuenta.</h3>        
        <BuscarMovimientosPorCuenta />
    </div>
  </>
  );
}
