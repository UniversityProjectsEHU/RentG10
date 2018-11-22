
window.addEventListener('load', inicio, false);
var correo, contra, nomb;
var cajadatos;
function inicio() {
    //Validación en tiempo real
    usuario = document.getElementById("email");
    contra = document.getElementById("contrasena");
    nomb = document.getElementById("nombre");
    usuario.addEventListener("input", validacion);
    contra.addEventListener("input", validacion);
    nomb.addEventListener("input", validacion);
    validacion(); 
    

    //DRAG AND DROP
    cajadatos = document.getElementById("cajadatos");
    var archivos = document.getElementById("archivos");
    archivos.addEventListener("change", procesar);
}

function validacion() {

    if (usuario.value === "") {
        usuario.setCustomValidity("Inserte su nombre o su apellido");
        usuario.style.background = "red";
    } else {
        usuario.setCustomValidity("");
        usuario.style.background = "green";
    }

    if (contra.value === "") {
        contra.setCustomValidity("Inserte su contraseña");
        contra.style.background = "red";
    } else {
        contra.setCustomValidity("");
        contra.style.background = "green";
    }

    if (nomb.value === "") {
        nomb.setCustomValidity("Inserte su nombre");
        nomb.style.background = "red";
    } else {
        nomb.setCustomValidity("");
        nomb.style.background = "green";
    }

}


//DRAG AND DROP
function procesar(evento) {
    var archivos = evento.target.files;
    cajadatos.innerHTML = "";

    var archivo = archivos[0];
    if (!archivo.type.match(/image.*/i)) {
        alert("Insertar una imagen");
    } else {
        cajadatos.innerHTML += "Nombre: " + archivo.name + "<br>";
        cajadatos.innerHTML += "Tamaño: " + archivo.size + " bytes<br>";
        var lector = new FileReader();
        lector.addEventListener("load", mostrar);
        lector.readAsDataURL(archivo);
    }
}
function mostrar(evento) {
    var resultado = evento.target.result;
    cajadatos.innerHTML += '<img src="' + resultado + '">';
} 

