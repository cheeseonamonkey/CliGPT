package misc

import kotlinx.cli.*
import models.Message
import models.http.request.ChatOptions

class CliArgs(args: Array<String>) {

   private val parser = ArgParser("AskGpt" )



   val verbose by parser.option(ArgType.Boolean, "verbose", "v","Enable verbose output").default(false)

   val tell by parser.option(ArgType.Boolean,    fullName = "tell",        shortName = "t",  description = "Execute shell command").default(false)
   val apiKey by parser.option(ArgType.Boolean,  fullName = "setApiKey",   shortName = "k",  description = "Set API key",).default(false)

   val prompt by parser.argument(ArgType.String, description = "Prompt")



   init {

      val result = parser.parse(args)

      validate()

   }

   private fun validate() :Boolean {
      if(apiKey){
         if(verbose)  println("Setting api key: $prompt")

         if( ! prompt.contains("sk-") || prompt.length < 8)
            println("WARNING: Check API key - possible bad value?")

         Store["apiKey"] = prompt

      }else{

         val c = Conversation(
            authToken = Store["apiKey"],
            chatOptions = ChatOptions(),
            systemMessage = "You are a virtual assistant, living inside my PC's command terminal!"
         )

         //add user message and send conversation
         c += Message("user", prompt)
         c.sendChatRequest()

      }




      return true;
   }

}

