
window.addEventListener('load', inicio, false);

var correo, contra;
var indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB;
var bd;
var dayInMillis = 24 * 3600000;
function inicio() {
    alert("estoy en inicio");
//    document.getElementById('botonlogin').addEventListener('click', guardar, false);
    document.getElementById('cerrarsesion').addEventListener('click', borrardatos, false);
    /*Obtener datos almacenados */
    var nombre = localStorage.getItem("Nombre");
    /*Mostrar datos almacenados*/
    var nombreusuario = localStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;

    var solicitud = indexedDB.open("basededatos");
    solicitud.addEventListener("success", comenzar);
    solicitud.addEventListener("upgradeneeded", crearbd);

    var button = document.getElementById("botonenviar");
    button.addEventListener("click", comprobar);

}
function comenzar(evento) {
    alert("sacses");
    bd = evento.target.result;
}

function borrardatos(evt) {
    localStorage.clear();
    /*Mostrar datos almacenados*/
    var nombreusuario = localStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
    location.href = "../html/pantallaInicial.html";
}

function crearbd(evento) {
    alert("Estoy creando");
    bd = evento.target.result;
    var basededatos = evento.target.result;
    var clientes = basededatos.createObjectStore("clientes", {keyPath: "correo"});
    clientes.createIndex('by_nombre', 'nombre', {unique: false});
    clientes.createIndex('by_contra', 'contra', {unique: false});
    clientes.createIndex('by_DNI', 'dni', {unique: true});
    clientes.createIndex('by_tel', 'telefono', {unique: true});
    clientes.add({correo: "admin@gmail.com", nombre: "admin1", contra: "admin", dni: "123456789", telefono: "111222333"});
    clientes.add({correo: "admin2@gmail.com", nombre: "admin2", contra: "admin2", dni: "123456787", telefono: "111222334"});



    var coches = basededatos.createObjectStore("coches", {keyPath: "matricula"});
    coches.createIndex('by_Marca', 'marca', {unique: false});
    coches.createIndex('by_caract', 'caracteristica', {unique: false});
    coches.add({matricula: "abc-789", marca: "Mercedes", caracteristica: "mediano"});
    coches.add({matricula: "abc-123", marca: "Fiat", caracteristica: "grande"});
    coches.add({matricula: "cba-547", marca: "Jaguar", caracteristica: "pequeño"});


    var reservas = basededatos.createObjectStore("reservas", {keyPath: "id", autoIncrement: true});
    reservas.createIndex('by_Matricula', 'matricula', {unique: false});
    reservas.createIndex('by_correo', 'correo', {unique: false});
    reservas.createIndex('by_fi', 'fi', {unique: false});
    reservas.createIndex('by_hi', 'hi', {unique: false});
    reservas.createIndex('by_ff', 'ff', {unique: false});
    reservas.createIndex('by_hf', 'hf', {unique: false});
    reservas.createIndex('by_lugar', 'lugar', {unique: false});


}
function comprobar() {
    alert("Etoi comprobando");
    var transaccion = bd.transaction(["reservas"]);
    alert("Estoy en bd");
    var almacenclientes = transaccion.objectStore("reservas");
    var puntero = almacenclientes.openCursor();
    puntero.addEventListener("success", mostrarlista);
}
function comprueba(event) {
    alert("comprueba");
    var puntero = event.target.result;
    if (puntero) {
        if (puntero.value.correo === document.getElementById('email').value) {
            alert("Bien");
            if (puntero.value.contra.toString() === document.getElementById('contrasena').value) {
                alert("Contra tambien bien");
                guardar(puntero.value.nombre.toString(), document.getElementById('email').value.toString());
                alert("Estas Logeado");
            }
        } else {
            alert("Lo sentimos tu usuario y contraseña no coinciden");
        }

    }
    puntero.continue();
}

function mostrarlista(evento) {
    alert("Estoy en mostrar lista");
    var puntero = evento.target.result;
    if (puntero) {
        alert(puntero.value.correo + "ESTA PUENTRO");
        alert(document.getElementById("emailcliente").value+"ESTE HTML");
        if (puntero.value.correo === document.getElementById("emailcliente").value) {
            alert("entro1");

            document.getElementById('cajadatos').innerHTML += "<div>" + puntero.value.id +
                    " / " + puntero.value.matricula + " / " + puntero.value.correo +
                    " / " + puntero.value.fi + " / " + puntero.value.hi +
                    " / " + puntero.value.ff + " / " + puntero.value.hf +
                    " / " + puntero.value.lugar + "</div>";
        }
        puntero.continue();
    }
}


function compareDates(date1, date2) {
    // Nos quedamos con los días completos pasados desde el 1 de enero de 1970
    let days1 = Math.floor(date1.getTime() / dayInMillis);
    let days2 = Math.floor(date2.getTime() / dayInMillis);
    // comparamos los días
    if (days1 > days2) {
        return 1;
    } else if (days1 < days2) {
        return -1;
    }
    return 0;
}



