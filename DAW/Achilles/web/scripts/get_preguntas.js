const bGetPreguntas = document.getElementById("conseguir_preguntas");

bGetPreguntas.addEventListener("click", async () => {
    const iNick = document.getElementById("nick");
    const formulario = document.getElementById("formulario");

        let url = `/Achilles/ControladorLogin/getPreguntasUsuario?nick=${iNick.value}`;

        let response = await fetch(url);
        if (response.ok) {
            let data = await response.json();
            console.log(data);
        formulario.innerHTML = `
            <div class="relative -space-y-px rounded-md shadow-sm">
                <div class="pointer-events-none absolute inset-0 z-10 rounded-md ring-1 ring-inset ring-zinc-500"></div>
                <div class="pl-2 relative block w-full rounded-t-md border-0 py-1.5 bg-zinc-700 text-selective-yellow-500 ring-1 ring-inset ring-zinc-500 sm:text-sm sm:leading-6">${data.p1}</div>
                <div>
                  <label for="respuesta_1" class="sr-only">respuesta 1</label>
                  <input id="respuesta_1" name="respuesta_1" required class="pl-2 relative block w-full border-0 py-1.5 bg-zinc-700 text-zinc-100 ring-1 ring-inset ring-zinc-500 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-brown-400 sm:text-sm sm:leading-6" placeholder="Respuesta 1">
                </div>
                <div class="pl-2 relative block w-full border-0 py-1.5 bg-zinc-700 text-selective-yellow-500 ring-1 ring-inset ring-zinc-500 sm:text-sm sm:leading-6">${data.p2}</div>
                <div>
                  <label for="respuesta_2" class="sr-only">respuesta 2</label>
                  <input id="respuesta_2" name="respuesta_2" required class="pl-2 relative block w-full rounded-b-md border-0 py-1.5 bg-zinc-700 text-zinc-100 ring-1 ring-inset ring-zinc-500 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-brown-400 sm:text-sm sm:leading-6" placeholder="Respuesta 2">
                </div>
                <div>
                  <label for="nueva_pwd" class="sr-only">pwd</label>
                  <input id="nueva_pwd" name="nueva_pwd" type="password" autocomplete="current-password" required class="p-2 relative block w-full rounded-b-md border-0 py-1.5 bg-zinc-700 text-zinc-100 ring-1 ring-inset ring-zinc-500 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-brown-400 sm:text-sm sm:leading-6" placeholder="Nueva contraseña">
                </div>
                <div>
                  <label for="nueva_pwd_repeticion" class="sr-only">repetir pwd</label>
                  <input id="nueva_pwd_repeticion" name="nueva_pwd_repeticion" type="password" autocomplete="current-password" required class="p-2 relative block w-full rounded-b-md border-0 py-1.5 bg-zinc-700 text-zinc-100 ring-1 ring-inset ring-zinc-500 placeholder:text-gray-400 focus:z-10 focus:ring-2 focus:ring-inset focus:ring-brown-400 sm:text-sm sm:leading-6" placeholder="Repite la contraseña">
                </div>
            </div>
            <div>
               <button id="botonSubmit" type="submit" class="flex w-full justify-center rounded-md bg-brown-400 px-3 py-1.5 text-sm font-semibold leading-6 text-zinc-100 hover:bg-brown-300 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-brown-500">
                 Enviar
               </button>
               <!-- <button type="submit" class="flex w-full justify-center rounded-md bg-gradient-to-r from-yellow-300 to-yellow-500 px-3 py-1.5 text-sm font-semibold leading-6 text-white hover:bg-yellow-400 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-yellow-500">
                 Sign in
               </button> -->
            </div>
        `

    }

});


