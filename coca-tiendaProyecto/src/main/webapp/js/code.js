window.addEventListener('load', function() {
  setTimeout(function() {
    document.getElementById('load').classList.add('hide');
  }, 2000); // Oculta la animación después de 2 segundos (2000 milisegundos)
});

// Función para mostrar el PopUp
function openPopup(popupId) {
    var popup = document.getElementById(popupId);
    if (popup) {
        popup.style.display = "block";
    }
}

// Función para ocultar el PopUp
function closePopup(popupId) {
    var popup = document.getElementById(popupId);
    if (popup) {
        popup.style.display = "none";
    }
}

// Evento click para el enlace de Acerca de
document.getElementById("about-link").addEventListener("click", function() {
    openPopup("about-popup");
});

// Evento click para el enlace de Contacto
document.getElementById("contact-link").addEventListener("click", function() {
    openPopup("contact-popup");
});

