export const buscarPersona = async( rut ) => {
    if(rut.length > 0){
        const requestOptions = {
            method: 'GET',
            headers: {
                "Content-Type": "application/json",
                "Access-Control-Allow-Origin": "*",
                "Authorization" : localStorage.getItem("token"),
                }
            }
        
            const url = `http://localhost:8080/api/personas/buscarPorRUT?rut=` + rut ;
    
        const resp = await fetch(url, requestOptions)
                            .then(response => response.json());
        return resp;
    }
}

export const buscarTodos = async( ) => {
    const requestOptions = {
        method: 'GET',
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Authorization" : localStorage.getItem("token"),
            }
        }
    
        const url = `http://localhost:8080/api/personas/buscarTodos`  ;

    const resp = await fetch(url, requestOptions)
                        .then(response => response.json());
    return resp;
}

export const guardarPersona = async( persona ) => {
    console.log(JSON.stringify( persona ))
    const requestOptions = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Authorization" : localStorage.getItem("token"),
            },
            body: JSON.stringify( persona )
        }
    
        let url = `http://localhost:8080/api/personas/guardarPersona`;

    const resp = await fetch(url, requestOptions)
                        .then(response => response.json());console.log(resp);    alert( JSON.stringify( resp ) );

    return resp; 
}

export const borrarPersona = async( rut ) => {
    const requestOptions = {
        method: 'DELETE',
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Authorization" : localStorage.getItem("token"),
            },
            body: JSON.stringify( rut )
        }
    
        let url = `http://localhost:8080/api/personas/borrarPorRUT`;

    const resp = await fetch(url, requestOptions)
                        .then(response => response.json());console.log(resp);    alert( JSON.stringify( resp ) );

    return resp;    
}

export const modificarPersona = async( persona ) => {
    const requestOptions = {
        method: 'PUT',
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            "Authorization" : localStorage.getItem("token"),
            },
            body: JSON.stringify( persona )
        }
    
        let url = `http://localhost:8080/api/personas/modificarPersona`;

    const resp = await fetch(url, requestOptions)
                        .then(response => response.json());console.log(resp);alert(resp);
    return resp;  
}