package com.moveingroup.beans.usuarioApuntado;

import java.io.IOException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.beans.security.AuthenticationUtilsBean;
import com.moveingroup.clients.UsuarioApuntadoClient;
import com.moveingroup.dto.UsuarioApuntadoDto;
import com.moveingroup.security.AuthenticationUtils;

import lombok.Data;

@Named
@Data
@Scope("view")
public class UsuarioApuntadoBean {
	
    private AuthenticationUtilsBean utilsBean = new AuthenticationUtilsBean();

    private AuthenticationUtils utils = new AuthenticationUtils();
	
	@Autowired
	private UsuarioApuntadoClient usuarioApuntadoClient;
	
	private List<UsuarioApuntadoDto> usuariosApuntados;
	
	private UsuarioApuntadoDto selectedUsuarioApuntado;

	public void initUsuariosApuntados(Long idActividad) throws IOException {
		FacesContext.getCurrentInstance().getExternalContext()
		.redirect("participantes.xhtml?faces-redirect=true");
		usuariosApuntados = usuarioApuntadoClient.findByActividadId(idActividad); //TODO: Pasar el Auth
	}
}
