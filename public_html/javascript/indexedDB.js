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
    
    
    
//    var botoncre=document.getElementById("botoncrear");
//    botoncre.addEventListener("click",crea);
    
}
function mostrarerror(){
    alert("error");
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
    clientes.add({correo:"admin@gmail.com",nombre:"admin1",contra:"admin",dni:"123456789",telefono:"111222333"});
    clientes.add({correo:"admin2@gmail.com",nombre:"admin2",contra:"admin2",dni:"123456787",telefono:"111222334"});
    
   

   var coches= basededatos.createObjectStore("coches",{keyPath:"matricula"});
   coches.createIndex('by_Marca', 'marca', {unique: false});
   coches.createIndex('by_caract', 'caracteristica', {unique: false});
   coches.add({matricula:"abc-789",marca:"Mercedes",caracteristica:"mediano"});
   coches.add({matricula:"abc-123",marca:"Fiat",caracteristica:"grande"});
   coches.add({matricula:"cba-547",marca:"Jaguar",caracteristica:"pequeño"});
   

   var reservas= basededatos.createObjectStore("reservas",{keyPath:"id"});
    reservas.createIndex('by_Matricula', 'matricula', {unique: true});
    reservas.createIndex('by_correo', 'correo', {unique: true});
    reservas.createIndex('by_fi', 'fi', {unique: false});
    reservas.createIndex('by_hi', 'hi', {unique: false});
    reservas.createIndex('by_ff', 'ff', {unique: false});
    reservas.createIndex('by_hf', 'hf', {unique: false});
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

