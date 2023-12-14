let nuevoDiv;
const bizumAContacto = (idUsuario) => {
    console.log("Esto funciona");
    
  window.location.href =`/Achilles/ControladorPrincipal/cantidadBizum?idUsuario=${idUsuario}`;
}

const bizumNoDisponible = (elemento) => {
    console.log("No disponible");
    // Crea el elemento div
    nuevoDiv = document.createElement('div');
  
    nuevoDiv.className = 'md:max-w-6xl mx-auto md:w-1/2 opacity-0 transition-opacity ease-out pointer-events-none fixed inset-x-0 bottom-10 sm:px-6 sm:pb-5 lg:px-8';

// Crea el contenido interno del div
    nuevoDiv.innerHTML = `
        <div class="pointer-events-auto flex items-center justify-between gap-x-6 bg-black px-6 py-2.5 sm:rounded-xl sm:py-3 sm:pl-4 sm:pr-3.5">
            <p class="text-sm leading-6 text-zinc-200">
            <a href="#">
                <strong class="font-semibold">Este contacto no tiene Bizum activado</strong>
            </a>
            </p>
            <button type="button" onclick="cerrarBanner()" class="-m-3 flex-none p-3 focus-visible:outline-offset-[-4px]">
            <span class="sr-only"></span>
            <svg class="h-5 w-5 text-selective-yellow-500" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                <path d="M6.28 5.22a.75.75 0 00-1.06 1.06L8.94 10l-3.72 3.72a.75.75 0 101.06 1.06L10 11.06l3.72 3.72a.75.75 0 101.06-1.06L11.06 10l3.72-3.72a.75.75 0 00-1.06-1.06L10 8.94 6.28 5.22z" />
            </svg>
            </button>
        </div>
`;
    console.log(nuevoDiv);
    document.body.appendChild(nuevoDiv);
    
     setTimeout(() => {
            nuevoDiv.classList.remove("opacity-0");
            nuevoDiv.classList.add("opacity-100");
        }, 100);
};


//funcion callback del evento onlick del boton de quitar el banner
const cerrarBanner = () => {
    nuevoDiv.classList.remove("opacity-100");
    nuevoDiv.classList.add("opacity-0");
    setTimeout(() => {
        nuevoDiv.remove();
    }, 300);
};