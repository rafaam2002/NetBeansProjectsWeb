//
//
//
//
//(function () {
//    'use strict';
////    var url = 'ws://localhost:8080/Sockets1/chat';
//    var url = 'ws://' + window.location.host + '/Achilles/chat';
//    const ws = new WebSocket(url);
//    const mensajes = document.getElementById('div_conversacion'); //falta
//    const boton = document.getElementById('button_enviar'); //check
//    const nickRec = document.getElementById('nickContacto');  //check
//    const mensaje = document.getElementById("textarea_text"); //check
//    const divNick = document.getElementById('div_nick'); //check
//    const nick = divNick.innerHTML;
//    let date;
//    let i = 0;
//    ws.onopen = onOpen;
//    ws.onclose = onClose;
//    ws.onmessage = onMessage;
//    boton.addEventListener('click', enviar);
//    window.addEventListener('unload', persistirMensajes);
//    document.addEventListener("visibilitychange", () => {
//        if (document.hidden) {
//            console.log("cambia pestania");
//            persistirMensajes();
//        }
//    });
//
//
//    function onOpen() {
//        console.log('Conectado');
//        enviarNick(nick);
////        ws.send('yo soy un bola e pinga')
//    }
//
//
//    function onClose() {
//        console.log('Desconectado');
//        persistirMensajes();
//    }
//
//    function enviar() {
//        date = new Date();
//        let anio = date.getFullYear();
//        let mes = date.getMonth() + 1;
//        let dia = date.getDate();
//        let horas = date.getHours();
//        let minutos = date.getMinutes();
//        let segundos = date.getSeconds();
//        // Fecha con el mismo formato que LocalDateTime (le suma un 0 a la derecha si hiciera falta)
//        let fechaFormateada = `${anio}-${mes < 10 ? '0' : ''}${mes}-${dia < 10 ? '0' : ''}${dia}T${horas < 10 ? '0' : ''}${horas}:${minutos < 10 ? '0' : ''}${minutos}:${segundos < 10 ? '0' : ''}${segundos}`;
//
//        var msg = {
//            nEmisor: nick,
//            nReceptor: nickRec.value,
//            text: mensaje.value,
//            fecha: fechaFormateada,
//        };
//        
//        ws.send(JSON.stringify(msg));
//    }
//    function onMessage(evt) {
////        console.log(evt);
//        localStorage.setItem(i, evt.data);
//        if (i >= 1) {
//            //si no se han mandado mas de 40 mensajes no hago nada
//            persistirMensajes();
//        }
//        i++;
//        var obj = JSON.parse(evt.data);
//        var msg = 'dice: ' + obj.text + " con fecha: " + obj.fecha + "con id: " ; //  'Nombre: ' + obj.nReceptor +
//        mensajes.innerHTML += '<br/>' + msg;
//    }
//    function enviarNick(nick) {
//        if (nick === "" || nick === null) {
//            console.log("no se puede enviar el nick");
//        } else {
//            var msg = {
//                nEmisor: nick,
//                nReceptor: "",
//                text: "nueva conexion",
//                fecha: "",
//            };
//            ws.send(JSON.stringify(msg));
//        }
//    }
//
//    async function persistirMensajes() {
////        console.log("Entra en persistir mensajes");
//        let arrayMensajes = [];
//        for (let i = 0; i < localStorage.length; i++) {
//            let mensaje = JSON.parse(localStorage.getItem(i));
//            arrayMensajes.push(mensaje);
//        }
//
//
//        
//        fetch("/Achilles/ControladorPrincipal/persistirMensajes", {
//            method: 'POST',
//            headers: {
//                'Content-Type': 'application/json'
//            },
//            body: JSON.stringify(arrayMensajes)
//        })
//                .then(function (respuesta) {
//                    console.log('Solicitud POST completada con éxito');
//                    //limpio el localStorage
//                    localStorage.clear();
//                    i = 0;
//                })
//                .catch(function (error) {
//                    console.error('Error al realizar la solicitud POST:', error);
//                });
//    }
//})();
//
//
