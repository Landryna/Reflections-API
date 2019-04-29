package program;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;




public class Main extends Application {
	private Pane pane;
	private Scene scene;

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	
		paneInjection();
		scene.getStylesheets().add(getClass().getClassLoader().getResource("FXML/viper.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();
		
		
	
	}
	
	private void paneInjection()
	{
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/Pane.fxml"));
		try {
			pane = loader.load();
			scene = new Scene(pane);
				
		} catch (IOException e) {
			System.out.println("Unable to load..");
		}
	}
	
	

}



