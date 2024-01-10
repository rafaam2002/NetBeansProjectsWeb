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
        let contR = 0;
        let contE = 0;

        while (indexEnviados < data.mEnviados.length && indexRecividos < data.mRecividos.length) {
            const nuevoDiv = document.createElement("div");
            mEnviado = data.mEnviados[indexEnviados];
            mRecivido = data.mRecividos[indexRecividos];
            fEnviado = new Date(mEnviado.fecha);
            fRecivido = new Date(mRecivido.fecha);
            if (fEnviado < fRecivido) { //mayor indica mas reciente
                contE++;
                contR = 0;
                nuevoDiv.classList.add("self-end");
                nuevoDiv.innerHTML = `
                        <div class="py-1.5 px-2 border-gray-200 bg-zinc-900 ${contE === 1 ? 'rounded-s-xl rounded-ee-xl' : 'rounded-xl'}">
                          <div class="flex items-end gap-2">
                           <p class="text-sm font-normal py-2.5 text-white">${mEnviado.text}</p>
                           <span class="text-xs font-normal text-gray-400">${mEnviado.fecha}</span>
                          </div>
                        </div>
                  `;
                indexEnviados++;
            } else {
                contR++;
                contE = 0;
                nuevoDiv.classList.add("self-start");
                nuevoDiv.innerHTML = `
                        <div class=" py-1.5 px-4 border-gray-200 bg-zinc-600 ${contR === 1 ? 'rounded-e-xl rounded-es-xl' : 'rounded-xl'}">
                           <div class="flex items-end gap-2">
                                <p class="text-sm font-normal py-2.5 text-white">${mRecivido.text}</p>
                                <span class="text-xs font-normal text-gray-400">${mRecivido.fecha}</span>
                           </div>
                        </div>
                  `;
                indexRecividos++;
            }
            div_chat.appendChild(nuevoDiv);
        }

        while (indexEnviados < data.mEnviados.length) {
            contE++;
            mEnviado = data.mEnviados[indexEnviados];
            const nuevoDiv = document.createElement("div");
            nuevoDiv.classList.add("self-end");
            nuevoDiv.innerHTML = `  
                        <div class="py-1.5 px-2 border-gray-200 bg-zinc-900 ${contE === 1 ? 'rounded-s-xl rounded-ee-xl' : 'rounded-xl'}">
                          <div class="flex items-end gap-2">
                           <p class="text-sm font-normal py-2.5 text-white">${mEnviado.text}</p>
                           <span class="text-xs font-normal text-gray-400">${mEnviado.fecha}</span>
                          </div>
                        </div>
                  `;


            div_chat.appendChild(nuevoDiv);
            indexEnviados++;
        }
        while (indexRecividos < data.mRecividos.length) {
            contR++;
            mRecivido = data.mRecividos[indexRecividos];
            const nuevoDiv = document.createElement("div");
            nuevoDiv.classList.add("self-start");
            nuevoDiv.innerHTML = `
                        <div class=" py-1.5 px-4 border-gray-200 bg-zinc-600 ${contR === 1 ? 'rounded-e-xl rounded-es-xl' : 'rounded-xl'}">
                           <div class="flex items-end gap-2">
                                <p class="text-sm font-normal py-2.5 text-white">${mRecivido.text}</p>
                                <span class="text-xs font-normal text-gray-400">${mRecivido.fecha}</span>
                           </div>
                        </div>
                  `;

            div_chat.appendChild(nuevoDiv);
            indexRecividos++;
        }

        const divNickContacto = document.createElement("div");
        divNickContacto.id = "div_nickContacto";
        divNickContacto.classList.add("hidden");
        divNickContacto.innerHTML = nickContacto;

        div_chat.appendChild(divNickContacto);

        div_chat.scrollTop = div_chat.scrollHeight;

//<div id = "div_nickContacto" class = "hidden">${nickContacto}</div>


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
        const chat = document.querySelector('#chat');
        let chatRows = 1;

        chat?.addEventListener("keydown", function (event) {
            const txtarea = event.target;
            if (event.shiftKey && event.key === "Enter") {
                chatRows++;
                if (chatRows < 6)
                    txtarea.rows = chatRows;
            } else {
                const lineas = chat.value.split("\n");
                chatRows = lineas.length;

                for (let i = lineas.length - 1; i >= 0; i--) {
                    if (lineas[i].trim() === '') {
                        chat.rows = chatRows--;
                    } else {
                        break;
                    }
                }
            }
        })

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
            contE++;
            contR = 0;

            const nuevoDiv = document.createElement("div");
            nuevoDiv.classList.add("self-end");

            nuevoDiv.innerHTML = `  
                        <div class="py-1.5 px-2 border-gray-200 bg-zinc-900 ${contE === 1 ? 'rounded-s-xl rounded-ee-xl' : 'rounded-xl'}">
                          <div class="flex items-end gap-2">
                           <p class="text-sm font-normal py-2.5 text-white">${msg.text}</p>
                           <span class="text-xs font-normal text-gray-400">${msg.fecha}</span>
                          </div>
                        </div>
                  `;

            div_chat.insertBefore(nuevoDiv, divInput);
            div_chat.scrollTop = div_chat.scrollHeight;
            mensaje.value = "";
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

            contR++;
            contE = 0;

            const nuevoDiv = document.createElement("div");

            nuevoDiv.classList.add("self-start");
            nuevoDiv.innerHTML = `
                        <div class=" py-1.5 px-4 border-gray-200 bg-zinc-600 ${contR === 1 ? 'rounded-e-xl rounded-es-xl' : 'rounded-xl'}">
                           <div class="flex items-end gap-2">
                                <p class="text-sm font-normal py-2.5 text-white">${msg.text}</p>
                                <span class="text-xs font-normal text-gray-400">${msg.fecha}</span>
                           </div>
                        </div>
                  `;



            div_chat.insertBefore(nuevoDiv, divInput);

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
