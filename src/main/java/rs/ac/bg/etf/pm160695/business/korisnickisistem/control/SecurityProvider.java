package rs.ac.bg.etf.pm160695.business.korisnickisistem.control;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

import org.wildfly.security.password.PasswordFactory;
import org.wildfly.security.password.WildFlyElytronPasswordProvider;
import org.wildfly.security.password.interfaces.SaltedSimpleDigestPassword;
import org.wildfly.security.password.spec.EncryptablePasswordSpec;
import org.wildfly.security.password.spec.SaltedPasswordAlgorithmSpec;

public class SecurityProvider {

	private static final Provider PASSWORD_PROVIDER = new WildFlyElytronPasswordProvider();

	/**
	 * Generisemo salt duzine 32 bita enkodovan u Base64 formatu
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
	 * Generisemo hes lozinke koristeci sha-512 algoritam uz koriscenje salta duzine
	 * 32 bita
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
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

}
