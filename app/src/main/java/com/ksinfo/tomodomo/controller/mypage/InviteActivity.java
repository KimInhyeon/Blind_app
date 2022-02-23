package com.ksinfo.tomodomo.controller.mypage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ksinfo.tomodomo.databinding.MpIvMainBinding;

import androidx.appcompat.app.AppCompatActivity;

import com.ksinfo.tomodomo.R;
import com.ksinfo.tomodomo.controller.common.MainActivity;
import com.ksinfo.tomodomo.databinding.CmMainBinding;
import com.ksinfo.tomodomo.model.itf.AnnualDataInterface;
import com.ksinfo.tomodomo.util.RetrofitFactory;

public class InviteActivity extends AppCompatActivity {

    // (09:37) 메모 따라하기 ShareDataCoding_1
    //viewbinding
    private MpIvMainBinding binding;

    // (10:50) 메모 따라하기 ShareDataCoding_4
    // picked image uri will ber ssaved in it
    private Uri imageUri = null;
    // input text from edit text layout will be saved in it
    private String textToShare="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // (10:00) 메모 따라하기 ShareDataCoding_2
        // q바인딩으로 해당 xml레이아웃을 선택했기에 setContentView(R.layout.mp_iv_main);가 필요없는 듯 하다.
        //setContentView(R.layout.mp_iv_main);
        binding = MpIvMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // (11:36~) 메모 따라하기 ShareDataCoding_5
        // 주석처리. 이미지를 클릭한 경우 추가를 하기위한 메소드이기에 당장 필요않음.
        /*
            //imageIv는 영상강의자분의 xml화면에 있는 이미지뷰 이름임.
            binding.imageIv.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    pickImage();
                }
            });
        */

        // (16:27) 메모 따라하기 ShareDataCoding_12
        // handle click, share text
        // 메모 주석처리된 부분은 영상 오리지널코드.
        /*
            binding.shareTextBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    //(17:40) 메모 따라하기 ShareDataCoding_15
                    //get text
                    textToShare = binding.textEt.getText().toString().trim();

                    //check if text is empty or not
                    if(TextUtils.isEmpty(textToShare)){
                        showToast("Enter text...");
                    }
                    else{
                        // (31:13) 메모 따라하기 ShareDataCoding_23
                        shareText();
                    }
                }
            });
        */

        binding.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //(17:40) 메모 따라하기 ShareDataCoding_15[참고]
                //get text
                textToShare = binding.inviteCode.getText().toString().trim();
                System.out.println("text of textToShare : " + textToShare); //메모 개인확인 목적으로 추가코드.

