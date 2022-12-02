package calculatrice.ma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.SwitchCompat;

import org.mariuszgromada.math.mxparser.*;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private SwitchCompat switchc;
    private EditText description ;
    private TextView display;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(R.string.app_name);

        this.description = findViewById(R.id.description);
        this.description.setShowSoftInputOnFocus(false);
        this.description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(description.getText().toString().equals(getString(R.string.display))){
                    description.setText("");
                }
            }
        });

        this.display = findViewById(R.id.result);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menucalcul,menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder m = (MenuBuilder) menu;
            m.setOptionalIconsVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch(item.getItemId()){
            case R.id.Item_scientifique:
                startActivity(new Intent(this,MainActivity2.class));
                finish();
                break;
            case R.id.Item_standard:
                break;
            case R.id.Item_English:

                setLocale("en");
                break;
            case R.id.Item_Frensh:

                setLocale("br");
                break;
            case R.id.Item_German:

                setLocale("de");
                break;
        }

        return true;
    }

    private void setLocale(String lang) {
        Locale loc = new Locale(lang.toLowerCase());
        Resources res = getResources();
        DisplayMetrics matrics = res.getDisplayMetrics();
        Configuration config = res.getConfiguration();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR1){
            config.setLocale(loc);
        }else{
            config.locale =loc;
        }
        res.updateConfiguration(config,matrics);
        onConfigurationChanged(config);
        recreate();

    }

    public void updateTEXT(String valTOADD){
        String oldVAL = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        if(oldVAL.equals(getString(R.string.display))){
            description.setText(valTOADD);
            this.description.setSelection(cursorPosision + 1);
        }else {

            String leftVal = oldVAL.substring(0, cursorPosision);
            String rightVal = oldVAL.substring(cursorPosision);
            this.description.setText(String.format("%s%s%s", leftVal, valTOADD, rightVal));
            this.description.setSelection(cursorPosision + 1);
        }
    }

    public void zeroBTN(View view){
        this.updateTEXT("0");
        getResult();
    }
    public void oneBTN(View view){
        this.updateTEXT("1");
        getResult();
    }
    public void twoBTN(View view){
        this.updateTEXT("2");
        getResult();
    }
    public void threeBTN(View view){
        this.updateTEXT("3");
        getResult();
    }
    public void fourBTN(View view){
        this.updateTEXT("4");
        getResult();
    }
    public void fiveBTN(View view){
        this.updateTEXT("5");
        getResult();
    }
    public void sixBTN(View view){
        this.updateTEXT("6");
        getResult();
    }
    public void sevenBTN(View view){
        this.updateTEXT("7");
        getResult();
    }
    public void eightBTN(View view){
        this.updateTEXT("8");
        getResult();
    }
    public void nineBTN(View view){
        this.updateTEXT("9");
        getResult();
    }
    public void pointBTN(View view){
        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        if(c!='×'&& c!='÷' && c!='+' && c!='-' && c!='.'  && c!='^' && c!=')'  && c!='%' && c!='(' ) {
            this.updateTEXT(".");
        }
    }
    public void addBTN(View view){
        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        if(c!='×'&& c!='÷' && c!='+' && c!='-' && c!='.'  && c!='^' && c!='(') {
            this.updateTEXT("+");
        }

    }
    public void subBTN(View view){

        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);

        if( c!='×'&& c!='÷' && c!='+' && c!='-' && c!='.'  && c!='^' && c!='(' ) {
                this.updateTEXT("-");
            }
    }
    public void multBTN(View view){
        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        if(c!='×'&& c!='÷' && c!='+' && c!='-' && c!='.' && c!='^' && c!='(') {
            this.updateTEXT("×");
        }
    }
    public void divBTN(View view){
        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        if(c!='×'&& c!='÷' && c!='+' && c!='-' && c!='.' && c!='^' && c!='(') {
            this.updateTEXT("÷");
        }
    }
    public void ereaseBTN(View view){
        this.description.setText("");
        this.display.setText("");

    }
    public void signeBTN(View view){
        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        char avantc = valeur.charAt(cursorPosision - 2);
        char avant_avantc = valeur.charAt(cursorPosision - 3);
            if (c == '(' && avantc== '-' ) {

                SpannableStringBuilder selection = (SpannableStringBuilder) description.getText();
                selection.replace(cursorPosision-1,cursorPosision,"");

                this.description.setSelection(cursorPosision - 2);

                this.updateTEXT("+");
                SpannableStringBuilder ss = (SpannableStringBuilder) description.getText();
                ss.replace(cursorPosision-1,cursorPosision,"");
        }else if(c=='+') {

            SpannableStringBuilder selection = (SpannableStringBuilder) description.getText();

            selection.replace(cursorPosision-1 ,cursorPosision,"");

            description.setText(selection);

            this.description.setSelection(cursorPosision - 1);
            this.updateTEXT("-(");
                this.description.setSelection(cursorPosision + 1);
            }
        else{
            this.updateTEXT("-(");
            this.description.setSelection(cursorPosision+2);
        }
    }
    public void equalBTN(View view){
        String actualResult = display.getText().toString();
        description.setText(actualResult);
        this.description.setSelection(actualResult.length());
    }
    public void getResult(){
        String expression =  description.getText().toString();
        expression=expression.replaceAll("÷","/");
        expression=expression.replaceAll("×","*");
        expression=expression.replaceAll("√\\(","sqrt(");

        Expression exp = new Expression(expression);
        String result = String.valueOf(exp.calculate());
        if(!result.equals("NaN")){
            display.setText(result);
        }
    }

    public void powerBTN(View view) {
        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        if (c != '×' && c != '÷' && c != '+' && c != '-' && c != '.' && c != '^' && c!='%' && c!='(')
        {    this.updateTEXT("^");
            }
        getResult();
    }


    public void racineBTN(View view){
        this.updateTEXT("√(");
        int cursorPosision = this.description.getSelectionStart();
        this.description.setSelection(cursorPosision + 1);

    }
    public void moduloBTN(View view){
        String valeur = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();
        char c = valeur.charAt(cursorPosision-1);
        if(c!='×'&& c!='÷' && c!='+' && c!='-' && c!='.' && c!='%' && c!='^' && c!='(') {

            this.updateTEXT("%");
        }
        getResult();
    }
    public void clearBTN(View view){

        String oldVAL = this.description.getText().toString();
        int cursorPosision = this.description.getSelectionStart();

        if(oldVAL.length()!=0 && cursorPosision!=0){

            SpannableStringBuilder selection = (SpannableStringBuilder) description.getText();
            selection.replace(cursorPosision-1,cursorPosision,"");
            description.setText(selection);
            this.description.setSelection(cursorPosision - 1);
            getResult();
        }

    }
    public void paranBTN(View view){
        int cursorPosision = this.description.getSelectionStart();
        String VAL = this.description.getText().toString();
        int leftpar = 0;
        int rightpar = 0;
        int ln = description.length();
        for (int i=0;i<ln;i++){
            if(VAL.charAt(i) == '('){
                leftpar++;
            }
            if(VAL.charAt(i) == ')'){
                rightpar++;
            }
        }

        char c = VAL.charAt(cursorPosision-1 );
        if( leftpar == rightpar || c == '('){
            this.updateTEXT("(");
        }else{
            this.updateTEXT(")");
            getResult();
        }

    }

}