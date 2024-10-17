package com.example.projeto.model;

public class Food {
    private int imgFood;
    private String nameFood;
    private String descriscaoFood;
    private String preco;


    public Food(int imgFood, String nameFood, String descriscaoFood, String preco) {
        this.imgFood = imgFood;
        this.nameFood = nameFood;
        this.descriscaoFood = descriscaoFood;
        this.preco = preco;
    }

    public int getImgFood() {
        return imgFood;
    }



    public String getNameFood() {
        return nameFood;
    }



    public String getDescriscaoFood() {
        return descriscaoFood;
    }



    public String getPreco() {
        return preco;
    }


}
