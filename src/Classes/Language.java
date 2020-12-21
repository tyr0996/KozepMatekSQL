/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Martin
 */
public class Language {
    private final String lang;
    private final static String[] supportedLanguages = {"HUN", "ENG"};
    public static Language currentLanguage = new Language("HUN");
    
    public Language(String lang){
        this.lang = lang;
    }
    
    public static void modifyCurrentLanguage(Language to){
        currentLanguage = to;
    }
    
    /**
     * @param check Ezt a nyelvet nézi meg, hogy támogatott-e, vagy nem
     * @return Ha támogatott akkor igaz értékkel tér vissza
     */
    public static Boolean isSupported(Language check){
        for(String s : supportedLanguages){
            if(s.equals(check.getLang())) return true;
        }
        return false;
    }
    
    /**
     * A ráhívott nyelvről eldönti, hogy benne támogatott-e
     * @return Ha támogatott akkor igaz értékkel tér vissza
     */
    public Boolean isSupported(){
         for(String s : supportedLanguages){
            if(s.equals(this.getLang())) return true;
        }
        return false;
    }
    
    public String getLang(){
        return this.lang;
    }
}
