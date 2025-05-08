package proyectofinal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Clase que gestiona una lista de usuarios con operaciones de inserción, borrado y lectura.
 * Incluye control de excepciones como parte de las buenas prácticas.
 */
public class GestionUsuarios {
    private List<Usuario> usuariosLista;

    public GestionUsuarios() {
        usuariosLista = new ArrayList<>();
    }

    // Inserta un usuario en la lista
    public void insertarUsuario(Usuario usuario) {
        usuariosLista.add(usuario);
    }

    // Borra un usuario por UUID con control de excepción
    public void borrarUsuario(String uuid) {
        Iterator<Usuario> iterator = usuariosLista.iterator();
        boolean eliminado = false;

        while (iterator.hasNext()) {
            Usuario usuario = iterator.next();
            if (usuario.getUuid().equals(uuid)) {
                iterator.remove();
                eliminado = true;
                break;
            }
        }

        // Lanzamiento de una excepción personalizada si no se encuentra el usuario
        if (!eliminado) {
            try {
                throw new UsuarioNoEncontradoException("Usuario con UUID " + uuid + " no encontrado.");
            } catch (UsuarioNoEncontradoException e) {
                // Captura y gestión de la excepción
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    // Muestra todos los usuarios de la lista
    public void leerUsuario() {
        for (Usuario u : usuariosLista) {
            System.out.println(u.listarUsuario());
        }
    }

    // Busca un usuario por nombre con excepción personalizada y buenas prácticas
    public void checkUser(String nombre) {
        boolean encontrado = false;

        for (Usuario a : usuariosLista) {
            if (a.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(a.listarUsuario());
                encontrado = true;
                break; // Se detiene si ya se ha encontrado
            }
        }

        // Propagación del error si no se encuentra el usuario
        try {
            if (!encontrado) {
                throw new UsuarioNoEncontradoException("No existe el usuario con nombre: " + nombre);
            }
        } catch (UsuarioNoEncontradoException e) {
            System.err.println("Excepción capturada: " + e.getMessage());
        }
    }
}
