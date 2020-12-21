
package Classes;

import java.util.ArrayList;

public abstract class Numbers {


    /**
     * A két szám legnagyobb közös osztóját számolja ki az Euklideszi algoritmussal
     *
     * @param i1 egyik szám
     * @param i2 másik szám
     * @return legnagyobb közös osztó
     */
    public static Integer lnko(Integer i1, Integer i2) {
        if (i1 % i2 == 0) return i2;
        else return lnko(i2, i1 % i2);
    }

    /**
     * Meghatározza a számok legkisebb közös többszörösét
     *
     * @param numbers ebben lévő számoknak számolja ki a legkisebb közös többszörösét
     * @return -1 ha nem létezik lkkt, és a számok legkisebb közös többszörösével, ha van ilyen
     */
    public static Integer lkkt(ArrayList<Number> numbers) {
        ArrayList<Integer> input = new ArrayList<>(numbers.size());
        ArrayList<ArrayList<Integer>> ararList = new ArrayList<>();
        ArrayList<Integer> backArrayList = new ArrayList<>();
        int back = -1;

        // Integer-ré konvertálás 
        for (Number num : numbers) input.add(num.intValue());

        // Integerré konvertáltak átalakítása prímtényezős felbontásra
        for (Integer i : input) ararList.add(primTenyezo(i));

        // Az algoritmus lehetséges maximális futásának meghatározása (Ezt itt lehetne fejleszteni, hogy tudja, hogy hány elem lesz benne a kész tömbben)
        int maxRun = 0;
        for (ArrayList<Integer> integers : ararList) maxRun = maxRun + integers.size();


        // Ez a while-ciklus egy olyan prímtényezős felbontást ad meg, amely a legkisebb közös többszörösé 
        int bigIndex = -1;
        int maxSizeArrayList = -1;
        //meghatározzuk a legnagyobb index-et
        for (ArrayList<Integer> integers : ararList) {
            if (integers.size() >= maxSizeArrayList) {
                maxSizeArrayList = integers.size();
                bigIndex++;
            }
        }
        while (ararList.size() != 0 && bigIndex != -1 && maxRun > -1) {
            System.out.println("bigIndex: " + bigIndex + ",   ararList.size():   " + ararList.size() + "ararList.get(bigIndex).size" + ararList.get(bigIndex).size());
            Integer vizsgalandoInteger = ararList.get(bigIndex).get(0);
            ArrayList<Integer> vizsgalando = new ArrayList<>(1);
            vizsgalando.add(vizsgalandoInteger);
            backArrayList.add(vizsgalandoInteger);

            removeElementFromArarList(ararList, vizsgalando);

            maxRun = maxRun - 1;

            for (int i = 0; i < ararList.size(); i++) {
                if (ararList.get(i).size() == 0) {
                    ararList.remove(ararList.get(i));
                }
            }
            if (ararList.size() == 0) break;

            maxSizeArrayList = -1;
            bigIndex = -1;
            //meghatározzuk a legnagyobb index-et
            for (int i = 0; i < ararList.size(); i++) {
                if (ararList.get(i).size() >= maxSizeArrayList) {
                    maxSizeArrayList = ararList.get(i).size();
                    bigIndex = i;
                }
            }
        }


        //ez pedig meghatározza magát a lkkt-t
        if (backArrayList.size() != 0) {
            back = 1;
            for (Integer i : backArrayList) back = back * i;
        }

        return back;
    }

    public static Integer lnko(ArrayList<Number> theese) {
        ArrayList<ArrayList<Integer>> ararList = new ArrayList<>();
        for (Number num : theese) {
            ararList.add(primTenyezo(num.intValue()));
        }
        ArrayList<Integer> backList = new ArrayList<>();

        ArrayList<Integer> vizsgalando = osszeselemkiszedese(ararList);

        for (Integer i : vizsgalando) {
            //itt azt nézzük meg, hogy benne van-e az összesben az az egyetlen szám, amit még ügye nem vizsgáltunk
            boolean bennevanosszesben = true;
            for (ArrayList<Integer> ar : ararList) {
                if (!ar.contains(i)) {
                    bennevanosszesben = false;
                    break;
                }
            }


            //amíg benne van, addíg hozzáadja a back-hez, meg eltávolítja az eredetiből, stb
            while (bennevanosszesben) {
                backList.add(i);

                //Megadott elem eltávolítása
                removeElementFromArarList(ararList, vizsgalando);

                //annak ellenőrzése, hogy továbbra is benne van-e az összesben vagy sem, 
                for (ArrayList<Integer> ar : ararList) {
                    if (!ar.contains(i)) {
                        bennevanosszesben = false;
                        break;
                    }
                }
            }
        }

        //A számok összeszorzását végzi
        int back = 1;
        for (Integer i : backList) {
            back = back * i;
        }

        return back;
    }

    private static void removeElementFromArarList(ArrayList<ArrayList<Integer>> ararList, ArrayList<Integer> vizsgalando){

        for (int j = 0; j < ararList.size(); j++) {
            ArrayList<Integer> actual = ararList.get(j);
            ArrayList<Integer> backInteger = new ArrayList<>();
            ArrayList<String> actualString = new ArrayList<>();

            for (Integer integer : actual) actualString.add(integer.toString());
            actualString.remove(vizsgalando.toString());
            for (String str : actualString) backInteger.add(Integer.parseInt(str));

            ararList.set(j, backInteger);
        }
    }


    //Azokat az elemeket kiválogatja ami a 2-dimenziós listában benne vannak, ismétlés nélkül adja vissza
    public static ArrayList<Integer> osszeselemkiszedese(ArrayList<ArrayList<Integer>> ararList) {
        ArrayList<Integer> back = new ArrayList<>();
        ArrayList<String> osszes = new ArrayList<>();
        for (ArrayList<Integer> arList : ararList) {
            for (Integer i : arList) {
                osszes.add(i.toString());
            }
        }

        while (!osszes.isEmpty()) {
            String actual = osszes.get(0);
            back.add(Integer.parseInt(actual));
            for (int i = 0; i < osszes.size(); i++) {
                if (actual.equals(osszes.get(i))) {
                    osszes.remove(i);
                    i = i - 1;
                }
            }
        }

        return back;

    }


    /**
     * @param szam felbontja prímtényezőkre ezt a számot
     * @return primtényezős felbontásával tér vissza
     */
    public static ArrayList<Integer> primTenyezo(Integer szam) {
        ArrayList<Integer> back = new ArrayList<>();

        boolean siker = false;

        for (int i = 2; i <= szam; i++) {
            if (szam % i == 0) {
                back.add(i);
                szam = szam / i;
                i = i - 1;
                siker = true;
            }
        }

        if (!siker) back.add(szam);

        return back;
    }


    //kivételek létrehozása
    public static class IllegalNumberFormatForLeastCommonMultipleException extends Exception {
        public IllegalNumberFormatForLeastCommonMultipleException() {
            ErrorMessage er = new ErrorMessage(5);
            er.print();
        }
    }

    public static class OverrunLeastCommonMultipleException extends Exception {
        public OverrunLeastCommonMultipleException() {
            ErrorMessage er = new ErrorMessage(6);
            er.print();
        }
    }


}

    
