
console.log("El script controlTurno.js se ha cargado correctamente.");

document.addEventListener("DOMContentLoaded", function () {
    const btnTurnos = document.getElementById("btnTurnos");
    const contenidoTurnos = document.getElementById("contenidoTurnos");

    btnTurnos.addEventListener("click", function () {
        if (contenidoTurnos.style.display === "none") {
            contenidoTurnos.style.display = "block";
        } else {
            contenidoTurnos.style.display = "none";
        }
    });
});
