/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal;

/**
 * Excepción personalizada para cuando un usuario no se encuentra.
 */
public class UsuarioNoEncontradoException extends Exception {
    
    // Constructor con mensaje personalizado
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje); // Propagación del mensaje al constructor padre (Exception)
    }
}

