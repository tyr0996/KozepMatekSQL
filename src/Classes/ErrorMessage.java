package Classes;

public class ErrorMessage {
    private final Integer errorCode;

    public ErrorMessage(Integer errorCode){
        this.errorCode = errorCode;
    }
    
    public String getErrorMessage(){
        String back = this.errorCode.toString() + "  ";
        String errorMessage = "";
        if(!Language.isSupported(Language.currentLanguage)) return "Unsupported language code: " + Language.currentLanguage;
        switch(Language.currentLanguage.getLang()){
            
            case "HUN" :
                errorMessage = switch (this.errorCode) {
                    case 1 -> "Hiba a tört létrehozásakor: a nulla nem lehet a nevezőben!";
                    case 2 -> "Hiba a tört létrehozásakor: nincs megadva számláló!";
                    case 3 -> "Hiba a tört létrehozásakor: nincs megadva nevező!";
                    case 4 -> "Hiba a tört létrehozásakor: nincs megadva adat!";
                    case 5 -> "Hiba a számok legkisebb közös többszörösének meghatározásakor: az egyik megadott szám nem egész szám!";
                    case 6 -> "Hiba a számok legkisebb közös többszörösének meghatározásakor: az algoritmus túlterhelődött, majd leált!";
                    default -> "Ismeretlen hibakód!";
                };
                break;
                
                
            case "ENG" :
                switch (this.errorCode) {
                    case 1 -> back = "An error occured while make a new fraction: You cann't make a fraction with denominator 0!";
                    case 2 -> errorMessage = "An error occured while make a new fraction: numerator is missing!";
                    case 3 -> errorMessage = "An error occured while make a new fraction: denominator is missing!";
                    case 4 -> errorMessage = "An error occured while make a new fraction: missing numbers!";
                    case 5 -> errorMessage = "An error occured while try to calculate the least common multiple of the numbers: one of the numbers is not an integer";
                    case 6 -> errorMessage = "An error occured while try to calculate the least common multiple of the numbers: the algorythm is overrunned and died";
                    default -> back = "Unkown errorcode!";
                }
                break;
        }
        return back + errorMessage;
    }
    
    public static String getErrorMessage(Integer errorCode){
        ErrorMessage error = new ErrorMessage(errorCode);
        return error.getErrorMessage();
    }
    
    public void print(){
        System.err.println("[ ERRORMESSAGE ] : " + this.getErrorMessage());
    }

    public static void print(Integer errorCode){
        ErrorMessage error = new ErrorMessage(errorCode);
        error.print();
    }
}
