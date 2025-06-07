/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misClases;

import javax.swing.ImageIcon;

/**
 *
 * @author Edson Leonardo
 */
public class Personaje {
   private String nombre;
    private ImageIcon imagen;

    public Personaje(String nombre, String rutaImagen) {
        this.nombre = nombre;

        // Cargar imagen como recurso del classpath
        var url = getClass().getClassLoader().getResource(rutaImagen);
        if (url != null) {
            this.imagen = new ImageIcon(url);
        } else {
            System.out.println("No se encontró la imagen: " + rutaImagen);
            this.imagen = new ImageIcon(); // imagen vacía
        }
    }

    public String getNombre() {
        return nombre;
    }

    public ImageIcon getImagen() {
        return imagen;
    }
}