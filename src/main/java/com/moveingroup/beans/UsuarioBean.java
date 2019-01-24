package com.moveingroup.beans;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.moveingroup.clients.UsuarioClient;
import com.moveingroup.dto.UsuarioDto;

import lombok.Data;

@Named
@Data
public class UsuarioBean {

	private List<UsuarioDto> usuarios;
    
    private UsuarioDto selectedUsuario;
    
    private UsuarioDto loggedUsuario;
     
    @Autowired
    private UsuarioClient usuarioClient;
     
    public void init() {
        usuarios = usuarioClient.getAll();
    }
    
    public void getLogged() {
    	//TODO: Cambiar método cuando esté implementado el login
    	UsuarioDto logged = new UsuarioDto();
    	logged = usuarioClient.getById((long) 0);
    	loggedUsuario = logged;
    }
}
