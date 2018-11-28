
window.addEventListener('load', inicio, false);

var correo,contra;
var indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB;
var bd;
function inicio() {
    alert("estoy en inicio");
//    document.getElementById('botonlogin').addEventListener('click', guardar, false);
    document.getElementById('cerrarsesion').addEventListener('click', borrardatos, false); 
    /*Obtener datos almacenados */
    var nombre = sessionStorage.getItem("Nombre");
    /*Mostrar datos almacenados*/
    var nombreusuario = sessionStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
    //Validación en tiempo real
    usuario=document.getElementById("email");
    contra=document.getElementById("contrasena");
    usuario.addEventListener("input",validacion);
    contra.addEventListener("input",validacion);
    validacion();
    var solicitud = indexedDB.open("basededatos");
    solicitud.addEventListener("success", comenzar);
    solicitud.addEventListener("upgradeneeded", crearbd);
    
    boton2=document.getElementById("botonlogin");
    boton2.addEventListener("click",comprobar);
    
}
function comenzar(evento){
    alert("sacses");
    bd=evento.target.result;
}


function guardar(nomb,ema) {
    sessionStorage.setItem("Nombre", nomb);
    sessionStorage.setItem("Email", ema);
    /*Obtener datos almacenados*/
    var nombre = sessionStorage.getItem("Nombre");
    /*Mostrar datos almacenados*/
    var nombreusuario = sessionStorage.getItem("Nombre");
    document.getElementById("nombree").innerHTML = nombreusuario;
}

function borrardatos (evt) {
    sessionStorage.clear();
    /*Mostrar datos almacenados*/
    var nombreusuario = sessionStorage.getItem("Nombre");
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
function comprobar(){
    alert("Etoi comprobando");
    var usuario,contraseña;
    usuario=document.getElementById('email').value;
    contraseña=document.getElementById("contrasena").value;
    var transaccion=bd.transaction(["clientes"]);
    var almacenclientes=transaccion.objectStore("clientes");
    var puntero=almacenclientes.openCursor();
    puntero.addEventListener("success",comprueba);
    
}
function comprueba(event){
    alert("comprueba");
    var puntero=event.target.result;
    var notfound=true;
    if(puntero){
        if(puntero.value.correo.toString()===document.getElementById('email').value){
           alert("Bien");
            if(puntero.value.contra.toString()===document.getElementById('contrasena').value){
                alert("Contra tambien bien");
                
                guardar(puntero.value.nombre.toString(),document.getElementById('email').value.toString());
                alert("Estas Logeado");
            }
        }
       
       
    } 
  
    puntero.continue();
}
