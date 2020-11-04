package br.com.caelum.ecommerce.dominio;

import javax.money.CurrencyUnit;
import javax.money.Monetary;

public class SystemUtils {

	public static final CurrencyUnit REAIS = Monetary.getCurrency("BRL");
}
