/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyectofinal;

import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Main {

    // El método main ahora propaga la excepción personalizada
    public static void main(String[] args) throws UsuarioNoEncontradoException {
        GestionUsuarios gestion = new GestionUsuarios();
        Scanner scanner = new Scanner(System.in);

        //PRUEBA MANUAL
        Usuario u1 = new Usuario("Alice", "abc123hashed");
        Usuario u2 = new Usuario("Bob", "xyz456hashed");

        // Insertar usuarios
        gestion.insertarUsuario(u1);
        gestion.insertarUsuario(u2);

        System.out.println("Usuarios después de insertar:");
        gestion.leerUsuario();

        // Borrar uno
        gestion.borrarUsuario(u1.getUuid());

        System.out.println("Usuarios después de borrar a Alice:");
        gestion.leerUsuario();

        // Buscar usuario (esto lanzará una excepción si no existe)
        gestion.checkUser(u1.getNombre()); // Usa getNombre() en lugar de acceso directo

        //PRUEBA POR EL SCANNER
        System.out.println("Registro de 2 usuarios:");

        int contador = 0;
        while (contador < 2) {
            System.out.println("Usuario #" + (contador + 1));

            try {
                System.out.print("Ingrese nombre: ");
                String nombre = scanner.nextLine().trim();
                if (nombre.isEmpty()) {
                    throw new IllegalArgumentException("El nombre no puede estar vacío.");
                }

                System.out.print("Ingrese contraseña (mínimo 6 caracteres): ");
                String contrasena = scanner.nextLine().trim();
                if (contrasena.length() < 6) {
                    throw new IllegalArgumentException("La contraseña debe tener al menos 6 caracteres.");
                }

                // En un caso real, aquí se aplicaría hash a la contraseña
                Usuario nuevoUsuario = new Usuario(nombre, contrasena);
                gestion.insertarUsuario(nuevoUsuario);
                contador++;

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Por favor, intente de nuevo.\n");
            }
        }

        System.out.println("\nUsuarios registrados:");
        gestion.leerUsuario();

        // Prueba de borrado
        try {
            System.out.print("\nIngrese el UUID del usuario a borrar: ");
            String uuidABorrar = scanner.nextLine().trim();
            gestion.borrarUsuario(uuidABorrar);
            System.out.println("Usuario borrado con éxito.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Prueba de búsqueda
        try {
            System.out.print("\nIngrese el nombre del usuario a buscar: ");
            String nombreBusqueda = scanner.nextLine().trim();
            gestion.checkUser(nombreBusqueda);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }

}
