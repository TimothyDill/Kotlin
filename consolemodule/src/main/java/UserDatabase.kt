// In UserDatabase.kt
package database

val listOfUsers: MutableList<Set<String>> = mutableListOf()

fun addUserSet(userSet: Set<String>) {
    listOfUsers.add(userSet)
}