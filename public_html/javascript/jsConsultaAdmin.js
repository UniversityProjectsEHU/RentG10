window.addEventListener('load', inicio, false);
function inicio() {
    document.getElementById('cerrarsesion').addEventListener('click', borrardatos, false); 
    /*Obtener datos almacenados */
    var nombre = localStorage.getItem("Nombre");
    /*Mostrar datos almacenados*/
    var nombreusuario = localStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
    
    if(document.getElementById("nombree").innerHTML !== "sernavarrops3@gmail.com"){
        location.href ="../html/pantallaInicial.html";
    }
}

//Cierra la sesion y te manda a la pantalla inicial
function borrardatos (evt) {
    localStorage.clear();
    /*Mostrar datos almacenados*/
    var nombreusuario = localStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
    location.href ="../html/pantallaInicial.html";
}


