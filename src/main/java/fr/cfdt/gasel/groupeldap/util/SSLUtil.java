package fr.cfdt.gasel.groupeldap.util;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;


/**
 * The type Ssl util.
 */
public class SSLUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SSLUtil.class);

    private SSLUtil(){}

    /**
     * Configur http conduit
     *
     * @param client      the connector
     * @param certifPath  the certif path
     * @param certifPasse the certif passe
     * @return the http conduit
     * @throws KeyStoreException         the key store exception
     * @throws IOException               the io exception
     * @throws CertificateException      the certificate exception
     * @throws NoSuchAlgorithmException  the no such algorithm exception
     * @throws UnrecoverableKeyException the unrecoverable key exception
     */
    public static HTTPConduit configHttpConduit(Client client, String certifPath, String certifPasse)  throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException{
        HTTPConduit http = (HTTPConduit) client.getConduit();

        HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

        httpClientPolicy.setConnectionTimeout(36000);
        httpClientPolicy.setAllowChunking(false);
        httpClientPolicy.setReceiveTimeout(32000);

        http.setClient(httpClientPolicy);
        TLSClientParameters tlsClientParameters  = http.getTlsClientParameters();
        if(null == tlsClientParameters) {
            tlsClientParameters = new TLSClientParameters();
        }
        tslIt(tlsClientParameters, http, certifPath, certifPasse);

        return http;
    }

    /**
     * set ssl config
     *
     * @param tlsClientParameters the tls connector parameters
     * @param http                the http
     * @param certifPath          the certif path
     * @param certifPasse         the certif passe
     * @throws KeyStoreException         the key store exception
     * @throws IOException               the io exception
     * @throws CertificateException      the certificate exception
     * @throws NoSuchAlgorithmException  the no such algorithm exception
     * @throws UnrecoverableKeyException the unrecoverable key exception
     */
    public static void tslIt(TLSClientParameters tlsClientParameters, HTTPConduit http, String certifPath, String certifPasse) throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableKeyException {

        KeyStore keyStore = getKeyStore(certifPath, certifPasse);
        KeyStore trustStore = getKeyStore(certifPath, certifPasse);

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

        keyManagerFactory.init(keyStore, certifPasse.toCharArray());
        KeyManager[] keyMgrs = keyManagerFactory.getKeyManagers();
        tlsClientParameters.setKeyManagers(keyMgrs);

        trustManagerFactory.init(trustStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        tlsClientParameters.setTrustManagers(trustManagers);

        tlsClientParameters.setDisableCNCheck(true);
        http.setTlsClientParameters(tlsClientParameters);

    }

    /**
     * load certif in key store
     *
     * @param certifPath  the certif path
     * @param certifPasse the certif passe
     * @return key store
     * @throws KeyStoreException        the key store exception
     * @throws IOException              the io exception
     * @throws CertificateException     the certificate exception
     * @throws NoSuchAlgorithmException the no such algorithm exception
     */
    public static KeyStore getKeyStore(String certifPath, String certifPasse)  throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException{
        InputStream inputStream = getCertifInputStream(certifPath);
        KeyStore keystore = KeyStore.getInstance("JKS");
        keystore.load(inputStream, certifPasse.toCharArray());

        return keystore;
    }

    private static InputStream getCertifInputStream(String configPath) throws IOException {
        File file = new File(configPath);
        if (!file.exists()) {
            LOGGER.info("CertifPath could not be loaded using absolute path, switching to load using ClassPathResource.");
            return new ClassPathResource(configPath).getInputStream();
        }

        return new FileInputStream(file);
    }
}
