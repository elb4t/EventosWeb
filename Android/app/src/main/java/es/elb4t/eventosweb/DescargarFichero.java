package es.elb4t.eventosweb;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * Created by eloy on 11/3/18.
 */

public class DescargarFichero {
    private Context ctx;

    public DescargarFichero(Context ctx) {
        this.ctx = ctx;
    }

    public void Descargar(String urlDescarga){
        String fichero = urlDescarga.substring(
                urlDescarga.lastIndexOf("/")+1,
                (urlDescarga.indexOf("?") == -1 ? urlDescarga.length() : urlDescarga.indexOf("?")));

        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/EventosWeb/"+fichero);

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(urlDescarga));
        request.setDescription("EventosWeb");
        request.setTitle(fichero);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationUri(Uri.fromFile(file));

        DownloadManager manager = (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
}
