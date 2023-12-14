const dolarSymbol = document.querySelector("#dolar_symbol");
const numberInput = document.querySelector("#number_input");
let posXdolarSymbol = 19;
let nElementosAnterior = 0;
let anchuraInput = 64;
let puntoComa = false;
let checkBorrado = -1;
let inputValue = "";
let delPuntoComa = false;
let PrimerPuntoComa = true;

dolarSymbol.addEventListener("click", () => {
  numberInput.focus();
});

numberInput.addEventListener("input", (e) => {
  addEspacio(e);
});

numberInput.addEventListener("keydown", (e) => {
  if ((e.key === "," || e.key === ".")) {
    addEspPuntoOComa();
  }
  else if(e.key === "."){}

  else if (e.key === "Backspace") {
    if (inputValue.length === checkBorrado) {
      checkBorrado = -2;
      delEspPuntoOComa();
    }
  }
  // if (e.key === "," || e.key === ".") {
  //   puntoComa = true;
  //   console.log(".,");
  //   addEspacio2(e);
  // }
});


const addEspacio = (e) => {
  inputValue = e.target.value;
  console.log(inputValue);
  if(inputValue.length === 0)PrimerPuntoComa = true;
  if (!puntoComa && !delPuntoComa) {
    posXdolarSymbol += (inputValue.length - nElementosAnterior) * 16;
    dolarSymbol.style.left = `${posXdolarSymbol}px`;
    numberInput.style.width = `${posXdolarSymbol + 29}px`;
    nElementosAnterior = inputValue.length;
  } else if (puntoComa) {
    puntoComa = false;
    if (PrimerPuntoComa) {
      PrimerPuntoComa = false;
      checkBorrado = inputValue.length + 2;
      posXdolarSymbol += 8;
      dolarSymbol.style.left = `${posXdolarSymbol}px`;
      numberInput.style.width = `${posXdolarSymbol + 29}px`;
      nElementosAnterior++;
    } else {
      addEspacio(e);
    }
  } else if (delPuntoComa) {
    delPuntoComa = false;
    posXdolarSymbol -= 8;
    dolarSymbol.style.left = `${posXdolarSymbol}px`;
    numberInput.style.width = `${posXdolarSymbol + 29}px`;
    nElementosAnterior--;
  }
};

const addEspPuntoOComa = () => {
  puntoComa = true;
};
const delEspPuntoOComa = () => {
  console.log("fun");
  delPuntoComa = true;
};

