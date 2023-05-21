package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;
import java.util.EnumMap;
import java.util.Map;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.Consola;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVehiculo;
import org.iesalandalus.programacion.utilidades.Entrada;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class Estadisticas extends Controlador {

	private VistaGrafica vistaGrafica = VistaGrafica.getInstancia();
	
	@FXML
    private Button btBuscar;

	@FXML
	private Button btSalir;

	@FXML
	private DatePicker dpFecha;

	@FXML
	private ImageView imEstadisticas;

	@FXML
	private ListView<String> lvEstadisticas;
	
	@FXML
    void buscar(ActionEvent event) {
		mostrarEstadisticasMensualesTipoVehiculo();
    }

	@FXML
	void salir(ActionEvent event) {
		getEscenario().close();
	}

	private Map<TipoVehiculo, Integer> inicializarEstadisticas() {
		Map<TipoVehiculo, Integer> estadisticas = new EnumMap<>(TipoVehiculo.class);
		for (int i = 0; i < TipoVehiculo.values().length; i++) {
			estadisticas.put(TipoVehiculo.get(i), 0);
		}
		return estadisticas;
	}

	public void mostrarEstadisticasMensualesTipoVehiculo() {

		lvEstadisticas.getItems().clear();
		Map<TipoVehiculo, Integer> estadisticas = inicializarEstadisticas();
		try {
			LocalDate fecha = dpFecha.getValue();
			for (Alquiler alquiler : vistaGrafica.getControlador().getListaAlquileres()) {
				if (alquiler.getFechaAlquiler().getMonth().equals(fecha.getMonth())
						&& alquiler.getFechaAlquiler().getYear() == fecha.getYear()) {
					estadisticas.put(TipoVehiculo.get(alquiler.getVehiculo()),
							estadisticas.get(TipoVehiculo.get(alquiler.getVehiculo())) + 1);
				}
			}
		} catch (IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("ERROR", "La fecha no es válida", getEscenario());
		}

		if (estadisticas == null) {
			Dialogos.mostrarDialogoError("ERROR",
					"La fecha no es válida", getEscenario());
		} else {
			for (Map.Entry<TipoVehiculo, Integer> entrada : estadisticas.entrySet()) {
				lvEstadisticas.getItems().add(entrada.toString());
			}
		}

	}
	
	
}
