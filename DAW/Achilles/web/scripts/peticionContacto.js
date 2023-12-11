
const divBanner = document.getElementById("banner_new_usuario");

const submitButton = document.getElementById("submit_button");
submitButton.onclick = realizarPeticion;




async function realizarPeticion() {
    console.log("se mete en realizar");
    const nick = document.getElementById('nick').value;
    const url = `/Achilles/ControladorPrincipal/addContacto?nick=${nick}`;
    let response = await fetch(url);
    if (response.ok) {
        let json = await response.json();
        //Creo cuerpo
        console.log(json);
        const text = json.text;
        divBanner.classList.add("opacity-100");
        const cuerpoBanner = document.createElement("div");
        cuerpoBanner.className = "pointer-events-auto flex items-center justify-between gap-x-6 bg-black px-6 py-2.5 sm:rounded-xl sm:py-3 sm:pl-4 sm:pr-3.5";

        // Relleno cuerpo
        cuerpoBanner.innerHTML = `
              <p class="text-sm leading-6 text-zinc-200">
                <a href="#">
                  <strong class="font-semibold"> ${text}</strong>
                </a>
              </p>
              <button id ="cerrar_banner" onclick="cerrarBanner()" type="button" class="-m-3 flex-none p-3 focus-visible:outline-offset-[-4px]">
                <span class="sr-only"></span>
                <svg class="h-5 w-5 text-selective-yellow-500" viewBox="0 0 20 20" fill="currentColor" aria-hidden="true">
                  <path d="M6.28 5.22a.75.75 0 00-1.06 1.06L8.94 10l-3.72 3.72a.75.75 0 101.06 1.06L10 11.06l3.72 3.72a.75.75 0 101.06-1.06L11.06 10l3.72-3.72a.75.75 0 00-1.06-1.06L10 8.94 6.28 5.22z" />
                </svg>
              </button>
            `;
        divBanner.appendChild(cuerpoBanner);

    } else {
        console.log("Error en la peticion");
    }

}
//funcion callback del evento onlick del boton de quitar el banner
const cerrarBanner = () => {
    divBanner.classList.remove("opacity-100");
    divBanner.classList.add("opacity-0");
};




