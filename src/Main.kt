import database.addUserSet
import database.listOfUsers

// Whitespace
var pinTest1 : Int? = null
var pinTest2 : Int? = null
var userSet : Set<String> = emptySet()
var userName : String = ""
var usr : String? = null
var pn : Int? = null
var pin: String = ""
// ---

fun pin() {
    println("Hello, $userName, Please input a pin - max of 4 digits")
    pinTest1 = readln().toInt()
    println("Please confirm your pin.")
    pinTest2 = readln().toInt()
    if (pinTest1 == pinTest2) {
        println("User Created - Returning to Login Screen.")
        pin = pinTest1.toString()
            userSet = setOf(userName, pin) // Need to find a way to make a table for these?
        addUserSet(userSet)
    } else {
        pinRetry()
    }
}


fun newUser() {
    println("Hello new user: please input a user name:")
    userName = readln()
    pin()
    }

fun pinRetry(){
    println("Please try again, pin mismatch")
    pin()

}

fun loginType() {
    println("Are you a new('New') or existing('Existing) user?")
    val loginType : String = readln().lowercase()
        when (loginType) {
            "new" -> newUser()
            "existing" -> {
                println("User Name:")
                usr = readln()
                println("Enter Pin:")
                pn = readln().toInt()

                println("Complete Login")
            }
            "admin" -> {
                println("Users:")
                println(listOfUsers)
            }
            else -> {
                print("Try again")
                loginType()
            }
    }
}

fun main() {
    val appName = "MiniVis"
    println("Welcome to $appName")
    loginType()

}