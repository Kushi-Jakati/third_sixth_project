package edu.guilford;

import java.io.File;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class RationalPane extends GridPane {

    // Rational attribute
    private Rational rational;

    // Three text fields
    private TextField userRationalField = new TextField();
    private TextField randomRationalField = new TextField();
    private TextField resultField = new TextField();

    // Add toggle group
    private ToggleGroup group = new ToggleGroup();

    // Three labels
    private Label userRationalLabel;
    private Label randomRationalLabel;
    private Label resultLabel;
    // Add a title to the pane
    private Label title = new Label("Rational Number Calculator");

    // Three RadioButtons
    private RadioButton negate;
    private RadioButton invert;
    private RadioButton add;

    // One button (when clicked on, it will pop up an image)
    private Button showImage;

    // Step 1. Declare an imageView Attribute
    private ImageView imageView = new ImageView();

    // Constructor
    public RationalPane(Rational rational) {
        Rational randomRational = new Rational();
        this.rational = rational;
        // Instantiation

        // Fields
        userRationalField = new TextField();
        randomRationalField = new TextField(randomRational.toString());
        randomRationalField.setEditable(false);
        resultField = new TextField();
        resultField.setEditable(false);

        // Labels
        userRationalLabel = new Label("Enter your rational number numerator: ");
        randomRationalLabel = new Label("Random rational number: ");
        resultLabel = new Label("Result: ");

        // RadioButtons (might change this medium), not that nice
        negate = new RadioButton("Negate");
        invert = new RadioButton("Invert");
        add = new RadioButton("Add to Random Rational Number");

        // Add RadioButtons to the toggle group
        negate.setToggleGroup(group);
        invert.setToggleGroup(group);
        add.setToggleGroup(group);

        // Buttons
        showImage = new Button("Ew,Math!");

        // Image
        // Step 2. Instantiate the ImageView attribute with the image in the resources
        // folder. We have to tell javafx
        // where our resource is and then convert it into an URL which needs to be
        // turned into a string.
        File avatar = new File(this.getClass().getResource("world.jpg").getPath());
        imageView = new ImageView(avatar.toURI().toString());

        // Add the labels, fields, and buttons to the pane

        // Labels
        this.add(title, 0, 0);
        this.add(userRationalLabel, 0, 1);
        this.add(randomRationalLabel, 0, 2);
        this.add(resultLabel, 0, 3);

        // Fields
        this.add(userRationalField, 1, 1);
        this.add(randomRationalField, 1, 2);
        this.add(resultField, 1, 3);

        // Add RadioButtons to the far right of the fields
        this.add(negate, 3, 1);
        this.add(invert, 3, 2);
        this.add(add, 3, 3);

        // Button in the bottom of the panel
        this.add(showImage, 0, 4);

        // Give a pane a border color of black
        this.setStyle("-fx-border-color: black;");
        // Give a pane a background color
        this.setStyle("-fx-background-color: lightblue;");
        // Bold title
        title.setStyle("-fx-font-weight: bold");
        // Italicize RadioButtons
        negate.setStyle("-fx-font-style: italic");
        invert.setStyle("-fx-font-style: italic");
        add.setStyle("-fx-font-style: italic");

        // Image
        // Step 3. Add the ImageView to the bottom half of the pane.
        // I didn't add it here but actually
        // added it in the button listener below. Scroll all the way down to the bottom
        // of the code to view the listener.

        // Add a listener that gets the value inputted into the Numerator and
        // Denominator textfields after user clicks enter
        userRationalField.setOnAction(e -> {
            // The three errors I want to deal with: outofbounds (numerator or denominator
            // can't
            // exceed max or min integer), an input with either
            // non-numbers or a /.
            // If it is a singular integer typed in, I want to add a /1 to the end of it.
            // code that deals with these three errors through try catch
            // Have an exception class is denom = 0; if denom = 0, throw exception

            // There are two "big" try and catch blocks and each try and catch block has a
            // try and catch block inside of it.

            // This is the first "big" try and catch block. It deals with the case where the
            // user inputs a rational number with a / in it. However, if the user
            // does something weird with the slashes, like inputting a double or triple
            // slash ("9////9/9"), the program has the small try and catch block for the
            // NumberFormatException that corrects the user input to a valid rational
            // number.
            try {
                // Set label to the value of the userRationalField
                userRationalLabel.setText("Rational Number: " + userRationalField.getText());
                // // if user input has more than one slash between numbers, remove all slashes
                // // after the first one
                // Get the value of the userRationalField, seperate it into a numerator and
                // denominator by the /, and set the rational object
                // 's numerator and denominator to that value
                String text = userRationalField.getText();
                String[] parts = text.split("/");
                // try catch block for NumberFormatException
                try {
                    String numeratorString = parts[0];
                    String denominatorString = parts[1];
                    int numerator = Integer.parseInt(numeratorString);
                    int denominator = Integer.parseInt(denominatorString);
                    rational.setNumerator(numerator);
                    rational.setDenominator(denominator);
                    String set = numerator + "/" + denominator;
                    userRationalLabel.setText("Rational Number: " + set);
                    // Make sure denominator is not negative for syntax purposes
                    if (denominator < 0 && numerator > 0) {
                        throw new DenominatorIsNegativeException("Denominator cannot be negative. Flipping signs.", numerator, denominator);
                    }
                    // if the denominator is 0, ask user to input a valid number
                    if (denominator == 0) {
                        throw new DenominatorIsZeroException("Denominator cannot be 0");
                    }
                } catch (NumberFormatException | StringIndexOutOfBoundsException n) {
                    // Have program set the numerator and denominator to the values before and after
                    // the first and last slash
                    // This is a big if statement --> so basically, the first if is checkng for the
                    // condition that the user input has more than one slash in it.
                    // The second if statement (while hasSlash is true) is checking for the first
                    // slash that has a number before it. The third if statement is checking for the
                    // last slash that has
                    // a number after it. Then the program will set the numerator and denominator to
                    // the values before and after the first and last slash. Then while hasSlash is
                    // false and the while loop
                    // stops.

                    try {
                        if (parts.length > 2 && text.contains("//")) {
                            // if user input has more than one slash between numbers, remove all slashes
                            // after the first one
                            // find the first index where there is a slash and a number before the slash
                            boolean hasSlash = true;
                            while (hasSlash) {
                                // iterating through each index of the string and then checking which index has
                                // a / and a number before that /.
                                for (int i = 0; i < text.length(); i++) {
                                    if (text.charAt(i) == '/' && Character.isDigit(text.charAt(i - 1))) {
                                        // get index of first slash
                                        int slash1 = i;
                                        // String moo = text.substring(0, i) + text.substring(i + 1);
                                        // System.out.println(moo);
                                        // get index of last slash with a number after it and remove all slashes in
                                        // betweeen the first and last slash
                                        // iterating through each index of the string (BACKWARDS, with j (no confusion
                                        // with i then), because it is quicker; checks which index has a / and a number
                                        // after that /.
                                        for (int j = text.length() - 1; j > 0; j--) {
                                            if (text.charAt(j) == '/' && Character.isDigit(text.charAt(j + 1))) {
                                                // get index of last slash with a number after it
                                                int slash2 = j;
                                                // moo will be the string with the index before the slash1, and then the
                                                // index after slash2
                                                String moo = text.substring(0, slash1) + "/"
                                                        + text.substring(slash2 + 1);
                                                // ACCOUNT FOR STRING INDEX OUT OF BOUNDS EXCEPTION SOMEHOW??? DUNNO
                                                // HOW??
                                                System.out.println(moo);
                                                // set everything appropriately
                                                userRationalField.setText(moo);
                                                userRationalLabel.setText("Rational Number: " + moo);
                                                rational.setNumerator(Integer.parseInt(moo.substring(0, slash1)));
                                                rational.setDenominator(Integer.parseInt(moo.substring(slash2 + 1)));
                                                hasSlash = false;
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("NumberFormatException: " + n.getMessage());
                            userRationalLabel.setText("Rational Number: ");
                            userRationalField.setText("Please enter in this format: a/b OR");
                        }
                    } catch (StringIndexOutOfBoundsException s) {
                        System.out.println("StringIndexOutOfBoundsException: " + s.getMessage());
                    }
                } catch (DenominatorIsZeroException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (DenominatorIsNegativeException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            } catch (ArrayIndexOutOfBoundsException a) {
                // If the input does not contain a single / and is all numbers, make the input
                // into a numerator and set the denominator to 1
                // Another try catch block for the case where the user inputs a single integer
                try {
                    int numerator = Integer.parseInt(userRationalField.getText());
                    int denominator = 1;
                    rational.setNumerator(numerator);
                    rational.setDenominator(denominator);
                    String set = numerator + "/" + denominator;
                    userRationalLabel.setText("Rational Number: " + set);
                } catch (NumberFormatException n) {
                    // If the input is greater than integer max or less than integer min, ask the
                    // user to input a valid number
                    rational.setNumerator(0);
                    rational.setDenominator(0);
                    userRationalField
                            .setText("Integers must be < " + Integer.MAX_VALUE + " and > " + Integer.MIN_VALUE + "");
                    userRationalLabel.setText("Only Integer & Fractional Inputs");
                }

            }
        });

        // Add a listener to the RadioButtons that change the resultField
        // when clicked on
        negate.setOnAction(e -> {
            // Get the value of the userRationalField, seperate it into a numerator and
            // denominator by the /, and set the rational object
            // 's numerator and denominator to that value
            String text = userRationalField.getText();
            // if getText doesn't have a / in it, then set the denominator and numerator to
            // 0
            if (!text.contains("/")) {
                rational.setNumerator(1);
                rational.setDenominator(1);
                resultField.setText(rational.toString());
                userRationalLabel.setText("Rational Number: ");
                userRationalField.setText("1/1");
            } else {
                String[] parts = text.split("/");
                String numeratorString = parts[0];
                String denominatorString = parts[1];
                int numerator = Integer.parseInt(numeratorString);
                int denominator = Integer.parseInt(denominatorString);
                // Make sure denominator is not negative
                if (denominator < 0 && numerator > 0) {
                    numerator = numerator * -1;
                    denominator = denominator * -1;
                }
                // Convert numerator and denominator to integers and set the rational object's
                // numerator and denominator to those values
                rational.setNumerator(numerator);
                rational.setDenominator(denominator);
                rational.negate();
                resultField.setText(rational.toString());
            }
        });
        invert.setOnAction(e -> {
            // Get the value of the userRationalField, seperate it into a numerator and
            // denominator by the /, and set the rational object
            // 's numerator and denominator to that value
            String text = userRationalField.getText();

            if (!text.contains("/")) {
                rational.setNumerator(1);
                rational.setDenominator(1);
                resultField.setText(rational.toString());
                userRationalLabel.setText("Rational Number: ");
                userRationalField.setText("1/1");
            } else {
                String[] parts = text.split("/");
                String numeratorString = parts[0];
                String denominatorString = parts[1];
                int numerator = Integer.parseInt(numeratorString);
                int denominator = Integer.parseInt(denominatorString);
                // Make sure denominator is not negative
                if (denominator < 0 && numerator > 0) {
                    numerator = numerator * -1;
                    denominator = denominator * -1;
                }
                // Convert numerator and denominator to integers and set the rational object's
                // numerator and denominator to those values
                rational.setNumerator(numerator);
                rational.setDenominator(denominator);
                rational.invert();
                resultField.setText(rational.toString());
            }
        });

        add.setOnAction(e -> {
            // Get the value of the userRationalField, seperate it into a numerator and
            // denominator by the /, and set the rational object
            // 's numerator and denominator to that value
            String text = userRationalField.getText();

            if (!text.contains("/")) {
                rational.setNumerator(1);
                rational.setDenominator(1);
                resultField.setText(rational.add(randomRational).toString());
                userRationalLabel.setText("Rational Number: ");
                userRationalField.setText("1/1");
            } else {
                String[] parts = text.split("/");
                String numeratorString = parts[0];
                String denominatorString = parts[1];
                int numerator = Integer.parseInt(numeratorString);
                int denominator = Integer.parseInt(denominatorString);
                // Make sure denominator is not negative
                if (denominator < 0 && numerator > 0) {
                    numerator = numerator * -1;
                    denominator = denominator * -1;
                }
                // Convert numerator and denominator to integers and set the rational object's
                // numerator and denominator to those values
                rational.setNumerator(numerator);
                rational.setDenominator(denominator);
                rational.invert();
                resultField.setText(rational.add(randomRational).toString());
            }
        });

        // Add a listener that displays or removes image every time button is clicked on
        // Step 3. Add the ImageView to the bottom half of the pane.
        // Steps 4 & 5: Write an event listener and connect it to the component
        // that will trigger the event. (open pr close the image everytime the button is
        // clicked)

        showImage.setOnAction(e -> {
            // Position image at the center of bottom half of the pane
            if (showImage.getText().equals("Ew,Math!")) {
                this.add(imageView, 1, 7);
                // alter image size
                imageView.setFitWidth(380);
                imageView.setFitHeight(380);
                showImage.setText("Hide Image");
            } else {
                this.getChildren().remove(imageView);
                showImage.setText("Ew,Math!");
            }
        });

    }

    // Create an exception class for the case where the user has a denominator of 0
    public class DenominatorIsZeroException extends Exception {
        public DenominatorIsZeroException(String message) {
            super(message);
            rational.setNumerator(1);
            rational.setDenominator(1);
            userRationalField.setText("Denominator cannot be 0");
            userRationalLabel.setText("Rational Number: ");
        }
    }

    // Create an exception class for the case where user has a negative denominator
    public class DenominatorIsNegativeException extends Exception {
        public DenominatorIsNegativeException(String message, int numerator, int denominator) {
            super(message);
            numerator = numerator * -1;
            denominator = denominator * -1;
            rational.setNumerator(numerator);
            rational.setDenominator(denominator);
            String set = numerator + "/" + denominator;
            userRationalLabel.setText("Rational Number: " + set);

        }
    }
}

// Errors: for fraction, it is expecting fraction to be in form of a/b, not a
// decimal or an integer.
// Result can't be typed in because it is set to not editable, which is fine.
// Random rational number can't be typed in because it is set to not editable,
// which is fine.
