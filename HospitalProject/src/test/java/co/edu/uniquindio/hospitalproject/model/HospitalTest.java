package co.edu.uniquindio.hospitalproject.model;


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
    void testCrearSala() {

        Sala sala = new Sala("S001", "Cirugía", true);

        boolean result = hospital.crearSala(sala);

        assertTrue(result, "La sala debería haberse creado correctamente.");
        Collection<Sala> salasRegistradas= hospital.listarSala();
        Sala salaRegistrada=null;
        for(Sala s : salasRegistradas ){
            if(s.getIdSala().equals("S001")){
                salaRegistrada = s;
            }
        }
        assertEquals(hospital.listarSala().size(), salasRegistradas.size(), "Debería haber una sala en el sistema.");
        assertNotNull(salaRegistrada,"Deberia encontrar la sala registrada");
        assertEquals("S001", salaRegistrada.getIdSala(), "El número de sala debería coincidir.");
        assertEquals("Cirugía", salaRegistrada.getNombreSala(), "El tipo de sala debería coincidir.");
    }

    @Test
    void testListarUsuarios() {

        Persona persona1 = new Paciente("123", "Juan", "Perez", LocalDate.of(2000, 10, 17), Genero.MASCULINO, TipoSangre.OPOSITIVO, "pepepicapapas@gmail.com", "1234567890");
        Persona persona2 = new Paciente("456", "Maria", "Lopez", LocalDate.of(2000, 10, 17), Genero.FEMENINO, TipoSangre.OPOSITIVO, "pepepicapapas2@gmail.com", "1234567890");
        Usuario usuario1 = new Usuario("juan123", "password123", TipoRol.PACIENTE, persona1);
        Usuario usuario2 = new Usuario("maria456", "password456", TipoRol.PACIENTE, persona2);
        hospital.crearUsuario(usuario1);
        hospital.crearUsuario(usuario2);


        Collection<Usuario> usuarios = hospital.listarUsuarios();


        assertNotNull(hospital.listarUsuarios(), "La lista de usuarios no debería ser nula.");
        assertEquals(hospital.listarUsuarios().size(), usuarios.size(), "Debería haber 2 usuarios registrados.");
    }

    @Test
    void testCrearUsuario() {
        Persona persona1 = new Paciente("123", "Juan", "Perez", LocalDate.of(2000, 10, 17), Genero.MASCULINO, TipoSangre.OPOSITIVO, "pepepicapapas@gmail.com", "1234567890");
        Usuario usuario = new Usuario("juan123", "password123", TipoRol.PACIENTE, persona1);

        boolean result = hospital.crearUsuario(usuario);

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


        boolean result = hospital.eliminarUsuario("juan123");

        assertTrue(result, "El usuario debería eliminarse correctamente.");
        assertEquals(0, hospital.listarUsuarios().size(), "La lista de usuarios debería estar vacía.");
    }

    @Test
    void testActualizarUsuario() {
        Persona persona1 = new Paciente("123", "Juan", "Perez", LocalDate.of(2000, 10, 17), Genero.MASCULINO, TipoSangre.OPOSITIVO, "pepepicapapas@gmail.com", "1234567890");
        Usuario usuarioOriginal = new Usuario("juan123", "password123", TipoRol.PACIENTE, persona1);
        hospital.crearUsuario(usuarioOriginal);

        // Actualizar datos del usuario
        Usuario usuarioActualizado = new Usuario("juan1234", "newPassword789", TipoRol.ADMIN, persona1);


        boolean result = hospital.actualizarUsuario("juan123", usuarioActualizado);


        assertTrue(result, "El usuario debería haberse actualizado correctamente.");
        Collection<Usuario> usuarios = hospital.listarUsuarios();
        Usuario usuarioGuardado = null;
        for(Usuario u : usuarios){
            if(u.getUsuario().equals("juan1234")){
                usuarioGuardado = u;
            }
        }
        assertNotNull(usuarioGuardado,"Deberia encontrar el usuario actualizado");
        assertEquals("newPassword789", usuarioGuardado.getPassword(), "La contraseña debería haberse actualizado.");
        assertEquals(TipoRol.ADMIN, usuarioGuardado.getTipoRol(), "El rol debería haberse actualizado a ADMIN.");
    }






        @Test
        void testEliminarSala() {

            Sala sala = new Sala("S001", "Cirugía", true);
            hospital.crearSala(sala);


            boolean result = hospital.eliminarSala("S001");


            assertTrue(result, "La sala debería eliminarse correctamente.");

        }

        @Test
        void testActualizarSala() {
            Sala sala = new Sala("S002", "UCI", true);
            hospital.crearSala(sala);

            // Datos actualizados de la sala
            Sala nuevaSala = new Sala("S002", "Consulta General", true);

            boolean result = hospital.actualizarSala("S002", nuevaSala);

            assertTrue(result, "La sala debería haberse actualizado correctamente.");

            Collection<Sala> salas= hospital.listarSala();
            Sala salaActualizada=null;
            for(Sala s : salas ){
                if(s.getIdSala().equals("S002")){
                    salaActualizada = s;
                }
            }
            assertNotNull(salaActualizada,"Deberia encontrar la sala actualizada");
            assertEquals("S002", salaActualizada.getIdSala(), "La nueva capacidad debería haber sido actualizada.");
            assertEquals("Consulta General", salaActualizada.getNombreSala(), "El nuevo tipo debería haber sido actualizado.");
        }

        @Test
        void testListarSalas() {

            Sala sala1 = new Sala("S001", "Cirugía", true);
            Sala sala2 = new Sala("S002", "UCI", true);
            hospital.crearSala(sala1);
            hospital.crearSala(sala2);


            Collection<Sala> salas = hospital.listarSala();


            assertNotNull(salas, "La lista de salas no debería ser nula.");
            assertEquals(4, salas.size(), "Debería haber 2 salas registradas en el sistema.");
        }
    }
