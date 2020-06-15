package com.dsa.grupo2.CoronavirusGameAndroid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.dsa.grupo2.CoronavirusGameAndroid.utils.ApiConn;
import com.google.android.material.textfield.TextInputLayout;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddThreadDialog extends DialogFragment {

    public interface NoticeDialogListener {
        public void onDialogThreadAdded(DialogFragment dialog);
        public void errorAddingThread(DialogFragment dialog);
    }

    NoticeDialogListener listener;

    private TextInputLayout nameInput;
    private EditText contentInput;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (NoticeDialogListener) context;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException("activity" + " must implement NoticeDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialog = inflater.inflate(R.layout.create_thread_popup,null );
        nameInput = dialog.findViewById(R.id.createForumName);
        contentInput = dialog.findViewById(R.id.createForumContent);
        builder.setTitle("Create Thread");
        builder.setView(dialog);
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialog, int which) {
                String title = nameInput.getEditText().getText().toString();
                String content = contentInput.getText().toString();
                MultipartBody.Part authorPart = MultipartBody.Part.createFormData("author", ApiConn.getInstace().getUsername());
                MultipartBody.Part namePart = MultipartBody.Part.createFormData("name", title);
                MultipartBody.Part contentPart = MultipartBody.Part.createFormData("content", content);

                Call<Void> createCall = ApiConn.getInstace().getForumService().postNewThread(authorPart, namePart, contentPart);

                createCall.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {

                        if (response.code() == 201) {
                            listener.onDialogThreadAdded(AddThreadDialog.this);
                        }
                        else {
                            listener.errorAddingThread(AddThreadDialog.this);
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        listener.errorAddingThread(AddThreadDialog.this);
                    }
                });

            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddThreadDialog.this.getDialog().cancel();

            }
        });
        return builder.create();
    }
}
