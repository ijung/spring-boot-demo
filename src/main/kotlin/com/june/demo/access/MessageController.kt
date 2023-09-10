package com.june.demo.access

import com.june.demo.data.MessageRepository
import com.june.demo.data.entities.MessageEntity
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/messages")
class MessageController(
    private val repository: MessageRepository
) {
    @PostMapping()
    fun save(@RequestBody entity: MessageEntity): MessageEntity = repository.save(entity)

    @GetMapping
    fun findAll(): List<MessageEntity> = repository.findAll().toList()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): MessageEntity? = repository.findByIdOrNull(id)

    @PutMapping("/{id}")
    fun save(@PathVariable id: Long, @RequestBody entity: MessageEntity): MessageEntity {
        val originEntity = repository.findById(id).get()
        originEntity.userId = entity.userId
        originEntity.message = entity.message
        return repository.save(originEntity)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Long) = repository.deleteById(id)
}