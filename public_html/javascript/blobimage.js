/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global URL */

var cajadatos;
function iniciar() {
    cajadatos = document.getElementById("cajadatos");
    var archivos = document.getElementById("archivos");
    archivos.addEventListener("change", procesar);
}
function procesar(evento) {
    cajadatos.innerHTML = "";
    var archivos = evento.target.files;
    var archivo = archivos[0];
    var lector = new FileReader();
    lector.addEventListener("load", function (evento) {
        mostrar(evento, archivo);
    });
    lector.readAsBinaryString(archivo);
}
function mostrar(evento, archivo) {
    var url = URL.createObjectURL(archivo);
    var imagen = document.createElement("img");
    imagen.src = url;
    cajadatos.appendChild(imagen);
}
window.addEventListener("load", iniciar);

