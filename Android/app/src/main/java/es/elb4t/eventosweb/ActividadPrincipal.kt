package es.elb4t.eventosweb

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView
import android.webkit.WebViewClient


class ActividadPrincipal : AppCompatActivity() {
    lateinit var navegador: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)
        navegador = findViewById(R.id.webkit)
        navegador.settings.javaScriptEnabled = true
        navegador.loadUrl("file:///android_asset/index.html")
        //navegador.loadUrl("https://eventos-3161f.firebaseapp.com/index.html")
        navegador.webViewClient = object :WebViewClient(){
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                var url_filtro:String = "http://www.androidcurso.com/"
                if (url != url_filtro){
                    view.loadUrl(url_filtro)
                }
                return false
            }
        }
    }
}
