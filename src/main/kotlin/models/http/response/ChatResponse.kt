@file:OptIn(ExperimentalSerializationApi::class)

package models.http.response


import kotlinx.serialization.*
import models.Message

@Serializable
data class ChatResponse(
   val id: String,
   @SerialName("object")
   val objectType: String,
   val created: Long,
   val choices: List<MessageChoice>,
   val usage: TokenUsage,
   val model :String,
) {
   val firstChoice: String get() = choices.firstOrNull()?.message?.content!!

   @Serializable
   data class MessageChoice(
      val index: Int,
      val message: Message,
      val finish_reason: String
   )

   @Serializable
   data class TokenUsage(
      val prompt_tokens: Int,
      val completion_tokens: Int,
      val total_tokens: Int
   )

}

