package callback;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * return value from thread
 *
 */
    public class PollingDigest extends Thread {
        private File input;
        private byte[] digest;

        public PollingDigest(File input) {
            this.input = input;
        }

        public void run( ) {
          try {
            FileInputStream in = new FileInputStream(input);
            MessageDigest sha = MessageDigest.getInstance("SHA");
            DigestInputStream din = new DigestInputStream(in, sha);
            int b;
            while ((b = din.read( )) != -1);
            din.close( );
            digest = sha.digest( );
          }
          catch (IOException ex) {
            System.err.println(ex);
          }
          catch (NoSuchAlgorithmException ex) {
            System.err.println(ex);
          }
        }

        public byte[] getDigest( ) {
          return digest;
        }
}

