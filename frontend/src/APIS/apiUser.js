export const login = async( user ) => {
    console.log(JSON.stringify( user ))

    var details = {
        'user': user.user,
        'password': user.password
    };

    var formBody = [];
    for (var property in details) {
        var encodedKey = encodeURIComponent(property);
        var encodedValue = encodeURIComponent(details[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }
    formBody = formBody.join("&");
    const url = `http://localhost:8080/api/auth/login` ;

    const resp = await fetch(url, {
        method: 'POST',
        headers: {
        'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    },
    body: formBody
    }).then(response =>  response.json() );
    alert( JSON.stringify( resp ) );
    localStorage.setItem('token', resp.token );

}
        
export const registro = async( user ) => {
    const requestOptions = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            },
            body: JSON.stringify( user )
        }
    
        let url = `http://localhost:8080/api/auth/registro`;

    const resp = await fetch(url, requestOptions)
                        .then(response => response.json());
    alert( JSON.stringify( resp ) );
        
    return resp;  
}
        