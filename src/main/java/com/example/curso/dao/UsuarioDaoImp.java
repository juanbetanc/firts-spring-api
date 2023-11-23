package com.example.curso.dao;

import com.example.curso.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

// Indica que esta clase va a interactuar con una base de datos
@Repository
// Asegura que los métodos se ejecuten todos con éxito
@Transactional
public class UsuarioDaoImp implements UsuarioDao{

    // Permite inyectar un EntityManager
    // EntityManajer es una interfaz de JPA que proporciona métodos para interactuar con una base de datos
    // Se encarga de las operaciones de persistencia (Almacenamiento y recuperación) de entidades JPA
    @PersistenceContext
    private EntityManager entityManager;

    // Método para obtener todos los usuarios
    // @Override sirve para indicar la sobreescritura de un método
    @Override
    public List getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();
    }

    // Método para obtener un usuario por id
    public Usuario getUsuario(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    // Método para eliminar un usuario por id
    public Usuario DeleteUsuario(Long id){
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
        return usuario;
    }

    // Método para guardar un usuario
    @Override
    public Usuario saveUsuario(Usuario usuario) {
        entityManager.persist(usuario);
        return usuario;
    }

    // Método para actualizar un usuario
    @Override
    public Usuario updateUsuario(Long id, Usuario dataUsuario){
        Usuario usuario = entityManager.find(Usuario.class, id);

        // Si el usuario es diferente de "null", cambia los datos del usuario por los datos del usuario que se envía en la petición
        if(usuario != null){
            usuario.setNombre(dataUsuario.getNombre());
            usuario.setApellido(dataUsuario.getApellido());
            usuario.setEmail(dataUsuario.getEmail());
            usuario.setPassword(dataUsuario.getPassword());

            // Guarda los cambios en la base de datos
            entityManager.merge(dataUsuario);
        }else{
            // Manejo de eror si el usuario no se ecuentra en la base de datos
            throw new RuntimeException("No se encontró el usuario con el id: " + id);
        }
        return usuario;
    }

    // Método para buscar un usuario por nombre
    @Override
    public List<Usuario> buscarUsuario(String nombre){
        String query = "FROM Usuario WHERE nombre LIKE :nombre";
        List<Usuario> usuarios = entityManager.createQuery(query, Usuario.class).setParameter("nombre", "%" + nombre + "%").getResultList();
        if(!usuarios.isEmpty()){
            return usuarios;
        }else{
            throw new RuntimeException("No se encontró el usuario con el nombre: " + nombre);
        }
    }
}
