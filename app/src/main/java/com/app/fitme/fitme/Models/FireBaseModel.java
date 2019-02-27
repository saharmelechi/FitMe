package com.app.fitme.fitme.Models;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class FireBaseModel {

    static public FireBaseModel instance = new FireBaseModel();
    final public String DB_NAME = "users";
    final public String STORAGE_NAME = "images";
    private FireBaseModel() {
    }

    public void addExercise(Exerciser m) {
        //MyMessage m = new MyMessage(FirebaseAuth.getInstance().getCurrentUser().getDisplayName(), message, img);

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference().child(DB_NAME);
        //mRef.child(mRef.push().getKey()).setValue(m);
//        mRef.child( FirebaseAuth.getInstance().getCurrentUser().getUid() + "/" + m.getDate()).setValue(m);
        mRef.child( "" + m.getDate()).setValue(m);
    }

    public FirebaseRecyclerOptions<Exerciser> getAllMessages() {

        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference(DB_NAME);

        FirebaseRecyclerOptions<Exerciser> options = new FirebaseRecyclerOptions.Builder<Exerciser>()
                .setQuery(mRef, Exerciser.class)
                .build();

        return options;
    }

    public void upload(final Exerciser exe){
        StorageReference storageRef = FirebaseStorage.getInstance().getReference().child(STORAGE_NAME );

        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        final StorageReference ref = storageRef.child(
                                    uid).child(exe.formatDate());

        ref.putFile(Uri.parse(exe.exeImage)).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }
                return ref.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri downloadUri = task.getResult();
                    exe.exeImage = downloadUri.toString();
                    FireBaseModel.instance.addExercise(exe);
                }
            }
        });
    }


}
