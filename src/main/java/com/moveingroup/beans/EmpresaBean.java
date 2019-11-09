package com.moveingroup.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.clients.EmpresaClient;
import com.moveingroup.clients.RolClient;
import com.moveingroup.clients.UserAccountClient;
import com.moveingroup.clients.empresa.EmpresaEmpresaClient;
import com.moveingroup.dto.EmpresaDto;
import com.moveingroup.dto.RolDto;
import com.moveingroup.dto.UserAccountDto;
import com.moveingroup.security.AuthenticationUtils;
import com.moveingroup.utils.Constantes;

import lombok.Data;

@Named
@Data
@Scope("view")
public class EmpresaBean {

	private AuthenticationUtils utils = new AuthenticationUtils();

	@Autowired
	private EmpresaClient empresaClient;

	@Autowired
	private EmpresaEmpresaClient empresaEmpresaClient;
	
	@Autowired
	private UserAccountClient userAccountClient;

	@Autowired
	private RolClient rolClient;

	private List<EmpresaDto> empresas;

	private EmpresaDto loggedEmpresa;

// Atributos de creación

	private String nombre;

	private String ciudad;

	private String pais;

	private String email;

	private String telefono;

	private String web;

	private String username;

	private String password;
	
	private String descripcion;

	public void init() {
		empresas = empresaClient.findAll();
	}

	public void getLogged() {
		EmpresaDto logged = new EmpresaDto();
		Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDEMPRESA));
		logged = empresaEmpresaClient.getById(idUsuario);
		loggedEmpresa = logged;
	}

	public void registro() {
		EmpresaDto empresaDto = new EmpresaDto();
		try {
			empresaDto.setId((long) 0);
			empresaDto.setNombre(nombre);
			empresaDto.setCiudad(ciudad);
			empresaDto.setPais(pais);
			empresaDto.setEmail(email);
			empresaDto.setTelefono(telefono);
			empresaDto.setWeb(web);
			empresaDto.setDescripcion(descripcion);

			EmpresaDto savedEmpresaDto = empresaClient.save(empresaDto);

			UserAccountDto userAccountDto = new UserAccountDto();

			RolDto rolDto = rolClient.findByTipoRol(Constantes.ROL_EMPRESA);

			userAccountDto.setId((long) 0);
			userAccountDto.setRol(rolDto);
			userAccountDto.setEmpresa(savedEmpresaDto);
			userAccountDto.setUsername(username);
			userAccountDto.setPassword(password);

			UserAccountDto savedUserAccountDto = userAccountClient.save(userAccountDto);

			limpiarDatos();

			if (savedEmpresaDto != null && savedUserAccountDto != null) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha registrado correctamente", ""));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext().redirect("estadisticas.xhtml?faces-redirect=true");
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el registro",
					    ""));
			}

		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error en el registro",
				    ""));
		}
	}

	private void limpiarDatos() {
		nombre = null;
		ciudad = null;
		pais = null;
		email = null;
		telefono = null;
		web = null;
		username = null;
		password = null;
		descripcion = null;
	}
}
