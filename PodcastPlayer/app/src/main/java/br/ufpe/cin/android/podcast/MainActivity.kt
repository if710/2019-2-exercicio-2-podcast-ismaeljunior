package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.ufpe.cin.android.podcast.Parser.parse
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            var result:String = ""
            try{
                result = URL("https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml").readText()
            }catch (e: Exception){
                uiThread {
                    oitexto.text = e.message
                }
            }
            var a:List<ItemFeed>? = null
            uiThread {
               try{
                   a = parse(result)
                   oitexto.text = result.substring(0, 50) //a[0]
               }catch (e: Exception){
                   oitexto.text = e.message
               }
            }

        }
    }



}
