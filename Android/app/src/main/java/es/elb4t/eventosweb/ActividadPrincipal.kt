package es.elb4t.eventosweb

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity


class ActividadPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)
        val intent = Intent(Intent.ACTION_VIEW)
        val uri = Uri.parse(
                "https://eventos-3161f.firebaseapp.com/index.html")
        intent.data = uri
        startActivity(intent)
    }
}
