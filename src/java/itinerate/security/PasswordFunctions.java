package itinerate.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public final class PasswordFunctions
{
	private static final String	PASSWORD_ALGORITHM	= "PBKDF2WithHmacSHA1";
	private static final int	SALT_BYTE_SIZE	   = 512;
	private static final int	PASS_BYTE_SIZE	   = 512;
	private static final int	ITERATION_COUNT	   = 2017;
	
	// Password hashing technique taken from: https://crackstation.net/hashing-security.htm
	/**
	 * Creates a hash, including the salt, for the given password.
	 * @param password
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public static String createHash(String password) throws InvalidKeySpecException,
	        NoSuchAlgorithmException
	{
		// Generate a salt
		SecureRandom rand = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		rand.nextBytes(salt);
		
		// Hash the password
		PBEKeySpec spec =
		        new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, PASS_BYTE_SIZE * 8);
		byte[] key =
		        SecretKeyFactory.getInstance(PASSWORD_ALGORITHM).generateSecret(spec).getEncoded();
		return toHex(salt) + ":" + toHex(key);
	}
	
	/**
	 * Determines if the password the user gave was the same as the password that was stored.
	 * @param password
	 * @param trueHash
	 * @return
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public static boolean verifyPassword(String password, String trueHash)
	        throws InvalidKeySpecException, NoSuchAlgorithmException
	{
		// Retrieve the hash and salt
		String[] hashArr = trueHash.split(":");
		byte[] salt = fromHex(hashArr[0]);
		byte[] hash = fromHex(hashArr[1]);
		
		// Hash the user given password
		PBEKeySpec spec =
		        new PBEKeySpec(password.toCharArray(), salt, ITERATION_COUNT, PASS_BYTE_SIZE * 8);
		byte[] key =
		        SecretKeyFactory.getInstance(PASSWORD_ALGORITHM).generateSecret(spec).getEncoded();
		
		// Compare them byte-wise
		return slowEquals(hash, key);
	}
	
	/**
	 * Computes, in constant time, whether or not the two hashes are equal. Apparently, you can time
	 * this algorithm to determine how close you are to the password by how long it takes to break
	 * from a "smart" comparison algorithm (break at first not equal)
	 * @param hash
	 * @param key
	 * @return
	 */
	private static boolean slowEquals(byte[] hash, byte[] key)
	{
		// The trick to its constant time nature is achieved by exploiting caching techniques. Both
		// parts of the boolean[] will be in the same page, while they will both be in the same CPU
		// cache (or not at all). Thus, the access time for either will be the same, and the second
		// acts as a dummy.
		boolean[] result = {true, true};
		for (int i = 0; i < hash.length && i < key.length; i++) {
			if ((hash[i] != key[i]) && result[0])
				result[0] = false;
			else
				result[1] = false;
		}
		return result[0];
	}
	
	/**
	 * Converts a string of hex digits to a byte array of the same value
	 * @param hex
	 * @return
	 */
	private static byte[] fromHex(String hex)
	{
		byte[] binary = new byte[hex.length() / 2];
		for (int i = 0; i < binary.length * 2; i += 2)
			binary[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
		return binary;
	}
	
	/**
	 * Returns a byte array to a string of hex digits of the same value
	 * @param bytes
	 * @return
	 */
	private static String toHex(byte[] bytes)
	{
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < bytes.length; i++)
			result.append(String.format("%02x", bytes[i]));
		return result.toString();
	}
	
	private PasswordFunctions() throws AssertionError
	{
		throw new AssertionError();
	}
}
