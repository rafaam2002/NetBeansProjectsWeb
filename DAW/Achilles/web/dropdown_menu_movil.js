const iconMenuClosed = document.querySelector("#icon-menu-closed");
const iconMenuOpen = document.querySelector("#icon-menu-open");
const buttonMenu = document.querySelector("#mobile-menu-buttom");
const menu = document.querySelector("#mobile-menu");
const div = document.querySelector("#prueba-div");

buttonMenu.addEventListener("click", () => {
  hiddenBlock(iconMenuClosed.classList);
  hiddenBlock(iconMenuOpen.classList);
  hiddenBlock(menu.classList);
});

// Funcion: Cambia de visible a bloque el elemento
const hiddenBlock = (classList) => {
  if (classList.contains("hidden")) {
    classList.remove("hidden");
    classList.add("block");
  } else {
    classList.remove("block");
    classList.add("hidden");
  }
};

