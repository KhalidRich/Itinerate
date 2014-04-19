package itinerate.security;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class PasswordTester
{

	public static void main(String[] args)
	{
		try {
	        System.out.println(PasswordFunctions.createHash("hello"));
	        System.out.println(PasswordFunctions.verifyPassword("hello", "32fc63b74e0288eb58b4a2d7d34ddc3dc57ba0ef04573134db09cac732743139780c8c8f88c1f9d52a25d287dd285183e5c25dba9f1cc875750272d86c3142119494c251b038d50ed366c775496e6636fd3f1896ef422d3d063ee6b96e19189d17ebe6569eb5b4f2c41e813113d97613c29028aedf5380db4bfc758f429ba5b8b7f073f9e4b64e313b3712fe0bf51de3390018d802f6f3763be751b4832428664caeeb4dc8d998ca3a5b99ed13b80d36b77d2a95ab60c08875a6da56804c6a6023a5125b815df498f59f70880b396c3942b0b58f60b9b36d6abc7c62042796cd4634b64c404ef2a74ef8863c2cf2dbee85c560dbff09dc241b19977084b84d62:fc57ee1e7e7d27a0b55042d970fac5cc13c8a40fbc9c02190f3a99c50d917e476bf3bed3810c70328d58bd3f04a6751dbdede7ae2b831bf8b8d4fb795257b6023c5ef585c6583e6d5357b93c9260f5247161a87be37532173f3e2f49ab0ec9bb42185edd0f161e6bfc7ce7c75093e9b173457652ab9fa3521d26d1fcbeef56b7d1a834016f0730092e882d50a85773e8fb0562534c32f340501abfb4acb5602d7aab8866c92d61c199916a60727c829cdd2350ae265986bfc170234dae12de438dbff07a669b52684cf5ef51ebb7eda5bd2f939c6939fc6f685791a3f5ccf0b4d7ae994cabbfeb7a4c12bbee974be67e8a2befff9865f88406178ba05201eca8"));
        } catch (InvalidKeySpecException e) {
        	e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
	}

}
