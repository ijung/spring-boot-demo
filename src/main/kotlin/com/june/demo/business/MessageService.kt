package com.june.demo.business

import com.june.demo.business.dtos.MessageRequestDto
import com.june.demo.business.dtos.MessageResponseDto
import com.june.demo.data.MessageRepository
import com.june.demo.data.entities.MessageEntity
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val repository: MessageRepository
) {
    fun save(dto: MessageRequestDto): Map<Long, MessageResponseDto> {
        val requestEntity = MessageRequestDto.Mapper.toMessageEntity(dto)
        val responseEntity = repository.save(requestEntity)
        return responseEntity.let { mapOf(it.id!! to MessageResponseDto.Mapper.by(it)) }
    }

    fun findAll(): Map<Long, MessageResponseDto> {
        val responseEntities = repository.findAll()
        return responseEntities.associateBy({ it.id!! }, { MessageResponseDto.Mapper.by(it) })
    }

    private fun findByIdOrThrow(id: Long): MessageEntity = repository.findByIdOrNull(id) ?:
        throw EntityNotFoundException("메시지가 없습니다(findByIdOrThrow): ID - $id")

    fun findById(id: Long): MessageResponseDto {
        val entity = findByIdOrThrow(id)
        return entity.let { MessageResponseDto.Mapper.by(it) }
    }

    fun save(id: Long, dto: MessageRequestDto): MessageResponseDto {
        val entity = findByIdOrThrow(id)
        MessageRequestDto.Mapper.updateMessageEntity(dto, entity)
        val responseEntity = repository.save(entity)
        return responseEntity.let { MessageResponseDto.Mapper.by(it) }
    }

    fun delete(id: Long) {
        val entity = findByIdOrThrow(id)
        repository.delete(entity)
    }
}
