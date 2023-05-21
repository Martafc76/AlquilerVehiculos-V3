package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class BorrarAlquiler extends Controlador {

	private VistaGrafica vistaGrafica = VistaGrafica.getInstancia();

	@FXML
	private Button btBorrar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btLimpiar;

	@FXML
	private DatePicker dpFechaAlquiler;

	@FXML
	private ImageView ivIconoCliente;

	@FXML
	private Label lbDniCliente;

	@FXML
	private Label lbFechaAlquiler;

	@FXML
	private Label lbMatricula;

	@FXML
	private TextField tfDni;

	@FXML
	private TextField tfMatricula;

	Cliente cliente;
	Vehiculo vehiculo;

	@FXML
	void borrar(ActionEvent event) {

		cliente = vistaGrafica.getControlador().buscar(Cliente.getClienteConDni(tfDni.getText()));
		vehiculo = vistaGrafica.getControlador().buscar(Vehiculo.getVehiculoConMatricula(tfMatricula.getText()));

		try {

			if (Dialogos.mostrarDialogoConfirmacion("Borrar alquiler", "¿Estás seguro de borrar este alquiler?",
					getEscenario())) 
			{
				vistaGrafica.getControlador().borrar(vistaGrafica.getControlador()
						.buscar(new Alquiler(cliente, vehiculo, dpFechaAlquiler.getValue())));

				Dialogos.mostrarDialogoInformacion("Alquiler borrado", "El alquiler ha sido borrado correctamente",
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

		Controles.limpiarCamposTexto(tfDni, tfMatricula);

	}

}
