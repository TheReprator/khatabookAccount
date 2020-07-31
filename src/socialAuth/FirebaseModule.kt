package reprator.khatabookAccount.socialAuth

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

/**
 * The container to inject all dependencies from the firebase module.
 */
val firebaseAuthModule get() = Kodein.Module("firebaseAuthFeatures") {
    bind<FirebaseAuthFeatures>() with singleton { FirebaseAuthFeaturesImpl(kodein) }
}