package com.moveingroup.beans.actividad;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.beans.security.AuthenticationUtilsBean;
import com.moveingroup.clients.ActividadClient;
import com.moveingroup.clients.UsuarioApuntadoClient;
import com.moveingroup.clients.empresa.EmpresaActividadClient;
import com.moveingroup.clients.usuario.UsuarioActividadClient;
import com.moveingroup.dto.ActividadDto;
import com.moveingroup.dto.UsuarioApuntadoDto;
import com.moveingroup.security.AuthenticationUtils;

import lombok.Data;

@Named
@Data
@Scope("session")
public class ActividadesBean {
	
    private AuthenticationUtilsBean utilsBean = new AuthenticationUtilsBean();

    private AuthenticationUtils utils = new AuthenticationUtils();
	
	@Autowired
	private ActividadClient actividadClient;
	
	@Autowired
	private UsuarioActividadClient usuarioActividadClient;
	
	@Autowired
	private EmpresaActividadClient empresaActividadClient;
	
	@Autowired
	private UsuarioApuntadoClient usuarioApuntadoClient;
	
	private List<UsuarioApuntadoDto> usuariosApuntados;
	
	private Long idActividad;
	
	private List<ActividadDto> actividades;
	
	private ActividadDto selectedActividad;

	public void init() {
		actividades = actividadClient.getAll();
	}
	
	public void initActividadesDeEmpresaAnonimos() {
		actividades = actividadClient.getAllByEmpresas();
	}

	public void initActividadesDeUsuario() {
		// TODO: Meter usuario logado
//		Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
		Long idUsuario = (long) 1;
		actividades = usuarioActividadClient.findByUsuarioId(idUsuario); //TODO: Pasar el Auth
	}
	
	public void initActividadesDeEmpresa() {
		// TODO: Meter usuario logado
//		Long idEmpresa = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDEMPRESA));
		Long idEmpresa = (long) 0;
		actividades = empresaActividadClient.findByEmpresaId(idEmpresa); //TODO: Pasar el Auth
	}
	
	public void cancelarActividad() {
		
	}
	
    public void cancelarActividadUsuario(Long id) {
	try {
	    ActividadDto actividadDto = usuarioActividadClient.findById(id);

	    //TODO: Pasar el Auth
	    String auth = "";
	    usuarioActividadClient.cancelarActividad(actividadDto.getId(), auth);
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
		    "Se ha cancelado la actividad: " + actividadDto.getNombre()));
	    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	    FacesContext.getCurrentInstance().getExternalContext().redirect("actividades-usuario.xhtml?faces-redirect=true");

	} catch (Exception e) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
		    "Error al intentar cancelar la actividad"));
	}
    }
    
    public void cancelarActividadEmpresa(Long id) {
	try {
	    ActividadDto actividadDto = empresaActividadClient.findById(id);

	    //TODO: Pasar el Auth
	    String auth = "";
	    empresaActividadClient.cancelarActividad(actividadDto.getId(), auth);
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
		    "Se ha cancelado la actividad: " + actividadDto.getNombre()));
	    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	    FacesContext.getCurrentInstance().getExternalContext().redirect("actividades-empresa.xhtml?faces-redirect=true");

	} catch (Exception e) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
		    "Error al intentar cancelar la actividad"));
	}
    }
    
	public String redirectParticipantes(Long id) throws IOException {
		usuariosApuntados = usuarioApuntadoClient.findByActividadId(id); //TODO: Pasar el Auth
		return "participantes.xhtml?faces-redirect=true";
	}
}
