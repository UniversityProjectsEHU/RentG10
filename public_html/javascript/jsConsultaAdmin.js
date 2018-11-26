window.addEventListener('load', inicio, false);
var puede;
function inicio() {
    document.getElementById('cerrarsesion').addEventListener('click', borrardatos, false);
    /*Obtener datos almacenados */
    var nombre = localStorage.getItem("Nombre");
    /*Mostrar datos almacenados*/
    var nombreusuario1 = localStorage.getItem("Nombre");
    var nombreusuario = localStorage.getItem("Nombre").toString();
    var emailusuario = localStorage.getItem("Email").toString();
    document.getElementById("nombree").innerHTML = nombreusuario1;

//    if ( localStorage.getItem("Nombre") === null) {
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
    localStorage.clear();
    /*Mostrar datos almacenados*/
    var nombreusuario = localStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
    location.href = "../html/pantallaInicial.html";
}


