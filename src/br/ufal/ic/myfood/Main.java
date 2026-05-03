package br.ufal.ic.myfood;

import easyaccept.EasyAccept;

public class Main {
    public static void main(String[] args) {
        for(int i = 1; i <= 6; i++) {
            for(int j = 1; j <= 2; j++) {
                EasyAccept.main(new String[] {"br.ufal.ic.myfood.Facade", "tests/us"+i+"_"+j+".txt"});
            }
        }

    }
}
