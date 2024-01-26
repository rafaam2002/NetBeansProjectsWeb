<%-- 
    Document   : cantidadBizum
    Created on : 13-dic-2023, 13:34:32
    Author     : rafaa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
        <style>
            .no-spinners::-webkit-inner-spin-button {
                -webkit-appearance: none;
                margin: 0;
            }
        </style>


        <title>Bizum</title>
    </head>
    <body class = "bg-zinc-800">
        <header>
            <% String userName = (String) request.getAttribute("nickUsuario");
                String numCuenta = (String) request.getAttribute("numCuenta");
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
                                    class="inline-flex items-center border-b-2 border-transparent px-1 pt-1 text-sm font-medium text-zinc-300 hover:border-selective-yellow-200 hover:text-selective-yellow-200"
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
                                    class="inline-flex items-center border-b-2 border-selective-yellow-500 px-1 pt-1 text-sm font-medium text-selective-yellow-500"
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
                            <!-- <button type="button" class="relative rounded-full bg-zinc-800 p-1 text-gray-400 hover:text-gray-500 focus:outline-none focus:ring-2 focus:ring-brotext-brown-200 focus:ring-offset-2">
                            <span class="absolute -inset-1.5"></span>
                            <span class="sr-only">View notifications</span>
                            <svg class="h-6 w-6" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" aria-hidden="true">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M14.857 17.082a23.848 23.848 0 005.454-1.31A8.967 8.967 0 0118 9.75v-.7V9A6 6 0 006 9v.75a8.967 8.967 0 01-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 01-5.714 0m5.714 0a3 3 0 11-5.714 0" />
                            </svg>
                            </button> -->

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
                                        href="#"
                                        class="text-red-600 transition duration-300 ease-out block px-4 py-2 text-sm hover:bg-zinc-700"
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
                            class="block border-l-4 border-transparent py-2 pl-3 pr-4 text-base font-medium text-zinc-200 hover:border-zinc-400 hover:bg-zinc-600 hover:text-zinc-400"
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
                            class="block border-l-4 border-selective-yellow-500 py-2 pl-3 pr-4 text-base font-medium text-selective-yellow-500"
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
            <!-- Barra de progreso -->
            <nav aria-label="Progress" class="mt-3 w-12/13 mx-auto md:max-w-6xl">
                <ol
                    role="list"
                    class="max-w-7xl mx-auto divide-y divide-zinc-700 rounded-md border-2 border-zinc-600 md:flex md:divide-y-0"
                    >
                    <li class="relative md:flex md:flex-1">
                        <!-- Paso Anterior -->
                        <a
                            href="/Achilles/ControladorPrincipal/hacerBizum"
                            class="flex items-center px-6 py-4 text-sm font-medium"
                            aria-current="step"
                            >
                            <span
                                class="flex h-10 w-10 flex-shrink-0 items-center justify-center rounded-full border-2 border-gray-300 group-hover:border-gray-400"
                                >
                                <span class="text-zinc-300 group-hover:text-zinc-500">01</span>
                            </span>
                            <span class="ml-4 text-sm font-medium text-zinc-300 group-hover:text-zinc-500"
                                  >Contacto</span
                            >
                        </a>
                        <!-- Arrow separator for lg screens and up -->
                        <div
                            class="absolute right-0 top-0 hidden h-full w-5 md:block"
                            aria-hidden="true"
                            >
                            <svg
                                class="h-full w-full text-zinc-600"
                                viewBox="0 0 22 80"
                                fill="none"
                                preserveAspectRatio="none"
                                >
                            <path
                                d="M0 -2L20 40L0 82"
                                vector-effect="non-scaling-stroke"
                                stroke="currentcolor"
                                stroke-linejoin="round"
                                />
                            </svg>
                        </div>
                    </li>
                    <li class="relative md:flex md:flex-1">
                        <!-- Paso actual -->
                        <span class="flex items-center px-6 py-4 text-sm font-medium">
                            <span
                                class="flex h-10 w-10 flex-shrink-0 items-center justify-center rounded-full border-2 border-selective-yellow-500"
                                >
                                <span class="text-selective-yellow-500"
                                      >02</span
                                >
                            </span>
                            <span
                                class="ml-4 text-sm font-medium text-selective-yellow-500"
                                >Cantidad</span
                            >
                        </span>
                    </li>
                </ol>
            </nav>
        </header>

        <main>
            <div class="flex min-h-full flex-col justify-center px-6 py-12 lg:px-8">
                <div class="sm:mx-auto sm:w-full sm:max-w-sm">
                    <h2
                        class="mt-10 text-center text-2xl font-bold leading-9 tracking-tight text-zinc-200"
                        >
                        ¿Cuánto dinero quieres enviar?
                    </h2>
                </div>

                <div class="mt-10 sm:mx-auto sm:w-full sm:max-w-sm">
                    <form class="space-y-6" action="/Achilles/ControladorPrincipal/guardarBizum" method="POST">
                        <div class="w-full flex justify-center">
                            <div
                                class="relative text-selective-yellow-500 focus-within:text-zinc-300"
                                >
                                <input
                                    type="number"
                                    name="cantidad"
                                    step="1"
                                    id="number_input"
                                    class="no-spinners pl-1 bg-zinc-700 text-3xl h-14 rounded-lg border-0 ring-1 ring-zinc-600 focus:ring-selective-yellow-500"
                                    style="width: 64px"
                                    />
                                <span
                                    class="absolute text-3xl"
                                    id="dolar_symbol"
                                    style="left: 35px; top: 14%"
                                    >
                                    $
                                </span>
                            </div>
                        </div>

                        <!-- Check Enviar/Recivir -->
                        <!--                        <div class=" z-0 w-full mt-10 group">
                                                    <label
                                                        class="relative inline-flex items-center mb-4 cursor-pointer"
                                                        >
                                                        <input
                                                            type="checkbox"
                                                            value="active"
                                                            class="sr-only peer"
                                                            name="bizum"
                                                            checked
                                                            />
                                                        <div
                                                            class="w-11 h-6 bg-gray-200 rounded-full peer peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all peer-checked:bg-selective-yellow-500"
                                                            ></div>
                                                        <span class="ml-3 text-sm font-medium text-zinc-400"
                                                              >Recibir/Enviar</span
                                                        >
                                                    </label>
                                                </div>-->

                        <div>
                            <div class="flex items-center justify-between -mt-4">
                                <label
                                    for="concepto"
                                    class="block text-sm font-medium leading-6 text-selective-yellow-500"
                                    >Concepto</label
                                >
                            </div>
                            <div class="mt-2">
                                <input
                                    id="concepto"
                                    name="concepto"
                                    type="text"
                                    placeholder="Cena Familiar"
                                    required
                                    class="block w-full pl-2 font-normal rounded-md border-0 py-1.5 bg-zinc-700 text-zinc-300 ring-1 ring-zinc-600 placeholder:text-zinc-400 focus:ring-selective-yellow-500 sm:text-sm"
                                    />
                            </div>
                        </div>
                        <div>
                            <button
                                type="submit"
                                class="flex w-full justify-center rounded-md bg-selective-yellow-600 px-3 py-1.5 text-sm font-semibold leading-6 text-zinc-200 shadow-sm hover:bg-selective-yellow-500 hover:text-zinc-950"
                                >
                                Enviar
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </main>
        <!-- Dropdown menu -->
        <script src="/Achilles/scripts/dropdown_menu.js"></script>
        <script src="/Achilles/scripts/dropdown_menu_movil.js"></script>
        <script src="/Achilles/scripts/bizumAContacto.js"></script>

        <script src="/Achilles/scripts/bizumForm.js"></script>

    </body>
</html>
