package com.in5bm.david.quinonez.models.idao;

import com.in5bm.david.quinonez.models.domain.Materia;
import java.util.List;
import javafx.collections.ObservableList;

public interface IMateriaDAO {

    public ObservableList getAll();

    public int add(Materia materia);

    public int update(Materia materia);

    public int delete(Materia materia);

}
