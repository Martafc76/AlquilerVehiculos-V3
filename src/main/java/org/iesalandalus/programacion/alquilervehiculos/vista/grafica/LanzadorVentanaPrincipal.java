package org.iesalandalus.programacion.alquilervehiculos.vista.grafica;

import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.controladores.VentanaPrincipal;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.recursos.LocalizadorRecursos;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Controladores;
import org.iesalandalus.programacion.alquilervehiculos.vista.grafica.utilidades.Dialogos;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LanzadorVentanaPrincipal extends Application {
	
//	public void start(Stage escenarioPrincipal) {
//		try {
//		
//			FXMLLoader cargadorVentanaPrincipal = new FXMLLoader(LocalizadorRecursos.class.getResource("vistas/VentanaPrincipal.fxml"));
//			Parent raiz = cargadorVentanaPrincipal.load();
//			
//			Scene escena = new Scene(raiz);
//			escenarioPrincipal.setTitle("Vista Gráfica Alquiler de Vehiculos");
//			escenarioPrincipal.setScene(escena);
//			escenarioPrincipal.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//	}

	public static void comenzar() {
		launch(LanzadorVentanaPrincipal.class);
	}

	public void start(Stage escenarioPrincipal) throws Exception {
        try {
            VentanaPrincipal ventanaPrincipal = (VentanaPrincipal) Controladores.get("vistas/VentanaPrincipal.fxml","Vista gráfica", null );
            //Dialogos.setHojaEstilos(ventanaPrincipal.getEscenario().getScene().getRoot().getStylesheets().get(0));
//            ventanaPrincipal.getEscenario().setOnCloseRequest(e -> confirmarSalida(ventanaPrincipal.getEscenario(), e));
//            Image icono = new Image (LocalizadorRecursos.class.getResourceAsStream("Dibujos/Coche.png"));
//            ventanaPrincipal.getEscenario().getIcons().add(icono);
            ventanaPrincipal.getEscenario().show();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

private void confirmarSalida(Stage escenario, WindowEvent e) {
    if (Dialogos.mostrarDialogoConfirmacion("Salir", "¿Seguro que quieres salir de la aplicación?", escenario)) {
        escenario.close();
    }
    else {
        e.consume();
    }
}

}
