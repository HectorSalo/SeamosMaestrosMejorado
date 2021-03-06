package com.google.firebase.example.seamosmejoresmaestros.Constructores;

import java.util.Date;

public class PublicadoresConstructor {
    private String NombrePublicador, ApellidoPublicador, telefono, correo, genero,idPublicador, imagen;
    private Double grupo;
    private Date ultAsignacion, ultAyudante, ultSustitucion, viejaAsignacion, viejaAyudante, viejaSustitucion;
    private boolean anciano, ministerial, precursor, superintendente, auxiliar;

    public PublicadoresConstructor() {}

    public String getNombrePublicador() {
        return NombrePublicador;
    }

    public String getApellidoPublicador() {
        return ApellidoPublicador;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getGenero() {
        return genero;
    }

    public String getIdPublicador() {
        return idPublicador;
    }

    public String getImagen() {
        return imagen;
    }

    public Date getUltAsignacion() {
        return ultAsignacion;
    }

    public Date getUltAyudante() {
        return ultAyudante;
    }

    public Date getUltSustitucion() {
        return ultSustitucion;
    }

    public Date getViejaAsignacion() {
        return viejaAsignacion;
    }

    public Date getViejaAyudante() {
        return viejaAyudante;
    }

    public Date getViejaSustitucion() {
        return viejaSustitucion;
    }

    public Double getGrupo() {
        return grupo;
    }

    public boolean isAnciano() {
        return anciano;
    }

    public boolean isMinisterial() {
        return ministerial;
    }

    public boolean isPrecursor() {
        return precursor;
    }

    public boolean isSuperintendente() {
        return superintendente;
    }

    public boolean isAuxiliar() {
        return auxiliar;
    }

    public void setNombrePublicador(String nombrePublicador) {
        NombrePublicador = nombrePublicador;
    }

    public void setApellidoPublicador(String apellidoPublicador) {
        ApellidoPublicador = apellidoPublicador;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setIdPublicador(String idPublicador) {
        this.idPublicador = idPublicador;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public void setUltAsignacion(Date ultAsignacion) {
        this.ultAsignacion = ultAsignacion;
    }

    public void setUltAyudante(Date ultAyudante) {
        this.ultAyudante = ultAyudante;
    }

    public void setUltSustitucion(Date ultSustitucion) {
        this.ultSustitucion = ultSustitucion;
    }

    public void setViejaAsignacion(Date viejaAsignacion) {
        this.viejaAsignacion = viejaAsignacion;
    }

    public void setViejaAyudante(Date viejaAyudante) {
        this.viejaAyudante = viejaAyudante;
    }

    public void setViejaSustitucion(Date viejaSustitucion) {
        this.viejaSustitucion = viejaSustitucion;
    }

    public void setGrupo(Double grupo) {
        this.grupo = grupo;
    }

    public void setAnciano(boolean anciano) {
        this.anciano = anciano;
    }

    public void setMinisterial(boolean ministerial) {
        this.ministerial = ministerial;
    }

    public void setPrecursor(boolean precursor) {
        this.precursor = precursor;
    }

    public void setSuperintendente(boolean superintendente) {
        this.superintendente = superintendente;
    }

    public void setAuxiliar(boolean auxiliar) {
        this.auxiliar = auxiliar;
    }
}
