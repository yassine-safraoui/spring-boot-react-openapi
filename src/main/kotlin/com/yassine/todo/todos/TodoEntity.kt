package com.yassine.todo.todos

import jakarta.annotation.PostConstruct
import jakarta.persistence.*
import java.util.Date


@Entity
@Table(name = "todos")
public class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null,
    var title: String? = null,
    var completed: Boolean = false,
    @Temporal(TemporalType.TIMESTAMP)
    var createdAt: Date = Date(),
)

public data class TodoUpdateRequest(
    val title: String? = null,
    val completed: Boolean? = null
) {
    @PostConstruct
    fun validate() {
        if (this.title == null && this.completed == null) {
            throw Exception("At least one field must be provided")
        }
    }
}

public data class TodoCreateRequest(
    val title: String,
    val completed: Boolean
)

public data class TodoResponse(
    val id: Long,
    val title: String,
    val completed: Boolean
) {
    constructor(todo: Todo) : this(todo.id!!, todo.title!!, todo.completed)
}