package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import java.time.LocalDate;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles.FormateadorCeldaFecha;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class BuscarAlquiler extends Controlador {

	private VistaGrafica vistaGrafica = VistaGrafica.getInstancia();

	@FXML
	private GridPane lbFechaDevolucion;

	@FXML
	private TableColumn<Alquiler, String> tcPrecio;

	@FXML
	private Button btBuscar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btDevolver;

	@FXML
	private Button btLimpiar;

	@FXML
	private Button btlistar;

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private DatePicker dpFechaDevolucion;

	@FXML
	private ImageView ivIconoCliente;

	@FXML
	private Label lbDniCliente;

	@FXML
	private Label lbFechaAlquiler;

	@FXML
	private Label lbMatricula;

	@FXML
	private TableColumn<Alquiler, String> tcDni;

	@FXML
	private TableColumn<Alquiler, LocalDate> tcFechaAlquiler;

	@FXML
	private TableColumn<Alquiler, LocalDate> tcFechaDevolucion;

	@FXML
	private TableColumn<Alquiler, String> tcMatricula;

	@FXML
	private TextField tfDniCliente;

	@FXML
	private TextField tfMatricula;

	private Cliente cliente;
	private Vehiculo vehiculo;

	@FXML
	private TableView<Alquiler> tvAlquiler;

	private String obtenerPrecio(int precio) {
		String precioADevolver = "*";
		if (precio > 0) {
			precioADevolver = Integer.toString(precio);
		}
		return precioADevolver;
	}

	@FXML
	void initialize() {
		tcPrecio.setCellValueFactory(
				fila -> new SimpleObjectProperty<String>(obtenerPrecio(fila.getValue().getPrecio())));
		tcFechaAlquiler.setCellFactory(celda -> new FormateadorCeldaFecha());
		tcFechaAlquiler.setCellValueFactory(new PropertyValueFactory<>("fechaAlquiler"));
		tcDni.setCellValueFactory(fila -> new SimpleObjectProperty<String>(fila.getValue().getCliente().getDni()));
		tcMatricula.setCellValueFactory(
				fila -> new SimpleObjectProperty<String>(fila.getValue().getVehiculo().getMatricula()));
		tcFechaDevolucion.setCellFactory(celda -> new FormateadorCeldaFecha());
		tcFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDevolucion"));
	}

	@FXML
	void buscar(ActionEvent event) {
		try {
			if (tfDniCliente.getText().isBlank() && !tfMatricula.getText().isBlank()) {
				vehiculo = vistaGrafica.getControlador()
						.buscar(Vehiculo.getVehiculoConMatricula(tfMatricula.getText()));
				tvAlquiler.setItems(
						FXCollections.observableList(vistaGrafica.getControlador().getListaAlquileres(vehiculo)));

			} else if (!tfDniCliente.getText().isBlank() && tfMatricula.getText().isBlank()) {
				cliente = vistaGrafica.getControlador().buscar(Cliente.getClienteConDni(tfDniCliente.getText()));
				tvAlquiler.setItems(
						FXCollections.observableList(vistaGrafica.getControlador().getListaAlquileres(cliente)));
			} else if (!tfDniCliente.getText().isBlank() && !tfMatricula.getText().isBlank()
					&& dpFechaAlquiler.getValue() != null) {
				tvAlquiler.getItems().clear();
				cliente = vistaGrafica.getControlador().buscar(Cliente.getClienteConDni(tfDniCliente.getText()));
				vehiculo = vistaGrafica.getControlador()
						.buscar(Vehiculo.getVehiculoConMatricula(tfMatricula.getText()));
				LocalDate fechaAlquiler = dpFechaAlquiler.getValue();
				Alquiler alquiler = new Alquiler(cliente, vehiculo, fechaAlquiler);
				tvAlquiler.getItems().add(vistaGrafica.getControlador().buscar(alquiler));
				dpFechaDevolucion.setDisable(false);
				btDevolver.setDisable(false);

			} else {
				
				Dialogos.mostrarDialogoError("ERROR", "Ha ocurrido un error al buscar", getEscenario());
			}

		} catch (Exception e) {
			e.getStackTrace();
			Dialogos.mostrarDialogoError("ERROR", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void cancelar(ActionEvent event) {
		getEscenario().close();
	}

	@FXML
	void devolver(ActionEvent event) {
		LocalDate fechaDevolucion = dpFechaDevolucion.getValue();
		try {
			vistaGrafica.getControlador().devolver(cliente, fechaDevolucion);
			limpiar(event);
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Ha ocurrido un error", e.getMessage(), getEscenario());
		}
		
	}

	@FXML
	void limpiar(ActionEvent event) {
		tvAlquiler.getItems().clear();
		dpFechaDevolucion.setDisable(true);
		btDevolver.setDisable(true);
		Controles.limpiarCamposTexto(tfDniCliente, tfMatricula);
		tvAlquiler.refresh();
	}

	@FXML
	void listar(ActionEvent event) {
		tvAlquiler.setItems(FXCollections.observableList(vistaGrafica.getControlador().getListaAlquileres()));
	}

}
