package com.moveingroup.beans;

import java.io.IOException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.beans.security.AuthenticationUtilsBean;
import com.moveingroup.clients.RolClient;
import com.moveingroup.clients.UserAccountClient;
import com.moveingroup.clients.UsuarioClient;
import com.moveingroup.clients.ValoracionClient;
import com.moveingroup.clients.usuario.UsuarioUsuarioClient;
import com.moveingroup.clients.usuario.UsuarioValoracionClient;
import com.moveingroup.dto.RolDto;
import com.moveingroup.dto.UserAccountDto;
import com.moveingroup.dto.UsuarioDto;
import com.moveingroup.dto.ValoracionDto;
import com.moveingroup.security.AuthenticationUtils;
import com.moveingroup.utils.Constantes;

import lombok.Data;

@Named
@Data
@Scope("view")
public class UsuarioBean {
	
	private AuthenticationUtilsBean utilsBean = new AuthenticationUtilsBean();
	
	private AuthenticationUtils utils = new AuthenticationUtils();

	@Autowired
	private UsuarioUsuarioClient usuarioUsuarioClient;
	
	@Autowired
	private UsuarioClient usuarioClient;
	
	@Autowired
	private ValoracionClient valoracionClient;
	
	@Autowired
	private UserAccountClient userAccountClient;
	
	@Autowired
	private RolClient rolClient;
	
	@Autowired
	private UsuarioValoracionClient usuarioValoracionClient;
	
	private List<UsuarioDto> usuarios;
	
	private List<UsuarioDto> mejoresValorados;
    
    private UsuarioDto selectedUsuario;
    
    private UsuarioDto loggedUsuario;
    
    // Atributos de creación
    
    private String nombre;
    
    private String apellidos;
    
    private String telefono;
    
    private String email;
    
    private String username;
    
    private String password;
     
    public void init() {
        usuarios = usuarioClient.getAll();
    }
    
    public void initMejoresValorados( ) {
    	mejoresValorados = usuarioClient.getMejoresValorados();
    }
    
    public void getLogged() {
    	UsuarioDto logged = new UsuarioDto();
    	Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
    	logged = usuarioUsuarioClient.getById(idUsuario);
    	loggedUsuario = logged;
    }
    
    public void registro() {
    	UsuarioDto usuarioDto = new UsuarioDto();
    	try {
    		ValoracionDto valoracionDto = new ValoracionDto();
    		valoracionDto.setId((long)0);
			valoracionDto.setMedalla("novato");
			valoracionDto.setPuntos(0);
			valoracionDto.setPuntosNegativos(0);
			valoracionDto.setRango(0);
			
			// Guardado de la valoración que se asociará al usuario
			ValoracionDto savedValoracionDto = valoracionClient.save(valoracionDto);
    		
			usuarioDto.setId((long) 0);
			usuarioDto.setNombre(nombre);
			usuarioDto.setApellidos(apellidos);
			usuarioDto.setTelefono(telefono);
			usuarioDto.setEmail(email);
			usuarioDto.setValoracion(savedValoracionDto);
			
			UsuarioDto savedUsuarioDto = usuarioClient.save(usuarioDto);
			
			UserAccountDto userAccountDto = new UserAccountDto();
			
			RolDto rolDto = rolClient.findByTipoRol(Constantes.ROL_USUARIO);
			
			userAccountDto.setId((long)0);
			userAccountDto.setRol(rolDto);
			userAccountDto.setUsuario(savedUsuarioDto);
			userAccountDto.setUsername(username);
			userAccountDto.setPassword(password);
			
			UserAccountDto savedUserAccountDto = userAccountClient.save(userAccountDto);
			
			limpiarDatos();
			
			if (savedUsuarioDto != null && savedValoracionDto != null && savedUserAccountDto != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					"Se ha registrado correctamente"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("login.xhtml?faces-redirect=true");
			}
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
				    "Error en el registro"));
		}
    }
    
	public void puntuarUsuarioPositivo(Long usuarioId) throws IOException {
		try {
			UsuarioDto usuarioDto = usuarioUsuarioClient.getById(usuarioId);
			ValoracionDto valoracionDto = usuarioDto.getValoracion();
			valoracionDto.setPuntos(valoracionDto.getPuntos() + 1);
			
			ValoracionDto savedValoracionDto = usuarioValoracionClient.update(valoracionDto);
			if (savedValoracionDto != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					"Usuario valorado positivamente"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
				    "Error al puntuar al usuario"));
		}
	}
	
	public void puntuarUsuarioNegativo(Long usuarioId) {
		try {
			UsuarioDto usuarioDto = usuarioUsuarioClient.getById(usuarioId);
			ValoracionDto valoracionDto = usuarioDto.getValoracion();
			valoracionDto.setPuntosNegativos(valoracionDto.getPuntosNegativos() + 1);
			
			ValoracionDto savedValoracionDto = usuarioValoracionClient.update(valoracionDto);
			if (savedValoracionDto != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Info",
					"Usuario valorado negativamente"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR",
				    "Error al puntuar al usuario"));
		}
	}
    
    private void limpiarDatos() {
    	nombre = null;
    	apellidos = null;
    	telefono = null;
    	email = null;
    	username = null;
    	password = null;
    }
}
