window.addEventListener('load', inicio, false);
var puede;
function inicio() {
    document.getElementById('cerrarsesion').addEventListener('click', borrardatos, false);
    /*Obtener datos almacenados */
    var nombre = sessionStorage.getItem("Nombre");
    /*Mostrar datos almacenados*/
    var nombreusuario1 = sessionStorage.getItem("Nombre");
    var nombreusuario = sessionStorage.getItem("Nombre").toString();
    var emailusuario = sessionStorage.getItem("Email").toString();
    document.getElementById("nombree").innerHTML = nombreusuario1;

//    if ( sessionStorage.getItem("Nombre") === null) {
//        location.href = "../html/pantallaInicial.html";
//    }
    puede = false;
    if (nombreusuario === "admin1" && emailusuario === "admin@gmail.com") {
        puede = true;
    }
    if (nombreusuario === "admin2" && emailusuario === "admin2@gmail.com") {
        puede = true;
    }
    if (puede === false) {
        location.href = "../html/pantallaInicial.html";
    }
}

//Cierra la sesion y te manda a la pantalla inicial
function borrardatos(evt) {
    sessionStorage.clear();
    /*Mostrar datos almacenados*/
    var nombreusuario = sessionStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
    location.href = "../html/pantallaInicial.html";
}


