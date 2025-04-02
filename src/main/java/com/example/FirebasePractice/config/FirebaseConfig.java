package com.example.FirebasePractice.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseConfig {

    @Bean
    public FirebaseApp firebaseApp() throws IOException {
        if (FirebaseApp.getApps().isEmpty()) { // Avoid duplicate initialization
            InputStream serviceAccount = getClass().getClassLoader().getResourceAsStream("firebase-service-account.json");

            if (serviceAccount == null) {
                throw new IOException("Service account file not found.");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://springbootpractice-2a5b3-default-rtdb.firebaseio.com")
//                    .setDatabaseUrl("https://springboot-firebase-ae09f-default-rtdb.firebaseio.com/")
                    .build();

            return FirebaseApp.initializeApp(options);
        } else {
            return FirebaseApp.getInstance();
        }
    }

    // ✅ Register FirebaseDatabase as a Spring Bean
    @Bean
    public FirebaseDatabase firebaseDatabase(FirebaseApp firebaseApp) {
        return FirebaseDatabase.getInstance(firebaseApp);
    }
}
