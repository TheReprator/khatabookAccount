package reprator.khatabookAccount.socialAuth

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import mu.KLogging
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.kodein.di.KodeinAware
import reprator.khatabookAccount.error.StatusCodeException
import java.io.FileInputStream

/*
https://firebase.google.com/docs/auth/admin/manage-sessions
* */
interface FirebaseAuthFeatures {
    fun firebaseAppInitialization()

    @Throws(FirebaseAuthException::class)
    fun verifyFirebaseToken(idToken: String): String

    @Throws(FirebaseAuthException::class)
    fun detectTokenRevocation(idToken: String): String
}

class FirebaseAuthFeaturesImpl(override val kodein: Kodein) : KodeinAware, FirebaseAuthFeatures {

    private companion object Logger : KLogging()
    val firebaseAuthFeatures : FirebaseAuthFeatures by instance()

    override fun firebaseAppInitialization() {
        val serviceAccount = FileInputStream("./config/khatabook-c7ad8-firebase-adminsdk-xpmdg-7887ac8bbe.json")

        val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://khatabook-c7ad8.firebaseio.com")
            .build()

        FirebaseApp.initializeApp(options)
    }

    /*
    * Verify ID tokens using the Firebase Admin SDK
    * This does not check whether or not the token has been revoked
    * */
    override fun verifyFirebaseToken(idToken: String): String =
        try {
            val decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken)
            decodedToken.uid
        } catch (exception: FirebaseAuthException) {
            throw StatusCodeException.UnAuthorizedUser("Invalid User", exception.cause)
        }

    /*
    * Detect ID token revocation in the SDK
    * */
    override fun detectTokenRevocation(idToken: String): String {
        return try {
            // Verify the ID token while checking if the token is revoked by passing checkRevoked
            // as true.
            val checkRevoked = true
            val decodedToken = FirebaseAuth.getInstance()
                .verifyIdToken(idToken, checkRevoked)
            // Token is valid and not revoked.`
            val uid = decodedToken.uid
            uid
        } catch (e: FirebaseAuthException) {
            if (e.errorCode == "id-token-revoked") {
                throw StatusCodeException.UnAuthorizedUser("Token Revoked. Please re-login", e.cause)
                // Token has been revoked. Inform the user to re-authenticate or signOut() the user.
            } else {
                throw StatusCodeException.UnAuthorizedUser("Invalid token", e.cause)
            }
        } catch (e: Exception) {
            throw e
        }
    }
}