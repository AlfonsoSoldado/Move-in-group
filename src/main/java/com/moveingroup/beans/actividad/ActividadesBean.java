package com.moveingroup.beans.actividad;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

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
	
	private List<ActividadDto> actividadesEmpresa;
	
	private ActividadDto selectedActividad;
	
	private Long loggedUser;

	// Atributos de filtrado
	
	private String nombre;
	
	private String pais;
	
	private String ciudad;
	
	private Date desde;
	
	private Date hasta;
	
	private boolean filtro;
	
	private Integer gananciasTotales;
	
	public void limpiar() {
		nombre = null;
		pais = null;
		ciudad = null;
		hasta = null;
		desde = null;
	}
	
	public void init() {
		if(!filtro) {
			limpiar();
			if(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO) != null) {
				loggedUser = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
			}
			actividades = actividadClient.getAll();
		} else {
			filtro = false;
		}
	}
	
	public String limpiarFiltro() {
		limpiar();
		if (utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO) != null) {
			loggedUser = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
		}
		actividades = actividadClient.getAll();
		return "actividades.xhtml?faces-redirect=true";
	}
	
	public void initActividadesDeEmpresaAnonimos() {
		actividadesEmpresa = actividadClient.getAllByEmpresas();
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
	
	public void initActividadesTerminadasDeEmpresa() {
		Long idEmpresa = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDEMPRESA));
		actividades = empresaActividadClient.findActividadesTerminadasByEmpresaId(idEmpresa);
	}
	
	public void filtrar() throws IOException {
		doFiltrar();
	}
	
	public String doFiltrar() throws IOException {
		actividades = actividadClient.filtrar(nombre,pais,ciudad, desde, hasta);
		filtro = true;
		return "usuario/actividades.xhtml?faces-redirect=true";
	}
	
	public void cancelarActividad() {
		
	}
	
    public void cancelarActividadUsuario(Long id) {
	try {
	    ActividadDto actividadDto = usuarioActividadClient.findById(id);

	    String auth = "";
	    usuarioActividadClient.cancelarActividad(actividadDto.getId(), auth);
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha cancelado la actividad: " + actividadDto.getNombre(),""
		    ));
	    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	    FacesContext.getCurrentInstance().getExternalContext().redirect("actividades-usuario.xhtml?faces-redirect=true");

	} catch (Exception e) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al intentar cancelar la actividad",
		    ""));
	}
    }
    
    public void cancelarActividadEmpresa(Long id) {
	try {
	    ActividadDto actividadDto = empresaActividadClient.findById(id);

	    String auth = "";
	    empresaActividadClient.cancelarActividad(actividadDto.getId(), auth);
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha cancelado la actividad: " + actividadDto.getNombre(),
		    ""));
	    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
	    FacesContext.getCurrentInstance().getExternalContext().redirect("actividades-empresa.xhtml?faces-redirect=true");

	} catch (Exception e) {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al intentar cancelar la actividad",
		    ""));
	}
    }
    
	public String redirectParticipantes(Long id) throws IOException {
		usuariosApuntados = usuarioApuntadoClient.findByActividadId(id);
		return "participantes.xhtml?faces-redirect=true";
	}
	
	public String redirectParticipantesTerminadas(Long id) throws IOException {
		usuariosApuntados = usuarioApuntadoClient.findByActividadId(id);
		return "participantes-terminadas.xhtml?faces-redirect=true";
	}
	
	public String redirectMisParticipantes(Long id) throws IOException {
		usuariosApuntados = usuarioApuntadoClient.findByActividadId(id);
		return "mis-participantes.xhtml?faces-redirect=true";
	}
	
	public String redirectMisParticipantesTerminadas(Long id) throws IOException {
		usuariosApuntados = usuarioApuntadoClient.findByActividadId(id);
		return "mis-participantes-terminadas.xhtml?faces-redirect=true";
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
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha enviado una petición de amistad al usuario " + ret.getAmigoB().getNombre() + " " + ret.getAmigoB().getApellidos(),
					""));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al enviar la petición de amistad",
					    ""));
			}
		} catch (Throwable e) {
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al enviar la petición de amistad",
				    ""));
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
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha borrado correctamente el participante",
			    ""));
		    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al intentar cancelar la actividad",
			    ""));
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
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha borrado correctamente el participante",
			    ""));
		    FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);

		} catch (Exception e) {
		    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al intentar cancelar la actividad",
			    ""));
		}
	}
	
	public void participar(Long actividadId) {
		UsuarioApuntadoDto usuarioApuntadoDto = new UsuarioApuntadoDto();
		try {
			ActividadDto actividadDto = usuarioActividadClient.findById(actividadId);
			
			if(actividadDto.getRango() > usuarioUsuarioClient.getById(new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO))).getValoracion().getRango()) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "No tienes el suficiente rango como para participar en esta actividad",
						""));
				
			} else {
				usuarioApuntadoDto.setActividad(actividadDto);
				Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
				usuarioApuntadoDto.setUsuario(usuarioUsuarioClient.getById(idUsuario));
				
				UsuarioApuntadoDto savedUsuarioApuntado = usuarioUsuarioApuntadoClient.save(usuarioApuntadoDto); 
				
				if (savedUsuarioApuntado != null) {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha apuntado correctamente",
						""));
					FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
					FacesContext.getCurrentInstance().getExternalContext()
							.redirect("actividades.xhtml?faces-redirect=true");
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error al intentar apuntarte. Comprueba que no estés ya apuntado.",
					""));
		}
	}
	
	public void calcularGananciasTotales(Long id) {
		try {
			this.empresaActividadClient.calcularGananciasTotales(id);
			
			FacesContext.getCurrentInstance().getExternalContext()
			.redirect("ganancias-empresa.xhtml?faces-redirect=true");
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error al calcular los ingresos.",
					""));
		}
	}
	
	public void gananciasEmpresaTotal() {
		gananciasTotales = 0;
		try {
			Long idEmpresa = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDEMPRESA));
			
			gananciasTotales = this.empresaActividadClient.gananciasEmpresaTotal(idEmpresa);
			if(gananciasTotales == null) {
				gananciasTotales = 0;
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ha ocurrido un error al calcular los ingresos.",
					""));
		}
		
	}
}
