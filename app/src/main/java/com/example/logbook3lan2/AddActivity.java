package com.example.logbook3lan2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.logbook3lan2.MyDatabasehelper;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name_input,dob_input,email_input;
    Button add_button;
    ImageButton selectImageButton;
    ImageView selectedImageView;
    private Uri cloudUri;
    private static boolean isMediaManagerInitialized = false;

    private void configCloudinary(){
        if (!isMediaManagerInitialized) {
            isMediaManagerInitialized = true;
             // Kiểm tra xem MediaManager đã được khởi tạo chưa
                Map<String, String> config = new HashMap<>();
                config.put("cloud_name", "dydd7mqle");
                config.put("api_key", "446888246178164");
                config.put("api_secret", "HcT6rhBOXzGh-pm1Agd8Q34rask");

                MediaManager.init(AddActivity.this,config);


//            if (MediaManager.get() == null) { // Kiểm tra xem MediaManager đã được khởi tạo chưa
//
//            }

        }
    }
//public static class MediaManagerSingleton {
//    private static MediaManager mediaManagerInstance = null;
//
//    private MediaManagerSingleton() {
//        // Không cho phép tạo đối tượng MediaManagerSingleton
//    }
//
//    public static MediaManager getInstance(Context context) {
//        if (mediaManagerInstance == null) {
//            // Khởi tạo MediaManager nếu chưa tồn tại
//            Map<String, String> config = new HashMap<>();
//            config.put("cloud_name", "dydd7mqle");
//            config.put("api_key", "446888246178164");
//            config.put("api_secret", "HcT6rhBOXzGh-pm1Agd8Q34rask");
//            MediaManager.init(context, config);
//            mediaManagerInstance = MediaManager.get();
//        }
//        return mediaManagerInstance;
//    }
//}



    MyDatabasehelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        configCloudinary();
        // Initialize the MediaManagerSingleton
//        MediaManagerSingleton mediaManagerSingleton = MediaManagerSingleton.getInstance(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name_input = findViewById(R.id.name_input);
        dob_input = findViewById(R.id.dob_input);
        email_input = findViewById(R.id.email_input);
        add_button= findViewById(R.id.add_button);
        selectImageButton = findViewById(R.id.image_button);
        selectedImageView = findViewById(R.id.selected_image_view);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở cửa sổ chọn hình ảnh
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabasehelper myDB = new MyDatabasehelper(AddActivity.this);

                String imagePath = ""; // Declare a variable to store the image path

                if (selectImageButton.getTag() != null) {
                    // upload anh using MediaManagerSingleton


                    //ởđây nahwtj
                    .upload(cloudUri)
                            .callback(new UploadCallback() {
                                @Override
                                public void onStart(String requestId) {
                                    // Xử lý khi tải lên bắt đầu
                                }

                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // Xử lý tiến trình tải lên (nếu cần)
                                }

                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // Xử lý khi tải lên thành công, bạn có thể lấy URL của hình ảnh từ resultData
                                    myDB.addStudent(name_input.getText().toString().trim(),
                                            dob_input.getText().toString().trim(),
                                            email_input.getText().toString().trim(), (String) resultData.get("url"));
                                }

                                @Override
                                public void onError(String requestId, ErrorInfo error) {
                                    // Xử lý khi xảy ra lỗi trong quá trình tải lên
                                }

                                @Override
                                public void onReschedule(String requestId, ErrorInfo error) {
                                    // Xử lý khi yêu cầu tải lên được xếp hàng lại (nếu cần)
                                }
                            })
                            .dispatch();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            cloudUri = selectedImageUri;
            if (selectedImageUri != null) {
                // Load and display the image using Picasso
                Picasso.get().load(selectedImageUri).into(selectedImageView);

                // Save the image path to the ImageButton's tag
                selectImageButton.setTag(selectedImageUri.toString());
            }
        }
    }
}package com.example.logbook3lan2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.cloudinary.android.MediaManager;
import com.cloudinary.android.callback.ErrorInfo;
import com.cloudinary.android.callback.UploadCallback;
import com.example.logbook3lan2.MyDatabasehelper;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText name_input,dob_input,email_input;
    Button add_button;
    ImageButton selectImageButton;
    ImageView selectedImageView;
    private Uri cloudUri;
    private static boolean isMediaManagerInitialized = false;

    private void configCloudinary(){
        if (!isMediaManagerInitialized) {
            isMediaManagerInitialized = true;
             // Kiểm tra xem MediaManager đã được khởi tạo chưa
                Map<String, String> config = new HashMap<>();
                config.put("cloud_name", "dydd7mqle");
                config.put("api_key", "446888246178164");
                config.put("api_secret", "HcT6rhBOXzGh-pm1Agd8Q34rask");

                MediaManager.init(AddActivity.this,config);


//            if (MediaManager.get() == null) { // Kiểm tra xem MediaManager đã được khởi tạo chưa
//
//            }

        }
    }
