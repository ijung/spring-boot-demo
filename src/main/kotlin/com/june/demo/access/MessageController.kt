package com.june.demo.access

import com.june.demo.business.MessageService
import com.june.demo.business.dtos.MessageRequestDto
import com.june.demo.business.dtos.MessageResponseDto
import jakarta.persistence.EntityNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/messages")
class MessageController(
    private val service: MessageService
) {
    @PostMapping()
    fun save(@RequestBody dto: MessageRequestDto): Map<Long, MessageResponseDto> = service.save(dto)

    @GetMapping
    fun findAll(): Map<Long, MessageResponseDto> = service.findAll()

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): MessageResponseDto = service.findById(id)

    @PutMapping("/{id}")
    fun save(@PathVariable id: Long, @RequestBody dto: MessageRequestDto): MessageResponseDto = service.save(id, dto)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable id: Long) = service.delete(id)

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFound(ex: EntityNotFoundException): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.message)
    }
}