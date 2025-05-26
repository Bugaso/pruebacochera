document.getElementById("mostrarDiv").addEventListener("click", function() {
    const nuevoDiv = document.getElementById("nuevoDiv");
    nuevoDiv.classList.toggle("visible"); // Activa la clase para animar
    nuevoDiv.style.display = "block"; // Hace visible el div si estaba oculto
});
