package rs.ac.bg.etf.pm160695.business.korisnickisistem.control;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.SaltedSimpleDigestPassword;
import org.wildfly.security.password.spec.EncryptablePasswordSpec;
import org.wildfly.security.password.spec.SaltedHashPasswordSpec;
import org.wildfly.security.password.spec.SaltedPasswordAlgorithmSpec;

public class SecurityProvider {

	private static final Provider PASSWORD_PROVIDER = new WildFlyElytronPasswordProvider();

	/**
	 * Metoda za generisanje salt-a duzine 32 bita
	 * 
	 * @return salt
	 */
	public String generateSalt() {
		byte[] salt = new byte[32];
		SecureRandom secureRandom = new SecureRandom();
		secureRandom.nextBytes(salt);

		return Base64.getEncoder().encodeToString(salt);
	}

	/**
	 * Metoda za generisanje hash-a lozinke koristeci SHA-512
	 * 
	 * @param clearPassword
	 * @param salt
	 * @return
	 */
	public String generateSaltedPassword(String clearPassword, String salt) {
		try {
			PasswordFactory passwordFactory = PasswordFactory
					.getInstance(SaltedSimpleDigestPassword.ALGORITHM_SALT_PASSWORD_DIGEST_SHA_512, PASSWORD_PROVIDER);

			SaltedPasswordAlgorithmSpec saltedSpec = new SaltedPasswordAlgorithmSpec(Base64.getDecoder().decode(salt));

			EncryptablePasswordSpec encryptableSpec = new EncryptablePasswordSpec(clearPassword.toCharArray(),
					saltedSpec);

			SaltedSimpleDigestPassword password = (SaltedSimpleDigestPassword) passwordFactory
					.generatePassword(encryptableSpec);

			return Base64.getEncoder().encodeToString(password.getDigest());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metoda za validaciju poslate lozinke na osnovu sacuvanog hasha i salta
	 * 
	 * @param clearPassword
	 * @param hashedPassword
	 * @param salt
	 * @return
	 */
	public boolean validatePassword(String clearPassword, String hashedPassword, String salt) {
		try {
			PasswordFactory passwordFactory = PasswordFactory
					.getInstance(SaltedSimpleDigestPassword.ALGORITHM_SALT_PASSWORD_DIGEST_SHA_512, PASSWORD_PROVIDER);

			SaltedHashPasswordSpec saltedHashSpec = new SaltedHashPasswordSpec(
					Base64.getDecoder().decode(hashedPassword), Base64.getDecoder().decode(salt));

			SaltedSimpleDigestPassword restoredPassword = (SaltedSimpleDigestPassword) passwordFactory
					.generatePassword(saltedHashSpec);

			return passwordFactory.verify(restoredPassword, clearPassword.toCharArray());
		} catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException e) {
			e.printStackTrace();
			return false;
		}
	}

}
