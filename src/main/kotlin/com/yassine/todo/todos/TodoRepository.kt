package com.yassine.todo.todos

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {

}

@Service
class TodoService(val todoRepository: TodoRepository) {
    fun getTodoById(id: Long): TodoResponse {
        val todo = todoRepository.findById(id).get()
        return TodoResponse(todo)
    }

    fun getAllTodos(): List<TodoResponse> {
        return todoRepository.findAll().sortedBy { it.createdAt }.map { TodoResponse(it) }
    }

    fun updateTodo(id: Long, newTodo: TodoUpdateRequest) {
        val todo = todoRepository.findById(id).get()
        todo.title = newTodo.title ?: todo.title
        todo.completed = newTodo.completed ?: todo.completed
        todoRepository.save(todo)
    }

    fun deleteTodoById(id: Long) {
        if (!todoRepository.existsById(id)) {
            throw Exception("Todo with id $id not found")
        }
        todoRepository.deleteById(id)
    }


    fun addTodo(todo: TodoCreateRequest) {
        val newTodo = Todo(
            null,
            title = todo.title,
            completed = todo.completed
        )
        todoRepository.save(newTodo)
    }

}