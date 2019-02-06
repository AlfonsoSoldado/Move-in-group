package com.moveingroup.beans;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.clients.ActividadClient;
import com.moveingroup.clients.UsuarioClient;
import com.moveingroup.dto.ActividadDto;

import lombok.Data;

@Named
@Data
@Scope("view")
public class ActividadesBean {
	
	@Autowired
	private ActividadClient actividadClient;
	
	@Autowired
	private UsuarioClient usuarioClient;

	private List<ActividadDto> actividades;

	private ActividadDto selectedActividad;

	private String name;

	private Date moment;

	private String city;

	private String country;

	private String address;

	private String type;

	private Integer price;

	public void init() {
		actividades = actividadClient.getAll();
	}

	public void addActividad() throws IOException {
		ActividadDto actividadDto = new ActividadDto();
		try {

			actividadDto.setName(name);
			actividadDto.setAddress(address);
			actividadDto.setCity(city);
			actividadDto.setCountry(country);
			actividadDto.setMoment(moment);
			actividadDto.setPrice(price);
			actividadDto.setTypeOfActivity(type);
			//TODO: Introducir usuario logado
			actividadDto.setUsuario(usuarioClient.getById((long) 0));

			limpiarDatos();
			ActividadDto ret = actividadClient.save(actividadDto);
			if (ret != null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
					"Se ha creado la actividad con éxito."));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext()
						.redirect("actividades.xhtml");
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
	}
}
