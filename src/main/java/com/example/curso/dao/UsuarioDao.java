package com.example.curso.dao;

import com.example.curso.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    Usuario getUsuario(Long id);

    Usuario DeleteUsuario(Long id);

    Usuario saveUsuario(Usuario usuario);

    Usuario updateUsuario(Long id, Usuario dataUsuario);

    List<Usuario> buscarUsuario(String nombre);

}
