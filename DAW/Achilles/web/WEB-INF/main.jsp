<%-- 
    Document   : main.jsp
    Created on : 09-dic-2023, 15:58:40
    Author     : rafaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html class="bg-zinc-800">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            rel="shortcut icon"
            href="/Achilles/images/espadafavicon.ico"
            type="image/x-icon"
            />
        <!--<link href="/dist/output.css" rel="stylesheet" />-->
        <link rel="stylesheet" href="https://rsms.me/inter/inter.css" />
        <script
            src="https://kit.fontawesome.com/c30631cae3.js"
            crossorigin="anonymous"
        ></script>
        <script src="https://cdn.tailwindcss.com"></script>
        <script>
            tailwind.config = {
                theme: {
                    extend: {
                        width: {
                            custom: "95.4%",
                            custommd: "93%"
                        },
                        colors: {
                            brown: {
                                DEFAULT: "#8F6600",
                                50: "#FFD15E",
                                100: "#FFCB47",
                                200: "#FFB700",
                                300: "#E0A100",
                                400: "#BD8700",
                                500: "#8F6600",
                                600: "#705000",
                                700: "#A37500",
                                800: "#6B4D00",
                                900: "#140F00",
                                950: "#050400"
                            },
                            "selective-yellow": {
                                DEFAULT: "#FFB700",
                                50: "#FFEBB8",
                                100: "#FFDE8A",
                                200: "#FFDA7A",
                                300: "#FFCE52",
                                400: "#FFC329",
                                500: "#FFB700",
                                600: "#C78F00",
                                700: "#8F6600",
                                800: "#573E00",
                                900: "#1F1600",
                                950: "#030200"
                            }
                        },
                        spacing: {
                            "12/13": "98%"
                        }
                    }
                }
            };
        </script>

        <title>General</title>
    </head>
    <body>
        <header>
            <% String numCuenta = (String) request.getAttribute("numCuenta");
                String userName = (String) request.getAttribute("nickUsuario");
            %>
            <nav class="bg-zinc-800 shadow-md shadow-zinc-900">
                <div class="mx-auto px-4 sm:px-6 lg:px-8">
                    <div class="flex h-16 justify-between">
                        <div class="flex">
                            <div class="flex flex-shrink-0 items-center">
                                <!-- <img class="h-8 w-auto" src="https://tailwindui.com/img/logos/mark.svg?color=indigo&shade=600" alt="Your Company"> -->
                                <img
                                    class="h-8 w-auto"
                                    src="/Achilles/espada.svg"
                                    alt="Logo espada"
                                    />
                            </div>
                            <div class="hidden sm:ml-6 sm:flex sm:space-x-8">
                                <!-- Current: "border-brotext-selecborder-selective-yellow-200 text-gray-900", Default: "border-transparent text-gray-500 hover:border-gray-300 hover:text-zinc-400" -->
                                <a
                                    href="/Achilles/ControladorPrincipal/main"
                                    class="inline-flex items-center border-b-2 border-selective-yellow-500 px-1 pt-1 text-sm font-medium text-selective-yellow-500"
                                    >General</a
                                >
                                <a
                                    href="/Achilles/ControladorPrincipal/getContactos"
                                    class="inline-flex items-center border-b-2 border-transparent px-1 pt-1 text-sm font-medium text-zinc-300 hover:border-selective-yellow-200 hover:text-selective-yellow-200"
                                    >Contactos</a
                                >
                                <a
                                    href="/Achilles/ControladorPrincipal/transferencia"
                                    class="inline-flex items-center border-b-2 border-transparent px-1 pt-1 text-sm font-medium text-zinc-300 hover:border-selective-yellow-200 hover:text-selective-yellow-200"
                                    >Transferencia</a
                                >
                                <a
                                    href="/Achilles/ControladorPrincipal/hacerBizum"
                                    class="inline-flex items-center border-b-2 border-transparent px-1 pt-1 text-sm font-medium text-zinc-300 hover:border-selective-yellow-200 hover:text-selective-yellow-200"
                                    >Bizum</a
                                >
                                <a
                                    href="/Achilles/ControladorPrincipal/conversaciones"
                                    class="inline-flex items-center border-b-2 border-transparent px-1 pt-1 text-sm font-medium text-zinc-300 hover:border-selective-yellow-200 hover:text-selective-yellow-200"
                                    >Conversaciones</a
                                >
                            </div>
                        </div>
                        <div class="hidden sm:ml-6 sm:flex sm:items-center">

                            <!-- Profile dropdown -->
                            <div class="relative ml-3">
                                <div>
                                    <button
                                        type="button"
                                        class="grou relative flex rounded-full bg-zinc-800 text-sm focus:outline-none focus:ring-2 ring-brown-400 focus:ring-brotext-brown-200 focus:ring-offset-2"
                                        id="user-menu-button"
                                        aria-expanded="false"
                                        aria-haspopup="true"
                                        >
                                        <span class="absolute -inset-1.5"></span>
                                        <span class="sr-only">Open user menu</span>
                                        <img
                                            class="h-8 w-8 rounded-full"
                                            src="/Achilles/images/usuario.png"
                                            alt=""
                                            />
                                    </button>
                                </div>

                                <!--
                                Dropdown menu, show/hide based on menu state.
                    
                                Entering: "transition ease-out duration-200"
                                  From: "transform opacity-0 scale-95"
                                  To: "transform opacity-100 scale-100"
                                Leaving: "transition ease-in duration-75"
                                  From: "transform opacity-100 scale-100"
                                  To: "transform opacity-0 scale-95"
                                -->
                                <div
                                    id="dropdown-menu"
                                    class="transition transform opacity-0 scale-95 absolute right-0 -z-10 mt-2 w-48 origin-top-right rounded-md bg-zinc-800 py-1 shadow-md shadow-zinc-950 ring-1 ring-zinc-900 ring-opacity-60 focus:outline-none"
                                    role="menu"
                                    aria-orientation="vertical"
                                    aria-labelledby="user-menu-button"
                                    tabindex="-1"
                                    >
                                    <!-- Active: "bg-zinc-700", Not Active: "" -->
                                    <div
                                        class="transition duration-300 ease-out block px-4 py-2 text-sm text-zinc-400 hover:bg-zinc-700"
                                        role="menuitem"
                                        tabindex="-1"
                                        id="user-menu-item-0"
                                        ><%=userName%></div
                                    >
                                    <div
                                        class="transition duration-300 ease-out block px-4 py-2 text-sm text-zinc-400 hover:bg-zinc-700"
                                        role="menuitem"
                                        tabindex="-1"
                                        id="user-menu-item-0"
                                        >IBAN: <%=numCuenta%></div
                                    >
                                    <a
                                        href="/Achilles/ControladorLogin/logout"
                                        class="transition duration-300 ease-out block px-4 py-2 text-sm text-red-600 hover:bg-zinc-700"
                                        role="menuitem"
                                        tabindex="-1"
                                        id="user-menu-item-0"
                                        >Salir</a
                                    >

                                </div>
                            </div>
                        </div>
                        <div class="-mr-2 flex items-center sm:hidden">
                            <!-- Mobile menu button -->
                            <button
                                id="mobile-menu-buttom"
                                type="button"
                                class="relative inline-flex items-center justify-center rounded-md p-2 text-zinc-200 hover:bg-zinc-600 hover:text-selective-yellow-500 focus:outline-none focus:ring-2 focus:ring-inset focus:ring-brotext-brown-200"
                                aria-controls="mobile-menu"
                                aria-expanded="false"
                                >
                                <span class="absolute -inset-0.5"></span>
                                <span class="sr-only">Abrir menu principal</span>
                                <!--
                                Icon when menu is closed.
                    
                                Menu open: "hidden", Menu closed: "block"
                                -->
                                <svg
                                    id="icon-menu-closed"
                                    class="block h-6 w-6"
                                    fill="none"
                                    viewBox="0 0 24 24"
                                    stroke-width="1.5"
                                    stroke="currentColor"
                                    aria-hidden="true"
                                    >
                                <path
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                    d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5"
                                    />
                                </svg>
                                <!--
                                Icon when menu is open.
                    
                                Menu open: "block", Menu closed: "hidden"
                                -->
                                <svg
                                    id="icon-menu-open"
                                    class="hidden h-6 w-6"
                                    fill="none"
                                    viewBox="0 0 24 24"
                                    stroke-width="1.5"
                                    stroke="currentColor"
                                    aria-hidden="true"
                                    >
                                <path
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                    d="M6 18L18 6M6 6l12 12"
                                    />
                                </svg>
                            </button>
                        </div>
                    </div>
                </div>

                <!-- Mobile menu, show/hide based on menu state. -->
                <div class="hidden" id="mobile-menu">
                    <div class="space-y-1 pb-3 pt-2">
                        <!-- Current: "bg-indigo-50 border-brotext-brown-200 text-indigo-700", Default: "border-transparent text-gray-500 hover:bg-gray-50 hover:border-gray-300 hover:text-zinc-400" -->
                        <a
                            href="/Achilles/ControladorPrincipal/main"
                            class="block border-l-4 border-selective-yellow-500 py-2 pl-3 pr-4 text-base font-medium text-selective-yellow-500"
                            >General</a
                        >
                        <a
                            href="/Achilles/ControladorPrincipal/getContactos"
                            class="block border-l-4 border-transparent py-2 pl-3 pr-4 text-base font-medium text-zinc-200 hover:border-zinc-400 hover:bg-zinc-600 hover:text-zinc-400"
                            >Contactos</a
                        >
                        <a
                            href="/Achilles/ControladorPrincipal/transferencia"
                            class="block border-l-4 border-transparent py-2 pl-3 pr-4 text-base font-medium text-zinc-200 hover:border-zinc-400 hover:bg-zinc-600 hover:text-zinc-400"
                            >Transferencia</a
                        >
                        <a
                            href="/Achilles/ControladorPrincipal/hacerBizum"
                            class="block border-l-4 border-transparent py-2 pl-3 pr-4 text-base font-medium text-zinc-200 hover:border-zinc-400 hover:bg-zinc-600 hover:text-zinc-400"
                            >Bizum</a
                        >
                        <a
                            href="/Achilles/ControladorPrincipal/conversaciones"
                            class="block border-l-4 border-transparent py-2 pl-3 pr-4 text-base font-medium text-zinc-200 hover:border-zinc-400 hover:bg-zinc-600 hover:text-zinc-400"
                            >Conversaciones</a
                        >
                    </div>
                    <div class="border-t border-gray-200 pb-3 pt-4 text-zinc-300">
                       
                            <div class = "flex justify-between" >
                                <div class="flex items-center">
                                    <div class="flex-shrink-0">
                                        <img
                                            class="h-10 w-10 rounded-full"
                                            src="/Achilles/images/usuario.png"
                                            alt=""
                                            />
                                    </div>

                                    <div id ="div_nick" class="ml-3 text-sm font-medium"><%=userName%></div>


                                    <div  class="ml-3 text-sm font-medium">IBAN: <%=numCuenta%></div>
                                </div>
                                
                                    <a class="mr-4 pt-2.5 text-sm text-red-600 font-medium" href="/Achilles/ControladorLogin/logout">Salir</a>         
                            </div>    
                    </div>
                </div>
            </nav>
        </header>

        <main class="grid lg:grid-cols-4 lg:grid-rows-3 lg:gap-5 sm:grid-cols-3 gap-4 sm:mx-8 mx-3 my-5">
            <!-- eChart grafica -->
            <article class="lg:col-span-full lg:row-span-2 sm:col-span-3 w-full sm:h-96 h-72  bg-zinc-700 rounded-md shadow-md shadow-black sm:pb-8"
                     id="grafica"
                     >
            </article>



            <!-- Capital -->
            <article
                class="lg:col-span-2 lg:row-span-1 sm:col-span-1 text-zinc-200 w-full h-full bg-zinc-700 rounded-md shadow-md shadow-black pt-5 pl-5"
                >
                <div class="flex justify-between">
                    <div class="flex flex-col justify-between pb-4">
                        <h1 class="text-selective-yellow-500 text-lg">Capital</h1>
                        <div class="text-2xl text-zinc-50" id = "capital_general"></div>
                        <div class="text-sm">Última semana</div>
                        <div class="text-sm" id = "porcentaje_semanal"></div>
                    </div>
                    <div class="">
                        <img
                            src="/Achilles/imagenmoneda.png"
                            width="140"

                            class=""
                            alt="monedas"
                            />
                    </div>
                </div>
            </article>

            <!-- Gastos e Ingresos -->
            <article
                class="lg:col-span-2 lg:row-span-1 sm:col-span-2 flex justify-evenly text-zinc-200 w-full h-full bg-zinc-700 rounded-md shadow-md shadow-black pr-5"
                >
                <div id="grafica_pie" class="md:w-1/2 w-1/3 h-full bg-zinc-700 md:pt-4"></div>
                <div class="flex flex-col justify-around py-5 text-zinc-300">
                    <h1 class="text-lg text-zinc-50">Resumen del Balance</h1>
                    <div class="flex">
                        <div class="w-6 h-6 bg-zinc-500 rounded-full mr-4"></div>
                        <h2 id="leyenda_capital">Capital: 1000€</h2>
                    </div>
                    <div class="flex">
                        <div class="w-6 h-6 bg-green-300 rounded-full mr-4"></div>
                        <h2 id="leyenda_ingresos">Ingresos: 100€</h2>
                    </div>
                    <div class="flex">
                        <div class="w-6 h-6 bg-red-400 rounded-full mr-4"></div>
                        <h2 id="leyenda_gastos">Gastos: 50€</h2>
                    </div>
                </div>
            </article>
            <!-- prueba chat -->
            <!--            <article>
                            <label>Nombre:</label>
                            <input type="text" name="name" id="input_usuario" />
            
                            <label>Mensaje</label>
                            <textarea id="textarea_text"></textarea>
            
                            <button id="button_enviar">Enviar</button>
            
                            <div id="div_conversacion"></div>
                        </article>-->
        </main>

        <!-- Apache Echarts -->
        <script src="https://cdn.jsdelivr.net/npm/echarts@5.4.3/dist/echarts.min.js"></script>
        <!-- Graficas -->
        <script src="/Achilles/scripts/graficas.js"></script>
        <!-- Dropdown menu --> 
        <script src="/Achilles/scripts/dropdown_menu.js"></script>
        <script src="/Achilles/scripts/dropdown_menu_movil.js"></script>
        
        <!--socket-->
        <!--        <script src="/Achilles/scripts/codigoSocket.js"></script>-->
    </body>
</html>
