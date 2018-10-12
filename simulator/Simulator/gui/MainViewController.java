package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import interfaces.AbstractComponent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.LoadException;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import vgu.consumer.ConsumerFactory;
import vgu.control.Control;
import vgu.generator.GeneratorFactory;

public class MainViewController implements Initializable {
	
	private Control control;
	
	@FXML
	private MenuBar menuBar;
	
	@FXML
	private TextField consumerSize;
	@FXML
	private TextField consumerAvgPower;
	@FXML
	private TextField consumerStd;
	
	@FXML
	private TextField generatorSize;
	@FXML
	private TextField generatorTotalPower;
	@FXML
	private TextField generatorStartPower;
	
	@FXML
	private Button submitConsumer;
	
	@FXML
	private Button submitGenerator;
	
	@FXML
	private Button resultButton;
	
	@FXML
    void onConsumersViewClicked(ActionEvent event) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsumersView.fxml"));
			Parent consumersViewParent = loader.load();
    	
			Scene consumersViewScene = new Scene(consumersViewParent);
			Stage stage = (Stage) menuBar.getScene().getWindow();
			stage.setScene(consumersViewScene);
			stage.setTitle("Consumers");
			stage.show();
		}
    	catch (LoadException e) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setContentText("Please input data first");
    		alert.showAndWait();
    	}
    
	}

    @FXML
    void onGeneratorsViewClicked(ActionEvent event) throws IOException {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("GeneratorsView.fxml"));
    		Parent generatorsViewParent = loader.load();
    	
    		Scene generatorsViewScene = new Scene(generatorsViewParent);
    		Stage stage = (Stage) menuBar.getScene().getWindow();
    		stage.setScene(generatorsViewScene);
    		stage.setTitle("Generators");
    		stage.show();
    	}
    	catch (LoadException e) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setContentText("Please input data first");
    		alert.showAndWait();
    	}
    }
    
    @FXML
    public void onSubmitConsumerClicked(ActionEvent event) throws IOException {
    	try {
    		Integer amount = Integer.parseInt(consumerSize.getText().trim());
    		Integer avgPower = Integer.parseInt(consumerAvgPower.getText());
    		Integer std = Integer.parseInt(consumerStd.getText());
    	
    		List<AbstractComponent> consumers = ConsumerFactory.generate(amount, avgPower, std);
    		DataUtils.addConsumers(control, consumers);
    		DataUtils.generateConsumers(control.getConsumers());
    	
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsumersView.fxml"));
    		Parent consumersViewParent = loader.load();
			Scene consumersViewScene = new Scene(consumersViewParent);
		
			Stage stage = (Stage) menuBar.getScene().getWindow();
			stage.setScene(consumersViewScene);
			stage.setTitle("Consumers");
			stage.show();
    	} 
    	catch (RuntimeException e) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setContentText("Please fill all parameters");
    		alert.showAndWait();
    	}
    }
    
    @FXML
    public void onSubmitGeneratorClicked(ActionEvent event) throws IOException {
    	try {
    		Integer amount = Integer.parseInt(generatorSize.getText().trim());
    		Double totalPower = Double.parseDouble(generatorTotalPower.getText());
    		Double startPower = Double.parseDouble(generatorStartPower.getText());
    	
    		List<AbstractComponent> generators = GeneratorFactory.generate(amount, totalPower, startPower);
    		DataUtils.addGenerators(control, generators);
    		DataUtils.generateGenerators(control.getGenerators());
    	
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("GeneratorsView.fxml"));
    		Parent generatorsViewParent = loader.load();
    		Scene generatorsViewScene = new Scene(generatorsViewParent);
		
    		Stage stage = (Stage) menuBar.getScene().getWindow();
    		stage.setScene(generatorsViewScene);
    		stage.setTitle("Generators");
    		stage.show();
    	} 
    	catch (RuntimeException e) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setContentText("Please fill all parameters");
    		alert.showAndWait();
    	}
    }
    
    @FXML
    public void onResultButtonClicked(ActionEvent event) throws IOException {
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("StatisticsView.fxml"));
    		Parent statisticsViewParent = loader.load();
    		Scene statisticsViewScene = new Scene(statisticsViewParent);
		
    		Stage stage = new Stage();
    		stage.initModality(Modality.APPLICATION_MODAL);
    		stage.setScene(statisticsViewScene);
    		stage.setTitle("Statistics");
    		stage.showAndWait();
    	}
    	catch (LoadException e) {
    		Alert alert = new Alert(AlertType.WARNING);
    		alert.setTitle("Warning");
    		alert.setContentText("Please input data first");
    		alert.showAndWait();
    	}
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		control = new Control();
	}

}
