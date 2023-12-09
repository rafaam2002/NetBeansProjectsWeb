document.addEventListener("DOMContentLoaded", function() {
    const dropdownMenu = document.querySelector('#dropdown-menu');
    const dropdownButton = document.querySelector('#user-menu-button');

    document.addEventListener('click',(e) => {
      if((e.target === dropdownButton 
        || dropdownButton.contains(e.target))
        && dropdownMenu.classList.contains('opacity-0'))
      {
        dropdownMenu.classList.remove("ease-in", "duration-95", "opacity-0", "scale-95","-z-10");
        dropdownMenu.classList.add("ease-out", "duration-200", "opacity-100", "scale-100","z-10");
      }
      else if(e.target !== dropdownMenu
        && !dropdownMenu.contains(e.target)
        && dropdownMenu.classList.contains("opacity-100"))
      {
        dropdownMenu.classList.remove("ease-out", "duration-200", "opacity-100", "scale-100", "z-10");
        dropdownMenu.classList.add("ease-in", "duration-95", "opacity-0", "scale-95","-z-10");
      }
    });
    
})

