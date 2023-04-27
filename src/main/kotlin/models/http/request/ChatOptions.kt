package models.http.request

data class ChatOptions (
   val model: String = "gpt-3.5-turbo",
   val temperature: Double? = 0.8,
   val maxTokens: Int? = 550,

   val presencePenalty: Double? = null,
   val frequencyPenalty: Double? = null,

   val n: Int? = null,
   val logitBias: Map<String, Int>? = null,
   val stream: Boolean? = null,
   val topP: Double? = null,
   val user: String? = null,
)