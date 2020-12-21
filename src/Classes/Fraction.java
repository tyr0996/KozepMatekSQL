/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;


import java.util.ArrayList;

/**
 * @author Martin
 */
public class Fraction extends Number {

    private final Number numerator; // szamlalo
    private final Number denominator; // nevezo

    //konstruktorok definiálása

    /**
     * létrehoz egy törtet
     *
     * @param numerator számláló
     * @param denominator nevező
     * @throws ZeroDenominatorException        Ha a tört nevezője 0, akkor ezt az exceptiont dobja
     * @throws NullDenominatorException        Ha egy olyan törtet szeretnénk létrehozni, aminek nincs nevezője, akkor ezt az exceptiont dobja
     * @throws Fraction.NullNumeratorException Ha egy olyan törtet szeretnénk létrehozni, aminek nincs számlálója, akkor ezt az exceptiont dobja
     */
    public Fraction(Number numerator, Number denominator) throws ZeroDenominatorException, NullDenominatorException, NullNumeratorException {
        if (numerator == null) {
            throw new NullNumeratorException();
        } else if (denominator == null) {
            throw new NullDenominatorException();
        } else if (numerator.toString().equals("0") || denominator.toString().equals("0.0")) {
            throw new ZeroDenominatorException();
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
    }


    //METÓDUSOK

    /**
     * Közös nevezőre hozza a törtet egy másik, paraméterként megadott törttel. Kiegészíthető lenne azzal, hogy olyan számlálójú törtet is össze lehessen hozni, amely tört
     * @param with Ezzel fogja azonos nevezőre hozni
     * @return Két Fraction-t tartalmazó osztállyal tér vissza
     * @throws ZeroDenominatorException Akkor dobja ezt a hibát, ha valamelyik törtnek 0 van a nevezőjében
     * @throws NullDenominatorException Akkor dobja ezt a hibát, ha hiányzik a tört nevezője
     * @throws NullNumeratorException   Akkor dobja ezt a hibát, ha hiányzik a tört számlálója
     */
    public ArrayList<Fraction> toSameDenominator(Fraction with) throws ZeroDenominatorException, NullDenominatorException, NullNumeratorException {
        Fraction f1 = this;

        //közös nevező meghatározása
        ArrayList<Number> newDenominators = new ArrayList<>();
        newDenominators.add(f1.denominator);
        newDenominators.add(with.denominator);
        Integer newDenominator = Numbers.lkkt(newDenominators);

//        Szükséges_szorzók_meghatározása
        int multiplerA = with.denominator.intValue() / newDenominator;
        int multiplerB = f1.denominator.intValue() / newDenominator;


        int a = f1.numerator.intValue() * multiplerA; //TODO itt kellene valamit kitalálni ahhoz, hogy ne csak int-tel tudjak dolgozni
        int b = with.numerator.intValue() * multiplerB;

        ArrayList<Fraction> back = new ArrayList<>(2);
        back.add(new Fraction(a, newDenominator));
        back.add(new Fraction(b, newDenominator));

        return back;
    }

    public static ArrayList<Fraction> toSameDenominator(Fraction f1, Fraction f2) throws ZeroDenominatorException, NullDenominatorException, NullNumeratorException {
        return f1.toSameDenominator(f2);
    }

    /**
     * @param theese AZ itt megadott törteket közös nevezőre hozza
     * @return visszatér ezen közös nevezős alakkal
     * @throws Fraction.ZeroDenominatorException Akkor dobja ezt a hibát, ha a közös nevezőre nulla jön ki
     * @throws Fraction.NullDenominatorException Akkor dobja ezt a hibát, ha valamiért hiányzik a nevező
     * @throws Fraction.NullNumeratorException   Akkor dobja ezt a hibát, ha valamiért hiányzik a számláló
     */
    public static ArrayList<Fraction> toSameDenominator(ArrayList<Fraction> theese) throws ZeroDenominatorException, NullDenominatorException, NullNumeratorException {
        ArrayList<Number> denominators = new ArrayList<>(theese.size());
        for (Fraction f : theese) denominators.add(f.denominator);


        //Abszolút közös nevező meghatározása
        Integer absoluteCommonDenominator = Numbers.lkkt(denominators);

        //Törtek létrehozása egyesével, majd bepakolása a back-be
        for (int i = 0; i < theese.size(); i++) {
            Fraction actual = theese.get(i);
            int multipler = absoluteCommonDenominator / actual.denominator.intValue();
            theese.add(i, new Fraction(actual.numerator.intValue() * multipler, absoluteCommonDenominator));
        }
        return theese;
    }

    @Override
    public int intValue() {
        try {
            double doubleBack = this.numerator.doubleValue() / this.denominator.doubleValue();
            return ((int) doubleBack);
        } catch (NullPointerException e) {
            return Integer.MIN_VALUE;
        }
    }

    @Override
    public long longValue() {
        try {
            return this.numerator.longValue() / this.denominator.longValue();
        } catch (NullPointerException e) {
            return Long.MIN_VALUE;
        }
    }

    @Override
    public float floatValue() {
        try {
            return this.numerator.floatValue() / this.denominator.floatValue();
        } catch (NullPointerException e) {
            return Float.MIN_VALUE;
        }
    }

    @Override
    public double doubleValue() {
        try {
            return this.numerator.doubleValue() / this.denominator.doubleValue();
        } catch (NullPointerException e) {
            return Double.MIN_VALUE;
        }
    }

    @Override
    public String toString() {
        try {
            return "frac{" + this.numerator + "}{" + this.denominator + "}";
        } catch (NullPointerException e) {
            return "";
        }

    }

    // kivételek létrehozása
    public static class ZeroDenominatorException extends Exception {
        public ZeroDenominatorException() {
            ErrorMessage er = new ErrorMessage(1);
            er.print();
        }
    }

    public static class NullNumeratorException extends Exception {
        public NullNumeratorException() {
            ErrorMessage er = new ErrorMessage(2);
            er.print();
        }
    }

    public static class NullDenominatorException extends Exception {
        public NullDenominatorException() {
            ErrorMessage er = new ErrorMessage(3);
            er.print();
        }
    }

    public static class NullFractionException extends Exception {
        public NullFractionException() {
            ErrorMessage er = new ErrorMessage(4);
            er.print();
        }
    }


}
