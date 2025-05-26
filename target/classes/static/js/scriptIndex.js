function showSection(id) {
    document.querySelectorAll('.section').forEach(sec => sec.style.display = 'none');
    document.getElementById(id).style.display = 'block';
}

// Manejo del formulario para añadir tipo de vehículo
document.addEventListener("DOMContentLoaded", () => {
    const formTipoVehiculo = document.getElementById('formTipoVehiculo');
    const mensaje = document.getElementById('mensaje');

    if (formTipoVehiculo) {
        formTipoVehiculo.addEventListener('submit', function (e) {
            e.preventDefault();

            // Aquí podrías integrar con tu backend usando fetch
            mensaje.style.display = 'block';

            setTimeout(() => {
                formTipoVehiculo.reset();
                mensaje.style.display = 'none';
            }, 2000);
        });
    }
});

//Envio de datos mediante solicitudes HTTP POST

document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("formTipoVehiculo");
    const respuesta = document.getElementById("respuesta");

    if (form) {
        form.addEventListener("submit", function (event) {
            event.preventDefault();

            const nombre = document.getElementById("nombre").value;
            const precio = parseFloat(document.getElementById("precio").value);

            fetch('/tipo-vehiculo/crear-con-precio', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ nombre, precio })
            })
            .then(response => {
                if (!response.ok) throw new Error("Error al guardar");
                return response.json();
            })
            .then(data => {
                respuesta.innerHTML = `<div class="alert alert-success">Tipo de vehículo "${data.nombre}" registrado correctamente.</div>`;
                form.reset();
            })
            .catch(error => {
                console.error("Error:", error);
                respuesta.innerHTML = `<div class="alert alert-danger">Hubo un error al guardar.</div>`;
            });
        });
    }
});
