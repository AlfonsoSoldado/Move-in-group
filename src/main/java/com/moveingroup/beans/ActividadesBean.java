package com.moveingroup.beans;

import java.util.List;

import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;

import com.moveingroup.clients.ActividadClient;
import com.moveingroup.dto.ActividadDto;

import lombok.Data;

@Named
@Data
public class ActividadesBean {

	private List<ActividadDto> actividades;
    
    private ActividadDto selectedActividad;
     
    @Autowired
    private ActividadClient actividadClient;
     
    public void init() {
        actividades = actividadClient.getAll();
    }
}
