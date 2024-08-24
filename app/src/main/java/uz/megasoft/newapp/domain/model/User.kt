package uz.megasoft.newapp.domain.model

data class User(
    val uid: String,
    val name: String,
    val email: String,
    val phone: String? = null
)
