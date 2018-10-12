package test;
import static org.loadui.testfx.GuiTest.find; 
import static org.junit.jupiter.api.Assertions.*;
//import static org.hamcrest.CoreMatchers.*;
import javafx.scene.input.KeyCode; 
import javafx.stage.Stage; 
import org.junit.After; 
import static org.testfx.api.FxAssert.verifyThat; 
import static org.junit.Assert.assertTrue; 
import static org.loadui.testfx.Assertions.assertNodeExists; 
import static org.testfx.matcher.base.NodeMatchers.*;

import java.io.IOException;
import java.util.List;

import org.loadui.testfx.GuiTest;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;

import gui.DataUtils;
import gui.Main;
import interfaces.AbstractComponent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;

public class testGUI extends ApplicationTest   {
	@Override
	public void start (Stage stage) throws Exception {
	  Parent mainNode = FXMLLoader.load(Main.class.getResource("MainView.fxml"));
	  stage.setScene(new Scene(mainNode));
	  stage.show();
	  stage.toFront();
	}
	
	@Before
	public void setUp () throws Exception {
	}

	@After
	public void tearDown () throws Exception {
	  FxToolkit.hideStage();
	  release(new KeyCode[]{});
	  release(new MouseButton[]{});
	}

	@Test
	public void testAddConsumerclicknon() {
		Button button = (Button)GuiTest.find("#submitConsumer");
		verifyThat(button,isVisible());
		verifyThat(button,isEnabled());
		clickOn("#submitConsumer");
		assertNodeExists(".alert");
	}
	@Test
	public void testAddGeneratorclicknon() {
		Button button2 = (Button)GuiTest.find("#submitGenerator");
		verifyThat(button2,isVisible());
		verifyThat(button2,isEnabled());
		clickOn("#submitGenerator");
		assertNodeExists(".alert");
	}
	/*@Test
	public void testAddGeneratorclicknon() {
		assertThrows(RuntimeException.class,
	            ()->{
	            	clickOn("#submitGenerator");
	            });
	}
	/*@Test
	public void testResultClicknon() {
		assertThrows(LoadException.class,
	            ()->{
	            	clickOn("resultButton");
	            });
	}*/
	@Test
	public void testIteration() {
		Button button2 = (Button)GuiTest.find("#resultButton");
		verifyThat(button2,isVisible());
		verifyThat(button2,isEnabled());
		clickOn("#resultButton");
		Button button3 = (Button)GuiTest.find("#nextButton");
		verifyThat(button3,isVisible());
		verifyThat(button3,isEnabled());
		clickOn("#nextButton");
		Button button4 = (Button)GuiTest.find("#showChartButton");
		verifyThat(button4,isVisible());
		verifyThat(button4,isEnabled());
		clickOn("#showChartButton");
	}
	@Test
	public void consumerbuttontest() {
		Button button1 = (Button)GuiTest.find("#submitConsumer");
		assertTrue(button1.isVisible());
		verifyThat(button1,isEnabled());
		verifyThat("#consumerSize",isVisible());
		verifyThat("#consumerSize",isEnabled());
		clickOn("#consumerSize");
		write("50");
		verifyThat("#consumerAvgPower",isVisible());
		verifyThat("#consumerAvgPower",isEnabled());
		clickOn("#consumerAvgPower");
		write("100");
		verifyThat("#consumerStd",isVisible());
		verifyThat("#consumerStd",isEnabled());
		clickOn("#consumerStd");
		write("0");
		clickOn("#submitConsumer");
		
		TableView<AbstractComponent> table = find("#table");
		/*try {
			List<AbstractComponent> consumers = DataUtils.getConsumersFromCSV("consumers.csv");
			table.getItems().addAll(consumers);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		for(int i=0; i<table.getItems().size() ;i++) {
			assertEquals(table.getItems().get(i).getMaxPower(),100);
			assertEquals(table.getItems().get(i).getMinPower(),0);
			assertEquals(table.getItems().get(i).getMaxChange(),100);
			assertEquals(table.getItems().get(i).getMinChange(),0);
		}
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsumersView.fxml"));
		assertNotNull(loader);
		Button button3 = (Button)GuiTest.find("#addButton");
		verifyThat(button3,isVisible());
		verifyThat(button3,isEnabled());
		Button button4 = (Button)GuiTest.find("#deleteButton");
		verifyThat(button4,isVisible());
		verifyThat(button4,isEnabled());
		Button button5 = (Button)GuiTest.find("#saveButton");
		verifyThat(button5,isVisible());	
		verifyThat(button5,isEnabled());
		//clickOn("#saveButton");
	}
	@Test
	public void generatorbuttontest() {
		Button button2 = (Button)GuiTest.find("#submitGenerator");
		assertTrue(button2.isVisible());
		verifyThat(button2,isEnabled());
		verifyThat("#generatorSize",isVisible());
		verifyThat("#generatorSize",isEnabled());		
		clickOn("#generatorSize");
		write("100");
		verifyThat("#generatorStartPower",isVisible());
		verifyThat("#generatorStartPower",isEnabled());
		clickOn("#generatorTotalPower");
		write("5000");
		verifyThat("#generatorStartPower",isVisible());
		verifyThat("#generatorStartPower",isEnabled());
		clickOn("#generatorStartPower");
		write("100");
		clickOn("#submitGenerator");
		
		
		TableView<AbstractComponent> table2 = find("#table");
		/*try {
			List<AbstractComponent> generators = DataUtils.getConsumersFromCSV("generators.csv");
			table2.getItems().addAll(generators);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i=0;i<table2.getItems().size();i++) {
			System.out.println(i+" "+table2.getItems().get(i).getMaxPower());
		}*/
		assertEquals(table2.getItems().get(0).getMaxPower(),1250.0);
		assertEquals(table2.getItems().get(0).getMinPower(),0);
		assertEquals(table2.getItems().get(0).getMaxChange(),625.0);
		assertEquals(table2.getItems().get(0).getMinChange(),625.0);
		for(int i=1; i<table2.getItems().size();i++) {
			assertEquals(table2.getItems().get(i).getMaxPower(),25.510204081632654);
			assertEquals(table2.getItems().get(i).getMinPower(),2.5510204081632653);
			assertEquals(table2.getItems().get(i).getMaxChange(),19.13265306122449);
			assertEquals(table2.getItems().get(i).getMinChange(),2.5510204081632653);
		}
		/*assertEquals(table2.getItems().get(table2.getItems().size()/2).getMaxPower(),1250.0);
		assertEquals(table2.getItems().get(table2.getItems().size()/2).getMinPower(),0);
		assertEquals(table2.getItems().get(table2.getItems().size()/2).getMaxChange(),625.0);
		assertEquals(table2.getItems().get(table2.getItems().size()/2).getMinChange(),625.0);
		
		for(int i=(table2.getItems().size()/2)+1; i<table2.getItems().size() ;i++) {
			assertEquals(table2.getItems().get(i).getMaxPower(),25.510204081632654);
			assertEquals(table2.getItems().get(i).getMinPower(),2.5510204081632653);
			assertEquals(table2.getItems().get(i).getMaxChange(),19.13265306122449);
			assertEquals(table2.getItems().get(i).getMinChange(),2.5510204081632653);
		}*/
		Button button6 = (Button)GuiTest.find("#addButton");
		verifyThat(button6, isVisible());
		verifyThat(button6,isEnabled());
		Button button7 = (Button)GuiTest.find("#deleteButton");
		verifyThat(button7,isVisible());	
		verifyThat(button7,isEnabled());
		Button button8 = (Button)GuiTest.find("#saveButton");
		verifyThat(button8,isVisible());
		verifyThat(button8,isEnabled());
	}
}
