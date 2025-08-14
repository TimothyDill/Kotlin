/* import database.UserDatabase
 import database.User

var userName: String = ""
var pn: Int? = null
var loggedIn = false

fun pin() {
    println("Hello $userName, please input a pin - max of 4 digits")
    val pinTest1 = readln().toInt()
    println("Please confirm your pin.")
    val pinTest2 = readln().toInt()

    if (pinTest1 == pinTest2) {
        val added = UserDatabase.addUser(userName, pinTest1)
        if (added) {
            println("User Created - Returning to Login Screen.\n")
        } else {
            println("User '$userName' already exists!")
        }
        loggedIn = true

        println("Would you like to log in now? (Y/N)")
        if (readln().equals("Y", ignoreCase = true)) {
            existingUser()
        }
    } else {
        pinRetry()
    }
}

fun newUser() {
    println("Please input a user name:")
    userName = readln()
    pin()
}

fun existingUser() {
    println("User Name:")
    userName = readln()

    println("Enter Pin:")
    pn = readln().toIntOrNull()

    val user = UserDatabase.findUser(userName)
    if (user != null && user.pin == pn) {
        loggedIn = true
        println("Login Complete")
    } else {
        println("Wrong Username or Pin, Try again.")
        existingUser()
    }
}

fun pinRetry() {
    println("Pin mismatch. Please try again.")
    pin()
}

fun loginType() {
    println("Are you a new('New') or existing('Existing') user?")
    when (readln().lowercase()) {
        "new" -> newUser()
        "existing" -> existingUser()
        "admin" -> {
            println("Users in database:")
            UserDatabase.getAllUsers().forEach {
                println("Username: ${it.username}, PIN: ${it.pin}")
            }
            userName = "Admin"
            loggedIn = true
        }
        else -> {
            println("Invalid input. Try again.")
            loginType()
        }
    }
}

fun main() {
    val appName = "MiniVis"
    println("Welcome to $appName")
    loginType()

    if (loggedIn) {
        println("Welcome $userName")
    } else {
        println("Error: Login failed")
    }
}




// DB


import java.io.File

data class User(val username: String, val pin: Int)

object UserDatabase {
    private val file = File("users.txt")
    private val users = mutableListOf<User>()

    init {
        if (file.exists()) {
            file.forEachLine { line ->
                val parts = line.split(",")
                if (parts.size == 2) {
                    val username = parts[0]
                    val pin = parts[1].toIntOrNull()
                    if (pin != null) {
                        users.add(User(username, pin))
                    }
                }
            }
        }
    }

    private fun save() {
        file.writeText(users.joinToString("\n") { "${it.username},${it.pin}" })
    }

    fun addUser(username: String, pin: Int): Boolean {
        if (users.any { it.username == username }) return false
        users.add(User(username, pin))
        save()
        return true
    }

    fun findUser(username: String): User? {
        return users.find { it.username == username }
    }

    fun updatePin(username: String, newPin: Int): Boolean {
        val index = users.indexOfFirst { it.username == username }
        return if (index != -1) {
            users[index] = users[index].copy(pin = newPin)
            save()
            true
        } else false
    }

    fun removeUser(username: String): Boolean {
        val removed = users.removeIf { it.username == username }
        if (removed) save()
        return removed
    }

    fun getAllUsers(): List<User> = users.toList()
}

 */