package machine

val ESPRESSO_RECIPE: Map<String, Int> = mapOf("waterPerCup" to 250, "milkPerCup" to 0, "beansPerCup"
        to 16, "cost" to 4)
val LATTE_RECIPE: Map<String, Int> = mapOf("waterPerCup" to 350, "milkPerCup" to 75, "beansPerCup"
        to 20, "cost" to 7)
val CAPPUCCINO_RECIPE: Map<String, Int> = mapOf("waterPerCup" to 200, "milkPerCup" to 100, "beansPerCup"
        to 12, "cost" to 6)

class CoffeeMachine(private var water: Int = 400,
                    private var milk: Int = 540,
                    private var beans: Int = 120,
                    private var cups: Int = 9,
                    private var money: Int = 550
) {

    /* Functionality */

    /* Status printer */
    private fun printStatus() {
        println("""The coffee machine has:
            ${this.water} ml of water
            ${this.milk} ml of milk
            ${this.beans} g of coffee beans
            ${this.cups} disposable cups
            $${this.money} of money""".trimIndent()
        )
    }

    /* Refill */
    private fun fillMachine(waterRefill: Int, milkRefill: Int, beansRefill: Int, cupsRefill: Int) {
        this.water += waterRefill
        this.milk += milkRefill
        this.beans += beansRefill
        this.cups += cupsRefill
    }

    private fun fillMenu() {
        println("Write how many ml of water you want to add:")
        val waterInput: Int = readln().toInt()
        println("Write how many ml of milk you want to add:")
        val milkInput: Int = readln().toInt()
        println("Write how many grams of coffee beans you want to add:")
        val beansInput: Int = readln().toInt()
        println("Write how many disposable cups you want to add:")
        val cupsInput: Int = readln().toInt()

        this.fillMachine(waterInput, milkInput, beansInput, cupsInput)
    }

    /* Emptying cash */
    private fun takeMoney() {
        println("I gave you $${this.money}")
        this.money = 0
    }

    /* Sell Coffee */
    private fun coffeeMenu() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:, back")
        when (readln()) {
            "1" -> makeCoffee(
                ESPRESSO_RECIPE["waterPerCup"]!!, ESPRESSO_RECIPE["milkPerCup"]!!,
                ESPRESSO_RECIPE["beansPerCup"]!!, ESPRESSO_RECIPE["cost"]!!)
            "2" -> makeCoffee(LATTE_RECIPE["waterPerCup"]!!, LATTE_RECIPE["milkPerCup"]!!,
                LATTE_RECIPE["beansPerCup"]!!, LATTE_RECIPE["cost"]!!)
            "3" -> makeCoffee(CAPPUCCINO_RECIPE["waterPerCup"]!!, CAPPUCCINO_RECIPE["milkPerCup"]!!,
                CAPPUCCINO_RECIPE["beansPerCup"]!!, CAPPUCCINO_RECIPE["cost"]!!)
            "back" -> {}
            else -> println("Unknown recipe")
        }
    }

    private fun makeCoffee(water: Int, milk: Int, beans: Int, money: Int) {
        when {
            this.water < water -> println("Sorry, not enough water!")
            this.milk < milk -> println("Sorry, not enough milk!")
            this.beans < beans -> println("Sorry, not enough coffee beans")
            this.cups < 1 -> println("Sorry, not enough disposable cups")
            else -> {
                println("I have enough resources, making you a coffee!")
                this.water -= water
                this.milk -= milk
                this.beans -= beans
                this.cups -= 1
                this.money += money
            }
        }

    }

    /* Main Menu */
    fun mainMenu() {
        var userInput = ""
        while(userInput != "exit") {
            when(userInput) {
                "buy" -> coffeeMenu()
                "fill" -> fillMenu()
                "take" -> takeMoney()
                "remaining" -> printStatus()
                else -> {}
            }
            println("Write action (buy, fill, take, remaining, exit):")
            userInput = readln().lowercase()
        }
    }
}