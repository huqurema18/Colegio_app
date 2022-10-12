package com.example.app_notas.db.models;

import java.util.ArrayList;

public class NotaEdit {
    public ArrayList<String> getListMaterias() {
        return listMaterias;
    }
    public int getSize() {
        return listMaterias.size();
    }
    public void setListMaterias(ArrayList<String> listMaterias) {
        this.listMaterias = listMaterias;
    }
    public void setNodoMaterias(String nodo) {
        listMaterias.add(nodo);
    }

    private ArrayList<String> listMaterias=new ArrayList<>();



}
