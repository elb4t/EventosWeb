package es.elb4t.eventosweb

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ProgressBar
import kotlinx.android.synthetic.main.actividad_principal.*




class ActividadPrincipal : AppCompatActivity() {
    lateinit var navegador: WebView
    private lateinit var barraProgreso: ProgressBar
    var dialogo: ProgressDialog? = null
    lateinit var btnDetener: Button
    lateinit var btnAnterior: Button
    lateinit var btnSiguiente: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.actividad_principal)
        barraProgreso = findViewById(R.id.barraProgreso)
        btnDetener = findViewById(R.id.btnDetener)
        btnAnterior = findViewById(R.id.btnAnterior)
        btnSiguiente = findViewById(R.id.btnSiguiente)
        navegador = findViewById(R.id.webkit)
        navegador.settings.javaScriptEnabled = true
        navegador.settings.builtInZoomControls = false
        navegador.loadUrl("file:///android_asset/index.html")
        //navegador.loadUrl("https://eventos-3161f.firebaseapp.com/index.html")
        navegador.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                var url_filtro: String = "http://www.androidcurso.com/"
                if (url != url_filtro) {
                    view.loadUrl(url_filtro)
                }
                return false
            }
        }
        navegador.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, progreso: Int) {
                barraProgreso.progress = 0
                barraProgreso.visibility = View.VISIBLE
                this@ActividadPrincipal.setProgress(progreso * 1000)
                barraProgreso.incrementProgressBy(progreso)
                if (progreso == 100) {
                    barraProgreso.visibility = View.GONE
                }
            }
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(this@ActividadPrincipal).setTitle("Mensaje")
                        .setMessage(message).setPositiveButton(android.R.string.ok, { dialogInterface: DialogInterface, i: Int ->
                            result.confirm()
                        }).setCancelable(false).create().show()
                return true
            }
        }
        navegador.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                dialogo = ProgressDialog(this@ActividadPrincipal)
                dialogo!!.setMessage("Cargando...")
                dialogo!!.setCancelable(true)
                dialogo!!.show()
                btnDetener.isEnabled = true
            }

            override fun onPageFinished(view: WebView, url: String) {
                dialogo!!.dismiss()
                btnDetener.isEnabled = false
                btnAnterior.isEnabled = view.canGoBack()
                btnSiguiente.isEnabled = view.canGoForward()
            }

            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                val builder = AlertDialog.Builder(this@ActividadPrincipal)
                builder.setMessage(description).setPositiveButton("Aceptar", null).setTitle("onReceivedError")
                builder.show()
            }
        }
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        urlText.clearFocus()
    }

    override fun onBackPressed() {
        if (navegador.canGoBack())
            navegador.goBack()
        else
            super.onBackPressed()
    }

    fun detenerCarga(v: View) {
        navegador.stopLoading()
    }

    fun irPaginaAnterior(v: View) {
        navegador.goBack()
    }

    fun irPaginaSiguiente(v: View) {
        navegador.goForward()
    }

    fun cargarUrlText(v: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(urlText.windowToken, 0)
        navegador.loadUrl("http://" + urlText.text.toString())
    }
}
