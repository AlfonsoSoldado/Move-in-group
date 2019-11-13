package com.moveingroup.beans;

import java.util.List;

import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.clients.usuario.UsuarioActividadClient;
import com.moveingroup.dto.ActividadDto;
import com.moveingroup.security.AuthenticationUtils;
import com.moveingroup.utils.Constantes;

import lombok.Data;

@Named
@Scope("session")
@Data
public class CalendarioBean {
	
	 private AuthenticationUtils utils = new AuthenticationUtils();

	@Autowired
	private UsuarioActividadClient usuarioActividadClient;
	
	private ScheduleModel eventModel;
	
	private ScheduleEvent event = new DefaultScheduleEvent();
    
    public void init() {
        eventModel = new DefaultScheduleModel();
        
        Long idUsuario = new Long(utils.getParamFromPayload(Constantes.PAYLOAD_IDUSUARIO));
        
        List<ActividadDto> actividades = this.usuarioActividadClient.findActividadesByApuntado(idUsuario);
        
        for(ActividadDto a: actividades) {
        	DefaultScheduleEvent event = new DefaultScheduleEvent();
            event.setTitle(a.getNombre());
            event.setStartDate(a.getMomento());
            event.setEndDate(a.getMomento());
            event.setDescription(a.getDireccion() + ", " + a.getCiudad() + " - " + a.getPais());
            eventModel.addEvent(event);
        }
 
    }
    
    public void onEventSelect(SelectEvent selectEvent) {
        event = (ScheduleEvent) selectEvent.getObject();
    }

}
