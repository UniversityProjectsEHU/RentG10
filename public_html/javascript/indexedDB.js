/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB;
window.addEventListener("load", iniciar);
var bd;
var boton2;
function iniciar() {
    alert("ini");
    cajadatos = document.getElementById("cajadatos");
    var boton = document.getElementById("botonenviar");
    boton.addEventListener("click", agregarobjeto);
    


    var solicitud = indexedDB.open("basededatos");
    solicitud.addEventListener("error", mostrarerror);
    solicitud.addEventListener("success", comenzar);
    solicitud.addEventListener("upgradeneeded", crearbd);
    
    boton2=document.getElementById("botonlogin");
    boton2.addEventListener("click",comprobar);
}
function mostrarerror(){
    alert("error");
}
function comenzar(evento){
    alert("sacses");
    bd=evento.target.result;
}
function comenzar(evento){
    alert("sacses");
    bd=evento.target.result;
}

function crearbd(evento) {
    alert("Estoy creando");
    bd=evento.target.result;
    var basededatos = evento.target.result;
    var clientes = basededatos.createObjectStore("clientes", {keyPath: "correo"});
    clientes.createIndex('by_nombre', 'nombre', {unique: false});
    clientes.createIndex('by_contra', 'contra', {unique: false});
    clientes.createIndex('by_DNI', 'dni', {unique: true});
    clientes.createIndex('by_tel', 'telefono', {unique: true});


   var coches= basededatos.createObjectStore("coches",{keyPath:"matricula"});
   coches.createIndex('by_Marca', 'marca', {unique: false});
   coches.createIndex('by_caract', 'caracteristica', {unique: false});

   var reservas= basededatos.createObjectStore("reservas",{keyPath:"id"});
    reservas.createIndex('by_Matricula', 'matricula', {unique: true});
    reservas.createIndex('by_correo', 'correo', {unique: true});
    reservas.createIndex('by_fhi', 'fhi', {unique: false});
    reservas.createIndex('by_fhf', 'fhf', {unique: false});
    reservas.createIndex('by_lugar', 'lugar', {unique: false});
}

function agregarobjeto() {
    
    alert("agrega");
    var transaccion = bd.transaction(["clientes"], "readwrite");
    var almacenclientes = transaccion.objectStore("clientes");
    
    var correo=document.getElementById("email").value;
    var nom=document.getElementById("nombre").value;
    var con=document.getElementById("contrasena").value;
    var dnie=document.getElementById("dni").value;
    var tel=document.getElementById("telefono").value;
    
    almacenclientes.add({correo:correo,nombre:nom,contra:con,dni:dnie,telefono:tel});
}

function comprobar(){
    alert("Etoi comprobando");
    var usuario,contraseña;
    usuario=document.getElementById("email").value;
    contraseña=document.getElementById("contrasena").value;
    
var transaction = bd.transaction(["clientes"]);
var objectStore = transaction.objectStore("clientes");
var request = objectStore.get(usuario);
request.onerror = function() {
  // Handle errors!
  boton2.setCustomValidity("Email incorrecto");
};
request.onsuccess = function() {
  // Do something with the request.result!
  request.objectStore.get(contraseña);
  
  request.onerror = function() {
  // Handle errors!
  boton2.setCustomValidity("Contraseña incorrecta incorrecto");
};
request.onsuccess = function() {
  // Do something with the request.result!
alert("eeeei");
};

};
}