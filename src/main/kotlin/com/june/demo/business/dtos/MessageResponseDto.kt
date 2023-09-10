package com.june.demo.business.dtos

import com.june.demo.data.entities.MessageEntity
import org.mapstruct.Mapper
import org.mapstruct.factory.Mappers
import java.time.LocalDateTime

data class MessageResponseDto(
    val userId: Long,

    val message: String,

    val createdDate: LocalDateTime,

    val lastModifiedDate: LocalDateTime
) {
    @Mapper
    abstract class DtoMapper {
        abstract fun by(entity: MessageEntity): MessageResponseDto

        fun toLong(nullable: Long?): Long = nullable!!

        fun toLocalDateTime(nullable: LocalDateTime?): LocalDateTime = nullable!!
    }
    companion object {
        val Mapper: DtoMapper = Mappers.getMapper(DtoMapper::class.java)
    }
}