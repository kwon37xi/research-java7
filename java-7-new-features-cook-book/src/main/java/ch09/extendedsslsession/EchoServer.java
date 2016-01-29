package ch09.extendedsslsession;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/* Server Key generation

keytool -genkey -keystore mySrvKeystore -keyalg RSA

# Run

java -Djavax.net.ssl.keyStore=/path/to/mySrvKeystore -Djavax.net.ssl.keyStorePassword=password ch09.extendedsslsession.EchoServer
 */
public class EchoServer {
    public static void main(String[] args) throws Exception {
        SSLServerSocketFactory sslServerSocketFactory =
                (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        SSLServerSocket sslServerSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(9999);

        System.out.println("Waiting for a client ...");
        SSLSocket sslSocket = (SSLSocket) sslServerSocket.accept();

        SSLParameters parameters = sslSocket.getSSLParameters();
        parameters.setAlgorithmConstraints(new SimpleConstraints());

        String endPoint = parameters.getEndpointIdentificationAlgorithm();
        System.out.println("End Point: " + endPoint);

        if (sslSocket.getSession() instanceof ExtendedSSLSession) {
            ExtendedSSLSession extendedSSLSession = (ExtendedSSLSession) sslSocket.getSession();

            System.out.println("Local Supported Signature Algorithms");

            String[] algorithms = extendedSSLSession.getLocalSupportedSignatureAlgorithms();

            for (String algorithm : algorithms) {
                System.out.println("Algorithm: " + algorithm);
            }

            System.out.println("Peer Supoorted Signature Algorithms");
            final String[] peerSupportedAlgorithms = extendedSSLSession.getPeerSupportedSignatureAlgorithms();

            for (String peerSupportedAlgorithm : peerSupportedAlgorithms) {
                System.out.println("Algorithm: " + peerSupportedAlgorithm);
            }
        }

        final InputStream inputStream = sslSocket.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String stringline = null;
        while ((stringline = bufferedReader.readLine()) != null) {
            System.out.println(stringline);
            System.out.flush();
        }
    }
}