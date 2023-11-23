package pila;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

/**
 *
 * @author rafaa
 */
public interface IPila {
    int getNum();
    void Apila(Object elemento)throws Exception;
    Object Desapila()throws Exception;
    Object Primero()throws Exception;
}
