import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;


public class ClasseBase implements Serializable {

    private static final long serialVersionUID = 1L; //evitar errores en recepción del array de objetos
    private String ruta;

    public ClasseBase() {

    }

    public ClasseBase(String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return this.ruta;
    }


    public void Ex1() {

        File ruta = new File(".");
        File[] archivos;
        if (ruta.exists()) {
            if (ruta.isDirectory()) {
                archivos = ruta.listFiles();
                for (int i = 0; i < archivos.length; i++) {
                    System.out.println(archivos[i].getName());
                }
            }
        }
    }

        public void Ex2(File ruta) { //Con recursividad

            String[] archivo = ruta.list();
            Arrays.sort(archivo); // ordenar array del String

            for (int i = 0; i < archivo.length; i++) {
                System.out.println(archivo[i]);
                String archivos = archivo[i];

                File f = new File(ruta.getAbsolutePath(), archivos);

                if (f.isDirectory()) {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            File[] ruta2 = f.listFiles();
                            Arrays.sort(ruta2);

                            for (int j = 0; j < ruta2.length; j++) {

                                System.out.println(String.format("%s (%s) -  %s",
                                        ruta2[j].getName(),
                                        ruta2[j].isDirectory() ? "Carpeta" : "Archivo",
                                        sdf.format(ruta2[j].lastModified())));

                               if(ruta2[j].isDirectory()) {
                                   Ex2(ruta2[j]);
                               }

                            }

        }
}
    }

    public void Ex3(File ruta) {

        String[] archivo = ruta.list();
        Arrays.sort(archivo); // ordenar array del String

        try {
            FileWriter writer = new FileWriter("nouArxiu.txt",true);

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

    public void Ex4(File ruta) {

        String[] archivo = ruta.list();
        Arrays.sort(archivo); // ordenar array del String

        try {
            FileWriter writer = new FileWriter("nouArxiu.txt",true);

            for (int i = 0; i < archivo.length; i++) {
                writer.write("\n" + archivo[i]);
                String archivos = archivo[i];

                File f = new File(ruta.getAbsolutePath(), archivos);

                if (f.isDirectory()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                    File[] ruta2 = f.listFiles();
                    Arrays.sort(ruta2);

                    for (int j = 0; j < ruta2.length; j++) {
                        writer.write("\n" + (String.format("%s (%s) -  %s",
                                ruta2[j].getName(),
                                ruta2[j].isDirectory() ? "Carpeta" : "Archivo",
                                sdf.format(ruta2[j].lastModified()))));

                        if(ruta2[j].isDirectory()) {
                            Ex2(ruta2[j]);
                        }
                    }
                }
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("No s´ha pogut crear l´arxiu");
        }
        try {
            FileReader reader = new FileReader("nouArxiu.txt");
            BufferedReader bufread = new BufferedReader(reader);

            String linea = "";
            while (linea != null) {
                linea = bufread.readLine();

                if (linea != null) {
                    System.out.println(linea);
                }
            }
            reader.close();

        } catch (IOException e) {
            System.out.println("L´arxiu no s´ha trovat");

        }

    }

    public void Ex5(ClasseBase[] arrayObjLanzado) {

        try { //Serialización
            ObjectOutputStream out1 = new ObjectOutputStream(new FileOutputStream("out.ser"));
            out1.writeObject(arrayObjLanzado);
            out1.close();

            //Desserialización
            ObjectInputStream in1 = new ObjectInputStream(new FileInputStream("out.ser"));
            ClasseBase[] arrayObjRecuperado = (ClasseBase[]) in1.readObject();
            in1.close();

            for (ClasseBase c : arrayObjRecuperado) {
                System.out.println("\n" + c.getRuta());
            }

        } catch (Exception e) {
        }

    }
}






