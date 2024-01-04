const input = document.querySelector("#userSearch");
const arrayContenido = document.querySelectorAll(".contenido");
let i = 0;
var patron = /pe.*/;

input.addEventListener("input", (e) => {
  let nombre = e.target.value.toLowerCase();
  let expresion = new RegExp("^" + nombre + ".*");

  arrayContenido.forEach((contenido) => {
    const divNick = contenido.querySelector(".div_nick");
    nick = divNick.innerHTML.toLowerCase();
    if(expresion.test(nick)){
      if(contenido.classList.contains("hidden")){
        contenido.classList.remove("hidden");
      }  
    } else {
      if(!contenido.classList.contains("hidden")){
        contenido.classList.add("hidden");
      }
    }
  });
});
