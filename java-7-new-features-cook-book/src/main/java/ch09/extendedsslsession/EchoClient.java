package ch09.extendedsslsession;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.*;

/*
# Run - 서버와 옵션 다름.
java -Djavax.net.ssl.trustStore=/path/to/mySrvKeystore -Djavax.net.ssl.trustStorePassword=password ch09.extendedsslsession.EchoClient
 */
public class EchoClient {
    public static void main(String[] args) throws Exception {
        SSLSocketFactory sslSocketFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        SSLSocket sslSocket = (SSLSocket) sslSocketFactory.createSocket("localhost", 9999);

        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        OutputStream outputStream = sslSocket.getOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            bufferedWriter.write(line + "\n");
            bufferedWriter.flush();
        }
    }
}
