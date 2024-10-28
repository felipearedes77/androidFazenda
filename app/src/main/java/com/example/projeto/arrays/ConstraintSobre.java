package com.example.projeto.arrays;

import com.example.projeto.fragments.SobreFragment;
import com.example.projeto.model.ItensSobre;

import java.util.ArrayList;

public class ConstraintSobre {

    public static ArrayList<ItensSobre> getSobreData() {
        ArrayList<ItensSobre> sobreList = new ArrayList<>();
        String desc = "Uma fazenda urbana, dedicada à produção e distribuição de alimentos naturais, busca otimizar seus processos para garantir a melhor qualidade possível dos frutos colhidos. O foco é melhorar a comunicação entre os setores da fazenda e facilitar o acesso às informações sobre cada etapa da produção, desde o planejamento até a distribuição dos alimentos.\n" +
                "Sistema adotado pela fazenda auxiliará no controle das quantidades colhidas e doadas, permitindo um monitoramento mais preciso e contribuindo para a preservação dos recursos naturais. Além disso, a fazenda implementa práticas de cultivo que visam melhorar a qualidade dos frutos, como o uso eficiente da segurança, técnicas de compostagem para enriquecer o solo e o manejo sustentável das plantações, garantindo uma colheita saudável e nutritiva. Através dessas práticas, o objetivo não é apenas aumentar a produtividade, mas também reduzir o desperdício e promover uma produção mais sustentável.\n" +
                "Além disso, a fazenda realiza parcerias com instituições de pesquisa e organizações ambientais para desenvolver métodos de cultivo mais inovadores e sustentáveis. Essas colaborações proporcionaram acesso a conhecimentos e recursos que podem ser fundamentais para a implementação de práticas agrícolas avançadas." ;

        ItensSobre sobre1 = new ItensSobre("Sobre",desc,"Contatos","+55 19 77889 - 1344","fazendinha@colheita.com.br");
        sobreList.add(sobre1);

        return sobreList;


    }
}
