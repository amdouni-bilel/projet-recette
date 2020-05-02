package tn.esprit.examengl.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import tn.esprit.examengl.entity.Recette;


@Dao
public interface RecetteDao {

    @Insert
    void insertOne(Recette pdt);

    @Delete
    void delete(Recette pdt);

    @Query("SELECT * FROM recette_table")
    List<Recette> getAllRecettes();

    @Query("SELECT * FROM recette_table WHERE uid == :id LIMIT 1")
    Recette findById(int id);

    @Query("SELECT * FROM recette_table WHERE nom == :nom LIMIT 1")
    Recette findByNom(String nom);

}
