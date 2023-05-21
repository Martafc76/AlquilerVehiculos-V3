package org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.VistaGrafica;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controles;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

public class BuscarCliente extends Controlador {

	private VistaGrafica vistaGrafica = VistaGrafica.getInstancia();

	@FXML
	private Button btBuscar;

	@FXML
	private Button btCancelar;

	@FXML
	private Button btLimpiar;

	@FXML
	private Button btlistar;

	@FXML
	private Button btModificarCliente;

	@FXML
	private ImageView ivIconoCliente;

	@FXML
	private Label lbDniCliente;

	@FXML
	private TableColumn<Cliente, String> tcDni;

	@FXML
	private TableColumn<Cliente, String> tcNombre;

	@FXML
	private TableColumn<Cliente, String> tcTelefono;

	@FXML
	private TextField tfDniCliente;

	@FXML
	private TextField tfNombre;

	@FXML
	private TextField tfTelefono;

	@FXML
	private TableView<Cliente> tvCliente;

	@FXML
	void initialize() {
		tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tcDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
		tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
	}

	private void refrescarModificacion() {
		tvCliente.getItems().clear();
		tvCliente.getItems()
				.add(vistaGrafica.getControlador().buscar((Cliente.getClienteConDni(tfDniCliente.getText()))));
	}

	@FXML
	void modificarCliente(ActionEvent event) {
		try {
			vistaGrafica.getControlador().modificar(tvCliente.getItems().get(0), tfNombre.getText(),
					tfTelefono.getText());
			refrescarModificacion();
		} catch (NullPointerException | IllegalArgumentException | OperationNotSupportedException e) {
			Dialogos.mostrarDialogoError("Ha ocurrido un error", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void buscarCliente(ActionEvent event) {
		try {
			tvCliente.getItems().clear();
			tvCliente.getItems()
					.add(vistaGrafica.getControlador().buscar((Cliente.getClienteConDni(tfDniCliente.getText()))));
			Cliente cliente = tvCliente.getItems().get(0);
			tfNombre.setDisable(false);
			tfTelefono.setDisable(false);
			btModificarCliente.setDisable(false);
			tfNombre.setText(cliente.getNombre());
			tfTelefono.setText(cliente.getTelefono());

		} catch (NullPointerException | IllegalArgumentException e) {
			Dialogos.mostrarDialogoError("Ha ocurrido un error", e.getMessage(), getEscenario());
		}

	}

	@FXML
	void listar(ActionEvent event) {
		limpiar(event);
		tvCliente.getItems().addAll(vistaGrafica.getControlador().getListaClientes());
		
	}

	@FXML
	void cancelar(ActionEvent event) {
		getEscenario().close();

	}

	@FXML
	void limpiar(ActionEvent event) {
		Controles.limpiarCamposTexto(tfDniCliente, tfNombre, tfTelefono);
		tvCliente.getItems().clear();
		tfNombre.setDisable(true);
		tfTelefono.setDisable(true);
		btModificarCliente.setDisable(true);

	}

}
