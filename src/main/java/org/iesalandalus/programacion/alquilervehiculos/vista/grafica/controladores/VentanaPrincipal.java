package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;

public class VentanaPrincipal extends Controlador {


	@FXML
	private Button btBorrarAlquiler;

	@FXML
	private Button btBorrarCliente;

	@FXML
	private Button btBorrarVehiculo;

	@FXML
	private Button btBuscarAlquiler;

	@FXML
	private Button btBuscarCliente;

	@FXML
	private Button btInsertarAlquiler;

	@FXML
	private Button btInsertarCliente;

	@FXML
	private Button btInsertarVehiculo;

	@FXML
	private Button buscarVehiculo;

	@FXML
	private Label lbTituloAlquiler;

	@FXML
	private Label lbTituloCliente;

	@FXML
	private Menu mEstadisticas;

	@FXML
	private Menu mbAcercaDe;

	@FXML
	private Menu mbSalir;

	@FXML
	void acercaDe(ActionEvent event) {
		AcercaDe acercaDe = (AcercaDe) Controladores.get("vistas/AcercaDe.fxml", "Acerca de...", getEscenario());
		acercaDe.getEscenario().showAndWait();

	}

	@FXML
	void estadisticas(ActionEvent event) {
		Estadisticas estadisticas = (Estadisticas) Controladores.get("vistas/Estadisticas.fxml", "Estadisticas", getEscenario());
		estadisticas.getEscenario().showAndWait();
	}

	@FXML
	void borrarAlquiler(ActionEvent event) {
		BorrarAlquiler borrarAlquiler = (BorrarAlquiler) Controladores.get("vistas/BorrarAlquiler.fxml",
				"Borrar alquiler existente", getEscenario());
		borrarAlquiler.getEscenario().showAndWait();

	}

	@FXML
	void borrarCliente(ActionEvent event) {
		BorrarCliente borrarCliente = (BorrarCliente) Controladores.get("vistas/BorrarCliente.fxml",
				"Borrar cliente existente", getEscenario());
		borrarCliente.getEscenario().showAndWait();
	}

	@FXML
	void borrarVehiculo(ActionEvent event) {
		BorrarVehiculo borrarVehiculo = (BorrarVehiculo) Controladores.get("vistas/BorrarVehiculo.fxml",
				"Borrar vehiculo existente", getEscenario());
		borrarVehiculo.getEscenario().showAndWait();

	}

	@FXML
	void buscarAlquiler(ActionEvent event) {
		BuscarAlquiler buscarAlquiler = (BuscarAlquiler) Controladores.get("vistas/BuscarAlquiler.fxml",
				"Buscar alquiler", getEscenario());
		buscarAlquiler.getEscenario().showAndWait();
	}

	@FXML
	void buscarCliente(ActionEvent event) {
		BuscarCliente buscarCliente = (BuscarCliente) Controladores.get("vistas/BuscarCliente.fxml", "Buscar cliente",
				getEscenario());
		buscarCliente.getEscenario().showAndWait();

	}

	@FXML
	void buscarVehiculo(ActionEvent event) {
		BuscarVehiculo buscarVehiculo = (BuscarVehiculo) Controladores.get("vistas/BuscarVehiculo.fxml",
				"Buscar vehiculo", getEscenario());
		buscarVehiculo.getEscenario().showAndWait();

	}

	@FXML
	void insertarAlquiler(ActionEvent event) {
		InsertarAlquiler insertarAlquiler = (InsertarAlquiler) Controladores.get("vistas/InsertarAlquiler.fxml",
				"Insertar alquiler nuevo", getEscenario());
		insertarAlquiler.getEscenario().showAndWait();

	}

	@FXML
	void insertarCliente(ActionEvent event) {
		InsertarCliente insertarCliente = (InsertarCliente) Controladores.get("vistas/InsertarCliente.fxml",
				"Insertar cliente nuevo", getEscenario());
		insertarCliente.getEscenario().showAndWait();
	}

	@FXML
	void insertarVehiculo(ActionEvent event) {
		InsertarVehiculo insertarVehiculo = (InsertarVehiculo) Controladores.get("vistas/InsertarVehiculo.fxml",
				"Insertar vehiculo nuevo", getEscenario());
		insertarVehiculo.getEscenario().showAndWait();

	}

	@FXML
	void salir(ActionEvent event) {
		Dialogos.mostrarDialogoConfirmacion("Salir de la aplicación", "¿Está seguro de que desea salir?",
				getEscenario());
		getEscenario().close();
	}

}