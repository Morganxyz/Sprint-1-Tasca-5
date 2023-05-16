import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Properties;

public class LlistatEx3 {

    public void Ex3(File ruta) {

        String[] archivo = ruta.list();
        Arrays.sort(archivo); // ordenar array del String

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File("D:/S1T5N2Ex1/src/PropertiesFile.properties")));

            File pathWriter  = new File(properties.getProperty("path"));
            FileWriter writer = new FileWriter(pathWriter,true);

            for (int i = 0; i < archivo.length; i++) {
                writer.write("\n" + archivo[i]);
                String archivos = archivo[i];

                File f = new File(ruta.getAbsolutePath(), archivos);

                if (f.isDirectory()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    File[] ruta2 = f.listFiles();
                    Arrays.sort(ruta2);

                    for (File f1 : ruta2) {
                        writer.write("\n" + (String.format("%s (%s) -  %s",
                                f1.getName(),
                                f1.isDirectory() ? "Carpeta" : "Archivo",
                                sdf.format(f1.lastModified()))));

                        if(f1.isDirectory()) {
                            Ex3(f1);
                        }
                    }
                }
            }
            writer.close();
        } catch (IOException e) {

        }
    }
    public File Properties(){

        Properties properties = new Properties();
        File ruta = null;

        try {

            properties.load(new FileInputStream(new File("D:/S1T5N2Ex1/src/PropertiesFile.properties")));
            ruta = new File(properties.getProperty("nomDirectori"));

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e ){
            e.printStackTrace();
        }
        return ruta;
    }

    }

