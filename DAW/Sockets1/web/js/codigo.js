
const nick = "rafa";
var ws;


(function () {
    'use strict';
    const nick = "rafa";
    var url = 'ws://localhost:8080/Sockets1/chat';
//    var url = 'ws://' + window.location.host + '/chat';
    ws = new WebSocket(url);

    var mensajes = document.getElementById('conversacion');
    var boton = document.getElementById('btnEnviar');
    var nombre = document.getElementById('usuario');
    var mensaje = document.getElementById("mensaje");

    ws.onopen = onOpen;

    //envio mi nick
//    (function () {
//        if (nick === "" || nick === null) {
//            console.log("no se puede enviar el nick");
//        } else {
//            var msg = {
//                nEmisor: nick,
//                nReceptor: "",
//                mensaje: ""
//            };
//            ws.send(JSON.stringify(msg));
//        }
//    })();
    ws.onclose = onClose;
    ws.onmessage = onMessage;
    boton.addEventListener('click', enviar);

    function onOpen() {
        console.log('Conectado');
        enviarNick(nick);
//        ws.send('yo soy un bola e pinga')
    }


    function onClose() {
        console.log('Desconectado');
    }

    function enviar() {
        var msg = {
            nombre: nombre.value,
            mensaje: mensaje.value
        };
        ws.send(JSON.stringify(msg));
    }
    function onMessage(evt) {
        console.log(evt);
        var obj = JSON.parse(evt.data);
        var msg =  'dice: ' + obj.mensaje; //  'Nombre: ' + obj.nReceptor +
        mensajes.innerHTML += '<br/>' + msg;
    }
    function enviarNick(nick) {
        if (nick === "" || nick === null) {
            console.log("no se puede enviar el nick");
        } else {
            var msg = {
                nEmisor: nick,
                nReceptor: "",
                mensaje: "nueva conexion"
            };
            ws.send(JSON.stringify(msg));
        }
    }
})();



