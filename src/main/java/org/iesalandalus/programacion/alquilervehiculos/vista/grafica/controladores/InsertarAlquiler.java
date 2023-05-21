package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class InsertarAlquiler extends Controlador {
	
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
	private Label lbFechaAlquiler;

	@FXML
	private Label lbMatricula;

	@FXML
	private TextField tfDni;

	@FXML
    private DatePicker dtFechaAlquiler;


	@FXML
	private TextField tfMatricula;
	
	private Cliente cliente;
	
	private Vehiculo vehiculo;

	@FXML
	void cancelar(ActionEvent event) {

		getEscenario().close();

	}
	

	@FXML
	void insertar(ActionEvent event) throws OperationNotSupportedException {
		
		try {
			cliente = Cliente.getClienteConDni(tfDni.getText());
			vehiculo = Vehiculo.getVehiculoConMatricula(tfMatricula.getText());
			vistaGrafica.getControlador().insertar(new Alquiler(cliente , vehiculo ,dtFechaAlquiler.getValue()));
			
			Dialogos.mostrarDialogoInformacion("Exito", "El alquiler ha sido insertado correctamente", getEscenario());
			getEscenario().close();
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Error", e.getMessage(), getEscenario());
			
		}
		

	}

	@FXML
	void limpiar(ActionEvent event) {

		Controles.limpiarCamposTexto(tfDni, tfMatricula);
		

	}

}
