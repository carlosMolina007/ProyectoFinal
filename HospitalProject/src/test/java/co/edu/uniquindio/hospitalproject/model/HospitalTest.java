package co.edu.uniquindio.hospitalproject.model;

import co.edu.uniquindio.hospitalproject.model.Hospital;
import co.edu.uniquindio.hospitalproject.model.Paciente;
import co.edu.uniquindio.hospitalproject.model.Persona;
import co.edu.uniquindio.hospitalproject.model.Usuario;
import co.edu.uniquindio.hospitalproject.model.enums.Genero;
import co.edu.uniquindio.hospitalproject.model.enums.TipoRol;
import co.edu.uniquindio.hospitalproject.model.enums.TipoSangre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioCrudTest {

    private Hospital hospital;

    @BeforeEach
    void setUp() {
        // Inicializamos el hospital antes de cada prueba
        hospital = Hospital.getInstancia();
        hospital.setNombre("Hospital de Prueba");
        hospital.setNit("987654-321");
    }

    @Test
    void testCrearUsuario() {
        // Arrange
        Persona persona1 = new Paciente("123", "Juan", "Perez", LocalDate.of(2000, 10, 17), Genero.MASCULINO, TipoSangre.OPOSITIVO, "pepepicapapas@gmail.com", "1234567890");
        Usuario usuario = new Usuario("juan123", "password123", TipoRol.PACIENTE, persona1);

        // Act
        boolean result = hospital.crearUsuario(usuario);

        // Assert
        assertTrue(result, "El usuario debería haberse creado correctamente.");
        Collection<Usuario> usuarios = hospital.listarUsuarios();
        assertEquals(1, usuarios.size(), "Debería haber un usuario registrado en el sistema.");
        Usuario usuarioRegistrado = usuarios.iterator().next();
        assertEquals("juan123", usuarioRegistrado.getUsuario(), "El nombre de usuario debería coincidir.");
        assertEquals(TipoRol.PACIENTE, usuarioRegistrado.getTipoRol(), "El rol del usuario debería ser PACIENTE.");
    }

    @Test
    void testEliminarUsuario() {
        // Arrange
        Persona persona1 = new Paciente("123", "Juan", "Perez", LocalDate.of(2000, 10, 17), Genero.MASCULINO, TipoSangre.OPOSITIVO, "pepepicapapas@gmail.com", "1234567890");
        Usuario usuario = new Usuario("juan123", "password123", TipoRol.ADMIN, persona1);
        hospital.crearUsuario(usuario);

        // Act
        boolean result = hospital.eliminarUsuario("juan123");

        // Assert
        assertTrue(result, "El usuario debería eliminarse correctamente.");
        assertEquals(0, hospital.listarUsuarios().size(), "La lista de usuarios debería estar vacía.");
    }

    @Test
    void testActualizarUsuario() {
        // Arrange
        Persona persona1 = new Paciente("123", "Juan", "Perez", LocalDate.of(2000, 10, 17), Genero.MASCULINO, TipoSangre.OPOSITIVO, "pepepicapapas@gmail.com", "1234567890");
        Usuario usuarioOriginal = new Usuario("juan123", "password123", TipoRol.PACIENTE, persona1);
        hospital.crearUsuario(usuarioOriginal);

        // Actualizar datos del usuario
        Usuario usuarioActualizado = new Usuario("juan123", "newPassword789", TipoRol.ADMIN, persona1);

        // Act
        boolean result = hospital.actualizarUsuario("juan123", usuarioActualizado);

        // Assert
        assertTrue(result, "El usuario debería haberse actualizado correctamente.");
        Usuario usuarioGuardado = hospital.listarUsuarios().iterator().next();
        assertEquals("newPassword789", usuarioGuardado.getPassword(), "La contraseña debería haberse actualizado.");
        assertEquals(TipoRol.ADMIN, usuarioGuardado.getTipoRol(), "El rol debería haberse actualizado a ADMIN.");
    }

    @Test
    void testListarUsuarios() {
        // Arrange
        Persona persona1 = new Paciente("123", "Juan", "Perez", LocalDate.of(2000, 10, 17), Genero.MASCULINO, TipoSangre.OPOSITIVO, "pepepicapapas@gmail.com", "1234567890");
        Persona persona2 = new Paciente("456", "Maria", "Lopez", LocalDate.of(2000, 10, 17), Genero.FEMENINO, TipoSangre.OPOSITIVO, "pepepicapapas2@gmail.com", "1234567890");
        Usuario usuario1 = new Usuario("juan123", "password123", TipoRol.PACIENTE, persona1);
        Usuario usuario2 = new Usuario("maria456", "password456", TipoRol.PACIENTE, persona2);
        hospital.crearUsuario(usuario1);
        hospital.crearUsuario(usuario2);

        // Act
        Collection<Usuario> usuarios = hospital.listarUsuarios();

        // Assert
        assertNotNull(usuarios, "La lista de usuarios no debería ser nula.");
        assertEquals(2, usuarios.size(), "Debería haber 2 usuarios registrados.");
    }
}
