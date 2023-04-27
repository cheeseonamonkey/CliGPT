@file:OptIn(ExperimentalSerializationApi::class, ExperimentalSerializationApi::class,
   ExperimentalSerializationApi::class, ExperimentalSerializationApi::class
)

package misc

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

object Store {

   @Serializable
   data class KeyValue(val key: String, val value: String)



   private val dataFile = File("${System.getProperty("user.home")}/.config/AskGpt/d.data")
      .apply {
         val dir = File("${System.getProperty("user.home")}/.config/AskGpt/")
         if (!dir.exists())
            dir.mkdirs()

         if ( ! this.exists()) this.createNewFile()
   }

   operator fun get(key: String) : String = getval(key) ?: ""
   operator fun set(key: String, value: String) = put(key,value)


   private val data: MutableList<KeyValue> = readDataFile()

   private fun put(key: String, value: String, verbose:Boolean=true) {

      //if(verbose) println("Setting '$key' to '$value'")

      val existingIndex = data.indexOfFirst { it.key == key }
      if (existingIndex != -1) {
         data[existingIndex] = KeyValue(key, value)
      } else {
         data.add(KeyValue(key, value))
      }
      saveDataFile()

   }

   private fun getval(key: String, verbose:Boolean=true): String? {
     // if(verbose) println("Getting '$key'")
      val keyValue = data.find { it.key == key }
      return keyValue?.value
   }

   private fun remove(key: String) {
      data.removeAll { it.key == key }
      saveDataFile()
   }

   private fun readDataFile(): MutableList<KeyValue> {


      val dataJson = dataFile.readText()
      return if (dataJson.isNotEmpty())
         Json.decodeFromString(dataJson)
       else
         mutableListOf()

   }

   @ExperimentalSerializationApi
   private fun saveDataFile() {



      val dataJson = Json.encodeToString(data)
      dataFile.writeText(dataJson)
   }


}