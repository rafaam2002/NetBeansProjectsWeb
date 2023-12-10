const input = document.querySelector('#userSearch');
let i = 0;
let nameUser = new String("");


input.addEventListener("input",(e)=>{
    nameUser = e.target.value;
//    console.log(nameUser);
});