
window.addEventListener('load', inicio, false);

function inicio() {
    document.getElementById('botonenviar').addEventListener('click', guardar, false);
    document.getElementById('cerrarsesion').addEventListener('click', borrardatos, false);
    /*Obtener datos almacenados */
    var nombre = localStorage.getItem("Nombre");
    /*Mostrar datos almacenados*/
    var nombreusuario = localStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
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
}

