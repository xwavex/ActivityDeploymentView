package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	public Stage primaryStage;

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		mainWindow();
	}

	public void mainWindow() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("MainWindowView.fxml"));
			AnchorPane pane = loader.load();

			FXMLLoader loaderCore = new FXMLLoader(Main.class.getResource("CoreView.fxml"));
			AnchorPane paneCore = loaderCore.load();

			Scene scene = new Scene(pane);
			Scene sceneCore = new Scene(paneCore);

			MainWindowController controller = loader.getController();
			controller.setMain(this);

			CoreController controllerCore = loaderCore.getController();
			controllerCore.setMain(this);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			sceneCore.getStylesheets().add(getClass().getResource("application.css").toExternalForm());


//			primaryStage.setScene(scene);
			primaryStage.setScene(sceneCore);
			primaryStage.show();


			controller.initializeListView();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
