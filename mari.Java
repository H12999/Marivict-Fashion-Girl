/**
 * app.js
 * Sistema de Carrito de Compras
 */

// 1. Selectores del DOM
const contadorCarritoDOM = document.getElementById('contador-carrito'); 
const botonesAgregar = document.querySelectorAll('.btn-agregar'); 

// 2. Inicialización del Carrito
// Intentamos cargar el carrito desde el almacenamiento local, si no existe, inicializamos como array vacío.
let carrito = JSON.parse(localStorage.getItem('carritoItems')) || [];

// Función para guardar el estado del carrito en el localStorage
function guardarCarrito() {
    localStorage.setItem('carritoItems', JSON.stringify(carrito));
}

// 3. Función para Actualizar la Interfaz
function actualizarContador() {
    // Si el contador existe en el HTML, actualiza su texto.
    if (contadorCarritoDOM) {
        contadorCarritoDOM.textContent = carrito.length;
    }
}

// 4. Función para Añadir Producto al Carrito
function agregarProducto(evento) {
    evento.preventDefault(); 
    
    // Obtener el elemento principal del producto (donde están los datos)
    const productoElemento = evento.target.closest('.producto');
    
    if (productoElemento) {
        // Obtenemos los datos del producto directamente de los atributos 'data-' del HTML
        const productoID = productoElemento.dataset.id;
        const nombreProducto = productoElemento.dataset.nombre;
        const precioProducto = parseFloat(productoElemento.dataset.precio); // Convertir a número

        // Crear un objeto con la información del producto
        const nuevoItem = {
            id: productoID,
            nombre: nombreProducto,
            precio: precioProducto,
            cantidad: 1 // Por defecto, se añade una unidad
        };

        // 1. Agregar el nuevoItem al array 'carrito'
        carrito.push(nuevoItem); 
        
        // 2. Guardar la nueva lista en localStorage
        guardarCarrito();

        // 3. Notificación y feedback (opcional)
        alert(`¡"${nombreProducto}" ha sido añadido a tu carrito!`);
        
        // 4. Actualizar la interfaz
        actualizarContador();
    }
}

// 5. Asignación de Event Listeners
botonesAgregar.forEach(boton => {
    boton.addEventListener('click', agregarProducto);
});

// 6. Ejecución inicial
// Asegurarse de que el contador se actualice al cargar la página
actualizarContador();

console.log("Sistema de tienda y carrito inicializado.");