package com.yassine.todo.todos

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/todos")
class TodosController(val todoService: TodoService) {

    @GetMapping("/")
    fun getAllTodos() =
        todoService.getAllTodos()

    @PostMapping("/")
    fun addTodo(@RequestBody todo: TodoCreateRequest) =
        todoService.addTodo(todo)

    @PostMapping("/{id}")
    fun updateTodo(@PathVariable id: Long, @RequestBody todo: TodoUpdateRequest) =
        todoService.updateTodo(id, todo)

    @DeleteMapping("/{id}")
    fun deleteTodoById(@PathVariable id: Long) =
        todoService.deleteTodoById(id)

}