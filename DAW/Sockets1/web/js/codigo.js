
(function () {
    'use strict';
    var url = 'ws://localhost:8080/Sockets1/chat';
//    var url = 'ws://' + window.location.host + '/chat';
    var ws = new WebSocket(url);
    var mensajes = document.getElementById('conversacion');
    var boton = document.getElementById('btnEnviar');
    var nombre = document.getElementById('usuario');
    var mensaje = document.getElementById("mensaje");

    ws.onopen = onOpen();
    ws.onclose = onClose();
    ws.onmessage = onMessage;
    boton.addEventListener('click', enviar);

    function onOpen() {
        console.log('Conectado');
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
        var obj = JSON.parse(evt.data);
        var msg = 'Nombre: ' + obj.nombre + 'dice: ' + obj.mensaje;
        mensajes.innerHTML += '<br/>' + msg;
    }
})();


