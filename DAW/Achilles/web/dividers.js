const filas = document.querySelectorAll("tr.contenido");

filas.forEach(filaContenido => {
    const dividerSup = filaContenido.previousElementSibling;
    const dividerInf = filaContenido.nextElementSibling;
    filaContenido.addEventListener("mouseover",()=>{
        if(dividerSup) dividerSup.classList.add("invisible");
        if(dividerInf) dividerInf.classList.add("invisible");
    })
    filaContenido.addEventListener("mouseout",()=>{
        if(dividerSup) dividerSup.classList.remove("invisible");
        if(dividerInf) dividerInf.classList.remove("invisible");
    });
});
