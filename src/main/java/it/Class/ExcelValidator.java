package it.Class;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;


public class ExcelValidator {
    final static String[] province = {"AG", "AL", "AN", "AO", "AQ", "AR", "AP", "AT", "AV", "BA", "BT", "BL", "BN", "BG", "BI", "BO", "BZ", "BS", "BR", "CA", "CL", "CB", "CI", "CE", "CT", "CZ", "CH", "CO", "CS", "CR", "KR", "CN", "EN", "FM", "FE", "FI", "FG", "FC", "FR", "GE", "GO", "GR", "IM", "IS", "SP", "LT", "LE", "LC", "LI", "LO", "LU", "MC", "MN", "MS", "MT", "VS", "ME", "MI", "MO", "MB", "NA", "NO", "NU", "OG", "OT", "OR", "PD", "PA", "PR", "PV", "PG", "PU", "PE", "PC", "PI", "PT", "PN", "PZ", "PO", "RG", "RA", "RC", "RE", "RI", "RN", "RM", "RO", "SA", "SS", "SV", "SI", "SR", "SO", "TA", "TE", "TR", "TO", "TP", "TN", "TV", "TS", "UD", "VA", "VE", "VB", "VC", "VR", "VV", "VI", "VT","EE"};
    final static String[] qualificheSociali = {"AC","AN","AR","AS","ASSOC","AT","AT2","AG","CA","DI","DO","GI","GU","GT","IS_MARI","IS1","IS2","IS_SUB","OA","OC","VOL","OT","PO","PA","SC","SO","TP","TS","TD"};
    final static String[] qualificheSportive ={"Non praticante","Atleta praticante","Atleta agonista","Dirigente","Tecnico","Ufficiale di gara"};
    final static String  RFC_5322_Email_Validataon ="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    ArrayList<String> line;
    String data;
    
    public ExcelValidator() {}

