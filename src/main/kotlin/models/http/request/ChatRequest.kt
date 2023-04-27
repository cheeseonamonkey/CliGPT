@file:OptIn(ExperimentalSerializationApi::class, ExperimentalSerializationApi::class)

package models.http.request

import kotlinx.serialization.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Message
import org.json.JSONObject

@Serializable
data class ChatRequest(
   val model : String,
   val messages: List<Message>,
   @SerialName("max_tokens")
   val maxTokens: Int?,
   val temperature: Double? = null,

   @SerialName("presence_penalty")
   val presencePenalty: Double? = null,
   @SerialName("frequency_penalty")
   val frequencyPenalty: Double? = null,

   val stream: Boolean? = null,
   @SerialName("logit_bias")
   val logitBias: Map<String, Int>? = null,

   ){
   constructor(
      messages: List<Message>,
      options: ChatOptions
   ) : this(
      options.model,
      messages,
      options.maxTokens,
      options.temperature,
      options.presencePenalty,
      options.frequencyPenalty,
      options.stream,
      options.logitBias,
   )



   fun toJsonStr() = Json.encodeToString(this)
   fun toJsonObject() = JSONObject( this.toJsonStr() )

}

