package com.example.booktobook.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.booktobook.Activity.AlertActivity;
import com.example.booktobook.Model.User;
import com.example.booktobook.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    public ImageButton profilefragment_alert;
    ImageView imageView;
    File tempFile;

    public TextView fragment_profile_textview_name;
    public TextView fragment_profile_textview_point;
    public TextView fragment_profile_textview_uploaded_book;
    public TextView fragment_profile_textview_borrow_book;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {

            Uri photoUri = data.getData();

            Cursor cursor = null;

            try {

                /*
                 *  Uri 스키마를
                 *  content:/// 에서 file:/// 로  변경한다.
                 */
                String[] proj = { MediaStore.Images.Media.DATA };

                assert photoUri != null;
                cursor = getActivity().getContentResolver().query(photoUri, proj, null, null, null);

                assert cursor != null;
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

                cursor.moveToFirst();

                tempFile = new File(cursor.getString(column_index));

            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }

            setImage();

        }
    }

    private void setImage() {

        ImageView imageView = getActivity().findViewById(R.id.imageview_profile);

        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap originalBm = BitmapFactory.decodeFile(tempFile.getAbsolutePath(), options);


        imageView.setImageBitmap(originalBm);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        final String id = pref.getString("ID", "");
        StorageReference mountainsRef = storageRef.child("user_profile_image/"+id);


        // Get the data from an ImageView as bytes
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
                // ...
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile,container,false);

        profilefragment_alert = view.findViewById(R.id.button_alert);
        imageView=view.findViewById(R.id.imageview_profile);

        fragment_profile_textview_name = view.findViewById(R.id.fragment_profile_name);
        fragment_profile_textview_point = view.findViewById(R.id.fragment_profile_point);
        fragment_profile_textview_borrow_book = view.findViewById(R.id.fragment_profile_borrow_book);
        fragment_profile_textview_uploaded_book = view.findViewById(R.id.fragment_profile_upload_book);

        profilefragment_alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AlertActivity.class);
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, 1);
            }
        });


        SharedPreferences pref = this.getActivity().getSharedPreferences("pref", MODE_PRIVATE);
        final String id = pref.getString("ID", "");
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        fragment_profile_textview_name.setText(""+id);

        DocumentReference docRef = db.collection("Users").document(id);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = documentSnapshot.toObject(User.class);

                fragment_profile_textview_point.setText(""+user.getPoint());
                fragment_profile_textview_borrow_book.setText(""+user.getBorrowed_book_count());
                fragment_profile_textview_uploaded_book.setText(""+user.getUploaded_book_count());
            }
        });

        StorageReference storageRef = FirebaseStorage.getInstance().getReference();
        StorageReference userProfileImageRef = storageRef.child("user_profile_image/"+id);
        final long ONE_MEGABYTE = 1024 * 1024;
        userProfileImageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                imageView.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                imageView.setImageResource(R.drawable.profile);
            }
        });






        return view;
    }


}
