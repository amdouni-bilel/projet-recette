package tn.esprit.examengl.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "recette_table")
public class Recette {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "nom")
    private String nom;

    @ColumnInfo(name = "description")
    private String description;

    @ColumnInfo(name = "marque")
    private String marque;



    public Recette( String nom, String description, String marque) {

        this.nom = nom;
        this.description = description;
        this.marque = marque;  }


    @Ignore
    public Recette( String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Recette{" +
                "uid=" + uid +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", marque='" + marque + '\'' +
                '}';
    }
}
