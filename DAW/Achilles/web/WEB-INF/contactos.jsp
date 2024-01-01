<%-- 
    Document   : contactos
    Created on : 10-dic-2023, 19:38:18
    Author     : rafaa
--%>

<%@page import="Modelo.Usuario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link
            rel="shortcut icon"
            href="/Achilles/images/espadafavicon.ico"
            type="image/x-icon"
            />
        <link rel="stylesheet" href="https://rsms.me/inter/inter.css" />
        <title>contactos</title>
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
    </head>
    <body class="bg-zinc-800">

        <header>
            <% String userName = (String) request.getAttribute("nickUsuario");
                System.out.println(userName);
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
                                    class="inline-flex items-center border-b-2 border-selective-yellow-500 px-1 pt-1 text-sm font-medium text-selective-yellow-500"
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
                                        ><%=userName%></a
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
                         <a
                            href="/Achilles/ControladorPrincipal/conversaciones"
                            class="block border-l-4 border-transparent py-2 pl-3 pr-4 text-base font-medium text-zinc-200 hover:border-zinc-400 hover:bg-zinc-600 hover:text-zinc-400"
                            >Conversaciones</a
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
                    </div>
                </div>
            </nav>
        </header>

        <main>
            <article class="mt-7">
                <div class="md:max-w-6xl max-w-7xl mx-auto bg-zinc-700 rounded-xl pb-4 shadow shadow-black">
                    <!-- Inputs de arriba -->
                    <div class="flex justify-between">
                        <!-- inputText -->
                        <div class="sm:flex sm:items-center pt-3 pl-4">
                            <label for="userSearch"></label>
                            <input
                                type="text"
                                name="userSearch"
                                id="userSearch"
                                placeholder="Search for user"
                                class="py-2 px-2 bg-zinc-800 rounded-md placeholder:text-zinc-400 text-zinc-200 focus:border-selective-yellow-500 focus:ring-selective-yellow-500"
                                />
                        </div>
                        <!-- Boton de aniadir Usuario -->
                        <div class="pt-10 pr-16 pb-6">
                            <button
                                type="button"
                                class="rounded-full bg-selective-yellow-500 p-2 text-white shadow-sm hover:bg-selective-yellow-300 focus-visible:outline focus-visible:outline-2 focus-visible:outline-offset-2 focus-visible:outline-selective-yellow-500"
                                onclick= "window.location.href = '/Achilles/ControladorPrincipal/nuevoContacto'"
                                >
                                <svg
                                    class="h-7 w-7"
                                    viewBox="0 0 20 20"
                                    fill="currentColor"
                                    aria-hidden="true"
                                    >
                                <path
                                    d="M10.75 4.75a.75.75 0 00-1.5 0v4.5h-4.5a.75.75 0 000 1.5h4.5v4.5a.75.75 0 001.5 0v-4.5h4.5a.75.75 0 000-1.5h-4.5v-4.5z"
                                    />
                                </svg>
                            </button>
                        </div>
                    </div>


                    <!--Tabla Dinamica-->
                    <%
                        List<Usuario> contactos = (List<Usuario>) request.getAttribute("contactos");

                        if (!contactos.isEmpty()) {
                            out.println(
                                    "<div class='mt-3 flow-root'>\n"
                                    + "    <div class='-my-2 w-full overflow-x-auto'>\n"
                                    + "        <div class='inline-block w-full align-middle'>\n"
                                    + "            <table class='min-w-full contactos'>\n"
                                    + "                <thead class='text-zinc-100'>\n"
                                    + "                    <tr>\n"
                                    + "                        <th scope='col' class='py-3.5 px-4 text-left text-sm font-semibold'>Name</th>\n"
                                    + "                        <th scope='col' class='px-3 py-3.5 text-left text-sm font-semibold'>Number</th>\n"
                                    + "                        <th scope='col' class='px-3 py-3.5 text-left text-sm font-semibold'>Status</th>\n"
                                    + "                    </tr>\n"
                                    + "                </thead>\n"
                                    + "                <tbody>\n");

                            for (Usuario contacto : contactos) {
//                                divider
                                out.println(""
                                        + "<tr>\n"
                                        + "    <td colspan='3' class='py-0'>\n"
                                        + "        <div class='ml-16 mr-4 first-letter:h-0 bg-zinc-800 border-t border-zinc-600 my-0'></div>\n"
                                        + "    </td>\n"
                                        + "</tr>");
//                                contacto
                                out.println("<tr class='contenido text-zinc-200 transition duration-100 ease-linear hover:bg-zinc-600'>");
                                out.println("    <td class='whitespace-nowrap py-5 px-4 pr-3 text-sm'>");
                                out.println("        <div class='flex items-center'>");
                                out.println("            <div class='h-11 w-11 flex-shrink-0'>");
                                out.println("                <img class='h-11 w-11 rounded-full'");
                                out.println("                     src='https://images.unsplash.com/photo-1517841905240-472988babdf9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80'");
                                out.println("                     alt=''/>");
                                out.println("            </div>");
                                out.println("            <div class='ml-4'>");
                                out.println("                <div class='font-medium'>" + contacto.getNick() + "</div>");
                                out.println("                <div class='mt-1 text-zinc-400'>");
                                out.println(contacto.getNombre() + " " + contacto.getApellido());
                                out.println("                </div>");
                                out.println("            </div>");
                                out.println("        </div>");
                                out.println("    </td>");
                                out.println("    <td class='whitespace-nowrap px-3 py-5 text-sm'>");
                                out.println(contacto.getNumTel());
                                out.println("    </td>");
                                out.println("    <td class='whitespace-nowrap px-3 py-5 text-sm text-gray-500'>");
                                if (contacto.isBizumActive()) {
                                    out.println("        <span class='inline-flex items-center rounded-md px-2 py-1 text-xs font-medium text-green-500 ring-1 ring-inset ring-green-500/70'>");
                                    out.println("            Active");
                                } else {
                                    out.println("        <span class='inline-flex items-center rounded-md px-2 py-1 text-xs font-medium text-red-500 ring-1 ring-inset ring-red-500/70'>");
                                    out.println("            idle");
                                }
                                out.println("        </span>");
                                out.println("    </td>");
                                out.println("</tr>");

                            }

                            out.println("           </tbody>\n"
                                    + "         </table>\n"
                                    + "        </div>\n"
                                    + "    </div>\n"
                                    + "</div>");

                        } else {
                            out.println("<h1 class = 'py-10 px-5 text-selective-yellow-500'> Aún no tienes ningun contacto! </h1>");
                        }
                    %>

                    <!-- Tabla Estatica-->
                    <!--                    <div class="mt-3 flow-root">
                                            <div class="-my-2 w-full overflow-x-auto">
                                                <div class="inline-block w-full align-middle">
                                                    <table class="min-w-full contactos">
                                                        <thead class="text-zinc-100">
                                                            <tr>
                                                                <th
                                                                    scope="col"
                                                                    class="py-3.5 px-4 text-left text-sm font-semibold"
                                                                    >
                                                                    Name
                                                                </th>
                    
                                                                <th
                                                                    scope="col"
                                                                    class="px-3 py-3.5 text-left text-sm font-semibold"
                                                                    >
                                                                    Number
                                                                </th>
                                                                <th
                                                                    scope="col"
                                                                    class="px-3 py-3.5 text-left text-sm font-semibold"
                                                                    >
                                                                    Status
                                                                </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                             divider 
                                                            <tr>
                                                                <td colspan="3" class="py-0">
                                                                    <div
                                                                        class="ml-16 mr-4 first-letter:h-0 bg-zinc-800 border-t border-zinc-600 my-0"
                                                                        ></div>
                                                                </td>
                                                            </tr>
                                                             contenido 
                                                            <tr
                                                                class="contenido text-zinc-200 transition duration-100 ease-linear hover:bg-zinc-600"
                                                                >
                                                                <td class="whitespace-nowrap py-5 px-4 pr-3 text-sm">
                                                                    <div class="flex items-center">
                                                                        <div class="h-11 w-11 flex-shrink-0">
                                                                            <img
                                                                                class="h-11 w-11 rounded-full"
                                                                                src="https://images.unsplash.com/photo-1517841905240-472988babdf9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                                                                                alt=""
                                                                                />
                                                                        </div>
                                                                        <div class="ml-4">
                                                                            <div class="font-medium">Lindsay Walton</div>
                                                                            <div class="mt-1 text-zinc-400">
                                                                                lindsay.walton@example.com
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td class="whitespace-nowrap px-3 py-5 text-sm">
                                                                    112112112
                                                                </td>
                                                                <td
                                                                    class="whitespace-nowrap px-3 py-5 text-sm text-gray-500"
                                                                    >
                                                                    <span
                                                                        class="inline-flex items-center rounded-md px-2 py-1 text-xs font-medium text-green-500 ring-1 ring-inset ring-green-500/70"
                                                                        >Active</span
                                                                    >
                                                                </td>
                                                            </tr>
                    
                                                             divider 
                                                            <tr>
                                                                <td colspan="3" class="py-0">
                                                                    <div
                                                                        class="ml-16 mr-4 h-0 bg-zinc-800 border-t border-zinc-600"
                                                                        ></div>
                                                                </td>
                                                            </tr>
                                                             contenido 
                                                            <tr
                                                                class="contenido text-zinc-200 transition duration-100 ease-linear hover:bg-zinc-600"
                                                                >
                                                                <td class="whitespace-nowrap py-5 pl-4 pr-3 text-sm">
                                                                    <div class="flex items-center">
                                                                        <div class="h-11 w-11 flex-shrink-0">
                                                                            <img
                                                                                class="h-11 w-11 rounded-full"
                                                                                src="https://images.unsplash.com/photo-1517841905240-472988babdf9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                                                                                alt=""
                                                                                />
                                                                        </div>
                                                                        <div class="ml-4">
                                                                            <div class="font-medium">Lindsay Walton</div>
                                                                            <div class="mt-1 text-zinc-400">
                                                                                lindsay.walton@example.com
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td class="whitespace-nowrap px-3 py-5 text-sm">
                                                                    112112112
                                                                </td>
                                                                <td
                                                                    class="whitespace-nowrap px-3 py-5 text-sm text-gray-500"
                                                                    >
                                                                    <span
                                                                        class="inline-flex items-center rounded-md px-2 py-1 text-xs font-medium text-green-500 ring-1 ring-inset ring-green-500/70"
                                                                        >Active</span
                                                                    >
                                                                </td>
                                                            </tr>
                                                             divider 
                                                            <tr>
                                                                <td colspan="3" class="py-0">
                                                                    <div
                                                                        class="ml-16 mr-4 h-0 bg-zinc-800 border-t border-zinc-600"
                                                                        ></div>
                                                                </td>
                                                            </tr>
                                                             contenido 
                                                            <tr
                                                                class="contenido text-zinc-200 transition duration-100 ease-linear hover:bg-zinc-600"
                                                                >
                                                                <td class="whitespace-nowrap py-5 pl-4 pr-3 text-sm">
                                                                    <div class="flex items-center">
                                                                        <div class="h-11 w-11 flex-shrink-0">
                                                                            <img
                                                                                class="h-11 w-11 rounded-full"
                                                                                src="https://images.unsplash.com/photo-1517841905240-472988babdf9?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=facearea&facepad=2&w=256&h=256&q=80"
                                                                                alt=""
                                                                                />
                                                                        </div>
                                                                        <div class="ml-4">
                                                                            <div class="font-medium">Lindsay Walton</div>
                                                                            <div class="mt-1 text-zinc-400">
                                                                                lindsay.walton@example.com
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                                </td>
                                                                <td class="whitespace-nowrap px-3 py-5 text-sm">
                                                                    112112112
                                                                </td>
                                                                <td
                                                                    class="whitespace-nowrap px-3 py-5 text-sm text-gray-500"
                                                                    >
                                                                    <span
                                                                        class="inline-flex items-center rounded-md px-2 py-1 text-xs font-medium text-green-500 ring-1 ring-inset ring-green-500/70"
                                                                        >Active</span
                                                                    >
                                                                </td>
                                                                 <td class="whitespace-nowrap px-3 py-5 text-sm text-gray-500">
                                                                      Member
                                                                    </td> 
                                                                 <td
                                                                      class="relative whitespace-nowrap py-5 pl-3 pr-4 text-right text-sm font-medium sm:pr-0"
                                                                    >
                                                                      <a href="#" class="text-indigo-600 hover:text-indigo-900"
                                                                        >Edit<span class="sr-only">, Lindsay Walton</span></a
                                                                      >
                                                                    </td> 
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>-->
                </div>
            </article>
        </main>

        <!-- Dropdown menu -->
        <script src="/Achilles/scripts/dropdown_menu.js"></script>
        <script src="/Achilles/scripts/dropdown_menu_movil.js"></script>

        <script src="/Achilles/scripts/dividers.js"></script>

        <!--<script src="scripts/dividers.js"></script>-->
        <script src="/Achilles/scripts/userSearch.js"></script>
    </body>
</html>
