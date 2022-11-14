
const val ERROR_CARD = -1
const val ERROR_LIMIT = -2
const val AMOUNT_LIMIT_CARD = 150_000
const val PAST_TRANSFERS_LIMIT_CARD = 600_000
const val AMOUNT_LIMIT_VK = 15_000
const val PAST_TRANSFERS_LIMIT_VK = 40_000

fun main() {
    printFee(calculateFee("Mastercard", 30000, 20000))
    printFee(calculateFee("Maestro", 70000, 15000))
    printFee(calculateFee("Mastercard", 30000, 200000))
    printFee(calculateFee("Visa", amount = 15000))
    printFee(calculateFee(amount = 20000))
    printFee(calculateFee("Master Card", 30000, 200000))
}

fun calculateFee(accountType: String = "VK Pay", pastTransfers: Int = 0, amount: Int): Int {
    return when (accountType) {
        "Mastercard", "Maestro" ->  if (amount > AMOUNT_LIMIT_CARD || pastTransfers > PAST_TRANSFERS_LIMIT_CARD) ERROR_LIMIT else if ((amount + pastTransfers) < 75_000) 0 else (((amount+pastTransfers-75_000) * 0.006)).toInt() + 20
        "Visa", "Мир" ->  if (amount > AMOUNT_LIMIT_CARD || pastTransfers > PAST_TRANSFERS_LIMIT_CARD) ERROR_LIMIT else ((amount * 0.0075)).toInt() + 30
        "VK Pay" -> if (amount > AMOUNT_LIMIT_VK || pastTransfers > PAST_TRANSFERS_LIMIT_VK) ERROR_LIMIT else 0
        else -> ERROR_CARD
    }
}

fun printFee(fee: Int) {
    when (fee) {
        ERROR_LIMIT -> println("Превышен лимит!")
        ERROR_CARD -> println("Введены некорректные данные")
        else -> println("Комиссия за перевод составит $fee руб. Продолжить?")
    }
}