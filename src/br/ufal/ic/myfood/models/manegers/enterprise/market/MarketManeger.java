package br.ufal.ic.myfood.models.manegers.enterprise.market;


import br.ufal.ic.myfood.exceptions.enterprise.InvalidFormatHourException;
import br.ufal.ic.myfood.exceptions.enterprise.InvalidHourException;
import br.ufal.ic.myfood.exceptions.enterprise.market.InvalidMarketTypeException;


import static java.lang.Integer.parseInt;

public class MarketManeger {
    public static void checkData(String marketType) throws Exception {
        if(marketType == null || marketType.equals("")){
            throw new InvalidMarketTypeException();
        }

    }

}
