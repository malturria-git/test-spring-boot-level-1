import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import { registro } from '../APIS/apiUser';


const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));
  
export const AltaUser = () => {
  const classes = useStyles();

    function handleInputChange(evt) {
        const value = evt.target.value;
        setUser({
        ...username,
        [evt.target.name]: value
    });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        registro(username);
    }

    const [username, setUser] = React.useState({
        username: "",
        password: ""
      });

  return (
    <>
    <form className={classes.root} noValidate autoComplete="off" style={{backgroundColor:'white'}} >
    <div style={{textAlign: 'center', paddingLeft:'40%',paddingTop:'10%', paddingBottom:'10%'}}>
        <TextField id="outlined-User" name="username" label="Usuario" variant="outlined" value={username.username} onChange={handleInputChange} />
        <TextField id="outlined-password" name="password" label="Password" variant="outlined" value={username.password} onChange={handleInputChange} />
        <Button size="small"  id="outlined-Login" name="outlined-Login" variant="contained" color="primary" onClick={ handleSubmit } >
            Registrarse
        </Button>
        </div>
      </form>
    </>
  );
}
