package com.example.curso.controllers;

import com.example.curso.dao.UsuarioDao;
import com.example.curso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController{

    // Crea un campo usuarioDao y automáticamente le proporciona una instancia del objeto UsuarioDao (@Autowired)
    @Autowired
    private UsuarioDao usuarioDao;

    @RequestMapping("/user")
    public List<Usuario> getUsuarios() {
        return usuarioDao.getUsuarios();
    }

    @RequestMapping("/user/{id}")
    public Usuario getUsuario(@PathVariable Long id) {
        return usuarioDao.getUsuario(id);
    }

    @RequestMapping(value = "/guardarUsuario", method = RequestMethod.POST)
    public ResponseEntity saveUsuario(@RequestBody Usuario usuario){
        usuarioDao.saveUsuario(usuario);
        return ResponseEntity.ok("Usuario guardado");
    }

    @PutMapping("/updateUser/{id}")
    public ResponseEntity editarUsuario(@PathVariable Long id, @RequestBody Usuario dataUsuario) {
        usuarioDao.updateUsuario(id, dataUsuario);
        return ResponseEntity.ok("Usuario actualizado");
    }

    @RequestMapping("/deleteUser/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id) {
        usuarioDao.DeleteUsuario(id);
        return ResponseEntity.ok("Usuario eliminado");
    }

    @RequestMapping("/searchUser/{nombre}")
    public List<Usuario> buscarUsuario(@PathVariable String nombre) {
        return usuarioDao.buscarUsuario(nombre);
    }
}
