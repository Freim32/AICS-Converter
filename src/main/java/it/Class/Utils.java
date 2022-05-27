package it.Class;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
    
    ArrayList<String> firstLine;

    public Utils(){
        String [] array= {"Cognome*", "Nome*", "Sesso*", "Data di nascita*", "Provincia di nascita*", "Comune di nascita*", "Codice Fiscale", "Indirizzo di residenza", "Cap di residenza", "Provincia di residenza", "Comune di residenza", "Email", "Telefono abitazione", "Cellulare","Fax Abitazione", "Telefono Ufficio", "Fax Ufficio", "Qualifica Sociale" , "Attivita Sociale" , "Tipo di certificato medico","Data di rilascio del certificato medico", "Qualifica sportiva" , "Attivita sportiva"};
        firstLine =new ArrayList<>(Arrays.asList(array));
    }
    public void MakeRowEquals(ArrayList<ArrayList<String>> data) throws Exception{
        try{
        for (ArrayList<String> row : data ){
            int size=row.size();
            for(int l = 0 ; l<23-size;l++){
                row.add("");
            }
        }
        }catch(Exception e){
            throw new Exception("Errore nella manipolazione del file Excel");
        }
    }
    public void CheckIfTemplate(ArrayList<ArrayList<String>> data) throws Exception{
        try{
        if (!data.get(0).equals(firstLine)){

            throw new Exception("Il File non è in un formato valido");
        }
        data.remove(0);
        }catch(Exception e){
            throw new Exception("Il File non è in un formato valido");
        }
    }

    public void CheckIfEmpty(ArrayList<ArrayList<String>> data) throws Exception{
        try{
            data.get(0);
        }catch(Exception e){
            throw new Exception("Il File è vuoto");
        }
    }
}
