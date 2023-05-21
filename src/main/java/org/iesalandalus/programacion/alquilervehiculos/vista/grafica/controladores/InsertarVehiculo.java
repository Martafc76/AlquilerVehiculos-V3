package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVehiculo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InsertarVehiculo extends Controlador {

	private VistaGrafica vistaGrafica = VistaGrafica.getInstancia();

	@FXML
	private Button btCancelar;

	@FXML
	private Button btInsertar;

	@FXML
	private Button btLimpiar;

	@FXML
	private ChoiceBox<TipoVehiculo> cbTipos;

	@FXML
	private Label lbCilindrada;

	@FXML
	private Label lbMatricula;

	@FXML
	private Label lbModelo;

	@FXML
	private Label lbPlazas;

	@FXML
	private Label lbPma;

	@FXML
	private Label lbmarca;

	@FXML
	private TextField tfCilindrada;

	@FXML
	private TextField tfMarca;

	@FXML
	private TextField tfPlazas;

	@FXML
	private TextField tfPma;

	@FXML
	private TextField tfmatricula;

	@FXML
	private TextField tfmodelo;

	@FXML
	private void initialize() {
		cbTipos.getItems().addAll(TipoVehiculo.values());
		cbTipos.setValue(TipoVehiculo.TURISMO);
		cbTipos.getSelectionModel().selectedItemProperty().addListener((ob, ov, nv) -> verTipo(nv));
	}

	private void verTipo(TipoVehiculo nv) {
		if (nv.equals(TipoVehiculo.TURISMO)) {
			Controles.deshabilitarCamposTexto(tfPlazas, tfPma);
			Controles.habilitarCamposTexto(tfCilindrada);
		} else if (nv.equals(TipoVehiculo.AUTOBUS)) {
			Controles.deshabilitarCamposTexto(tfPma, tfCilindrada);
			Controles.habilitarCamposTexto(tfPlazas);
		} else {
			Controles.deshabilitarCamposTexto(tfCilindrada);
			Controles.habilitarCamposTexto(tfPlazas, tfPma);
		}

	}

	@FXML
	void insertar(ActionEvent event) {

		TipoVehiculo tipoVehiculo = cbTipos.getValue(); // Obtener el tipo de vehículo seleccionado
		int plazas = 0;
		// Crea el objeto de vehículo según el tipo seleccionado
		Vehiculo vehiculo;
		try {
			switch (tipoVehiculo) {

			case TURISMO:
				int cilindrada = Integer.parseInt(tfCilindrada.getText()); // Obtiene la cilindrada del campo de texto
				vehiculo = new Turismo(tfMarca.getText(), tfmodelo.getText(), cilindrada, tfmatricula.getText());
				break;
			case FURGONETA:
				plazas = Integer.parseInt(tfPlazas.getText());
				int pma = Integer.parseInt(tfPma.getText());
				vehiculo = new Furgoneta(tfMarca.getText(), tfmodelo.getText(), pma, plazas, tfmatricula.getText());
				break;
			case AUTOBUS:
				plazas = Integer.parseInt(tfPlazas.getText());
				vehiculo = new Autobus(tfMarca.getText(), tfmodelo.getText(), plazas, tfmatricula.getText());
				break;
			default:
				throw new IllegalArgumentException("Tipo de vehículo no válido");
			}

			// Insertar el vehículo en la base de datos o realizar la acción correspondiente

			vistaGrafica.getControlador().insertar(vehiculo);
			Dialogos.mostrarDialogoInformacion("Exito", "El vehículo ha sido insertado correctamente", getEscenario());
			getEscenario().close();
		} catch (NumberFormatException e) {
			Dialogos.mostrarDialogoError("Ha ocurrido un error", "El formato del número no es válido", getEscenario());
		} catch (NullPointerException | IllegalArgumentException |  OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Ha ocurrido un error", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void cancelar(ActionEvent event) {
		getEscenario().close();

	}

	@FXML
	void limpiar(ActionEvent event) {
		Controles.limpiarCamposTexto(tfCilindrada, tfMarca, tfPlazas, tfPma, tfmatricula, tfmodelo);

	}

}
