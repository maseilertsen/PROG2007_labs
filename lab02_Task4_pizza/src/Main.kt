/**
 * Task 4 - Kotlin lab PROG2007 - Mobile programming course NTNU
 */

// Class
class Pizza(
    val id: Int,
    val name: String,
    val price: Double,
    val topping: List<String>
)

//Order models
data class OrderItem(val pizza: Pizza)
data class Order(val orderId: Int, val items: MutableList<OrderItem> = mutableListOf())

// Initialization of objects
val pizzas = listOf(
    Pizza(1,"Pepperoni", 299.00, listOf("Tomato", "Cheese", "Salami")),
    Pizza(2,"Hawaiian", 179.00, listOf("Cheese", "Salami", "Ham", "Pineapple")),
    Pizza(3,"Veggie", 349.00, listOf("Cheese", "Cheese", "Peppers", "Onions")),
)
// Order state
val orders = mutableListOf<Order>() // List that holds all orders that have been completed (with option 9)
var nextOrderId = 1
var currentOrder: Order? = null // current order status

// Global function to print all pizzas
fun showAllPizzas() {
    print("Show with toppings? (y/n?: ")
    var withToppings = false // default state
    val topping = readln() // Get input from user - NOT SAFEGUARDED!
    if (topping == "y"){
         withToppings = true
    }
        println("\nAll pizzas:")
        for (pizza in pizzas) {
            println("\t" +
                    pizza.id + " - " +
                    pizza.name.padEnd(10) + " - " +
                    pizza.price.toString().padStart(6))

            if (withToppings) {
                println("\tIngredients: "+ pizza.topping.toString().removePrefix("[").removeSuffix("]") + '\n')
            }
    }
}
fun showTopping(){
    printPizza()

    print("\nWhat pizza do you want to show toppings of? (use id): ")
    val pizzaId = readln().toInt()
    for (pizza in pizzas) {
        if (pizza.id == pizzaId) {
            println("\t${pizza.name}-topping: " + pizza.topping.toString().removePrefix("[").removeSuffix("]") + '\n')
        }
    }
}
// Main menu loop
fun menu(){
    do {
        printMenu()
        print("Choose an option: ")
        val opt = readln().toInt() // Reads input from user - NOT SAFEGUARDED!
        when (opt) {
            1 -> showAllPizzas()
            2 -> showTopping()
            3 -> orderPizza()
            0 -> println("----------\nExiting program, bye bye!")
        }
        println() // Newline to make output look nicer
    } while (opt != 0) // Exit loop
}
// Menu printing
fun printMenu(){
    println("1 - Show pizza")
    println("2 - List Topping")
    println("3 - Order pizza")
    println("0 - exit")
}

fun printPizza(){
    println("\nAll pizzas:")
    for (pizza in pizzas) {
        println("\t" +
                pizza.id + " - " +
                pizza.name.padEnd(10))
    }
}

fun orderPizza(){
    do {
       println("\nOptions:\n" +
               "1 - Order pizza\n" +
               "2 - See price\n" +
               "3 - Display all order\n" +
               "4 - Update orders\n" +
               "5 - Delete specific order (by ID)\n" +
               "6 - Delete all orders\n" +
               "9 - Add Order (buy)\n" +
               "0 - exit")
        val opt = readln().toInt()
        when (opt) {
            1 -> addToOrder()
            2 -> totalPrice()
            3 -> listPizzaOrders()
            4 -> updateOrder()
            5 -> deletePizzaOrder()
            6 -> deleteAllOrders()
            9 -> addOrder()
            0 -> println("\t-----\nORDER CANCELLED\n\t-----")
        }

    } while (opt != 0) // exit loop
}

fun addToOrder(){
    printPizza()
    print("What pizza do you want to add?: ")
    val opt = readln().toInt()

    for (pizza in pizzas) {
        if (currentOrder == null) {
            currentOrder = Order(nextOrderId++)
        }
        if (pizza.id == opt) {
            currentOrder!!.items.add(OrderItem(pizza))
            println("added ${pizza.name} to order #${currentOrder!!.orderId}")
        }
    }
}

fun totalPrice() {
    if (activeOrder()) {
        val total = currentOrder!!.items.sumOf { it.pizza.price }
        println("Total: $total")
    }
}

fun listPizzaOrders(){
    if (orders.isEmpty()){println("\t---No orders completed..----")}
    for (order in orders) {
        println("Order: ${order.orderId}")
        for (item in order.items) {
            println("\t- ${item.pizza.name} (${item.pizza.price})")
            }
    }
}

fun addOrder(){
    if (!activeOrder()){ return } // Exits if no orders are recorded.
    println("Adding order #${nextOrderId-1}")
    orders.add(currentOrder!!)
    currentOrder = null

}

// Returns true when there are one or more orders in the "records"
fun activeOrder(): Boolean {
    if (currentOrder == null || currentOrder!!.items.isEmpty()) {
        println("\t--- No active order.---")
        return false // No orders recorded
    } else {
        return true // There are orders in the record! Proceed
    }
}

fun deleteAllOrders(){
    print("Are you sure you want to delete all orders? (y/n?)")
    val opt = readln()
    if (opt == "y" || opt == "yes") { // Not safeguarded!
        orders.clear()
        println("All orders cleared.")
    } else {
        println("--- Delete operation cancelled ---")
    }
}

fun deletePizzaOrder() {
    listPizzaOrders()
    print("what order to delete? (use numeric id): ")
    val opt = readln().toInt()


    // .firstOrNull returns the first object -> else null (and iterates through the collection!)
    val orderToRemove = orders.firstOrNull { it.orderId == opt }
    if (orderToRemove != null){
        orders.remove(orderToRemove)
        println("Removed order #${opt}")
    } else {
        println("No order with id $opt found.")
    }
}

fun updateOrder(){
    if (orders.isEmpty()){
        println("\t---No orders completed..--")
        return
    }

    listPizzaOrders()
    print("What pizza do you want to update? (use numeric id): ")
    val opt = readln().toIntOrNull() ?: return // safer way to handle non-numeric values
    val order = orders.firstOrNull { it.orderId == opt }
    if (order != null) {
        println("No order with id $opt found")
    }

    println("Order #${order!!.orderId} items:")
    order.items.forEachIndexed { index, it ->
        println("${index + 1}.${it.pizza.name} (${it.pizza.price})")
    }
    print("Which to replace? (1..${order.items.size}): ")
    val index = readln().toIntOrNull() ?: return
    if (index !in 1..order.items.size){
        println("invalid index")
        return
    }

    printPizza()
    print("Enter new pizza id: ")
    val newId = readln().toIntOrNull() ?: return
    val newPizza = pizzas.firstOrNull { it.id == newId }
    if (newPizza == null){
        println("No pizza with id $newId found.")
        return
    }

    order.items[index - 1] = OrderItem(newPizza)
    println("Updated order #${order.orderId}: item $index is now ${newPizza.name}")
}

fun main (){
    menu()
}
