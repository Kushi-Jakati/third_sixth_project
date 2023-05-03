package edu.guilford;

import java.util.Random;

//A more simpler mathematical Rational class. 
public class Rational {
    // A Rational object should have two integer attributes that store the numerator
    // and denominator.
    private int numerator;
    private int denominator;

    //Method to be invoked in constructors to ensure that syntax of rational number (with regard to signs)
    //is correct.
    private void ensuringSigns() {
        // For syntax and algorithmetic purposes..
        // If numerator and denominator are both negative, then make them both positive.
        // If else, if numerator is positive and denominator is negative, then make them
        // both negative. Else, do nothing.
        if (denominator == 0) {
            System.out.println("Your denominator cannot be 0. Please try again with a denominator that is not 0.");
        } else if (numerator < 0 && denominator < 0) {  
            // else if numerator and denominator are both negative, 
            //then make them both positive.
            numerator = -numerator;
            denominator = -denominator;
        } else if (numerator > 0 && denominator < 0) {
            // else if numerator is positive and denominator is negative, then make them
            // both negative. 
            numerator = -numerator;
            denominator = -denominator;
        }
    }
    
    // Constructor that generatesnRational number with random values for the numerator and denominator in the
    // range [–100, 100]. CHECK THIS. CHECK THIS. CHECK THIS.
    public Rational() {
        Random random = new Random();
        // Random integer between -100 and 100
        numerator = random.nextInt(201) - 100;
        denominator = random.nextInt(201) - 100;
        // If there is an attempt to generate a Rational number with
        // a denominator of 0, then the constructor should select a new random number.
        while (denominator == 0) {
            // Random integer between -100 and 100
            denominator = random.nextInt(201) - 100;
        }
        ensuringSigns(); 
    }

    // Write a second constructor that takes two arguments and uses them to
    // initialize the attributes.
    // Rational numbers should not have denominators equal to zero. Add code that
    // prints out a warning if the constructor with parameter attempts to generate a
    // Rational number with a 0 denominator.
    public Rational(int numerator, int denominator) {
        this.numerator = numerator;
        this.denominator = denominator;
        ensuringSigns(); 
    }

    // Add a toString method that displays a Rational object in a reasonable format.
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // Getters and Setters
    public int getNumerator() {
        return numerator;
    }

    public void setNumerator(int numerator) {
        this.numerator = numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public void setDenominator(int denominator) {
        this.denominator = denominator;
    }

    //Method called negate. It should reverse the sign of the rational number.

    public void negate() {
        //Parameters are what user types into scanner for expected numerator and denominator for negation
        if (denominator == 0) {
            System.out.println("Your denominator cannot be 0 if we need to negate. Please try again with a denominator that is not 0.");
        } else if (denominator != 0) {

            //System Calculations
            //System.out.println("Negating the rational number " + toString() + "...");
            numerator = -numerator;
            System.out.println("Negated Rational Number calculated from the system: " + toString() + "\n");
        }

    }

    // Write a method called invert that swaps the numerator and denominator. Add
    // lines to main to test the new method.
    public void invert() {
        if (denominator == 0) {
            System.out.println("Your denominator cannot be 0 if we need to invert. Please try again with a denominator that is not 0.");
        } else if (denominator != 0) {

            //System Calculations
            //System.out.println("Inverting the rational number " + toString() + "...");
            int invertPlaceholder = numerator;
            numerator = denominator;
            denominator = invertPlaceholder;
            ensuringSigns();
            System.out.println("Invert calculated from the system: " + toString() + "\n");
        }
    }

    // An instance method called toDouble that converts the rational number to
    // a double (floating-point number) and returns the result. This method does not
    // modify the object. As always, test the new method.
    public String toDouble() {
        double doubleRationalNumber = 0.0;
        String doubleRationalNumberString = "";
        if (denominator ==0) {
            System.out.println("Your denominator cannot be 0 if we need to find the double. Please try again with a denominator that is not 0.");
        }else if (denominator !=0) {

            //System Calculations
            //System.out.println("Converting the rational number " + toString() + " to a double...");
            doubleRationalNumber = (double) numerator / denominator;
            doubleRationalNumberString = Double.toString(doubleRationalNumber);
            System.out.println("Double calculated from the system: ");
        }
        return doubleRationalNumberString;
    }

    // Add method: adding two rational numbers together. 
    public Rational add(Rational rational) {
        if (denominator == 0 || rational.denominator == 0) {
            System.out.println("Your denominator cannot be 0 if we need to find the sum. Please try again with a denominator that is not 0.");
        } else {
            
            //System Output
            //System.out.println("Calculating the sum of rational numbers (your rational number and a randomly generated one)...");
            // Create a new rational object that represents the sum of the two rational
            // numbers
            int newNumerator = (numerator * rational.getDenominator()) + (denominator * rational.getNumerator());
            int newDenominator = denominator * rational.getDenominator();
            Rational newRational = new Rational(newNumerator, newDenominator);
            newRational.ensuringSigns();
            //Final System Calculations Output
            System.out.println("The sum of " + toString() + " and " + rational.toString() + " from our program is "
                    + newRational.toString() + ".");
                    return newRational;
        }
        return null;
    }
            
}
