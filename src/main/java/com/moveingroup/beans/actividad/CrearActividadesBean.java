package com.moveingroup.beans.actividad;

import java.io.IOException;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.clients.empresa.EmpresaEmpresaClient;
import com.moveingroup.clients.usuario.UsuarioActividadClient;
import com.moveingroup.clients.usuario.UsuarioUsuarioClient;
import com.moveingroup.dto.ActividadDto;

import lombok.Data;

@Named
@Data
@Scope("view")
public class CrearActividadesBean {

	@Autowired
	private UsuarioActividadClient usuarioActividadClient;
	
	@Autowired
	private UsuarioUsuarioClient usuarioUsuarioClient;
	
	@Autowired
	private EmpresaEmpresaClient empresaEmpresaClient;
	
	private String name;

	private Date moment;

	private String city;

	private String country;

	private String address;

	private String type;

	private Integer price;
	
	private Integer rango;
	
	public void addActividadUsuario() throws IOException {
		ActividadDto actividadDto = new ActividadDto();
		try {
			actividadDto.setId((long) 0);
			actividadDto.setNombre(name);
			actividadDto.setDireccion(address);
			actividadDto.setCiudad(city);
			actividadDto.setPais(country);
			actividadDto.setMomento(moment);
			actividadDto.setPrecio(price);
			actividadDto.setTipoActividad(type);
			actividadDto.setCancelada(false);
			actividadDto.setRango(rango);
			//TODO: Introducir usuario logado
			actividadDto.setUsuario(usuarioUsuarioClient.getById((long) 1));

			limpiarDatos();
			ActividadDto ret = usuarioActividadClient.save(actividadDto);
			if (ret != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					"Se ha creado la actividad con éxito."));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("actividades-usuario.xhtml");
			}
		} catch (Throwable e) {
			// TODO: Tratar excepción
		}
	}
	
	public void addActividadEmpresa() throws IOException {
		ActividadDto actividadDto = new ActividadDto();
		try {
			actividadDto.setId((long) 0);
			actividadDto.setNombre(name);
			actividadDto.setDireccion(address);
			actividadDto.setCiudad(city);
			actividadDto.setPais(country);
			actividadDto.setMomento(moment);
			actividadDto.setPrecio(price);
			actividadDto.setTipoActividad(type);
			actividadDto.setCancelada(false);
			//TODO: Introducir usuario logado
			actividadDto.setEmpresa(empresaEmpresaClient.getById((long) 1));

			limpiarDatos();
			ActividadDto ret = usuarioActividadClient.save(actividadDto);
			if (ret != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					"Se ha creado la actividad con éxito."));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("actividades-empresa.xhtml");
			}
		} catch (Throwable e) {
			// TODO: Tratar excepción
		}
	}
	
	private void limpiarDatos() {
		name = null;
		address = null;
		city = null;
		country = null;
		moment = null;
		price = null;
		type = null;
		rango = null;
	}
}
