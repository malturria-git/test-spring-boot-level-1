export const guardarCC = async( cuentaCorriente ) => {
    console.log(JSON.stringify( cuentaCorriente ))
    const requestOptions = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Authorization" : localStorage.getItem("token"),
            },
        body: JSON.stringify( cuentaCorriente )
        }
    
    let url = `http://localhost:8080/api/cuentascorrientes/guardarCuentaCorriente`;
        const resp = await fetch(url, requestOptions)
        .then(response => response.json());
        console.log(resp);alert( JSON.stringify( resp ) );
    return resp;   
}

export const borrarCC = async( cuentaCorriente ) => {
        const requestOptions = {
            method: 'DELETE',
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*",
                "Authorization" : localStorage.getItem("token"),
                },
            body: JSON.stringify( cuentaCorriente )
            }
        
        const url = `http://localhost:8080/api/cuentascorrientes/borrarCuentaCorriente`;
    
        const resp = await fetch(url, requestOptions)
            .then(response => response.json()); console.log(resp);alert( JSON.stringify( resp ) );
        return resp;        
}

export const buscarTodasCuentas = async( ) => {
    const requestOptions = {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Authorization" : localStorage.getItem("token"),
            }
          }
   
    const url = `http://localhost:8080/api/cuentascorrientes/buscarTodasCuentas`  ;

    const resp = await fetch(url, requestOptions)
        .then(response => response.json()); console.log(resp);
    return resp;
    }

export const guardarMovimiento = async( movimiento ) => {
    console.log(JSON.stringify( movimiento ) )
        const requestOptions = {
            method: 'POST',
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*",
                "Authorization" : localStorage.getItem("token"),
                },
            body: JSON.stringify( movimiento )
            }
        
        let url = `http://localhost:8080/api/movimientos/guardarMovimiento`;
    
        const resp = await fetch(url, requestOptions)
                           .then(response => response.json());console.log(resp);alert( JSON.stringify( resp ) );
        return resp;  

}

export const buscarMovimientoPorCuenta = async( cuenta ) => {
    const requestOptions = {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Authorization" : localStorage.getItem("token"),
            }
        }
    
    const url = `http://localhost:8080/api/movimientos/buscarMovimientoPorCuenta?cuenta=`+ cuenta  ;

    const resp = await fetch(url, requestOptions)
                        .then(response => response.json());console.log(resp);
    return resp;
}