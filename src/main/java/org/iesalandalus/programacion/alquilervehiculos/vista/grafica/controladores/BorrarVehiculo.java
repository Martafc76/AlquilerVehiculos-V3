package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
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

public class BorrarVehiculo extends Controlador {

	private VistaGrafica vistaGrafica = VistaGrafica.getInstancia();

	@FXML
	private Button btBorrar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btLimpiar;

	@FXML
	private ImageView ivBorrar;

	@FXML
	private Label lbMatricula;

	@FXML
	private TextField tfMatricula;

	@FXML
	void borrar(ActionEvent event) {
		try {
			if (Dialogos.mostrarDialogoConfirmacion("Borrar vehiculo", "¿Estás seguro de borrar este vehiculo?",
					getEscenario())) {
				vistaGrafica.getControlador().borrar(
						vistaGrafica.getControlador().buscar(Vehiculo.getVehiculoConMatricula(tfMatricula.getText())));
				Dialogos.mostrarDialogoInformacion("Vehiculo borrado", "El vehiculo ha sido borrado correctamente",
						getEscenario());
			}
			getEscenario().close();
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Ha ocurrido un error", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void cancelar(ActionEvent event) {

		getEscenario().close();

	}

	@FXML
	void limpiar(ActionEvent event) {
		Controles.limpiarCamposTexto(tfMatricula);

	}

}
