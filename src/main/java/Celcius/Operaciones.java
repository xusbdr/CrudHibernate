package Celcius;

import java.util.Calendar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;




public class Operaciones {

    private SessionFactory sessionFactory;

    public Operaciones() {
        // Inicializar las sesiones de Hibernate
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    
    
    
    
    
    
    
    //--------------------------------------------------------
    
    
    

    // VE LOS DATOS SELECCIONANDO UN ID
    public void verDatos(int idCliente) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Buscar el cliente por su ID
            Cliente cliente = session.get(Cliente.class, idCliente);

            if (cliente != null) {
                // Mostrar los datos del cliente
                System.out.println("Datos del cliente con ID " + idCliente + ":");
                System.out.println("ID: " + cliente.getIdCliente() +
                                   ", Nombre: " + cliente.getNombreCompleto() +
                                   ", Direccion: " + cliente.getDireccion() +
                                   ", Correo: " + cliente.getCorreo() +
                                   ", Telefono: " + cliente.getTelefono() +
                                   ", Foto: " + cliente.getFoto());
                // Puedes mostrar más campos si es necesario

                transaction.commit();
            } else {
                System.out.println("No se encontró ningún cliente con el ID especificado.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    
    
    
    
    
    
    
    
    //-----------------------
    
    
    
    

    public void borrarDato(int idCliente, int idVenta) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Buscar la venta por su ID y el ID del cliente asociado
            Venta venta = session.createQuery("FROM Venta v WHERE v.idVenta = :idVenta AND v.cliente.idCliente = :idCliente", Venta.class)
                                 .setParameter("idVenta", idVenta)
                                 .setParameter("idCliente", idCliente)
                                 .uniqueResult();

            if (venta != null) {
                // Eliminar la venta encontrada
                session.delete(venta);
                
                transaction.commit();
                System.out.println("La venta ha sido eliminada correctamente.");
            } else {
                System.out.println("No se encontró ninguna venta asociada al cliente con el ID especificado.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    
    
    
   
    
    // -----------------------
    
    
    

    public void modificarDato(int idModificar, String nombreCompleto, String direccion, String correo, String telefono, String foto) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Obtener el objeto Cliente a modificar por su ID
            Cliente dato = session.get(Cliente.class, idModificar);

            if (dato != null) {
                // Verificar que el nombre completo no sea nulo ni vacío
                if (nombreCompleto != null && !nombreCompleto.isEmpty()) {
                    dato.setNombreCompleto(nombreCompleto);
                }
                // Verificar y actualizar los otros campos del objeto Cliente si los valores no son nulos
                if (direccion != null) {
                    dato.setDireccion(direccion);
                }
                if (correo != null) {
                    dato.setCorreo(correo);
                }
                if (telefono != null) {
                    dato.setTelefono(telefono);
                }
                if (foto != null) {
                    dato.setFoto(foto);
                }

                // Actualizar el objeto Cliente en la base de datos
                session.update(dato);

                transaction.commit();
                System.out.println("El cliente ha sido modificado correctamente.");
            } else {
                System.out.println("No se encontró ningún cliente con el ID especificado.");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    
    
    
    //--------------------------------
    
    
    

    public void insertar(Album album, Cliente cliente) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Crear una nueva instancia de Venta
            Venta venta = new Venta();
            venta.setAlbum(album);
            venta.setCliente(cliente);

            // Obtener la fecha y hora actuales con Calendar
            Calendar cal = Calendar.getInstance();
            venta.setFecha(cal.getTime()); // Fecha actual

            // Guardar la Venta en la base de datos
            session.save(venta);

            transaction.commit();
            System.out.println("Venta agregada correctamente.");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al agregar la venta: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    
    

    // PARA EL INSERTAR

    public Album obtenerAlbumPorId(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Album album = null;

        try {
            transaction = session.beginTransaction();
            album = session.get(Album.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al obtener el álbum por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }

        return album;
    }
    
    
    
    
    //----
    
    
    

    public Cliente obtenerClientePorId(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Cliente cliente = null;

        try {
            transaction = session.beginTransaction();
            cliente = session.get(Cliente.class, id);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error al obtener el cliente por ID: " + e.getMessage());
            e.printStackTrace();
        } finally {
            session.close();
        }

        return cliente;
    }
}










//