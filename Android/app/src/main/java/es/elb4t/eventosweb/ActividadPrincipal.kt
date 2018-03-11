package es.elb4t.eventosweb

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar




class ActividadPrincipal : AppCompatActivity() {
    lateinit var navegador: WebView
    private lateinit var barraProgreso: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)
        barraProgreso = findViewById(R.id.barraProgreso)
        navegador = findViewById(R.id.webkit)
        navegador.settings.javaScriptEnabled = true
        navegador.settings.builtInZoomControls = false
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
        navegador.webChromeClient = object : WebChromeClient(){
            override fun onProgressChanged(view: WebView, progreso: Int) {
                barraProgreso.progress = 0
                barraProgreso.visibility = View.VISIBLE
                this@ActividadPrincipal.setProgress(progreso * 1000)
                barraProgreso.incrementProgressBy(progreso)
                if (progreso == 100) {
                    barraProgreso.visibility = View.GONE
                }
            }
        }
    }
}
