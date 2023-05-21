package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class InsertarCliente extends Controlador {

	private VistaGrafica vistaGrafica = VistaGrafica.getInstancia();

	@FXML
	private Button btCancelar;

	@FXML
	private Button btInsertar;

	@FXML
	private Button btLimpiar;

	@FXML
	private ImageView ivIconoCliente;

	@FXML
	private Label lbDniCliente;

	@FXML
	private Label lbNombreCliente;

	@FXML
	private Label lbNumCliente;

	@FXML
	private TextField tfDniCliente;

	@FXML
	private TextField tfNombreCliente;

	@FXML
	private TextField tfNumCliente;

	@FXML
	void cancelarInsertarCliente(ActionEvent event) {

		getEscenario().close();

	}

	@FXML
	void insertarCliente(ActionEvent event) {
		try {
			vistaGrafica.getControlador()
					.insertar(new Cliente(tfNombreCliente.getText(), tfDniCliente.getText(), tfNumCliente.getText()));
			Dialogos.mostrarDialogoInformacion("Exito", "El cliente ha sido insertado correctamente", getEscenario());
			getEscenario().close();
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Ha ocurrido un error", e.getMessage(), getEscenario());

		}

	}

	@FXML
	void limpiarInsertarCliente(ActionEvent event) {
		Controles.limpiarCamposTexto(tfNombreCliente, tfDniCliente, tfNumCliente);

	}

	@FXML
	void initialize() {
	}


}
