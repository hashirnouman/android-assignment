package pk.edu.uiit.aridNo2474.MAD_assignment2_q2.hashirApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup extends AppCompatActivity {

    EditText nameET, phoneET, emailET, passwordET, confirmPasswordET, countryET;
    Button signupBtn, loginBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initialize();
        handleEvents();
    }

    private void handleEvents() {

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signup.this, pk.edu.uiit.aridNo2474.MAD_assignment2_q2.hashirApp.Login.class);
                startActivity(i);
            }
        });
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameET.getText().toString();
                String phone = phoneET.getText().toString();
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String confirmPassword = confirmPasswordET.getText().toString();
                String country = countryET.getText().toString();

                //validate
                if(email.matches("") || password.matches("") || confirmPassword.matches("")) {
                    Toast.makeText(Signup.this, "Enter valid email and password", Toast.LENGTH_LONG).show();
                    if(!password.matches(confirmPassword)) {
                        Toast.makeText(Signup.this, "Passwords do not match.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    long result = db.insertStudentRecord(name, email, phone, password, country);
                    if(result == -1) {
                        Toast.makeText(Signup.this, "Something went wrong.", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Signup.this, "You have been registered. Thank You.", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(Signup.this, pk.edu.uiit.aridNo2474.MAD_assignment2_q2.hashirApp.Card.class);
                        i.putExtra("id", String.valueOf(result));
                        startActivity(i);
                    }
                }
            }
        });
    }

    private void initialize() {
        nameET = (EditText) findViewById(R.id.nameEditText);
        phoneET = (EditText) findViewById(R.id.phoneEditText);
        emailET = (EditText) findViewById(R.id.emailEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);
        confirmPasswordET = (EditText) findViewById(R.id.confirmPasswordEditText);
        countryET = (EditText) findViewById(R.id.countryEditText);

        signupBtn = (Button) findViewById(R.id.signupBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        db = new DBHelper(Signup.this);
    }
}