package com.example.projeto.model;

public class ItensSobre {
    String textviewSobre;
    String textviewButtom;
    String textviewContatos;
    String textviewNumero;
    String textviewEmail;

    public ItensSobre(String textviewSobre, String textviewButtom, String textviewContatos, String textviewNumero, String textviewEmail) {
        this.textviewSobre = textviewSobre;
        this.textviewButtom = textviewButtom;
        this.textviewContatos = textviewContatos;
        this.textviewNumero = textviewNumero;
        this.textviewEmail = textviewEmail;
    }

    public String getTextviewSobre() {
        return textviewSobre;
    }

    public String getTextviewButtom() {
        return textviewButtom;
    }

    public String getTextviewContatos() {
        return textviewContatos;
    }

    public String getTextviewNumero() {
        return textviewNumero;
    }

    public String getTextviewEmail() {
        return textviewEmail;
    }
}
