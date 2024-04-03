// Obtener los elementos relevantes
var registerModal = document.getElementById('registerModal');
var loginModal = document.getElementById('loginModal');
var registerBtn = document.getElementById("registerBtn");
var entrar = document.getElementById("entrar");
var closeBtns = document.getElementsByClassName("close");

// Función para mostrar ventana emergente de registro
registerBtn.onclick = function() {
    registerModal.style.display = "block";
}

// Función para mostrar ventana emergente de inicio de sesión
entrar.onclick = function() {
    loginModal.style.display = "block";
}

// Función para cerrar la ventana emergente haciendo clic en el botón de cerrar
for (var i = 0; i < closeBtns.length; i++) {
    closeBtns[i].onclick = function() {
        registerModal.style.display = "none";
        loginModal.style.display = "none";
    }
}

// Función para cerrar la ventana emergente haciendo clic en cualquier parte fuera de ella
window.onclick = function(event) {
    if (event.target == registerModal || event.target == loginModal) {
        registerModal.style.display = "none";
        loginModal.style.display = "none";
    }
}

document.getElementById("loginBtn").addEventListener("click", function(event) {
    event.preventDefault(); // Evitar el comportamiento predeterminado de enviar el formulario

    // Aquí pondrías tu lógica de inicio de sesión

    // Simulación de inicio de sesión exitoso
    // Supongamos que la autenticación fue exitosa y el usuario está registrado
    // Cambiar la visibilidad de los botones
    document.getElementById("userButtons").style.display = "none";
    document.getElementById("afterRegistration").style.display = "flex";
});


//Codigo JS para el carrusel ////////////////////
