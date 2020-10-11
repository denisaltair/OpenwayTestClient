package kz.multibank.cardposclient.entities

import java.lang.Exception
import java.lang.IllegalArgumentException

enum class Currency (var longName:String, var shortName:String, var symbol:String, var code:String){
    KZT("Тенге", "тг", "₸","398"),
    RUB("Рубль", "руб", "₽","643"),
    USD("Доллар", "дол", "$","840");

    companion object {
        fun valueOfFromCode(code: String): Currency {
            for (currency in Currency.values()) {
                if (currency.code == code) return currency
            }
            throw IllegalArgumentException()
        }
    }



}