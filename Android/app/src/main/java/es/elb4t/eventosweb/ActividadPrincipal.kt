package es.elb4t.eventosweb

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebView




class ActividadPrincipal : AppCompatActivity() {
    lateinit var navegador: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)
        navegador = findViewById(R.id.webkit)
        navegador.loadUrl("https://eventos-3161f.firebaseapp.com/index.html")
    }
}
