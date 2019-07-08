package com.moveingroup.beans.actividad;

import java.io.IOException;
import java.util.ArrayList;
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
import com.moveingroup.clients.empresa.EmpresaUsuarioApuntadoClient;
import com.moveingroup.clients.usuario.UsuarioActividadClient;
import com.moveingroup.clients.usuario.UsuarioAmigosClient;
import com.moveingroup.clients.usuario.UsuarioUsuarioApuntadoClient;
import com.moveingroup.clients.usuario.UsuarioUsuarioClient;
import com.moveingroup.dto.ActividadDto;
import com.moveingroup.dto.AmigosDto;
import com.moveingroup.dto.UsuarioApuntadoDto;
import com.moveingroup.dto.UsuarioDto;
import com.moveingroup.security.AuthenticationUtils;
import com.moveingroup.utils.Constantes;

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
	
	@Autowired
	private UsuarioUsuarioApuntadoClient usuarioUsuarioApuntadoClient;
	
	@Autowired
	private EmpresaUsuarioApuntadoClient empresaUsuarioApuntadoClient;
	
	@Autowired
	private UsuarioUsuarioClient usuarioUsuarioClient;
	
	@Autowired
	private UsuarioAmigosClient usuarioAmigosClient;
	
	private List<UsuarioApuntadoDto> usuariosApuntados;
	
	private Long idActividad;
	
	private List<ActividadDto> actividades;
	
	private ActividadDto selectedActividad;
	
	private Long loggedUser;

	public void init() {
		if(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO) != null) {
			loggedUser = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
		}
		actividades = actividadClient.getAll();
	}
	
	public void initActividadesDeEmpresaAnonimos() {
		actividades = actividadClient.getAllByEmpresas();
	}

	public void initActividadesDeUsuario() {
		Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
		actividades = usuarioActividadClient.findByUsuarioId(idUsuario);
	}
	
	public void initActividadesTerminadas() {
		Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
		actividades = usuarioActividadClient.findActividadesTerminadas(idUsuario);
	}
	
	public void initActividadesDeEmpresa() {
		Long idEmpresa = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDEMPRESA));
		actividades = empresaActividadClient.findByEmpresaId(idEmpresa);
	}
	
	public void cancelarActividad() {
		
	}
	
    public void cancelarActividadUsuario(Long id) {
	try {
	    ActividadDto actividadDto = usuarioActividadClient.findById(id);

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
		usuariosApuntados = usuarioApuntadoClient.findByActividadId(id);
		return "participantes.xhtml?faces-redirect=true";
	}
	
	public String redirectMisParticipantes(Long id) throws IOException {
		usuariosApuntados = usuarioApuntadoClient.findByActividadId(id);
		return "mis-participantes.xhtml?faces-redirect=true";
	}
	
	public void enviarPeticion(UsuarioDto amigoB) {
		AmigosDto amigosDto = new AmigosDto();
		try {
			Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
			UsuarioDto amigoA = usuarioUsuarioClient.getById(idUsuario);
			amigosDto.setAmigoA(amigoA);

			amigosDto.setAmigoB(amigoB);
			amigosDto.setYaEsAmigo(false);
			
			AmigosDto ret = usuarioAmigosClient.save(amigosDto);
			if (ret != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					"Se ha enviado una petición de amistad al usuario " + ret.getAmigoB().getNombre() + " " + ret.getAmigoB().getApellidos()));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			}
		} catch (Throwable e) {
			// TODO: Tratar excepción
		}
	}
	
	public void deleteParticipantesUsuario(Long idUsuarioApuntado) throws IOException {
		try {
		    usuarioUsuarioApuntadoClient.delete(idUsuarioApuntado);
		    List<UsuarioApuntadoDto> newUsuariosApuntados = new ArrayList<>();
		    for(UsuarioApuntadoDto ua: usuariosApuntados) {
		    	if(ua.getId() != idUsuarioApuntado) {
		    		newUsuariosApuntados.add(ua);
		    	}
		    }
		    usuariosApuntados = newUsuariosApuntados;
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
			    "Se ha borrado correctamente el participante"));
		    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
			    "Error al intentar cancelar la actividad"));
		}
	}
	
	public void deleteParticipantesEmpresa(Long idUsuarioApuntado) throws IOException {
		try {
		    empresaUsuarioApuntadoClient.delete(idUsuarioApuntado);
		    List<UsuarioApuntadoDto> newUsuariosApuntados = new ArrayList<>();
		    for(UsuarioApuntadoDto ua: usuariosApuntados) {
		    	if(ua.getId() != idUsuarioApuntado) {
		    		newUsuariosApuntados.add(ua);
		    	}
		    }
		    usuariosApuntados = newUsuariosApuntados;
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO",
			    "Se ha borrado correctamente el participante"));
		    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
			    "Error al intentar cancelar la actividad"));
		}
	}
	
	public void participar(Long actividadId) {
		UsuarioApuntadoDto usuarioApuntadoDto = new UsuarioApuntadoDto();
		try {
			ActividadDto actividadDto = usuarioActividadClient.findById(actividadId);
			
			if(actividadDto.getRango() > usuarioUsuarioClient.getById(new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO))).getValoracion().getRango()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ATENCIÓN",
						"No tienes el suficiente rango como para participar en esta actividad"));
				
			} else {
				usuarioApuntadoDto.setActividad(actividadDto);
				Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
				usuarioApuntadoDto.setUsuario(usuarioUsuarioClient.getById(idUsuario));
				
				UsuarioApuntadoDto savedUsuarioApuntado = usuarioUsuarioApuntadoClient.save(usuarioApuntadoDto); 
				
				if (savedUsuarioApuntado != null) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
						"Se ha apuntado correctamente"));
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("actividades.xhtml?faces-redirect=true");
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					"Ha ocurrido un error al intentar apuntarte. Comprueba que no estés ya apuntado."));
		}
	}
}
