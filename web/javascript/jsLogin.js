
window.addEventListener('load', inicio, false);

var correo,contra;
function inicio() {
    
    //Validación en tiempo real
    usuario=document.getElementById("email");
    contra=document.getElementById("contrasena");
    usuario.addEventListener("input",validacion);
    contra.addEventListener("input",validacion);
    validacion();
    
    
    boton2=document.getElementById("botonlogin");
    boton2.addEventListener("click",comprobar);
    
}


function validacion(){
    if(usuario.value===""){
        usuario.setCustomValidity("Inserte su nombre o su apellido");
        usuario.style.background ="red" ;
    }
    else{
        usuario.setCustomValidity("");
        usuario.style.background = "green";
    }
    
    if(contra.value===""){
        contra.setCustomValidity("Inserte su contraseña");
        contra.style.background="red";
    }
    else{
        contra.setCustomValidity("");
        contra.style.background = "green";
    }
    
    
 

}
