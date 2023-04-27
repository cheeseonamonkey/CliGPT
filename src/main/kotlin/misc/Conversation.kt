package misc

import models.Message
import models.http.request.ChatOptions
import models.http.request.ChatRequest



class Conversation(
   private val authToken :String,
   private val chatOptions: ChatOptions = ChatOptions(),
   private val systemMessage:String = "",

) : IRequestsHttp {

   override val requester: Requester = Requester(authToken)
   private val messages: MutableList<Message> = mutableListOf( Message("system", systemMessage) )

   init {

   }

   fun addMessage(role: String, message: String ) = messages.add(Message(role, message)) // private - use operator override (+=)
   operator fun plusAssign(message: Message) { messages.add(message) }
   fun getLatestMessages(n: Int = 1): List<Message> = messages.takeLast(n)
   fun clear() = messages.clear()

   fun sendChatRequest() {

      val r = requester.getChat(this)
      println(r.firstChoice)

   }




   fun chatCompletionRequest(): ChatRequest = ChatRequest(messages, chatOptions)





}