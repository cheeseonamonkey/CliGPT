@file:OptIn(ExperimentalSerializationApi::class)

package misc


import kotlinx.coroutines.runBlocking
import khttp.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import models.http.response.ChatResponse


class Requester(
   private val auth :String,
) {


   private val chatUrl = "https://api.openai.com/v1/chat/completions"
   private val defaultHeaders = mapOf(
      Pair("Authorization", "Bearer $auth"),
      Pair("Content-Type", "application/json"),
   )

   fun getChat(convo :Conversation, verbose :Boolean = false) = runBlocking {


      if(verbose) println("sending request:\n ${convo.chatCompletionRequest().toJsonStr()}")
      val r = post(
         url = chatUrl,
         headers = defaultHeaders,
         json = convo.chatCompletionRequest().toJsonObject()
      )

      if(verbose) println("received response:\n ${r.text}")

      if(r.text.contains("error"))
         if(r.text.contains("Incorrect API key provided")){
            println("Incorrect API key provided!")

            throw Exception("Incorrect API key provided!")
         }



      val respObj = Json.decodeFromString<ChatResponse>(r.text)


      return@runBlocking respObj
   }

}

