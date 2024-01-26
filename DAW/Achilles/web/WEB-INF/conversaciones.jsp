<%-- 
    Document   : conversaciones.jsp
    Created on : 27-dic-2023, 11:37:29
    Author     : rafaa
--%>

<%@page import="Modelo.MensajeEntity"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="bg-zinc-800 h-screen overflow-hidden">
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
            /* Para el scrollbar en general */
            ::-webkit-scrollbar {
                width: 8px;
            }

            ::-webkit-scrollbar-track {
                border-radius: 8px;
            }

            ::-webkit-scrollbar-thumb {
                background: #888;
                border-radius: 8px
            }

            ::-webkit-scrollbar-thumb:hover {
                background: #555;
            }

            .p0s8B {
                position: absolute;
                z-index: 100;
                display: block;
                width: 8px;
                height: 13px;
            }
        </style>

        <title>Conversaciones</title>
    </head>
    <body class ="h-full">
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
                                    class="inline-flex items-center border-b-2 border-transparent px-1 pt-1 text-sm font-medium text-zinc-300 hover:border-selective-yellow-200 hover:text-selective-yellow-200"
                                    >Bizum</a
                                >
                                <a
                                    href="/Achilles/ControladorPrincipal/getContactos"
                                    class="inline-flex items-center border-b-2 border-selective-yellow-500 px-1 pt-1 text-sm font-medium text-selective-yellow-500"
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
                                        ><%=numCuenta%></div
                                    >
                                     <a
                                        href="/Achilles/ControladorLogin/logout"
                                        class="transition duration-300 ease-out block px-4 py-2 text-sm text-red-600hover:bg-zinc-700"
                                        role="menuitem"
                                        tabindex="-1"
                                        id="user-menu-item-0"
                                        >Salir</a
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
                            class="block border-l-4 border-transparent py-2 pl-3 pr-4 text-base font-medium text-zinc-200 hover:border-zinc-400 hover:bg-zinc-600 hover:text-zinc-400"
                            >Bizum</a
                        >
                        <a
                            href="/Achilles/ControladorPrincipal/conversaciones"
                            class="block border-l-4 border-selective-yellow-500 py-2 pl-3 pr-4 text-base font-medium text-selective-yellow-500"
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




        <main class="flex flex-row max-w-7xl mx-5 mt-3 p-4 md:p-2 h-5/6 overflow-hidden">
            <!-- contactos -->
            <div class="md:w-1/4 w-full md:col-span-3 items-center text-zinc-200 bg-zinc-700 text-xs xl:text-sm 2xl:text-base rounded-md shadow-md shadow-black p-5 overflow-auto">
                <%
                    List<Usuario> contactos = (List<Usuario>) request.getAttribute("contactos");

                    if (!contactos.isEmpty()) {
                        for (Usuario contacto : contactos) {
                            out.println("<div class='cursor-pointer pointer flex space-x-2 py-3 article_chat hover:bg-zinc-600 transition ease-in border-b border-zinc-500' onclick = 'getConversacion(event)'  id = '" + contacto.getNick() + "'>");
                            out.println("    <div style='pointer-events:none'>");
                            out.println("        <img class='h-11 w-11 rounded-full' src='/Achilles/images/usuario.png' alt='' />");
                            out.println("    </div>");
                            out.println("    <div style='pointer-events:none'>");
                            out.println("        <div class='font-medium'> " + contacto.getNick() + "</div>");
                            out.println("    </div>");
                            out.println("</div>");
                        }

                    }
                %>             
            </div>
            <div class="w-full hidden md:flex bg-zinc-700 ml-1 rounded-md shadow-md shadow-black flex-col justify-between "
                 >
                <!-- campo para los mensajes -->
                <div id="div_chat" class="pt-2 px-8 flex flex-col gap-1.5 !overflow-auto">
                         <img
                                    class="h-56 w-56 mx-auto mb-10 mt-12"
                                    src="/Achilles/images/elija_conversacion.png"
                                    alt="Elija conversacion image"
                                    />
                         <p class="mx-auto text-xl text-selective-yellow-500">Elija una conversación</p>
                </div>
                   
               
                <!--Campo de texto para chat-->
                <div class="">
                    <form>
                       
                        <div
                            class="flex items-center px-3 py-2 rounded-lg bg-zinc-700 w-full"
                            >
                            <textarea
                                id="textarea_text"
                                rows="1"
                                class="block mx-4 p-2.5 w-full text-sm resize-none rounded-lg border focus:ring-selective-yellow-500 focus:border-selective-yellow-500 bg-zinc-800 border-gray-600 placeholder-gray-400 text-white"
                                placeholder="Your message..."
                                ></textarea>
                            <button
                                type="button"
                                id="button_enviar"
                                class="inline-flex justify-center p-2 text-blue-600 rounded-full cursor-pointer hover:bg-blue-100 dark:text-blue-500 dark:hover:bg-gray-600"
                                >
                                <svg
                                    class="w-5 h-5 rotate-90 rtl:-rotate-90 text-selective-yellow-500"
                                    aria-hidden="true"
                                    xmlns="http://www.w3.org/2000/svg"
                                    fill="currentColor"
                                    viewBox="0 0 18 20"
                                    >
                                <path
                                    d="m17.914 18.594-8-18a1 1 0 0 0-1.828 0l-8 18a1 1 0 0 0 1.157 1.376L8 18.281V9a1 1 0 0 1 2 0v9.281l6.758 1.689a1 1 0 0 0 1.156-1.376Z"
                                    />
                                </svg>
                                <span class="sr-only">Send message</span>
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </main>










        <!-- Dropdown menu --> 
        <script src="/Achilles/scripts/dropdown_menu.js"></script>
        <script src="/Achilles/scripts/dropdown_menu_movil.js"></script>
        <!--getConversacion-->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.30.1/moment.min.js"></script>
        <script src="/Achilles/scripts/get_conversacion.js"></script>
        <!--socket-->
        <script src = "/Achilles/scripts/codigo_socket.js"></script>
    </body>

</html>
