fun main() {
    calculateFee("Mastercard", 30000.00, 20000.00)
    calculateFee("Maestro", 70000.00, 15000.00)
    calculateFee("Visa", amount = 15000.00)
    calculateFee(amount = 15000.00)
}

fun calculateFee(accountType: String = "VK Pay", transferSumMonth: Double = 0.0, amount: Double) {
    val fee = when (accountType) {
        "Mastercard", "Maestro" -> if ((amount + transferSumMonth) < 75_000.00) 0 else ((amount+transferSumMonth-75_000.00) * 0.006) + 20.00
        "Visa", "Мир" -> (amount * 0.0075) + 30.00
        else -> 0
    }
    println("Комиссия за перевод с Вашего счета/карты $accountType на сумму $amount составит $fee рублей. Продолжить?")
}