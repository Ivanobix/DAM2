package com.acme.ejer5_08;

import android.os.AsyncTask;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class DescargaWeb extends AsyncTask<String, Void, String> {

    protected String resultado;
    protected String params;
    protected ArrayList<Equipo> equipos;

    public DescargaWeb() {
        resultado = "";
        params = "";
        equipos = new ArrayList<>();
    }

    // doInBackground: realizar la tarea asignada en segundo plano
    @Override
    protected String doInBackground(String... urls) {
        try {
            return descargaUrl(urls[0]);
        } catch (IOException e) {
            return null;
        }
    }

    // onPostExecute: tratamos los datos obtenidos
    @Override
    protected void onPostExecute(String result) {
        if (result != null && result.contains(",")) {
            String[] datos = result.split("<br/>");
            for (int i = 0; i < datos.length; i++) {
                String[] datosEquipo = datos[i].split(",");
                if (datosEquipo.length == 4) {
                    Equipo equipo = new Equipo();
                    equipo.setNombre(datosEquipo[0]);
                    equipo.setCiudad(datosEquipo[1]);
                    equipo.setConferencia(datosEquipo[2]);
                    equipo.setDivision(datosEquipo[3]);
                    equipos.add(equipo);
                }
            }
        }
        Log.d("result", getResultado());
    }

    // descargaUrl: establece la conexión HTTP y devuelve el contenido del InputStream
    // convertido a String
    private String descargaUrl(String myurl) throws IOException {
        InputStream is = null;
        try {
            // Preparamos la conexión
            URL url = new URL(myurl);
            HttpURLConnection con;
            if (myurl.startsWith("https")) {
                con = (HttpsURLConnection) url.openConnection();
            } else {
                con = (HttpURLConnection) url.openConnection();
            }
            con.setReadTimeout(10000);     // milisegundos
            con.setConnectTimeout(15000);  // milisegundos
            con.setRequestMethod("POST");
            con.setDoInput(true);

            // Añadimos parámetros (metodo POST)
            String parametros = "user=nbauser&pass=nbauser";
            if (!params.equals("")) {
                parametros = parametros + params;
            }
            con.setFixedLengthStreamingMode(parametros.getBytes().length);
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(parametros);
            out.close();

            // Descargamos los datos
            con.connect();
            is = con.getInputStream();

            // Convertimos los datos obtenidos (InputStream) a String
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int i;
            while ((i = is.read()) != -1) {
                os.write(i);
            }

            // Convertimos al codigo de caracteres deseado antes de devolverlos
            // String result = os.toString("UTF-8");
            resultado = os.toString("iso-8859-1");
            Log.d("TodoBien", "-" + resultado + "-");

        } catch (IOException e) {
            Log.d("ErrorBD", e.getMessage());
        } finally {
            // Cerramos el InputStream
            if (is != null) {
                is.close();
            }
        }
        return resultado;
    }

    public ArrayList<Equipo> getEquipos() {

        return equipos;
    }

    public String getResultado() {

        return resultado;
    }

    public void setParams(Map<String, String> valores) {
        params = "";
        for (Map.Entry<String, String> entry : valores.entrySet()) {
            params = params + "&" + entry.getKey() + "=" + entry.getValue();
        }
        Log.d("Params", params);
    }
}
