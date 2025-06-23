// misClases/GeneradorPersonajes.java
package misClases;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeneradorPersonajes {
    public static List<Personaje> obtenerPersonajesAleatorios() {
    List<Personaje> todos = new ArrayList<>();
    todos.add(new Personaje("Cartel", "cartel.png"));
    todos.add(new Personaje("Armorstand", "armorstand.png"));
    todos.add(new Personaje("Ghast seco", "GhastSeco.png"));
    todos.add(new Personaje("Montura", "montura.png"));
    todos.add(new Personaje("Saco de tinta", "sacodetintabrillante.png"));
    todos.add(new Personaje("Skeleton skull", "skeletonskull.png"));
    todos.add(new Personaje("Tierra", "tierra.png"));
    todos.add(new Personaje("totem", "totem.png"));
    todos.add(new Personaje("Tridente", "tridente.png"));
    todos.add(new Personaje("Vision Nocturna", "visionnocturna.png"));
    todos.add(new Personaje("Leche", "leche.png"));
    todos.add(new Personaje("Afilador", "afilador.png"));
    todos.add(new Personaje("Antorcha", "antorcha.png"));
    todos.add(new Personaje("Ballesta", "ballesta.png"));
    todos.add(new Personaje("Carne", "carnepodrida.png"));
    todos.add(new Personaje("Caña", "cañadepescar.png"));
    todos.add(new Personaje("EndCrystal", "endcrystal.png"));
    todos.add(new Personaje("EnderChest", "enderchest.png"));
    todos.add(new Personaje("EnderPerl", "enderperl.png"));
    todos.add(new Personaje("Escudo", "escudo.png"));
    todos.add(new Personaje("Espada", "espadamadera.png"));
    todos.add(new Personaje("Glowston", "glowston.png"));
    todos.add(new Personaje("Ice", "iceblue.png"));
    todos.add(new Personaje("Lana Rosa", "lanarosa.png"));
    todos.add(new Personaje("Libro Encantado", "libroencantado.png"));
    todos.add(new Personaje("Lingote cobre", "lingotedecobre.png"));
    todos.add(new Personaje("Melon", "melon.png"));
    todos.add(new Personaje("Obsidiana llorosa", "obsidianallorosa.png"));
    todos.add(new Personaje("Ojo de araña", "ojodearaña.png"));
    todos.add(new Personaje("Pararayos", "pararayos.png"));
    todos.add(new Personaje("Pico netherite", "piconetherite.png"));
    todos.add(new Personaje("Pluma", "pluma.png"));
    todos.add(new Personaje("Saplig", "saplig.png"));
    todos.add(new Personaje("Scaffolding", "scaffolding.png"));
        Collections.shuffle(todos);
        return new ArrayList<>(todos.subList(0, 24));
    }
}
