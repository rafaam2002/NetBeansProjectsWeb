<%-- 
    Document   : transferencia
    Created on : 13-dic-2023, 20:36:13
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

        <title>Transferencia</title>
    </head>
    <body class = "bg-zinc-800">
        <header>
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
                                    class="inline-flex items-center border-b-2 border-selective-yellow-500 px-1 pt-1 text-sm font-medium text-selective-yellow-500"
                                    >Transferencia</a
                                >
                                <a
                                    href="/Achilles/ControladorPrincipal/hacerBizum"
                                    class="inline-flex items-center border-b-2 border-transparent px-1 pt-1 text-sm font-medium text-zinc-300 hover:border-selective-yellow-200 hover:text-selective-yellow-200"

                                    >Bizum</a
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
                                            src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
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
                                    <a
                                        href="#"
                                        class="transition duration-300 ease-out block px-4 py-2 text-sm text-zinc-400 hover:bg-zinc-700"
                                        role="menuitem"
                                        tabindex="-1"
                                        id="user-menu-item-0"
                                        >Tu Perfil</a
                                    >
                                    <a
                                        href="/Achilles/ControladorPrincipal/cargarChats"
                                        class="transition duration-300 ease-out block px-4 py-2 text-sm text-zinc-400 hover:bg-zinc-700"
                                        role="menuitem"
                                        tabindex="-1"
                                        id="user-menu-item-1"
                                        >Chats</a
                                    >
                                    <a
                                        href="/ControladorLogin/logout"
                                        class="transition duration-300 ease-out block px-4 py-2 text-sm text-zinc-400 hover:bg-zinc-700"
                                        role="menuitem"
                                        tabindex="-1"
                                        id="user-menu-item-2"
                                        >Log out</a
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
                            href="general.html"
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
                    </div>
                    <div class="border-t border-gray-200 pb-3 pt-4 text-zinc-300">
                        <div class="flex items-center px-4">
                            <div class="flex-shrink-0">
                                <img
                                    class="h-10 w-10 rounded-full"
                                    src="https://images.unsplash.com/photo-1472099645785-5658abf4ff4e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                                    alt=""
                                    />
                            </div>
                            <div class="ml-3">
                                <% String userName = (String) request.getAttribute("nickUsuario");
                                    System.out.println(userName);
                                %>
                                <div class="text-sm font-medium"><%=userName%></div>
                            </div>
                            <button
                                type="button"
                                class="relative ml-auto flex-shrink-0 rounded-full bg-zinc-800 p-1 text-gray-200 hover:text-selective-yellow-500 focus:outline-none focus:ring-2 focus:ring-brotext-brown-200 focus:ring-offset-2"
                                >
                                <span class="absolute -inset-1.5"></span>
                                <span class="sr-only">View notifications</span>
                                <svg
                                    class="h-6 w-6"
                                    fill="none"
                                    viewBox="0 0 24 24"
                                    stroke-width="1.5"
                                    stroke="currentColor"
                                    aria-hidden="true"
                                    >
                                <path
                                    stroke-linecap="round"
                                    stroke-linejoin="round"
                                    d="M14.857 17.082a23.848 23.848 0 005.454-1.31A8.967 8.967 0 0118 9.75v-.7V9A6 6 0 006 9v.75a8.967 8.967 0 01-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 01-5.714 0m5.714 0a3 3 0 11-5.714 0"
                                    />
                                </svg>
                            </button>
                        </div>
                        <div class="mt-3 space-y-1">
                            <a
                                href="#"
                                class="block px-4 py-2 text-base font-medium hover:bg-zinc-600 hover:text-selective-yellow-500"
                                >Tu perfil</a
                            >
                            <a
                                href="/Achilles/ControladorPrincipal/cargarChats"
                                class="block px-4 py-2 text-base font-medium hover:bg-zinc-600 hover:text-selective-yellow-500"
                                >Chats</a
                            >
                            <a
                                href="/ControladorLogin/logout"
                                class="block px-4 py-2 text-base font-medium hover:bg-zinc-600 hover:text-selective-yellow-500"
                                >Log out</a
                            >
                        </div>
                    </div>
                </div>
            </nav>
        </header>
        <main>
            <form action="/Achilles/ControladorTransferencia/hacerTransferencia" method="POST" class="mx-auto max-w-5xl bg-zinc-700 mt-5 rounded-md">
                <div
                    class="h-20 bg-zinc-600 rounded-t-md flex justify-center items-center text-selective-yellow-500 font-bold"
                    >
                    <h1>Datos de la Transferencia</h1>
                </div>
                <div class="border-b border-gray-900/10 pb-12 px-5 text-zinc-200">
                    <div class="mt-10 grid grid-cols-1 gap-x-6 gap-y-8 sm:grid-cols-6">
                        <div class="sm:col-span-3">
                            <label
                                for="first-name"
                                class="block text-sm font-medium leading-6"
                                >Nombre del destinatario</label
                            >
                            <div class="mt-2">
                                <input
                                    type="text"
                                    name="first-name"
                                    id="first-name"
                                    autocomplete="given-name"
                                    class="bg-zinc-400 block w-full rounded-md border-0 py-1.5 text-black font-normal ring-1 ring-inset ring-zinc-300 placeholder:text-zinc-600 focus:ring-2 focus:ring-inset focus:ring-selective-yellow-600 sm:text-sm sm:leading-6"
                                    />
                            </div>
                        </div>

                        <div class="sm:col-span-3">
                            <label for="last-name" class="block text-sm font-medium leading-6"
                                   >Apellido del destinatario</label
                            >
                            <div class="mt-2">
                                <input
                                    type="text"
                                    name="last-name"
                                    id="last-name"
                                    autocomplete="family-name"
                                    class="bg-zinc-400 block w-full rounded-md border-0 py-1.5 text-black ring-1 ring-inset ring-zinc-300 placeholder:text-gray-600 focus:ring-2 focus:ring-inset focus:ring-selective-yellow-500 sm:text-sm sm:leading-6"
                                    />
                            </div>
                        </div>

                        <div class="sm:col-span-4">
                            <label for="iban" class="block text-sm font-medium leading-6"
                                   >IBAN</label
                            >
                            <div class="mt-2">
                                <input
                                    id="iban"
                                    name="iban"
                                    type="text"
                                    autocomplete="on"
                                    class="bg-zinc-400 block w-full rounded-md border-0 py-1.5 text-black ring-1 ring-inset ring-zinc-300 placeholder:text-gray-600 focus:ring-2 focus:ring-inset focus:ring-selective-yellow-500 sm:text-sm sm:leading-6"
                                    />
                            </div>
                        </div>

                        <div class="sm:col-span-full">
                            <label for="importe" class="block text-sm font-medium leading-6"
                                   >Importe €</label
                            >
                            <div class="mt-2 w-1/3">
                                <input
                                    type="text"
                                    name="importe"
                                    id="importe"
                                    autocomplete="cc-number"
                                    class="bg-zinc-400 block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-zinc-300 placeholder:text-gray-600 focus:ring-2 focus:ring-inset focus:ring-selective-yellow-500 sm:text-sm sm:leading-6"
                                    />
                            </div>
                        </div>

                        <div class="col-span-full">
                            <label for="concepto" class="block text-sm font-medium leading-6"
                                   >Concepto</label
                            >
                            <div class="mt-2">
                                <textarea
                                    id="concepto"
                                    name="concepto"
                                    rows="3"
                                    class="bg-zinc-400 block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-gray-300 placeholder:text-gray-600 focus:ring-2 focus:ring-inset focus:ring-selective-yellow-500 sm:text-sm sm:leading-6"
                                    ></textarea>
                            </div>
                        </div>

                        <div class="sm:col-span-full">
                            <label for="fecha" class="block text-sm font-medium leading-6"
                                   >Fecha</label
                            >
                            <div class="mt-2 w-1/3">
                                <input
                                    type="date"
                                    id="fecha"
                                    name="fecha"
                                    class="bg-zinc-400 block w-full rounded-md border-0 py-1.5 text-black shadow-sm ring-1 ring-inset ring-zinc-300 placeholder:text-gray-600 focus:ring-2 focus:ring-inset focus:ring-selective-yellow-500 sm:text-sm sm:leading-6"
                                    />
                            </div>
                        </div>
                    </div>
                    <div class="mt-6 flex items-center justify-end gap-x-6">
                        <button
                            type="button"
                            class="text-sm font-semibold leading-6 text-white"
                            >
                            Cancel
                        </button>
                        <button
                            type="submit"
                            class="rounded-md bg-selective-yellow-500 px-3 py-2 text-sm font-semibold text-zinc-900 shadow-sm hover:bg-selective-yellow-300 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-selective-yellow-500 transform scale-100 hover:scale-110 transition-transform ease-in"
                            >
                            Transferir
                        </button>
                    </div>
                </div>

            </form>
        </main>

        <!-- Dropdown menu --> 
        <script src="/Achilles/scripts/dropdown_menu.js"></script>
        <script src="/Achilles/scripts/dropdown_menu_movil.js"></script>                               
    </body>
</html>
