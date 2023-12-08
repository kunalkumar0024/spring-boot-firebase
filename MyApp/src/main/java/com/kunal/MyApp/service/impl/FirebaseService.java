package com.kunal.MyApp.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.FirebaseApp;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FirebaseService {

    private final Firestore firestore;

    @Autowired
    public FirebaseService(FirebaseApp firebaseApp) {
        firestore = FirestoreClient.getFirestore(firebaseApp);
    }

    public CollectionReference getCollection(String collectionName) {
        return firestore.collection(collectionName);
    }

    public void storeUserData(String username, String mobileNumber) {
        CollectionReference usersCollection = firestore.collection("MyDb");

        Map<String, Object> userData = new HashMap<>();
        userData.put("username", username);
        userData.put("mobileNumber", mobileNumber);

        DocumentReference userDocument = usersCollection.document(username);
        userDocument.set(userData);

        System.out.println("User data stored successfully.");
    }

    public Map<String, Object> getUserData(String username) throws Exception {
        DocumentReference userDocument = firestore.collection("MyDb").document(username);
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = userDocument.get();
        DocumentSnapshot document = documentSnapshotApiFuture.get();

        if (document.exists()) {
            return document.getData();
        } else {
            throw new Exception("User not found");
        }
    }

    public void updateUserData(String username, Map<String, Object> updatedData) throws Exception {
        DocumentReference userDocument = firestore.collection("MyDb").document(username);

        // Update user data with the provided map
        ApiFuture<WriteResult> updateResult = userDocument.update(updatedData);

        updateResult.get(); // Wait for the update to complete (optional)
    }

    public void deleteUserData(String username) throws Exception {
        DocumentReference userDocument = firestore.collection("MyDb").document(username);

        ApiFuture<WriteResult> deleteResult = userDocument.delete();

        deleteResult.get(); // Wait for the delete to complete (optional)
    }
}
