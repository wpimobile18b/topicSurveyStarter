package wpi.krichards.firebasedemo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseWrapper {

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;

    FirebaseWrapper() {
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }

    public void writeToUser(String key, String val) {
        // TODO: Add Code Here
    }

    public DatabaseReference getUserRef(String key) {
        // TODO: Add Code Here

        return null; // TODO: Delete this (only here to allow compilation of earlier steps)
    }

}
