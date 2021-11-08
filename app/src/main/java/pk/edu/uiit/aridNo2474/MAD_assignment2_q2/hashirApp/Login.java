package pk.edu.uiit.aridNo2474.MAD_assignment2_q2.hashirApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pk.edu.uiit.aridNo2474.MAD_assignment2_q2.hashirApp.DBHelper;
import pk.edu.uiit.aridNo2474.MAD_assignment2_q2.hashirApp.R;
import pk.edu.uiit.aridNo2474.MAD_assignment2_q2.hashirApp.Signup;

public class Login extends AppCompatActivity {

    EditText emailET, passwordET;
    Button signupBtn, loginBtn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();
        handleEvents();
    }

    private void handleEvents() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                //validate
                if(email.matches("") || password.matches("")) {
                    Toast.makeText(Login.this, "Enter valid email and password", Toast.LENGTH_LONG).show();
                    return;
                }

                // check db user details
                long id = db.checkLoggedUser(email, password);
                if (id == -1)
                    Toast.makeText(Login.this, "No Student Found with this email and password", Toast.LENGTH_LONG).show();
                else {
                    Intent i = new Intent(Login.this, pk.edu.uiit.aridNo2474.MAD_assignment2_q2.hashirApp.Card.class);
                    i.putExtra("id", String.valueOf(id));
                    startActivity(i);
                }

            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Signup.class);
                startActivity(i);
            }
        });

    }

    private void initialize() {

        emailET = (EditText) findViewById(R.id.emailEditText);
        passwordET = (EditText) findViewById(R.id.passwordEditText);

        signupBtn = (Button) findViewById(R.id.signupBtn);
        loginBtn = (Button) findViewById(R.id.loginBtn);

        db = new DBHelper(Login.this);
    }
}