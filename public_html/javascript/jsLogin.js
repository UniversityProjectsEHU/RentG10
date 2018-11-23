
window.addEventListener('load', inicio, false);
var correo,contra;
function inicio() {
    document.getElementById('botonenviar').addEventListener('click', guardar, false);
    document.getElementById('cerrarsesion').addEventListener('click', borrardatos, false); 
    /*Obtener datos almacenados */
    var nombre = localStorage.getItem("Nombre");
    /*Mostrar datos almacenados*/
    var nombreusuario = localStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
    //Validación en tiempo real
    usuario=document.getElementById("email");
    contra=document.getElementById("contrasena");
    usuario.addEventListener("input",validacion);
    contra.addEventListener("input",validacion);
    validacion();
}

function guardar(evt) {
    localStorage.setItem("Nombre", document.getElementById('email').value);
    /*Obtener datos almacenados*/
    var nombre = localStorage.getItem("Nombre");
    /*Mostrar datos almacenados*/
    var nombreusuario = localStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
}

function borrardatos (evt) {
    localStorage.clear();
    /*Mostrar datos almacenados*/
    var nombreusuario = localStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
     location.href ="../html/pantallaInicial.html";
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
