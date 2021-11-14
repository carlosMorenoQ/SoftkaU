package codigo;

import java.io.*;

public class LecturaDB {
    BufferedReader bufeer;
    String archivoDB = "baseDatos.txt";

    public void leer() throws FileNotFoundException {
        FileReader archivo = new FileReader(archivoDB);
        bufeer = new BufferedReader(archivo);
    }

    public String[] buscarTraduccion(String palabra) throws IOException {
        String lectura = "";
        String[] retorno = null;
        leer();
        while (lectura != null) {
            lectura = bufeer.readLine();
            retorno = lectura.split(",");
            if (retorno[0].equals(palabra))
                return retorno;
        }
        return retorno;
    }

    public void registrarPalabra(String registro) throws IOException {
        FileWriter escritura = new FileWriter(archivoDB, true);
        escritura.write(registro + "\n");
        escritura.close();
    }

}
