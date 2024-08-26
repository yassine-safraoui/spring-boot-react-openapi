package com.yassine.todo.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null
) {


}