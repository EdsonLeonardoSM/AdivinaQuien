/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package misClases;

import javax.swing.ImageIcon;
import java.io.Serializable;

public class Personaje implements Serializable {
    private String nombre;
    private String rutaImagen; // solo guardamos el nombre o ruta

public Personaje(String nombre, String rutaImagen) {
    this.nombre = nombre;
    this.rutaImagen = "/imagenes/" + rutaImagen;
}

    public String getNombre() { return nombre; }

    public String getRutaImagen() { return rutaImagen; }

    public ImageIcon getImagen() {
        return new ImageIcon(getClass().getResource(rutaImagen)); // carga local
    }
}
