import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recetario.Receta

@Dao
interface RecetaDao {
    @Insert
    fun insert(receta: Receta)

    @Query("SELECT * FROM recetas WHERE tipo = :tipo")
    fun getRecetasByTipo(tipo: String): List<Receta>
}