                //check if text is empty or not
                if (TextUtils.isEmpty(textToShare)) {
                    showToast("inviteCode empty! check please!");
                }
                else{
                    // (31:13) 메모 따라하기 ShareDataCoding_23[참고]
                    shareText();
                }
            }
        });

        // (16:53) 메모 따라하기 ShareDataCoding_13
        // handle click, share image
        /*
            binding.shareImageBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    // (19:35) 메모 따라하기 ShareDataCoding_17
                    //check if image is picked or not
                    if (imageUri == null){
                        showToast("Pick image...);
                    }
                    else{
                        // (31:18) 메모 따라하기 ShareDataCoding_24
                        shareImage();
                    }
                }
            });
        */

        // (17:10) 메모 따라하기 ShareDataCoding_14
        // handle click, share image and text
        /*
            binding.shareBothBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    //(18:50) 메모 따라하기 ShareDataCoding_16
                    //get text
                    textToShare = binding.textEt.getText().toString().trim();

                    //check if text is empty or not, and images is picked or not
                    if(TextUtils.isEmpty(textToShare)){
                        showToast("Enter text...");
                    }
                    else if(imageUri == null){
                        showToast("Pick image...");
                    }
                    else{
                        // (31:22) 메모 따라하기 ShareDataCoding_25
                        shareBoth();
                    }
                }
            }
        */


        // (12:04) 메모 따라하기 ShareDataCoding_6
        // intent to pick image from gallery
        /*
            private void pickImage(){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                // (16:15) 메모 따라하기 ShareDataCoding_11
                galleryActivityResultLancher.launch(intent);
            }
        */

        // (13:00) 메모 따라하기 ShareDataCoding_7
        /*
            private ActivityResultLancher<Intent> galleryActivityResultLancher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallBack<ActivityResult>(){
                    @Override
                    public void onActivityResult(ActiviyResult result){
                        //handle the result in both cases; either image is picked or not
                        if(result.getResultCode() == Activity.RESULT_OK){
                            // (15:??) 메모 따라하기 ShareDataCoding_10
                            //image picked
                            showToast("Image Picked from gallery");

                            //get image uri
                            Intent data = data.getData();

                            imageUri = data.getData();

                            //set image to imageView
                            binding.imageIv.setImageURI(imageUri);
                        }
                        else{
                            // (15:??) 메모 따라하기 ShareDataCoding_9
                            //cancelled
                            showToast("Canceled...");
                        }
        */
    }

    // (14:45) 메모 따라하기 ShareDataCoding_8
    /*
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    */
    // (14:45) 메모 따라하기 ShareDataCoding_8[참고]
    private void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // (20:00) 메모 따라하기 ShareDataCoding_18
    /*
    private void shareText(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here"); //sharing with email apps
        shareIntent.putExtra(Intent.EXTRA_TEXT, textToshare);
        startActivity(Intent.createChooser(shareIntent, "Share Via"));
    }
    */
    // (20:00) 메모 따라하기 ShareDataCoding_18[참고]
    private void shareText(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here"); //for sharing with email apps
        shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
        startActivity(Intent.createChooser(shareIntent, "Share Via"));
    }

    // (21:55) 메모 따라하기 ShareDataCoding_19
    // (30:20) 메모 따라하기 ShareDataCoding_22( 21:55의 작성에서 오류수정 및 누락코드 추가)
    /*
    private void shareImage(){
        Uri contentUri = getContentUri();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/png");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here"); //for sharing with email apps
        shareIntent.putExtra(Intent.EXTRA_STREAM, contentUri );
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share Via"));
    }
    */

    // (30:12) 메모 따라하기 ShareDataCoding_21
    /*
    private void shareBoth(){
        Uri contentUri = getContentUri();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/png");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here"); //for sharing with email apps
        shareIntent.putExtra(Intent.EXTRA_TEXT, textToShare);
        shareIntent.putExtra(Intent.EXTRA_STREAM, getContentUri() );
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivity(Intent.createChooser(shareIntent, "Share Via"));
    }
    */


    // (23:30) 메모 따라하기 ShareDataCoding_20
    /*
    private Uri getContentUri(){
        Bitmap bitmap = null;
        //get bitmap from uri
        try{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                ImageDecoder.Source source = ImageDecoder.createSource(getContentResolver(), imageUri);
                bitmap = ImageDecoder.decodeBitmap(source);
            }
            else{
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            }
        }

        catch(Exception e){
            showToast(""+e.getMessage());
        }

        //if you want to get bitmap from imageview instead of uri then
        //BitmapDrawable bitmapDrawable = (BitmapDrawable) binding.imageIv.getDrawable();
        //bitmap = bitmapDrawable.getBitmap();

        File imagesFolder = new File(getCacherDir(), "images"); // 메모 files_path.xml의 <cache-path의 path값("images/") 따라 작성.
        Uri contentUri = null;
        try{
            imagesFolder.mkdirs(); //create if not exists
            File file = new File(imagesFolder, "shared_image.png");
            FileOutputStream stream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
            stream.flush();
            strean.close();
            contentUri = FileProvider.getUriForFile(this,"com.ksinfo.tomodomo.fileprovider",file); //메모 2번째는 Manifest에 등록한 <provider android:authorities에 기입한 값을 복붙하여 넣는다.
        }
        catch (Exception e){
            showToast(""+e.getMessage() );
        }

        return contentUri;
    }
    */

    /*
    // 메모 영상기반으로 제작한 share기능
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if(item.getItemId() == R.id.share_menu){
            //메모 텍스트 콘텐츠 보내기
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");

            Intent shareIntent = Intent.createChooser(sendIntent, null);
            startActivity(shareIntent);
        }
        else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    //ImageButton btnInviteToShate = (ImageButton) findViewById(R.id.share_menu_invite);

    Button btnInvite = (Button) findViewById(R.id.shareBtn);
    */

    // 메모 사용화면의 이미지버튼에 share기능삽입.
    // 동영상의 share버튼 기능은 menu 레이아웃을 별개로 만들기에 그대로 활용은 어렵다 판단하여 재구성 실시.
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.id.share_menu_invite, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //메모 Android 인텐트 리졸버 사용
        if(item.getItemId() == R.id.share_menu){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }
    */

    /*
    public void sendSMS(View v){
        String smsNum = smsNumber.getText().toString();
        String smsText = smsTextContext.getText().toString();

        if (smsNum.length()>0 && smsText.length()>0){
            sendSMS(smsNum, smsText);
        }else{
            Toast.makeText(this, "全部入力してください。", Toast.LENGTH_SHORT).show();
        }
    }
    */
}

