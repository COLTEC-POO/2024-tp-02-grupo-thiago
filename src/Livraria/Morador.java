package Livraria;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Morador extends Usuario{
    Morador(String nome, String cpf, Date data, int id){
        super(nome, cpf, data, id, 2);
    }
}