//public static class MediaManagerSingleton {
//    private static MediaManager mediaManagerInstance = null;
//
//    private MediaManagerSingleton() {
//        // Không cho phép tạo đối tượng MediaManagerSingleton
//    }
//
//    public static MediaManager getInstance(Context context) {
//        if (mediaManagerInstance == null) {
//            // Khởi tạo MediaManager nếu chưa tồn tại
//            Map<String, String> config = new HashMap<>();
//            config.put("cloud_name", "dydd7mqle");
//            config.put("api_key", "446888246178164");
//            config.put("api_secret", "HcT6rhBOXzGh-pm1Agd8Q34rask");
//            MediaManager.init(context, config);
//            mediaManagerInstance = MediaManager.get();
//        }
//        return mediaManagerInstance;
//    }
//}



    MyDatabasehelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        configCloudinary();
        // Initialize the MediaManagerSingleton
//        MediaManagerSingleton mediaManagerSingleton = MediaManagerSingleton.getInstance(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        name_input = findViewById(R.id.name_input);
        dob_input = findViewById(R.id.dob_input);
        email_input = findViewById(R.id.email_input);
        add_button= findViewById(R.id.add_button);
        selectImageButton = findViewById(R.id.image_button);
        selectedImageView = findViewById(R.id.selected_image_view);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở cửa sổ chọn hình ảnh
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabasehelper myDB = new MyDatabasehelper(AddActivity.this);

                String imagePath = ""; // Declare a variable to store the image path

                if (selectImageButton.getTag() != null) {
                    // upload anh using MediaManagerSingleton


                    //ởđây nahwtj
                    MediaManager.get().upload(cloudUri)
                            .callback(new UploadCallback()  {
                                @Override
                                public void onStart(String requestId) {
                                    // Xử lý khi tải lên bắt đầu
                                }

                                @Override
                                public void onProgress(String requestId, long bytes, long totalBytes) {
                                    // Xử lý tiến trình tải lên (nếu cần)
                                }

                                @Override
                                public void onSuccess(String requestId, Map resultData) {
                                    // Xử lý khi tải lên thành công, bạn có thể lấy URL của hình ảnh từ resultData
                                    myDB.addStudent(name_input.getText().toString().trim(),
                                            dob_input.getText().toString().trim(),
                                            email_input.getText().toString().trim(), (String) resultData.get("url"));
                                }

                                @Override
                                public void onError(String requestId, ErrorInfo error) {
                                    // Xử lý khi xảy ra lỗi trong quá trình tải lên
                                }

                                @Override
                                public void onReschedule(String requestId, ErrorInfo error) {
                                    // Xử lý khi yêu cầu tải lên được xếp hàng lại (nếu cần)
                                }
                            })
                            .dispatch();
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            cloudUri = selectedImageUri;
            if (selectedImageUri != null) {
                // Load and display the image using Picasso
                Picasso.get().load(selectedImageUri).into(selectedImageView);

                // Save the image path to the ImageButton's tag
                selectImageButton.setTag(selectedImageUri.toString());
            }
        }
    }
}