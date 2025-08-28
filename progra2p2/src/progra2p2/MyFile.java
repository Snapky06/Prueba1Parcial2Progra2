package progra2p2;

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
    


}
