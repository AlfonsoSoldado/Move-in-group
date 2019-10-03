package com.moveingroup.beans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import com.moveingroup.clients.ActividadClient;
import com.moveingroup.clients.EmpresaClient;
import com.moveingroup.clients.UsuarioClient;
import com.moveingroup.clients.ValoracionClient;
import com.moveingroup.utils.Constantes;

import lombok.Data;

@Named
@Data
@Scope("session")
public class EstadisticasBean {

	@Autowired
	private UsuarioClient usuarioClient;

	@Autowired
	private EmpresaClient empresaClient;

	@Autowired
	private ValoracionClient valoracionClient;

	@Autowired
	private ActividadClient actividadClient;
	
	private PieChartModel pieModel1;
	private PieChartModel pieModel2;
	private PieChartModel pieModel3;

	@PostConstruct
	public void init() {
		createPieModels();
	}

	private void createPieModels() {
		createPieModelUsuariosYEmpresasRegistradas();
		createPieModelMedallas();
		createPieModelActividades();
	}

	private void createPieModelUsuariosYEmpresasRegistradas() {
		try {
			pieModel2 = new PieChartModel();

			pieModel2.set("Usuarios", usuarioClient.usuarioCount());
			pieModel2.set("Empresas", empresaClient.empresaCount());

			pieModel2.setTitle("Porcentaje de usuarios y empresas registradas");
			pieModel2.setLegendPosition("e");
			pieModel2.setFill(false);
			pieModel2.setShowDataLabels(true);
			pieModel2.setDiameter(150);
			pieModel2.setShadow(false);
		} catch (Throwable e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ha ocurrido un error inesperado al intentar rescatar las estadísticas", ""));
		}

	}

	private void createPieModelMedallas() {
		try {

			pieModel1 = new PieChartModel();

			pieModel1.set("Extraordinarios", valoracionClient.countByMedalla(Constantes.MEDALLA_EXTRAORDINARIO));
			pieModel1.set("Buenísimos", valoracionClient.countByMedalla(Constantes.MEDALLA_BUENISIMO));
			pieModel1.set("Buenos", valoracionClient.countByMedalla(Constantes.MEDALLA_BUENO));
			pieModel1.set("Novatos", valoracionClient.countByMedalla(Constantes.MEDALLA_NOVATO));
			pieModel1.set("Malos", valoracionClient.countByMedalla(Constantes.MEDALLA_MALO));
			pieModel1.set("Malísimos", valoracionClient.countByMedalla(Constantes.MEDALLA_MALISIMO));

			pieModel1.setTitle("Porcentaje de valoraciones de usuarios");
			pieModel1.setLegendPosition("e");
			pieModel1.setFill(false);
			pieModel1.setShowDataLabels(true);
			pieModel1.setDiameter(150);
			pieModel1.setShadow(false);
		} catch (Throwable e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ha ocurrido un error inesperado al intentar rescatar las estadísticas", ""));
		}
	}
	
	private void createPieModelActividades() {
		try {

			pieModel3 = new PieChartModel();

			pieModel3.set("Terminadas", actividadClient.countByActividad(Constantes.ACTIVIDAD_TERMINADA));
			pieModel3.set("Canceladas", actividadClient.countByActividad(Constantes.ACTIVIDAD_CANCELADA));
			pieModel3.set("Activas", actividadClient.countByActividad(Constantes.ACTIVIDAD_ACTIVA));

			pieModel3.setTitle("Actividades terminadas, canceladas y activas");
			pieModel3.setLegendPosition("e");
			pieModel3.setFill(false);
			pieModel3.setShowDataLabels(true);
			pieModel3.setDiameter(150);
			pieModel3.setShadow(false);
		} catch (Throwable e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Ha ocurrido un error inesperado al intentar rescatar las estadísticas", ""));
		}
	}

}
