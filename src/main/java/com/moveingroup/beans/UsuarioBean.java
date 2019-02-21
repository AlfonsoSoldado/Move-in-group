package com.moveingroup.beans;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.moveingroup.clients.UsuarioClient;
import com.moveingroup.clients.usuario.UsuarioUsuarioClient;
import com.moveingroup.dto.UsuarioDto;

import lombok.Data;

@Named
@Data
public class UsuarioBean {

	@Autowired
	private UsuarioUsuarioClient usuarioUsuarioClient;
	
	@Autowired
	private UsuarioClient usuarioClient;
	
	private List<UsuarioDto> usuarios;
	
	private List<UsuarioDto> mejoresValorados;
    
    private UsuarioDto selectedUsuario;
    
    private UsuarioDto loggedUsuario;
     
    public void init() {
        usuarios = usuarioClient.getAll();
    }
    
    public void initMejoresValorados( ) {
    	mejoresValorados = usuarioClient.getMejoresValorados();
    }
    
    public void getLogged() {
    	//TODO: Cambiar método cuando esté implementado el login
    	UsuarioDto logged = new UsuarioDto();
    	logged = usuarioUsuarioClient.getById((long) 0);
    	loggedUsuario = logged;
    }
}
