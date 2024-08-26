package com.yassine.todo.controller

import com.yassine.todo.repository.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class usersController(private val userService: UserService) {
    @GetMapping("/{id}")
    fun getUser(id: Long) = userService.getUser(id)

    @GetMapping("/")
    fun getAllUsers() = userService.getAllUsers()
}

@Controller
@RequestMapping("/")
class HomepageController() {

    @GetMapping("/")
    fun homepageView(model: Model): String {
        return "homepage"
    }
}