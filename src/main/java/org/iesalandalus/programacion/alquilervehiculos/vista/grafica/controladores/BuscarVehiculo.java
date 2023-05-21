package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Autobus;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Furgoneta;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;
import org.iesalandalus.programacion.alquilervehiculos.vista.texto.TipoVehiculo;

import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class BuscarVehiculo extends Controlador {

	private VistaGrafica vistaGrafica = VistaGrafica.getInstancia();

	@FXML
	private Button btBuscar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btLimpiar;

	@FXML
	private Button btListar;

	@FXML
	private ImageView ivCoche;

	@FXML
	private Label lbMatricula;

	@FXML
	private TableColumn<Vehiculo, String> tcCilindrada;

	@FXML
	private TableColumn<Vehiculo, String> tcMarca;

	@FXML
	private TableColumn<Vehiculo, String> tcMatricula;

	@FXML
	private TableColumn<Vehiculo, String> tcModelo;

	@FXML
	private TableColumn<Vehiculo, String> tcPlazas;

	@FXML
	private TableColumn<Vehiculo, String> tcPma;

	@FXML
	private TextField tfmatricula;

	@FXML
	private TableView<Vehiculo> tvVehiculo;
	
	private String obtenerPma(Vehiculo vehiculo) {
        String pma = "*";
        if (vehiculo instanceof Furgoneta furgoneta) {
            pma = Integer.toString(furgoneta.getPma());
        }
        return pma;
    }

    private String obtenerPlazas(Vehiculo vehiculo) {
        String plazas = "*";
        if (vehiculo instanceof Furgoneta furgoneta) {
            plazas = Integer.toString(furgoneta.getPlazas());
        }
        if (vehiculo instanceof Autobus autobus) {
            plazas = Integer.toString(autobus.getPlazas());
        }
        return plazas;
    }

    private String obtenerCilindrada(Vehiculo vehiculo) {
        String cilindrada = "*";
        if (vehiculo instanceof Turismo turismo) {
            cilindrada = Integer.toString(turismo.getCilindrada());
        }
        return cilindrada;
    }

	@FXML
	void initialize() {

		tcCilindrada.setCellValueFactory(fila -> new SimpleObjectProperty<String>(obtenerCilindrada(fila.getValue())));
		tcMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		tcMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
		tcModelo.setCellValueFactory(new PropertyValueFactory<>("modelo"));
		tcPlazas.setCellValueFactory(fila -> new SimpleObjectProperty<String>(obtenerPlazas(fila.getValue())));
		tcPma.setCellValueFactory(fila -> new SimpleObjectProperty<String>(obtenerPma(fila.getValue())));

	}

	@FXML
	void buscar(ActionEvent event) {
		
		try {
			tvVehiculo.getItems().clear();

			String matricula = tfmatricula.getText();

			Vehiculo vehiculo = vistaGrafica.getControlador().buscar(Vehiculo.getVehiculoConMatricula(matricula));

			tvVehiculo.getItems().add(vehiculo);

		} catch (IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("Ha ocurrido un error", e.getMessage(), getEscenario());
		}
	}

	@FXML
	void cancelar(ActionEvent event) {
		getEscenario().close();

	}

	@FXML
	void limpiar(ActionEvent event) {
		Controles.limpiarCamposTexto(tfmatricula);

	}

	@FXML
	void listar(ActionEvent event) {

		limpiar(event);
		tvVehiculo.getItems().addAll(vistaGrafica.getControlador().getListaVehiculos());

	}

}
