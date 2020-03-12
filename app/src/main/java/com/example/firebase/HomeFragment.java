package com.example.firebase;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    NavController navController;
    ImageView imageView;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth firebaseAuth;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView= view.findViewById(R.id.accountImage);

        FirebaseUser user = firebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            DocumentReference docRef = db.collection("users").document(user.getUid());


            if (docRef != null) {
                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        User cuerrentUser = documentSnapshot.toObject(User.class);

                        if (cuerrentUser!=null) {
                            Glide.with(requireActivity())
                                    .load(cuerrentUser.urlImage)
                                    .circleCrop()
                                    .into(imageView);                        }
                    }
                });

//                name.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
//                email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            }
        }

    }
}
