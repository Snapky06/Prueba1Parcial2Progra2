/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_1;


import java.io.File;
import java.io.IOException;
import java.util.Date;

public class MyFile {

    private File file = null;

    public void setFile(String dir) {
        file = new File(dir);
    }

    public void info() {
        if (file.exists()) {
            System.out.println("\n SI EXISTE: \n-----------");
            System.out.println("Nombre: " + file.getName());
            System.out.println("Path: " + file.getPath());
            System.out.println("Absoluto: " + file.getAbsolutePath());
            System.out.println("Padre: " + file.getAbsoluteFile().getParentFile().getParent());
            System.out.println("Bytes: " + file.length());

            if (file.isFile()) {
                System.out.println("ES UN ARCHIVO");
            } else if (file.isDirectory()) {
                System.out.println("ES UN FOLDER");
            }

            System.out.println("Ultima Modif: " + new Date(file.lastModified()));
        } else {
            System.out.println("Aun no existe");
        }
    }

    public void crearArchivo() throws IOException {
    if (file.createNewFile()) {
        System.out.println("Archivo creado con exito!");
    } else {
        System.out.println("No se pudo crear: ya existe.");
    }
}

public void crearFolder() {
    if (file.mkdir()) {
        System.out.println("Carpeta creada con exito!");
    } else {
        System.out.println("No se pudo crear (ya existe o error en la ruta).");
    }
}

    public void borrarDirectorio() {
        if (file != null && file.isDirectory()) {
            File[] archivos = file.listFiles();
            if (archivos != null) {
                for (File f : archivos) {
                    if (f.delete()) {
                        System.out.println("Borrado: " + f.getAbsolutePath());
                    } else {
                        System.out.println("No se pudo borrar: " + f.getAbsolutePath());
                    }
                }
            }
            if (file.delete()) {
                System.out.println("Directorio borrado: " + file.getAbsolutePath());
            } else {
                System.out.println("No se pudo borrar el directorio: " + file.getAbsolutePath());
            }
        } else {
            System.out.println("El path no es un directorio o no existe");
        }
    }

    private void tree(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.println(tab + dir.getName());
            for (File child : dir.listFiles()) {
                if (!child.isHidden()) {
                    tree(child, tab + "-");
                }

            }
        }

    }
    
    void tree(){
    tree(file,"-");
    }
    
   public void MostrarDir() {

    if (!file.exists() || !file.isDirectory()) {
        System.out.println("El path no es un directorio o no existe");
        return;
    }

    System.out.println("Directorio de: " + file.getAbsolutePath());
    System.out.println("Ultima Modificacion      Tipo        Tamano         Nombre");

    File[] archivos = file.listFiles();
    if (archivos == null) {
        System.out.println("(No se pudo leer el contenido)");
        return;
    }

    int numarchivos = 0;
    int directorios = 0;
    long totalBytes = 0;

    String[] tamanos = new String[archivos.length];
    int maxTamLen = 1;
    for (int i = 0; i < archivos.length; i++) {
        File a = archivos[i];
        String tam = a.isDirectory() ? "-" : tipotam(a.length());
        tamanos[i] = tam;
        if (tam.length() > maxTamLen) maxTamLen = tam.length();
    }

    for (int i = 0; i < archivos.length; i++) {
        File arch = archivos[i];

        String fecha = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                .format(new java.util.Date(arch.lastModified()));
        String tipo = arch.isDirectory() ? "<DIR>" : "FILE";
        String tam  = tamanos[i];
        String nombre = arch.getName();

        String linea = fecha;

        linea += "     " + tipo;
        for (int j = tipo.length(); j < 8; j++) linea += " ";

        linea += "        " + tam;
        for (int j = tam.length(); j < maxTamLen; j++) linea += " ";

        linea += "      " + nombre;

        System.out.println(linea);

        if (arch.isDirectory()) {
            directorios++;
        } else {
            numarchivos++;
            totalBytes += arch.length();
        }
    }

    System.out.println();
    System.out.println(numarchivos + " archivos    " + tipotam(totalBytes));

    double libresGB = file.getFreeSpace() / (1024.0 * 1024 * 1024);
    System.out.println(directorios + " directorios "
            + String.format(java.util.Locale.US, "%.1f GB", libresGB) + " libres");
}

private static String tipotam(long bytes) {
    if (bytes < 1024) return bytes + " B";
    double b = bytes;
    String[] u = {"KB","MB","GB"};
    int i = 0;
    while (b >= 1024 && i < u.length - 1) {
        b /= 1024.0;
        i++;
    }
    return String.format(java.util.Locale.US, "%.1f %s", b, u[i]);
}




}

