package controler;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Controller {

	
	public static boolean checkIfClassExist;
	Object obj;
	Class<?> cls;
	Field[] field;
	Method method;
	public ArrayList<Method> methodsGet;
	public ArrayList<Method> methodsSet;
	public ArrayList<TextField> valuesTextFieldArray;
	public ArrayList<TextArea> valuesTextAreaArray;
	public ArrayList<String> checkType;
	public ArrayList<String> showingVariable;
	/// values of textfields and textarea
	private TextField valuesText;
	private TextField classText;
	private TextArea valueText;
	/// getting methods data
	private String nameOfMethod;
	private String returnType;
	private String[] values;

	// validating inputs and input order
	private volatile boolean textInClass = false;
	private int stringIterator = 0;
	private volatile boolean classCreated = false;

	@FXML
	private TextField classSearch;
	@FXML
	private Button createObject;
	@FXML
	private Button saveObject;
	@FXML
	private HBox HBox;
	@FXML
	private TextArea changesDisplay;
	@FXML
	private ScrollPane scrollPane;

	



	@FXML
	void pressedCreateObject(ActionEvent event) {

		createClass();

	}

	@FXML
	void pressedSaveObject(ActionEvent event) throws IllegalAccessException {
		values = new String[100];
		stringIterator = 0;
		if (classCreated == true) {
			changesDisplay.appendText("\n__________________________CHANGES___________________________\n");
			valuesTextFieldArray.stream().forEach(n -> {
				values[stringIterator] = n.getText();
				try {
					validateType();

					stringIterator++;

				} catch (IllegalArgumentException e) {
					System.out.println(e);
					stringIterator++;
				}

			});
			if (textInClass == true) {
				String textArea = valueText.getText();
				changesDisplay.appendText(textArea);
			}
		}
	}

	public void validateType() {
		String type = checkType.get(stringIterator);

		switch (type) {
		case "S":
			try {
				methodsSet.get(stringIterator).invoke(obj, values[stringIterator].toString());
				String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
				changesDisplay.appendText(x+"\n");
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				String x = "Invalid data input for: "  + showingVariable.get(stringIterator);
				changesDisplay.appendText(x+"\n");
			}
			break;
		case "I":
			try {
				
				if (values[stringIterator].equals("null")) {
					methodsSet.get(stringIterator).invoke(obj, (Object) null);
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				} else {
					methodsSet.get(stringIterator).invoke(obj, Integer.parseInt(values[stringIterator]));
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
			}

			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				String x = "Invalid data input for: "  + showingVariable.get(stringIterator);
				changesDisplay.appendText(x+"\n");
			}
			break;
		case "F":
			try {
				if (values[stringIterator].equals("null")) {
			

					methodsSet.get(stringIterator).invoke(obj, (Object) null);
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}

				else {
					methodsSet.get(stringIterator).invoke(obj, Float.parseFloat(values[stringIterator]));
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				String x = "Invalid data input for: "  + showingVariable.get(stringIterator);
				changesDisplay.appendText(x+"\n");
			}
			break;
		case "D":
			try {

				if (values[stringIterator].equals("null")) {

					methodsSet.get(stringIterator).invoke(obj, (Object) null);
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}

				else {

					methodsSet.get(stringIterator).invoke(obj, Double.parseDouble(values[stringIterator]));
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				String x = "Invalid data input for: "  + showingVariable.get(stringIterator);
				changesDisplay.appendText(x+"\n");
			}
			break;
		case "L":
			try {

				if (values[stringIterator].equals("null")) {
					

					methodsSet.get(stringIterator).invoke(obj, (Object) null);
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
				else

				{

					methodsSet.get(stringIterator).invoke(obj, (long)Long.parseLong(values[stringIterator]));
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				String x = "Invalid data input for: "  + showingVariable.get(stringIterator);
				changesDisplay.appendText(x+"\n");
			}
			break;
		case "C":
			try {

				if (values[stringIterator].equals("null")) {
					

					methodsSet.get(stringIterator).invoke(obj, (Object) null);
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
				
				else
				{

					methodsSet.get(stringIterator).invoke(obj, (char)Integer.parseInt(values[stringIterator]));
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				String x = "Invalid data input for: "  + showingVariable.get(stringIterator);
				changesDisplay.appendText(x+"\n");
			}
			break;
		case "By":
			try {

				if (values[stringIterator].equals("null")) {
					

					methodsSet.get(stringIterator).invoke(obj, (Object) null);
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
				else
				{

					methodsSet.get(stringIterator).invoke(obj, Byte.parseByte(values[stringIterator]));
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				String x = "Invalid data input for: "  + showingVariable.get(stringIterator);
				changesDisplay.appendText(x+"\n");
			}
			break;
		case "Sh":
			try {

				if (values[stringIterator].equals("null")) {
					

					methodsSet.get(stringIterator).invoke(obj, (Object) null);
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
				else

				{

					methodsSet.get(stringIterator).invoke(obj, Short.parseShort(values[stringIterator]));
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				String x = "Invalid data input for: "  + showingVariable.get(stringIterator);
				changesDisplay.appendText(x+"\n");
			}
			break;
		case "B":
			try {

				if (values[stringIterator].equals("null")) {
					methodsSet.get(stringIterator).invoke(obj, (Object) null);
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
				
				else if  (values[stringIterator].equals("true")) {
					methodsSet.get(stringIterator).invoke(obj,true);
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
				else if  (values[stringIterator].equals("false")) {

					methodsSet.get(stringIterator).invoke(obj,false);
					String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
				}
				else
				{
					String x = "Not valid boolean information: "
							+ "last saved change:" + methodsGet.get(stringIterator).invoke(obj, null);
					changesDisplay.appendText(x+"\n");
					
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				String x = "Invalid data input for: "  + showingVariable.get(stringIterator);
				changesDisplay.appendText(x+"\n");
			}
			break;
		}

	}

	public void createClass() {

		String checkClass = new String();
		checkClass = classSearch.getText();

		try {
			clearView();
			cls = Class.forName(checkClass);
			obj = cls.newInstance();
			changesDisplay.setText("New instance of class:  " + checkClass + " created \n");
			field = cls.getDeclaredFields();
			classCreated = true;
			initializeClass();
			showCreatedObject();
		} catch (ClassNotFoundException e) {
			changesDisplay.appendText("Class not existing\n");
		} catch (InstantiationException e) {
			changesDisplay.appendText("error");
		} catch (IllegalAccessException e) {
			changesDisplay.appendText("No permissions");
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showCreatedObject() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		if (textInClass == true) {
			String textArea = valueText.getText();
			changesDisplay.appendText(textArea);
			stringIterator=0;
			for(int i=0;i<field.length-1;i++)
			{
				String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
			changesDisplay.appendText(x+"\n");
			stringIterator++;
			}
			stringIterator=0;
		}
		else
		{
			stringIterator=0;
			for(int i=0;i<field.length-1;i++)
			{
				String x = showingVariable.get(stringIterator) + "  " + methodsGet.get(stringIterator).invoke(obj, null);
			changesDisplay.appendText(x+"\n");
			stringIterator++;
			}
			stringIterator=0;
		}
		
	}
	public void createArrays() {
		valuesTextFieldArray = new ArrayList<TextField>();
		valuesTextAreaArray = new ArrayList<TextArea>();
		methodsGet = new ArrayList<Method>();
		methodsSet = new ArrayList<Method>();
		checkType = new ArrayList<String>();
		showingVariable = new ArrayList<String>();
	}

	public void initializeClass() throws IllegalAccessException {
		createArrays();

		// creating Vbox for data input
		VBox valuesFields = new VBox();
		HBox.getChildren().add(valuesFields);
		valuesFields.setPrefSize(350, 450);
		// creating Vbox for showing info about variable
		VBox classFields = new VBox();
		HBox.getChildren().add(classFields);
		classFields.setPrefSize(350, 450);

		for (int i = 0; i < field.length; i++) {
			field[i].setAccessible(true);
			try {
				nameOfMethod = field[i].getName().toString();
				nameOfMethod = nameOfMethod.substring(0, 1).toUpperCase() + nameOfMethod.substring(1).toLowerCase();
				method = cls.getMethod("get" + nameOfMethod);
				returnType = method.getReturnType().toString();
				methodsGet.add(method);
				setMethods();

			} catch (NoSuchMethodException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}

			if (field[i].getName().toString().equals("text")) {
				valueText = new TextArea();
				valueText.setPrefSize(200, 250);
				valuesFields.getChildren().add(valueText);
				TextArea areaTextm = new TextArea();
				areaTextm.setPrefSize(200, 250);
				classFields.getChildren().add(areaTextm);

				valuesTextAreaArray.add(areaTextm);
				valueText.setText((String.valueOf(field[i].get(obj))));
				areaTextm.setText("<-----" + field[i].getName().toString());
				textInClass = true;

			}

			else {

				valuesText = new TextField();
				classText = new TextField();
				valuesFields.getChildren().add(valuesText);
				classFields.getChildren().add(classText);
				valuesTextFieldArray.add(valuesText);

				try {
					valuesText.setText((String.valueOf(field[i].get(obj))));

				} catch (IllegalArgumentException e) {

				}
				classText.setText("<-------" + field[i].getName().toString());
				showingVariable.add(field[i].getName().toString().toUpperCase());
			}
			
		}

	}

	public void clearView() {
		HBox.getChildren().clear();
		valueText = null;
		textInClass = false;

	}

	
	
	
	public void setMethods() {

		switch (returnType) {
		case "class java.lang.String":
			try {
				method = cls.getMethod("set" + nameOfMethod, String.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("S");
			methodsSet.add(method);

			break;
		case "class java.lang.Integer":
			try {
				method = cls.getMethod("set" + nameOfMethod, Integer.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("I");
			methodsSet.add(method);

			break;
		case "class java.lang.Boolean":
			try {
				method = cls.getMethod("set" + nameOfMethod, Boolean.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("B");
			methodsSet.add(method);

			break;
		case "class java.lang.Character":
			try {
				method = cls.getMethod("set" + nameOfMethod, Character.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("C");
			methodsSet.add(method);

			break;
		case "class java.lang.Byte":
			try {
				method = cls.getMethod("set" + nameOfMethod, Byte.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("By");
			methodsSet.add(method);

			break;
		case "class java.lang.Short":
			try {
				method = cls.getMethod("set" + nameOfMethod, Short.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("Sh");
			methodsSet.add(method);

			break;
		case "class java.lang.Long":
			try {
				method = cls.getMethod("set" + nameOfMethod, Long.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("L");
			methodsSet.add(method);

			break;
		case "class java.lang.Double":
			try {
				method = cls.getMethod("set" + nameOfMethod, Double.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("D");
			methodsSet.add(method);

			break;
		case "class java.lang.Float":
			try {
				method = cls.getMethod("set" + nameOfMethod, Float.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("F");
			methodsSet.add(method);

			break;
		case "boolean":
			try {
				method = cls.getMethod("set" + nameOfMethod, boolean.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("B");
			methodsSet.add(method);

			break;
		case "int":
			try {
				method = cls.getMethod("set" + nameOfMethod, int.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("I");
			methodsSet.add(method);

			break;
		case "float":
			try {
				method = cls.getMethod("set" + nameOfMethod, float.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("F");
			methodsSet.add(method);

			break;
		case "double":
			try {
				method = cls.getMethod("set" + nameOfMethod, double.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("D");
			methodsSet.add(method);

			break;
		case "short":
			try {
				method = cls.getMethod("set" + nameOfMethod, short.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("Sh");
			methodsSet.add(method);

			break;
		case "long":
			try {
				method = cls.getMethod("set" + nameOfMethod, long.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("L");
			methodsSet.add(method);

			break;
		case "byte":
			try {
				method = cls.getMethod("set" + nameOfMethod, byte.class);
			} catch (NoSuchMethodException e) {
				System.out.println("nie ma metody");
			} catch (SecurityException e) {

			}
			checkType.add("By");
			methodsSet.add(method);
			

			break;

		}

	}

	

}
