window.addEventListener('load', inicio, false);
var indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB;
var bd;

function inicio() {
    alert("estoy en inicio");
    document.getElementById('cerrarsesion').addEventListener('click', borrardatos, false); 
    /*Obtener datos almacenados */
    var nombre = sessionStorage.getItem("Nombre");
    /*Mostrar datos almacenados*/
    var nombreusuario = sessionStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
    
    var solicitud = indexedDB.open("basededatos");
    solicitud.addEventListener("success", comenzar);
    solicitud.addEventListener("upgradeneeded", crearbd);
}
function comenzar(evento) {
    alert("sacses");
    bd = evento.target.result;
}
function crearbd(evento){
    {
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
} 
function borrardatos (evt) {
    sessionStorage.clear();
    /*Mostrar datos almacenados*/
    var nombreusuario = sessionStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
     location.href ="../html/pantallaInicial.html";
}

