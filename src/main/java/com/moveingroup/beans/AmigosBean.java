package com.moveingroup.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.beans.security.AuthenticationUtilsBean;
import com.moveingroup.clients.usuario.UsuarioAmigosClient;
import com.moveingroup.dto.AmigosDto;
import com.moveingroup.security.AuthenticationUtils;
import com.moveingroup.utils.Constantes;

import lombok.Data;

@Named
@Data
@Scope("session")
public class AmigosBean {

	private AuthenticationUtilsBean utilsBean = new AuthenticationUtilsBean();
	
	private AuthenticationUtils utils = new AuthenticationUtils();
	
	@Autowired
	private UsuarioAmigosClient usuarioAmigosClient;
	
	private List<AmigosDto> amigos;
	
	private Long usuarioLogado;
	
	public void init() {
		Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
		amigos = usuarioAmigosClient.getMisAmigos(idUsuario);
		usuarioLogado = idUsuario;
	}
	
	public void getMisPeticionesDeAmistad() {
		Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
		amigos = usuarioAmigosClient.getMisPeticionesDeAmistad(idUsuario);
	}
	
	public void aceptarPeticion(AmigosDto amigosDto) {
		try {
			AmigosDto ret = usuarioAmigosClient.aceptarPeticion(amigosDto.getId(), amigosDto);
			if (ret != null && ret.isYaEsAmigo()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					"Petición aceptada"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext().redirect("mis-peticiones-amistad.xhtml?faces-redirect=true");
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
					    "Error al aceptar la petición de amistad"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
				    "Error al aceptar la petición de amistad"));
		}
	}
	
	public void rechazarPeticion(AmigosDto amigosDto) {
		try {
			usuarioAmigosClient.rechazarPeticion(amigosDto.getId());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atención",
					"Petición rechazada"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext().redirect("mis-peticiones-amistad.xhtml?faces-redirect=true");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
				    "Error al rechazar la petición de amistad"));
		}
	}
	
}
