
package com.in5bm.david.quinonez.models.domain;

import java.sql.Time;

/**
 *
 * @author informatica
 */
import java.time.LocalTime;

public class Materia {
    private int idMateria;
    private String nombreMateria;
    private int cicloEscolar;
    private LocalTime horarioInicio;
    private LocalTime horarioFinal;
    private String catedratico;
    private String salon;
    private int cupoMaximo;
    private int cupoMinimo;
    private int nota;

    public Materia() {
    }

    public Materia(int idMateria) {
        this.idMateria = idMateria;
    }

    public Materia(int idMateria, String nombreMateria, int cicloEscolar, LocalTime horarioInicio, LocalTime horarioFinal, String catedratico, String salon, int cupoMaximo, int cupoMinimo, int nota) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.cicloEscolar = cicloEscolar;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.catedratico = catedratico;
        this.salon = salon;
        this.cupoMaximo = cupoMaximo;
        this.cupoMinimo = cupoMinimo;
        this.nota = nota;
    }

    public Materia(String nombreMateria, int cicloEscolar, LocalTime horarioInicio, LocalTime horarioFinal, String catedratico, String salon, int cupoMaximo, int cupoMinimo, int nota) {
        this.nombreMateria = nombreMateria;
        this.cicloEscolar = cicloEscolar;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.catedratico = catedratico;
        this.salon = salon;
        this.cupoMaximo = cupoMaximo;
        this.cupoMinimo = cupoMinimo;
        this.nota = nota;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(int cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalTime getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(LocalTime horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public String getCatedratico() {
        return catedratico;
    }

    public void setCatedratico(String catedratico) {
        this.catedratico = catedratico;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getCupoMinimo() {
        return cupoMinimo;
    }

    public void setCupoMinimo(int cupoMinimo) {
        this.cupoMinimo = cupoMinimo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Materia{" + "idMateria=" + idMateria + ", nombreMateria=" + nombreMateria + ", cicloEscolar=" + cicloEscolar + ", horarioInicio=" + horarioInicio + ", horarioFinal=" + horarioFinal + ", catedratico=" + catedratico + ", salon=" + salon + ", cupoMaximo=" + cupoMaximo + ", cupoMinimo=" + cupoMinimo + ", nota=" + nota + '}';
    }
    
}
