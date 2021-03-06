package com.moveingroup.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moveingroup.dto.ActividadDto;
import com.moveingroup.dto.EmpresaDto;
import com.moveingroup.dto.UsuarioApuntadoDto;
import com.moveingroup.entities.Actividad;
import com.moveingroup.entities.UsuarioApuntado;
import com.moveingroup.repositories.ActividadRepository;
import com.moveingroup.utils.Constantes;

@Service
public class ActividadService {

	@Autowired
	private ActividadRepository actividadRepository;

	@Autowired
	private UsuarioApuntadoService usuarioApuntadoService;
	
	@Autowired
	private EmpresaService empresaService;
	
	public List<ActividadDto> findAll() {
		List<Actividad> target = new ArrayList<>();
		List<ActividadDto> res = new ArrayList<>();

		target = actividadRepository.findAll(new Date());

		for (Actividad actividad : target) {
			ModelMapper modelMapper = new ModelMapper();
			res.add(modelMapper.map(actividad, ActividadDto.class));
		}

		return res;
	}
	
	public List<ActividadDto> filtrar(String nombre, String pais, String ciudad, Date desde, Date hasta) {
		List<ActividadDto> res = new ArrayList<>();
		
		if("null".equals(nombre)) {
			nombre = null;
		}
		if("null".equals(pais)) {
			pais = null;
		}
		if("null".equals(ciudad)) {
			ciudad = null;
		}
		
		try {
			List<Actividad> actividades = actividadRepository.filtrar(nombre, pais, ciudad, desde, hasta);
			
			for (Actividad actividad : actividades) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(actividad, ActividadDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
	}
	
	public List<ActividadDto> getAllByEmpresas() {
		List<Actividad> target = new ArrayList<>();
		List<ActividadDto> res = new ArrayList<>();

		Iterable<Actividad> source = actividadRepository.getAllByEmpresas();
		source.forEach(target::add);

		for (Actividad actividad : target) {
			ModelMapper modelMapper = new ModelMapper();
			res.add(modelMapper.map(actividad, ActividadDto.class));
		}

		return res;
	}
	
	public List<ActividadDto> findByUsuarioId(Long id) {
		List<ActividadDto> res = new ArrayList<>();
		
		try {
			List<Actividad> actividades = actividadRepository.findByUsuarioId(id, new Date());
			
			for (Actividad actividad : actividades) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(actividad, ActividadDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
	}
	
	public List<ActividadDto> findActividadesTerminadas(Long id) {
		List<ActividadDto> res = new ArrayList<>();
		
		try {
			List<Actividad> actividades = actividadRepository.findActividadesTerminadas(id, new Date());
			
			for (Actividad actividad : actividades) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(actividad, ActividadDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
	}
	
	public List<ActividadDto> findActividadesByApuntado(Long id) {
		List<ActividadDto> res = new ArrayList<>();
		
		try {
			List<UsuarioApuntadoDto> usuariosApuntadosDto = this.usuarioApuntadoService.findByUsuarioId(id);
			for(UsuarioApuntadoDto uaDto: usuariosApuntadosDto) {
				res.add(uaDto.getActividad());
			}

			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
	}
	
	public List<ActividadDto> findByEmpresaId(Long id) {
		List<ActividadDto> res = new ArrayList<>();
		
		try {
			List<Actividad> actividades = actividadRepository.findByEmpresaId(id, new Date());
			
			for (Actividad actividad : actividades) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(actividad, ActividadDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
	}
	
	public List<ActividadDto> findActividadesTerminadasByEmpresaId(Long id) {
		List<ActividadDto> res = new ArrayList<>();
		
		try {
			List<Actividad> actividades = actividadRepository.findActividadesTerminadasByEmpresa(id, new Date());
			
			for (Actividad actividad : actividades) {
				ModelMapper modelMapper = new ModelMapper();
				res.add(modelMapper.map(actividad, ActividadDto.class));
			}
			
			return res;
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
	}

	public ActividadDto findById(Long id) {
		ActividadDto actividadDto = new ActividadDto();
		try {
			Actividad actividad = actividadRepository.findById(id).orElse(null);
			ModelMapper modelMapper = new ModelMapper();
			actividadDto = modelMapper.map(actividad, ActividadDto.class);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		return actividadDto;
	}
	
	public void deleteActividad(Long id) {
		if (actividadRepository.findById(id).isPresent()) {
			actividadRepository.delete(actividadRepository.findById(id).orElse(null));
		}
	}

	public ActividadDto save(ActividadDto actividadDto) {
		ModelMapper modelMapper = new ModelMapper();
		Actividad actividad = modelMapper.map(actividadDto, Actividad.class);

		try {
			Actividad savedActividad = actividadRepository.save(actividad);
			
			if(actividadDto.getUsuario() != null) {
				UsuarioApuntado usuarioApuntado = new UsuarioApuntado();
				
				usuarioApuntado.setActividad(savedActividad);
				usuarioApuntado.setUsuario(savedActividad.getUsuario());
				
				UsuarioApuntadoDto uADto = modelMapper.map(usuarioApuntado, UsuarioApuntadoDto.class);
				
				usuarioApuntadoService.save(uADto);
			}
			
			return modelMapper.map(savedActividad, ActividadDto.class);

		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}

	}
	
	public ActividadDto cancelarActividad(Long id) {
		ActividadDto actividadDto = new ActividadDto();
		try {
			Actividad actividad = actividadRepository.findById(id).orElse(null);
			
			actividad.setCancelada(true);
			
			Actividad actividadSaved = actividadRepository.save(actividad);
			
			ModelMapper modelMapper = new ModelMapper();
			actividadDto = modelMapper.map(actividadSaved, ActividadDto.class);
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
		return actividadDto;
	}
	
	public Long countByActividad(String tipoActividad) {
		Long res = 0L;
		try {
			if(tipoActividad.equals(Constantes.ACTIVIDAD_ACTIVA)) {
				Date date = new Date();
				res = actividadRepository.countActividadesActivas(date);
			} else if (tipoActividad.equals(Constantes.ACTIVIDAD_TERMINADA)) {
				Date date = new Date();
				res = actividadRepository.countActividadesTerminadas(date);
			} else if (tipoActividad.equals(Constantes.ACTIVIDAD_CANCELADA)) {
				res = actividadRepository.countActividadesCanceladas();
			}
		} catch (Throwable e) {
			throw new IllegalArgumentException();
		}
		return res;
	}
	
	public Integer calcularGananciasTotales(Long id) {
		Integer res = 0;
		try {
			Actividad actividad = this.actividadRepository.findById(id).orElseGet(null);
			if(actividad != null) {
				List<UsuarioApuntadoDto> usuariosApuntados = this.usuarioApuntadoService.findByActividadId(id);
				res = usuariosApuntados.size() * actividad.getPrecio();
				
				ModelMapper modelMapper = new ModelMapper();
				ActividadDto actividadDto = modelMapper.map(actividad, ActividadDto.class);
				actividadDto.setGananciasTotales(res);
				
				EmpresaDto empresaDto = actividadDto.getEmpresa();
				
				if(empresaDto.getIngresos() == null) {
					empresaDto.setIngresos(res);
				} else {
					empresaDto.setIngresos(empresaDto.getIngresos() + res);
				}
				
				this.save(actividadDto);
				this.empresaService.save(empresaDto);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		
		return res;
	}
	
	public Integer gananciasEmpresaTotal(Long id) {
		Integer res = 0;
		try {
			EmpresaDto empresaDto = this.empresaService.findOne(id);
			res = empresaDto.getIngresos();
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		
		return res;
	}
	
	public Double getGananciasAdmin() {
		Double res = 0.;
		try {
			List<Integer> allGanancias = this.actividadRepository.getGananciasAdmin();
			for(Integer i: allGanancias) {
				if(i != null) {
					Double porcGanado = i*0.10;
					res += porcGanado;
				}
			}
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		return res;
	}
}