    public void validate(ArrayList<ArrayList<String>> list) throws Exception {

        for (Integer lineIndex = 2; lineIndex <= list.size()+1; lineIndex++) {
            line = list.get(lineIndex-2);

            if (line.size() != 23) {
                throw new Exception("La riga " + lineIndex.toString() + " ha dimensioni errate.");
            }

            // check if the first 5 columns are non-empty
            for (Integer columnIndex = 1; columnIndex < 7; columnIndex++){
                if (line.get(columnIndex-1).isEmpty()) {
                    throw new Exception("Cella [" + Character.toString(columnIndex + 64) + lineIndex.toString() + "]: la cella non può essere vuota");
                    //JOptionPane.showMessageDialog(null, "ciao");
                }
            }
            
            // 0 cognome
            data=line.get(0);
            if (data.length() > 50) {
                throw new Exception("Cella [A" + lineIndex.toString() + "]: il cognome non può avere più di 50 caratteri");
            }
            

            // 1 nome
            data=line.get(1);
            if (data.length() > 50) {
                throw new Exception("Cella [B" + lineIndex.toString() + "]: il nome non può avere più di 50 caratteri");
            }
            
            // 2 sesso
            line.set(2, line.get(2).toUpperCase());
            data=line.get(2);
            if (!(data.equals("M") || data.equals("F"))) {
                throw new Exception("Cella [C" + lineIndex.toString() + "]: il sesso può essere solamente 'M' o 'F'");
            }

            // 3 data di nascita
            data=line.get(3);
            if (data.length() != 10) {
                throw new Exception("Cella [D" + lineIndex.toString() + "]: la data deve essere nel formato GG/MM/AAAA (es. 01/01/1900)");
            }else{
                try {
                    SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
                    sdfrmt.setLenient(false);

                    sdfrmt.parse(data);
                } catch (Exception e) {
                    throw new Exception("Cella [D" + lineIndex.toString() + "]: la data deve essere nel formato GG/MM/AAAA (es. 01/01/1900)");
                }
            }

            // 4 provincia di nascita
            line.set(4, line.get(4).toUpperCase());
            data=line.get(4);
            if (!Arrays.stream(province).anyMatch(data::equals)) {
                throw new Exception("Cella [E" + lineIndex.toString() + "]: la provincia di nascita non è valida");
            }

            // 5 comune di nascita
            data=line.get(5);
            if (data.length() > 65) {
                throw new Exception("Cella [F" + lineIndex.toString() + "]: il comune di nascita non può avere più di 65 caratteri");
            }
            

            // 6 codice fiscale
            line.set(6,line.get(6).toUpperCase());
            data=line.get(6);
            if (!(data.isEmpty() || line.get(4).equals("EE"))){              
                if (data.length()!=16){
                throw new Exception("Cella [G" + lineIndex.toString() + "]: il codice fiscale non è valido");
                }
            }

            // 7 indirizzo di residenza 
            if (line.get(7).length() > 50) {
                throw new Exception("Cella [H" + lineIndex.toString() + "]: l'indirizzo non può avere più di 50 caratteri");
            }

            // 8 CAP di residenza 
            data=line.get(8);
            if (!data.isEmpty()){
                if ( data.length()!=5){
                    throw new Exception("Cella [I" + lineIndex.toString() + "]: il CAP non è valido");
                }
                try {
                    Long.parseLong(data);
                } catch (Exception e) {
                    throw new Exception("Cella [I" + lineIndex.toString() + "]: il CAP non è valido");
                }
            }

            // 9 provincia di residenza 
            line.set(9, line.get(9).toUpperCase());
            data=line.get(9);
            if (!data.isEmpty() && !Arrays.stream(province).anyMatch(data::equals)) {
                throw new Exception("Cella [J" + lineIndex.toString() + "]: la provincia di residenza non è valida");
            }

            // 10 comune di residenza 
            data=line.get(10);
            if (data.length() > 65) {
                throw new Exception("Cella [K" + lineIndex.toString() + "]: il comune di residenza non può avere più di 65 caratteri");
            }

        
            // 11 email
            data=line.get(11);
            if (data.length()>50){
                throw new Exception("Cella [L" + lineIndex.toString() + "]: l'email non è valida ");
            }

            if (!data.isEmpty()){
                if (!Pattern.compile(RFC_5322_Email_Validataon).matcher(data).matches()){
                    throw new Exception("Cella [L" + lineIndex.toString() + "]: l'email non è valida ");
            }
            }

            // 12 telefono abitazione
            data=line.get(12);
            if (!data.isEmpty()){
                if (data.length() > 12){
                    throw new Exception("Cella [M" + lineIndex.toString() + "]: il in numero di telefono non è valido");
                }
                try {
                    Long.parseLong(data);
                } catch (Exception e) {
                    throw new Exception("Cella [M" + lineIndex.toString() + "]: il in numero di telefono non è valido");
                }
            }

            // 13 cellulare
            data=line.get(13);
            if (!data.isEmpty()){
                if (data.length() > 12){
                    throw new Exception("Cella [N" + lineIndex.toString() + "]: il in numero di cellulare non è valido");
                }
                try {
                    Long.parseLong(data);
                } catch (Exception e) {
                    throw new Exception("Cella [N" + lineIndex.toString() + "]: il in numero di cellulare non è valido");
                }
            }

            // 14 fax abitazione
            data=line.get(14);
            if (!data.isEmpty()){
                if (data.length() > 12){
                    throw new Exception("Cella [O" + lineIndex.toString() + "]: il fax non è valido");
                }
                try {
                    Long.parseLong(data);
                } catch (Exception e) {
                    throw new Exception("Cella [O" + lineIndex.toString() + "]: il fax non è valido");
                }
            }

            // 15 telefono ufficio
            data=line.get(15);
            if (!data.isEmpty()){
                if (data.length() > 12){
                    throw new Exception("Cella [P" + lineIndex.toString() + "]: il in numero di telefono non è valido");
                }
                try {
                    Long.parseLong(data);
                } catch (Exception e) {
                    throw new Exception("Cella [P" + lineIndex.toString() + "]: il in numero di telefono non è valido");
                }
            }

            // 16 fax ufficio
            data=line.get(16);
            if (!data.isEmpty()){
                if (data.length() > 12){
                    throw new Exception("Cella [Q" + lineIndex.toString() + "]: il fax non è valido");
                }
                try {
                    Long.parseLong(data);
                } catch (Exception e) {
                    throw new Exception("Cella [Q" + lineIndex.toString() + "]: il fax non è valido");
                }
            }

            // 17 qualifica sociale 
            line.set(17, line.get(17).toUpperCase());
            data=line.get(17);
            if (!data.isEmpty() && !Arrays.stream(qualificheSociali).anyMatch(data::equals)) {
                throw new Exception("Cella [R" + lineIndex.toString() + "]: la qualifica sociale non è valida");
            }

            // 18 attivita sociale   
            data=line.get(18);
            if (!data.isEmpty() && data.length()>10) {
                throw new Exception("Cella [S" + lineIndex.toString() + "]: l'attivita sociale non è valida");
            }

            // 19 tipo di certificato medico
            line.set(19, line.get(19).toUpperCase());
            data=line.get(19);
            if (!data.isEmpty() && !(data.equals("N") || data.equals("A"))) {
                throw new Exception("Cella [T" + lineIndex.toString() + "]: il Certificato medico può essere solamente 'N' o 'A'");
            }

            // 20 data di rilascio del certificato medico
            data=line.get(3);
            if (!data.isEmpty()){
                if (data.length() != 10) {
                    throw new Exception("Cella [U" + lineIndex.toString() + "]: la data deve essere nel formato GG/MM/AAAA (es. 01/01/1900)");
                }

                try {
                    SimpleDateFormat sdfrmt = new SimpleDateFormat("dd/MM/yyyy");
                    sdfrmt.setLenient(false);

                    sdfrmt.parse(data);
                } catch (Exception e) {
                    throw new Exception("Cella [U" + lineIndex.toString() + "]: la data deve essere nel formato GG/MM/AAAA (es. 01/01/1900)");
                }

            // 21 qualifica sportiva
            data=line.get(21).toLowerCase().strip().replaceAll(" +", " ");
            if( !data.isEmpty()){
                line.set(21,data.substring(0,1).toUpperCase()+data.substring(1));
                data=line.get(21);
                if (!data.isEmpty() && !Arrays.stream(qualificheSportive).anyMatch(data::equals)) {
                    throw new Exception("Cella [V" + lineIndex.toString() + "]: la qualifica sportiva non è valida");
                }
            }
            
            // 22 attività sportiva
            data=line.get(10);
            if (data.length() > 65) {
                throw new Exception("Cella [W" + lineIndex.toString() + "]: l'ativita sportiva puo avre al massimo 10 caratteri");
            }


            }
        }
    }
}