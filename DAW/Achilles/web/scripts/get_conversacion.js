let nickContacto;
let url;
let div_chat;
let divInput;

async function getConversacion(e) {
    div_chat = document.getElementById("div_chat");
    div_chat.innerHTML = "";
    const div_seleccionado = e.target;
    nickContacto = div_seleccionado.id;
    url = `/Achilles/ControladorPrincipal/getConversacion?nick=${nickContacto}`;
    let response = await fetch(url);
    if (response.ok) {
        let data = await response.json();

       console.log(data);

        let indexEnviados = 0;
        let indexRecividos = 0;
        let fEnviado;
        let fRecivido;
        let mEnviado;
        let mRecivido;



        while (indexEnviados < data.mEnviados.length && indexRecividos < data.mRecividos.length) {
            const nuevoDiv = document.createElement("div");
            mEnviado = data.mEnviados[indexEnviados];
            mRecivido = data.mRecividos[indexRecividos];
            fEnviado = new Date(mEnviado.fecha);
            fRecivido = new Date(mRecivido.fecha);
            if (fEnviado < fRecivido) { //mayor indica mas reciente

                nuevoDiv.innerHTML = `
                    <div class="float-right mr-2 pt-1">
                      <div class="flex items-start gap-2.5">
                        <div class="w-8 h-8 rounded-full">Yo</div>
                        <div
                          class="flex flex-col w-full max-w-[320px] leading-1.5 p-4 border-gray-200 bg-zinc-900 rounded-e-xl rounded-es-xl"
                        >
                          <div class="flex items-center space-x-2 rtl:space-x-reverse">
                            <span class="text-sm font-normal text-gray-400">${mEnviado.fecha}</span>
                          </div>
                          <p class="text-sm font-normal py-2.5 text-white">
                            ${mEnviado.text}
                          </p>
                        </div>
                      </div>
                    </div>
                  `;

                indexEnviados++;
            } else {



                nuevoDiv.innerHTML = `
                    <div class="float-left pt-1">
                      <div class="flex items-start gap-2.5">
                        <img
                          class="w-8 h-8 rounded-full"
                          src="/Achilles/images/usuario.png"
                          alt="user image"
                        />
                        <div
                          class="flex flex-col w-full max-w-[320px] leading-1.5 p-4 border-gray-200 bg-zinc-600 rounded-e-xl rounded-es-xl"
                        >
                          <div class="flex items-center space-x-2 rtl:space-x-reverse">
                            <span class="text-sm font-semibold dark:text-white">${mRecivido.nEmisor}</span>
                            <span class="text-sm font-normal text-gray-400">${mRecivido.fecha}</span>
                          </div>
                          <p class="text-sm font-normal py-2.5 text-white">
                           ${mRecivido.text}
                          </p>
                        </div>
                      </div>
                    </div>
                  `;

                indexRecividos++;
            }
            const clearDiv = document.createElement("div");
            clearDiv.classList.add("clear-both");

            div_chat.appendChild(nuevoDiv);
            div_chat.appendChild(clearDiv);

        }




        while (indexEnviados < data.mEnviados.length) {
            mEnviado = data.mEnviados[indexEnviados];
            const nuevoDiv = document.createElement("div");

            nuevoDiv.innerHTML = `
                    <div class="float-right mr-2 pt-1">
                      <div class="flex items-start gap-2.5">
                        <div class="w-8 h-8 rounded-full">Yo</div>
                        <div
                          class="flex flex-col w-full max-w-[320px] leading-1.5 p-4 border-gray-200 bg-zinc-900 rounded-e-xl rounded-es-xl"
                        >
                          <div class="flex items-center space-x-2 rtl:space-x-reverse">
                            <span class="text-sm font-normal text-gray-400">${mEnviado.fecha}</span>
                          </div>
                          <p class="text-sm font-normal py-2.5 text-white">
                            ${mEnviado.text}
                          </p>
                        </div>
                      </div>
                    </div>
                  `;


            const clearDiv = document.createElement("div");
            clearDiv.classList.add("clear-both");

            div_chat.appendChild(nuevoDiv);
            div_chat.appendChild(clearDiv);
            indexEnviados++;
        }
        while (indexRecividos < data.mRecividos.length) {
            mRecivido = data.mRecividos[indexRecividos];
            const nuevoDiv = document.createElement("div");
            nuevoDiv.innerHTML = `
                    <div class="float-left pt-1">
                      <div class="flex items-start gap-2.5">
                        <img
                          class="w-8 h-8 rounded-full"
                          src="/Achilles/images/usuario.png"
                          alt="user image"
                        />
                        <div
                          class="flex flex-col w-full max-w-[320px] leading-1.5 p-4 border-gray-200 bg-zinc-600 rounded-e-xl rounded-es-xl"
                        >
                          <div class="flex items-center space-x-2 rtl:space-x-reverse">
                            <span class="text-sm font-semibold dark:text-white">${mRecivido.nEmisor}</span>
                            <span class="text-sm font-normal text-gray-400">${mRecivido.fecha}</span>
                          </div>
                          <p class="text-sm font-normal py-2.5 text-white">
                           ${mRecivido.text}
                          </p>
                        </div>
                      </div>
                    </div>
                  `;
            const clearDiv = document.createElement("div");
            clearDiv.classList.add("clear-both");

            div_chat.appendChild(nuevoDiv);
            div_chat.appendChild(clearDiv);
            indexRecividos++;
        }

        divInput = document.createElement("div");
        divInput.innerHTML = `
            <label for="chat" class="sr-only">Your message</label>
            <div class="flex items-center px-3 py-2 rounded-lg bg-zinc-700">
              <textarea
                id="textarea_text"
                rows="1"
                class="block mx-4 p-2.5 w-full text-sm rounded-lg border focus:ring-selective-yellow-500 focus:border-selective-yellow-500 bg-zinc-800 border-gray-600 placeholder-gray-400 text-white"
                placeholder="Your message..."
              ></textarea>
              <button
                type="submit"
                id = "button_enviar"
                class="inline-flex justify-center p-2 text-blue-600 rounded-full cursor-pointer hover:bg-blue-100 dark:text-blue-500 dark:hover:bg-gray-600"
              >
                <svg
                  class="w-5 h-5 rotate-90 rtl:-rotate-90 text-selective-yellow-500"
                  aria-hidden="true"
                  xmlns="http://www.w3.org/2000/svg"
                  fill="currentColor"
                  viewBox="0 0 18 20"
                >
                  <path
                    d="m17.914 18.594-8-18a1 1 0 0 0-1.828 0l-8 18a1 1 0 0 0 1.157 1.376L8 18.281V9a1 1 0 0 1 2 0v9.281l6.758 1.689a1 1 0 0 0 1.156-1.376Z"
                  />
                </svg>
                <span class="sr-only">Send message</span>
              </button>
            </div>
            <div id = "div_nickContacto" class = "hidden">${nickContacto}</div>
          `;
        div_chat.appendChild(divInput);


//        codigo Socket

        url = 'ws://' + window.location.host + '/Achilles/chat';
        const ws = new WebSocket(url);
//        const mensajes = document.getElementById('div_conversacion'); //falta
        const boton = document.getElementById('button_enviar'); //check
        const div_nickRec = document.getElementById('div_nickContacto');  //check
        const nickRec = div_nickRec.innerHTML;
        const mensaje = document.getElementById("textarea_text"); //check
        const divNick = document.getElementById('div_nick'); //check
        const nick = divNick.innerHTML;


        let date;
        let i = 0;
        ws.onopen = onOpen;
        ws.onclose = onClose;
        ws.onmessage = onMessage;
        boton.addEventListener('click', enviar);
//        window.addEventListener('unload', persistirMensajes);
//        document.addEventListener("visibilitychange", () => {
////            if (document.hidden) {
////                console.log("cambia pestania");
////                persistirMensajes();
////            }
//        });


        function onOpen() {
            console.log('Conectado');
            enviarNick(nick);
        }


        function onClose() {
            console.log('Desconectado');
//            persistirMensajes();
        }

        function enviar() {
            console.log("enviando")
            date = new Date();
            let anio = date.getFullYear();
            let mes = date.getMonth() + 1;
            let dia = date.getDate();
            let horas = date.getHours();
            let minutos = date.getMinutes();
            let segundos = date.getSeconds();
            // Fecha con el mismo formato que LocalDateTime (le suma un 0 a la derecha si hiciera falta)
            let fechaFormateada = `${anio}-${mes < 10 ? '0' : ''}${mes}-${dia < 10 ? '0' : ''}${dia}T${horas < 10 ? '0' : ''}${horas}:${minutos < 10 ? '0' : ''}${minutos}:${segundos < 10 ? '0' : ''}${segundos}`;

            var msg = {
                nEmisor: nick,
                nReceptor: nickRec,
                text: mensaje.value,
                fecha: fechaFormateada
            };
            ws.send(JSON.stringify(msg));
            console.log(JSON.stringify(msg));

            localStorage.setItem(i, JSON.stringify(msg));
//            if (i >= 1) {
            //si no se han mandado mas de 40 mensajes no hago nada
            persistirMensajes();
//            }
            i++;

            const nuevoDiv = document.createElement("div");
            nuevoDiv.innerHTML = `
                    <div class="float-right mr-2 pt-1">
                      <div class="flex items-start gap-2.5">
                        <div class="w-8 h-8 rounded-full">Yo</div>
                        <div
                          class="flex flex-col w-full max-w-[320px] leading-1.5 p-4 border-gray-200 bg-zinc-900 rounded-e-xl rounded-es-xl"
                        >
                          <div class="flex items-center space-x-2 rtl:space-x-reverse">
                            <span class="text-sm font-normal text-gray-400">${msg.fecha}</span>
                          </div>
                          <p class="text-sm font-normal py-2.5 text-white">
                            ${msg.text}
                          </p>
                        </div>
                      </div>
                    </div>
                  `;
            const clearDiv = document.createElement("div");
            clearDiv.classList.add("clear-both");

            div_chat.insertBefore(nuevoDiv, divInput);
            div_chat.insertBefore(clearDiv, divInput);
        }
        function onMessage(evt) {

            console.log(evt);
//            localStorage.setItem(i, evt.data);
//            if (i >= 1) {
//                //si no se han mandado mas de 40 mensajes no hago nada
//                persistirMensajes();
//            }
//            i++;
            var msg = JSON.parse(evt.data);

            const nuevoDiv = document.createElement("div");
            nuevoDiv.innerHTML = `
                    <div class="float-left pt-1">
                      <div class="flex items-start gap-2.5">
                        <img
                          class="w-8 h-8 rounded-full"
                          src="/Achilles/images/usuario.png"
                          alt="user image"
                        />
                        <div
                          class="flex flex-col w-full max-w-[320px] leading-1.5 p-4 border-gray-200 bg-zinc-600 rounded-e-xl rounded-es-xl"
                        >
                          <div class="flex items-center space-x-2 rtl:space-x-reverse">
                            <span class="text-sm font-semibold dark:text-white">${msg.nEmisor}</span>
                            <span class="text-sm font-normal text-gray-400">${msg.fecha}</span>
                          </div>
                          <p class="text-sm font-normal py-2.5 text-white">
                           ${msg.text}
                          </p>
                        </div>
                      </div>
                    </div>
                  `;

            const clearDiv = document.createElement("div");
            clearDiv.classList.add("clear-both");

            div_chat.insertBefore(nuevoDiv, divInput);
            div_chat.insertBefore(clearDiv, divInput);

        }
        function enviarNick(nick) {
            if (nick === "" || nick === null) {
                console.log("no se puede enviar el nick");
            } else {
                var msg = {
                    nEmisor: nick,
                    nReceptor: "",
                    text: "nueva conexion",
                    fecha: ""
                };
                console.log("nick usuario: " + nick)
                ws.send(JSON.stringify(msg));
            }
        }

        async function persistirMensajes() {

            console.log("persistiendo");
            let arrayMensajes = [];
            for (let i = 0; i < localStorage.length; i++) {
                let mensaje = JSON.parse(localStorage.getItem(i));
                arrayMensajes.push(mensaje);
            }



            fetch("/Achilles/ControladorPrincipal/persistirMensajes", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(arrayMensajes)
            })
                    .then(function (respuesta) {
                        console.log('Solicitud POST completada con éxito');
                        //limpio el localStorage
                        localStorage.clear();
                        i = 0;
                    })
                    .catch(function (error) {
                        console.error('Error al realizar la solicitud POST:', error);
                    });
        }

    } else {
        console.log("Error en la peticion");

    }


}
