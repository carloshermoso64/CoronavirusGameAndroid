package com.dsa.grupo2.CoronavirusGameAndroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dsa.grupo2.CoronavirusGameAndroid.models.User;
import com.dsa.grupo2.CoronavirusGameAndroid.services.UserService;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;
import com.dsa.grupo2.CoronavirusGameAndroid.utils.CircleTransform;
import com.google.android.material.textfield.TextInputLayout;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class ProfileActivity extends AppCompatActivity {

    TextView name, email, level;
    User profileUser;
    UserService userService = ApiConn.getInstace().getUserService();
    ImageView profilePic;
    TextInputLayout newName, newEmail, newPassword;
    Button sendChanges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.EditName);
        email = findViewById(R.id.EditEmail);
        level = findViewById(R.id.EditLevel);
        profilePic = findViewById(R.id.editProfilePic);
        newName = findViewById(R.id.ProfileNewName);
        newEmail = findViewById(R.id.ProfileNewEmail);
        newPassword = findViewById(R.id.ProfileNewPassword);
        sendChanges = findViewById(R.id.ProfileSendChanges);

        newName.setVisibility(View.GONE);
        newEmail.setVisibility(View.GONE);
        newPassword.setVisibility(View.GONE);
        sendChanges.setVisibility(View.GONE);


        Call<User> userCall = userService.getUserByID(ApiConn.getInstace().getUserId());

        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                profileUser = response.body();
                name.setText(profileUser.getName());
                email.setText(profileUser.getEmail());
                level.setText("Level: " + String.valueOf(profileUser.getLevel()));
                Picasso.get().load("http://"+ ApiConn.getInstace().getIP() + ":8080/dsaApp/user/image/"+ApiConn.getInstace().getUserId()).fit()
                        .transform(new CircleTransform())
                        .placeholder(R.drawable.defaultprofile)
                        .memoryPolicy(MemoryPolicy.NO_CACHE )
                        .networkPolicy(NetworkPolicy.NO_CACHE)
                        .error(R.drawable.defaultprofile).into(profilePic);

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void changePicture(View v) {
        final String[] options = {"Camera", "Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Select source of image");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Camera")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 1);

                } else if (options[item].equals("Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    String[] mimeTypes = {"image/jpeg", "image/png"};
                    intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap) extras.get("data");

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 0, stream);
            byte[] byteArray = stream.toByteArray();
            RequestBody requestBody = RequestBody
                    .create(MediaType.parse("application/octet-stream"), byteArray);

            MultipartBody.Part imagePart = MultipartBody.Part.createFormData("file","image.png", requestBody);

            Call<Void> uploadImage = userService.uploadImage(imagePart,ApiConn.getInstace().getUserId());

            uploadImage.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.code() == 201) {
                        Toast.makeText(getApplicationContext(), "Image uploaded", Toast.LENGTH_LONG).show();
                        Picasso.get().load("http://"+ ApiConn.getInstace().getIP() + ":8080/dsaApp/user/image/"+ApiConn.getInstace().getUserId()).fit()
                                .transform(new CircleTransform())
                                .placeholder(R.drawable.defaultprofile)
                                .memoryPolicy(MemoryPolicy.NO_CACHE )
                                .networkPolicy(NetworkPolicy.NO_CACHE)
                                .error(R.drawable.defaultprofile).into(profilePic);
                    }

                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {

                }
            });

        }
        else if (requestCode == 2 && resultCode == RESULT_OK) {

            Uri selectedImage = data.getData();
            try {
                Bitmap image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.PNG, 0, stream);
                byte[] byteArray = stream.toByteArray();
                RequestBody requestBody = RequestBody
                        .create(MediaType.parse("application/octet-stream"), byteArray);
                MultipartBody.Part imagePart = MultipartBody.Part.createFormData("file","image.png", requestBody);

                Call<Void> uploadImage = userService.uploadImage(imagePart,ApiConn.getInstace().getUserId());

                uploadImage.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.code() == 201) {
                            Toast.makeText(getApplicationContext(), "Image uploaded", Toast.LENGTH_LONG).show();
                            Picasso.get().load("http://"+ ApiConn.getInstace().getIP() + ":8080/dsaApp/user/image/"+ApiConn.getInstace().getUserId()).fit()
                                    .transform(new CircleTransform())
                                    .placeholder(R.drawable.defaultprofile)
                                    .memoryPolicy(MemoryPolicy.NO_CACHE )
                                    .networkPolicy(NetworkPolicy.NO_CACHE)
                                    .error(R.drawable.defaultprofile).into(profilePic);
                        }

                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void showChangeForm(View v) {
        newName.getEditText().setText(name.getText());
        newEmail.getEditText().setText(email.getText());
        newPassword.getEditText().setText(ApiConn.getInstace().getPassword());
        newName.setVisibility(View.VISIBLE);
        newEmail.setVisibility(View.VISIBLE);
        newPassword.setVisibility(View.VISIBLE);
        sendChanges.setVisibility(View.VISIBLE);
    }

    public void sendChanges(View v) {

        User u = new User(newName.getEditText().getText().toString(),
                newEmail.getEditText().getText().toString(),
                newPassword.getEditText().getText().toString());

        Call<User> newUser = ApiConn.getInstace().getUserService().updateUser(u,ApiConn.getInstace().getUserId());
        newUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code()==201) {
                    User u = response.body();
                    name.setText(u.getName());
                    email.setText(u.getEmail());
                    ApiConn.getInstace().setUsername(u.getName());
                    ApiConn.getInstace().setEmail(u.getEmail());
                    ApiConn.getInstace().setPassword(newPassword.getEditText().getText().toString());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
